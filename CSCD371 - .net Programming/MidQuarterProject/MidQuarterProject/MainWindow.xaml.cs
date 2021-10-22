using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Threading;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Data.SQLite;

namespace MidQuarterProject
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private List<FileEvents> fileEvents = new List<FileEvents>();
        private string pathToWatch = "";
        private FileSystemWatcher fsw = null;
        sql sqlClient = sql.getSql();
        private Boolean saveSql = false;
        private DBWindow dbWindow;
        private aboutWindow aboutWindow;
        private helpWindow helpWindow;

        public MainWindow()
        {
            InitializeComponent();
        }



        private void queryDatabaseClick(object sender, RoutedEventArgs e)
        {
            this.dbWindow = new DBWindow();
            this.dbWindow.ShowDialog();
        }

        private void clickStop(object sender, RoutedEventArgs e) {
            if (fsw == null || fsw.EnableRaisingEvents == false) {
            }
            else {
                //ViewGrid.Items.Add()
                //mainListBox.Items.Add("== WATCH OF " + pathToWatch + " ENDED AT " + getTime() + " ==");
                fsw.EnableRaisingEvents = false;
                stopButtons.IsEnabled = false;
                startButtons.IsEnabled = true;
                Stop.IsEnabled = false;
                Start.IsEnabled = true;
            }
        }

        private void clickExit(object sender, RoutedEventArgs e)
        {
            clickStop(sender,e);
            checkChanges(fileEvents);
            Environment.Exit(0);
        }

        private void checkChanges(List<FileEvents> fileEvents) {
            if (fileEvents.Count == 0)
            {
                return;
            }
            else {
                if (MessageBox.Show("You have unsaved changes. Would you like to save it to your SQL database?", "Unsaved logs!", MessageBoxButton.YesNo, MessageBoxImage.Warning)==MessageBoxResult.Yes) {
                    saveSql = true;
                    foreach(FileEvents events in fileEvents){
                        sqlClient.sqlInit();
                        sqlClient.sqlAdd(events);
                    }
                    sqlClient.sqlClose();
                }
            }
        }

        private void clickStart(object sender, RoutedEventArgs e) {
                pathToWatch = pathInput.Text;
                if (checkUserInput(pathToWatch)) {
                    fswInit(pathToWatch, extensionInput.Text);
                    //mainListBox.Items.Add("== WATCH OF " + pathToWatch + " STARTED AT " + getTime() + " ==");
                    fsw.EnableRaisingEvents = true;
                    startButtons.IsEnabled = false;
                    stopButtons.IsEnabled = true;
                Start.IsEnabled = false;
                Stop.IsEnabled = true;
                }
        }


        private void aboutClick(object sender, RoutedEventArgs e) {
            this.aboutWindow= new aboutWindow();
            this.aboutWindow.ShowDialog();
        }

        private void fswInit(string directoryToWatch, string filterType) {

            //still need to check userinput for correct path. check old assignment. 
            if (fsw == null) {
                 fsw = new FileSystemWatcher();
            }
                fsw.Filter = filterType.Length > 1 ? "*."+filterType : null;
                fsw.Path = directoryToWatch;
                fsw.NotifyFilter = NotifyFilters.DirectoryName | NotifyFilters.LastAccess | NotifyFilters.LastWrite | NotifyFilters.FileName;
                fsw.Changed += Fsw_Changed;
                fsw.Created += Fsw_Changed;
                fsw.Deleted += Fsw_Changed;
                fsw.Renamed += Fsw_Renamed;
        }

        private Boolean checkUserInput(string check)
        {
            if (!Directory.Exists(check))
            {
                MessageBox.Show("Sorry! Directory does not exist","Error!");
                return false;
            }
            else {
                return true;
            }
        }

        private void Fsw_Renamed(object sender, RenamedEventArgs e)
        {
            Dispatcher.BeginInvoke(
                (Action)(() =>
                {
                    
                    string temp = e.OldName + " : " + e.ChangeType + " : " + e.Name + " : " + e.FullPath + " : " + getTime();
                    //mainListBox.Items.Add(temp);
                    Console.WriteLine(temp);
                    string ext = System.IO.Path.GetExtension(e.FullPath);
                    var newobj = new FileEvents(getTime(), e.ChangeType, e.Name, e.FullPath, e.OldName,ext);
                    ViewGrid.Items.Insert(0,newobj);
                    if (saveSql)
                    {
                        sqlClient.sqlAdd(newobj);
                    }
                    else {
                        fileEvents.Add(newobj);
                    }
                }));

        }

        private void Fsw_Changed(object sender, FileSystemEventArgs e)
        {
            Dispatcher.BeginInvoke(
               (Action)(() =>
               {
                   string temp = e.Name + " : " + e.ChangeType + " : " + e.FullPath + " : " + getTime();
                   //mainListBox.Items.Add(temp);
                   Console.WriteLine(temp);
                   string ext = System.IO.Path.GetExtension(e.FullPath);
                   var newobj = new FileEvents(getTime(), e.ChangeType, null, e.FullPath, e.Name,ext);
                   ViewGrid.Items.Insert(0,newobj);
                   if (saveSql)
                   {
                       sqlClient.sqlAdd(newobj);
                   }
                   else {
                       
                       fileEvents.Add(newobj);
                   }
               }));
        }

        private static DateTime getTime()
        {
            DateTime now = DateTime.Now;
            return now;
        }

        private void sqlEnable(object sender, RoutedEventArgs e) {
            disableSQL.IsEnabled = true;
            enableSQL.IsEnabled = false;
            saveSql = true;
            sqlClient.sqlInit();
        }

        private void sqlDisable(object sender, RoutedEventArgs e) {
            disableSQL.IsEnabled = false;
            enableSQL.IsEnabled = true;
            saveSql = false;
            sqlClient.sqlClose();
        }

        private void pdfOption(object sender, MouseButtonEventArgs e)
        {
            extensionInput.Text = "pdf";
        }

        private void txtOption(object sender, MouseButtonEventArgs e)
        {
            extensionInput.Text = "txt";
        }

        private void blankOption(object sender, MouseButtonEventArgs e)
        {
            extensionInput.Text = null;
        }

        private void SqlToolButton_Click(object sender, RoutedEventArgs e)
        {
            if (fileEvents.Count == 0)
            {
            }
            else {
                saveSql = true;
                foreach (FileEvents events in fileEvents)
                {
                    sqlClient.sqlInit();
                    sqlClient.sqlAdd(events);
                }
                MessageBox.Show("Saved results into SQL database!");
                fileEvents.Clear();
                sqlClient.sqlClose();
            }
        }

        private void Helpbutton_Click(object sender, RoutedEventArgs e)
        {
            this.helpWindow = new helpWindow();
            this.helpWindow.ShowDialog();
        }
    }
}
