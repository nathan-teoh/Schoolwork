using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace MidQuarterProject
{
    /// <summary>
    /// Interaction logic for DBWindow.xaml
    /// </summary>
    public partial class DBWindow : Window
    {
        sql client = sql.getSql();
        public DBWindow()
        {
            InitializeComponent();
        }

        private void submit_Click(object sender, RoutedEventArgs e)
        {
            client.sqlInit();
            ViewGrid.Items.Clear();
            string extension = "";
            client.sqlite_cmd = client.sqlite_conn.CreateCommand();
            if (extensionInput.Text.Length > 1)
            {
                extension = "." + extensionInput.Text.Replace(".", string.Empty);

                string temp = "SELECT * FROM log WHERE ext='" + extension + "'";
                client.sqlite_cmd.CommandText = temp;
                Console.WriteLine(temp);
                client.sqlite_datareader = client.sqlite_cmd.ExecuteReader();
                while (client.sqlite_datareader.Read())
                {
                    string timeIn = (string)client.sqlite_datareader["time"];
                    string typeIn = (string)client.sqlite_datareader["type"];
                    string name = (string)client.sqlite_datareader["name"];
                    string fullpath = (string)client.sqlite_datareader["fullpath"];
                    string newname = client.sqlite_datareader["newname"].ToString().Length > 1 ? (string)client.sqlite_datareader["newname"] : null;
                    string ext = client.sqlite_datareader["ext"].ToString().Length > 1 ? (string)client.sqlite_datareader["ext"] : null;
                    ViewGrid.Items.Add( new FileEvents(timeIn, typeIn, name, fullpath, newname, ext));
                    System.Console.WriteLine(client.sqlite_datareader);
                }
            }
            else
            {
                client.sqlite_cmd.CommandText = "SELECT * FROM log";
                client.sqlite_datareader = client.sqlite_cmd.ExecuteReader();
                while (client.sqlite_datareader.Read())
                {
                    string timeIn = (string)client.sqlite_datareader["time"];
                    string typeIn = (string)client.sqlite_datareader["type"];
                    string name = (string)client.sqlite_datareader["name"];
                    string fullpath = (string)client.sqlite_datareader["fullpath"];
                    string newname = client.sqlite_datareader["newname"].ToString().Length >1 ? (string)client.sqlite_datareader["newname"] : null;
                    string ext = client.sqlite_datareader["ext"].ToString().Length > 1? (string)client.sqlite_datareader["ext"] : null;
                    ViewGrid.Items.Add(new FileEvents(timeIn,typeIn,name,fullpath,newname,ext));
                    System.Console.WriteLine(client.sqlite_datareader);
                }
            }
        }

        private void MenuItem_Click(object sender, RoutedEventArgs e)
        {
            if (MessageBox.Show("Are you sure you want to delete all logs? THERE IS NO TURNING BACK!", "ARE YOU SURE?", MessageBoxButton.YesNo, MessageBoxImage.Warning) == MessageBoxResult.Yes)
            {
                client.sqlClear();
                submit_Click(sender, e);
            }
            else {
                submit_Click(sender, e);
            }

        }
    }
}
