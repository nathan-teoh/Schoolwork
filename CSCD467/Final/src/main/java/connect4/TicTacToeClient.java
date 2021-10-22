package connect4;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.comprehend.model.Entity;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class TicTacToeClient extends JFrame implements Runnable 
{
   private JTextField idField; // textfield to display player's mark
   private JTextArea displayArea; // JTextArea to display output
   private JPanel boardPanel; // panel for tic-tac-toe board
   private JPanel panel2; // panel to hold board
   private Square board[][]; // tic-tac-toe board
   private Square currentSquare; // current square
   private String myMark; // this client's mark
   private boolean myTurn; // determines which client's turn it is
   private final String X_MARK = "X"; // mark for first client
   private final String O_MARK = "O"; // mark for second client
   private final int bsize = 16;
   private String myQueueUrl = "";
   private final AmazonSQS sqs = AmazonSQSClient.builder()
		   							.withRegion("us-west-1")
		   							.build();

   // set up user-interface and board
   public TicTacToeClient( String host )
   { 
	 
	   					
      displayArea = new JTextArea( 4, 30 ); // set up JTextArea
      displayArea.setEditable( false );
      add( new JScrollPane( displayArea ), BorderLayout.SOUTH );

      boardPanel = new JPanel(); // set up panel for squares in board
      boardPanel.setLayout( new GridLayout( bsize, bsize, 0, 0 ) ); //was 3

      board = new Square[ bsize ][ bsize ]; // create board

      // loop over the rows in the board
      for ( int row = 0; row < board.length; row++ ) 
      {
         // loop over the columns in the board
         for ( int column = 0; column < board[ row ].length; column++ ) 
         {
            // create square. initially the symbol on each square is a white space.
            board[ row ][ column ] = new Square( " ", row * bsize + column );
            boardPanel.add( board[ row ][ column ] ); // add square       
         } // end inner for
      } // end outer for

      idField = new JTextField(); // set up textfield
      idField.setEditable( false );
      add( idField, BorderLayout.NORTH );
      
      panel2 = new JPanel(); // set up panel to contain boardPanel
      panel2.add( boardPanel, BorderLayout.CENTER ); // add board panel
      add( panel2, BorderLayout.CENTER ); // add container panel

      setSize( 600, 600 ); // set size of window
      setVisible( true ); // show window
      
      createQueue();

      startClient();
   } // end TicTacToeClient constructor

   public void createQueue() {	//CREATE SQS FIFO QUEUE
	   try {
		   System.out.println("Creating queue now");
		   final Map<String, String> attributes = new HashMap<>();
		   /**Not sure if FIFO needed. Technically if each user is paused after sending exactly one request, FIFO shouldn't be needed
		   attributes.put("FifoQueue", "true");
		   attributes.put("ContentBasedDeduplication", "True");
		   final CreateQueueRequest cqr = new CreateQueueRequest("Connect_4.fifo").withAttributes(attributes);
		   
		   Only issue is that I run into that reflection error again. have to dig deeper into this. 
		    **/
		   final CreateQueueRequest cqr = new CreateQueueRequest("Connect_4");	//.withAttributes(attributes);
           this.myQueueUrl = sqs.createQueue(cqr).getQueueUrl();  
           System.out.println("Success! Listing all queues in your account.\n");
           for (final String queueUrl : sqs.listQueues().getQueueUrls()) {
               System.out.println("  QueueUrl: " + queueUrl);
           }
	   }catch(Exception e) {
		   System.out.println("Error! Couldn't create new queue. Check following error for details." );
		   System.out.println(e);
	   }
   }
   
   public String popQueue() {


	   System.out.println("Receiving messages from MyQueue.\n");
       final ReceiveMessageRequest receiveMessageRequest =
               new ReceiveMessageRequest(myQueueUrl);
       final java.util.List<Message> messages = sqs.receiveMessage(receiveMessageRequest)
               .getMessages();
	   /**
	    * 
	    * 
	  	Return first message body.
       for (final Message message : messages) {
           System.out.println("Message");
           System.out.println("  MessageId:     "
                   + message.getMessageId());
           System.out.println("  ReceiptHandle: "
                   + message.getReceiptHandle());
           System.out.println("  MD5OfBody:     "
                   + message.getMD5OfBody());
           System.out.println("  Body:          "
                   + message.getBody());
           for (final Entry<String, String> entry : message.getAttributes()
                   .entrySet()) {
               System.out.println("Attribute");
               System.out.println("  Name:  " + entry
                       .getKey());
               System.out.println("  Value: " + entry
                       .getValue());
           }
       }
       System.out.println();
       **/
       return messages.get(0).getBody();
   }
   // start the client thread
   public void startClient()
   {  
      // create and start worker thread for this client
      ExecutorService worker = Executors.newFixedThreadPool( 1 );
      worker.execute( this ); // execute client
   } // end method startClient

   // control thread that allows continuous update of displayArea
   public void run()
   {
      myMark =  "X"; //Get player's mark (X or O). We hard coded here in demo. In your implementation, you may get this mark dynamically 
                     //from the cloud service. This is the initial state of the game.
      sqs.sendMessage(myQueueUrl, "First user connected. 'X' as user1.");

      SwingUtilities.invokeLater( 
         new Runnable() 
         {         
            public void run()
            {
               // display player's mark
               idField.setText( "You are player \"" + myMark + "\"" );
            } // end method run
         } // end anonymous inner class
      ); // end call to SwingUtilities.invokeLater
         
      myTurn = ( myMark.equals( X_MARK ) ); // determine if client's turn
      System.out.println("MY TURN IS "+myTurn);

      // program the game logic below
      while ( ! isGameOver() )
      {
    	      // Here in this while body, you will program the game logic. 
    	      // You are free to add any helper methods in this class or other classes.
    	      // Basically, this client player will retrieve a message from cloud in each while iteration
    	      // and process it until game over is detected.
    	      // Please check the processMessage() method below to gain some clues.
          
      } // end while
      
      
   } // end method run
   
   // You have write this method that checks the game board to detect winning status.
   private boolean isGameOver() {
	   return false;
   }

   // This method is not used currently, but it may give you some hints regarding
   // how one client talks to other client through cloud service(s).
   private void processMessage( String message )
   {
      // valid move occurred
      if ( message.equals( "Opponent Won" ) ) 
      {
         displayMessage( "Game over, Opponent won.\n" );
         // then highlight the winning locations down below.
         
      } // end if
      else if ( message.equals( "Opponent moved" ) ) 
      {
         int location = getOpponentMove(); // Here get move location from opponent
         								
         int row = location / bsize; // calculate row
         int column = location % bsize; // calculate column

         setMark(  board[ row ][ column ], 
            ( myMark.equals( X_MARK ) ? O_MARK : X_MARK ) ); // mark move                
         displayMessage( "Opponent moved. Your turn.\n" );
         myTurn = true; // now this client's turn
      } // end else if
      else
         displayMessage( message + "\n" ); // display the message
   } // end method processMessage

   //Here get move location from opponent
   private int getOpponentMove() {
	 final ReceiveMessageRequest rmr = new ReceiveMessageRequest(myQueueUrl);
	 final java.util.List<Message> messages= sqs.receiveMessage(new ReceiveMessageRequest(myQueueUrl)).getMessages();
	   
	   return 0;
   }
   // manipulate outputArea in event-dispatch thread
   private void displayMessage( final String messageToDisplay )
   {
      SwingUtilities.invokeLater(
         new Runnable() 
         {
            public void run() 
            {
               displayArea.append( messageToDisplay ); // updates output
            } // end method run
         }  // end inner class
      ); // end call to SwingUtilities.invokeLater
   } // end method displayMessage

   // utility method to set mark on board in event-dispatch thread
   private void setMark( final Square squareToMark, final String mark )
   {
      SwingUtilities.invokeLater(
         new Runnable() 
         {
            public void run()
            {
               squareToMark.setMark( mark ); // set mark in square
            } // end method run
         } // end anonymous inner class
      ); // end call to SwingUtilities.invokeLater
   } // end method setMark

   // Send message to cloud service indicating clicked square
   public void sendClickedSquare( int location )
   {
      // if it is my turn
      if ( myTurn ) 
      {
         // Below you send the clicked location to the cloud service that will notify the opponent,
    	     // Or the opponent will retrieve the move location itself.
         // Please write your own code below.
    	  
         myTurn = false; // not my turn anymore
      } // end if
   } // end method sendClickedSquare

   // set current Square
   public void setCurrentSquare( Square square )
   {
      currentSquare = square; // set current square to argument
   } // end method setCurrentSquare

   // private inner class for the squares on the board
   private class Square extends JPanel 
   {
      private String mark; // mark to be drawn in this square
      private int location; // location of square
   
      public Square( String squareMark, int squareLocation )
      {
         mark = squareMark; // set mark for this square
         location = squareLocation; // set location of this square

         addMouseListener( 
            new MouseAdapter() {
               public void mouseReleased( MouseEvent e )
               {
                  setCurrentSquare( Square.this ); // set current square
                  TicTacToeClient.this.setMark( currentSquare, myMark );
                  displayMessage("You clicked at location: " + getSquareLocation() + "\n");
                  sqs.sendMessage(new SendMessageRequest(myQueueUrl, Integer.toString(getSquareLocation())));	//Send every coordinate to SQS. 
                  // You may have to send location of this square to 
                  // the cloud service that will notify the opponent player.
                  //if(isValidMove()) // you have write your own method isValidMove().
                        //sendClickedSquare( getSquareLocation() );
                  
               } // end method mouseReleased
            } // end anonymous inner class
         ); // end call to addMouseListener
      } // end Square constructor

      // return preferred size of Square
      public Dimension getPreferredSize() 
      { 
         return new Dimension( 30, 30 ); // return preferred size
      } // end method getPreferredSize

      // return minimum size of Square
      public Dimension getMinimumSize() 
      {
         return getPreferredSize(); // return preferred size
      } // end method getMinimumSize

      // set mark for Square
      public void setMark( String newMark ) 
      { 
         mark = newMark; // set mark of square
         repaint(); // repaint square
      } // end method setMark
   
      // return Square location
      public int getSquareLocation() 
      {
         return location; // return location of square
      } // end method getSquareLocation
   
      // draw Square
      public void paintComponent( Graphics g )
      {
         super.paintComponent( g );

         g.drawRect( 0, 0, 29, 29 ); // draw square
         g.drawString( mark, 11, 20 ); // draw mark   
      } // end method paintComponent
   } // end inner-class Square
   
   

  public static void main( String args[] )
  {
     TicTacToeClient application; // declare client application

     // if no command line args
     if ( args.length == 0 )
        application = new TicTacToeClient( "" ); // 
     else
        application = new TicTacToeClient( args[ 0 ] ); // use args

     application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
  } // end main

} // end class TicTacToeClient


