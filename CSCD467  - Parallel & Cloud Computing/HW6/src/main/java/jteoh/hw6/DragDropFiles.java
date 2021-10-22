package jteoh.hw6;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
//import javax.swing.event.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.*;
import java.io.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.util.Iterator;
import java.util.List;


public class DragDropFiles extends JFrame {

    private DefaultListModel model = new DefaultListModel();
    private int count = 0;
    private JTree tree;
    private JLabel label;
    private JButton download;
    private JButton add;
    private JButton delete;
    private DefaultTreeModel treeModel;
    private TreePath namesPath;
    private JPanel wrap;
    private TreePath downloadPath = null;

    private static DefaultTreeModel getDefaultTreeModel() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("All My Buckets");
        DefaultMutableTreeNode parent;
        DefaultMutableTreeNode nparent;
       
        try {
        	 final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
						.withRegion("us-east-1")//choose default region
						.withForceGlobalBucketAccessEnabled(true)//if bucket is not in the specified region, look at all
						.build();
        	 //I could use the default credentials as well, but I chose to do this as it's easier to mess with code than profiles.
        	 
			List<Bucket> bucketList = s3.listBuckets();
			
			for(Bucket b : bucketList) {	//for every bucket b in BucketList
			parent = new DefaultMutableTreeNode(b.getName());	//create a root for every bucket
			root.add(parent);	//add it to GUI
			
			ObjectListing listing = s3.listObjects(b.getName());	//get list of objects for every bucket
			List<S3ObjectSummary> summaries = listing.getObjectSummaries();	//List
			
			while(listing.isTruncated()) {
				listing = s3.listNextBatchOfObjects (listing);
				summaries.addAll (listing.getObjectSummaries());	//add all bucket objects to list
			}
			
			for(S3ObjectSummary l : summaries) {
				parent.add(new DefaultMutableTreeNode(l.getKey()));	//for every object in list, add under root. 
				}
			}
	        
        }catch(Exception e) {
        	e.printStackTrace();
        }

        return new DefaultTreeModel(root);
    }

    public DragDropFiles() {
        super("Drag and Drop File Transfers in Cloud");

        treeModel = getDefaultTreeModel();
        
        tree = new JTree(treeModel);
        tree.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        tree.setDropMode(DropMode.ON);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        namesPath = tree.getPathForRow(2);
        tree.expandRow(2);
        tree.expandRow(1);
        tree.setRowHeight(0);

        //Handles the tree node selection event that triggered by user selection
        //Identify which tree node(file name) has been selected, for downloading.
        //For more info, see TreeSelectionListener Class in Java
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                                   tree.getLastSelectedPathComponent();

                if (node == null) {
                	return;
                }
                /* React to the node selection. */
                downloadPath = e.getNewLeadSelectionPath();
            }
        });
        
        tree.setTransferHandler(new TransferHandler() {

            public boolean canImport(TransferHandler.TransferSupport info) {
                // we'll only support drops (not clip-board paste)
                if (!info.isDrop()) {
                    return false;
                }
                info.setDropAction(COPY); //Tony added
                info.setShowDropLocation(true);
                // we import Strings and files. Only allows strings and files
                if (!info.isDataFlavorSupported(DataFlavor.stringFlavor) &&
                		!info.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    return false;
                }
                
                // fetch the drop location
                JTree.DropLocation dl = (JTree.DropLocation)info.getDropLocation();
                TreePath path = dl.getPath();

                // we don't support invalid paths or descendants of the names folder
                if (path == null ) {	//|| namesPath.isDescendant(path) removed to make it allow upload to other buckets than first
                    return false;
                }
                return true;
            }

            public boolean importData(TransferHandler.TransferSupport info) {            	
            		// if we can't handle the import, say so
                if (!canImport(info)) {
                    return false;
                }
                // fetch the drop location
                JTree.DropLocation dl = (JTree.DropLocation)info.getDropLocation();
                
                // fetch the path and child index from the drop location
                TreePath path = dl.getPath();
                int childIndex = dl.getChildIndex();
                // fetch the data and bail if this fails
                String uploadName = "";
                Transferable t = info.getTransferable();            
                try {
                	final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
    						.withRegion("us-east-1")//choose default region
    						.withForceGlobalBucketAccessEnabled(true)//if bucket is not in the specified region, look at all
    						.build();
                	
                	java.util.List<File> l =
                          (java.util.List<File>)t.getTransferData(DataFlavor.javaFileListFlavor);
                	
                	for(File f : l) {
                		uploadName = f.getName();// for uploading status
                    	s3.putObject(dl.getPath().getLastPathComponent().toString(), f.getName(), "Uploaded Something?");	//for every file dropped, we upload
                    	PutObjectRequest request = new PutObjectRequest(dl.getPath().getLastPathComponent().toString(),f.getName(),f);	//every put needs a request
                    	s3.putObject(request);	//send
                    	break;
                    	}
                }catch(Exception e) {
                	return false;
                }
                
                // if child index is -1, the drop was on top of the path, so we'll
                // treat it as inserting at the end of that path's list of children
                if (childIndex == -1) {
                    childIndex = tree.getModel().getChildCount(path.getLastPathComponent());
                }

                // create a new node to represent the data and insert it into the model
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(uploadName);
                DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode)path.getLastPathComponent();
                treeModel.insertNodeInto(newNode, parentNode, childIndex);

                // make the new node visible and scroll so that it's visible
                tree.makeVisible(path.pathByAddingChild(newNode));
                tree.scrollRectToVisible(tree.getPathBounds(path.pathByAddingChild(newNode)));
				
                //Display uploading status
                label.setText("UpLoaded **" + uploadName + "** successfully!");

                return true;
            }
            
        });

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        this.wrap = new JPanel();
        this.label = new JLabel("Status Bar...");
        wrap.add(this.label);
        p.add(Box.createHorizontalStrut(4));
        p.add(Box.createGlue());
        p.add(wrap);
        p.add(Box.createGlue());
        p.add(Box.createHorizontalStrut(4));
        getContentPane().add(p, BorderLayout.NORTH);

        getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
        download = new JButton("Download");
        download.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        	    //You have to program here in this method in response to downloading a file from the cloud,
        		//Refer to TreePath class about how to extract the bucket name and file name out of 
        		//the downloadPath object.
        		final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
        				.withRegion("us-east-1")//choose default region
  						.withForceGlobalBucketAccessEnabled(true)//if bucket is not in the specified region, look at all
  						.build();
        		
        	    if(downloadPath != null && downloadPath.getPathCount() > 2 ) {	//check to make sure file selected and not just bucket
        	    		File destFile = new File("./copy-"+downloadPath.getLastPathComponent());
        	    		JOptionPane.showMessageDialog(null, "You are now downloading a file from cloud from buckets:" + 
        	    				downloadPath.toString() + ". It will be stored at: " + destFile.getAbsolutePath());
        	    		
        	    		S3Object fullObject = null;
        	    		s3.getObject(new GetObjectRequest(downloadPath.getPathComponent(1).toString(),downloadPath.getLastPathComponent().toString()),destFile);
        	    		if(destFile.exists()) {
        	    			label.setText("DownLoaded **" + destFile.getName() + "** successfully!");
        	    		}
        	    }else {
        	    	JOptionPane.showMessageDialog(null, "Sorry! Can't download a whole bucket. Only individual files. Please select the file and try again.");
        	    }
        	  } 
        } );
        
        delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String deletedName = downloadPath.getLastPathComponent().toString();
        		label.setText("Deleting **"+ downloadPath.getLastPathComponent().toString()+"** in progress!");
        		try {
        			int deleteConfirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ?");
        			if( deleteConfirmation ==0) {	//if user hits yes
        				final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                				.withRegion("us-east-1")//choose default region
          						.withForceGlobalBucketAccessEnabled(true)//if bucket is not in the specified region, look at all
          						.build();
            			
            			if(downloadPath.getPathCount()==2) {	//if bucket selected
                			//AWS requires bucket to be empty before deleting the bucket. Idea from AWS SDK Docs.
                			ObjectListing objectList = s3.listObjects(downloadPath.getLastPathComponent().toString());
                			while(true) {
                				Iterator<S3ObjectSummary> objIter = objectList.getObjectSummaries().iterator();
                				while(objIter.hasNext()) {
                					s3.deleteObject(downloadPath.getLastPathComponent().toString(), objIter.next().getKey());
                				}
                				if(objectList.isTruncated()) {
                					objectList = s3.listNextBatchOfObjects(objectList);
                				}else {
                					break;
                				}
                			}
                			System.out.println(downloadPath.getPathComponent(1).toString());
            				s3.deleteBucket(downloadPath.getPathComponent(1).toString());
                		}else if(downloadPath.getPathCount()==3){
                			s3.deleteObject(new DeleteObjectRequest(downloadPath.getPathComponent(1).toString(),downloadPath.getLastPathComponent().toString()));
                		}
            			treeModel.removeNodeFromParent((MutableTreeNode) downloadPath.getLastPathComponent());
            			//check path length to see if it's a bucket or if it's a file.
                		//also prompt user to make sure they are sure they want to delete it.
            			label.setText("Deleted **"+deletedName+"** succesfully!");
            			DefaultTreeModel dtm = getDefaultTreeModel();
            			dtm.getRoot();
            			treeModel.reload((TreeNode) dtm.getRoot());

        			}
        			
        			
        		}catch(Exception es) {
        			es.printStackTrace();
        		}
        	}
        });
        

        add = new JButton("Add Bucket");
        add.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        			String newBucketName = JOptionPane.showInputDialog("Please enter a name for the new bucket:");
        			
        			if(!newBucketName.isEmpty()) {
        				try {
            				final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                    				.withRegion("us-east-1")//choose default region
              						.withForceGlobalBucketAccessEnabled(true)//if bucket is not in the specified region, look at all
              						.build();
            				
            				if(!s3.doesBucketExistV2(newBucketName)) {
            					s3.createBucket(new CreateBucketRequest(newBucketName));
                    			label.setText("Created new bucket named **"+newBucketName+"** succesfully!");
                    			treeModel.reload();
            				}else {
            					JOptionPane.showMessageDialog(null, "Sorry! Bucket already exists");
            				}
            			}catch(Exception eee) {
            				eee.printStackTrace();
            			}
        			}	
        	}
        	//No idea how to add a new node to update the tree once you add a bucket. It adds, but it wont refresh till you open and close the app again.
        });        
        
        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        wrap = new JPanel();
        //wrap.add(new JLabel("Show drop location:"));
        wrap.add(download);
        wrap.add(delete);
        wrap.add(add);
        p.add(Box.createHorizontalStrut(4));
        p.add(Box.createGlue());
        p.add(wrap);
        p.add(Box.createGlue());
        p.add(Box.createHorizontalStrut(4));
        getContentPane().add(p, BorderLayout.SOUTH);

        getContentPane().setPreferredSize(new Dimension(400, 450));
    }

    private static void increaseFont(String type) {
        Font font = UIManager.getFont(type);
        font = font.deriveFont(font.getSize() + 4f);
        UIManager.put(type, font);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        DragDropFiles test = new DragDropFiles();
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Display the window.
        test.pack();
        test.setVisible(true);
    }
    
    
    private void copyFile(File source, File dest)
    		throws IOException {
	    	InputStream input = null;
	    	OutputStream output = null;
	    	try {
	    		input = new FileInputStream(source);
	    		output = new FileOutputStream(dest);
	    		byte[] buf = new byte[1024];
	    		int bytesRead;
	    		while ((bytesRead = input.read(buf)) > 0) {
	    			output.write(buf, 0, bytesRead);
	    		}
	    	} finally {
	    		input.close();
	    		output.close();
	    	}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {                
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    increaseFont("Tree.font");
                    increaseFont("Label.font");
                    increaseFont("ComboBox.font");
                    increaseFont("List.font");
                } catch (Exception e) {}

                //Turn off metal's use of bold fonts
	        UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}
