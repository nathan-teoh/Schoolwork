using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using System.Data.SQLite;
using System.IO;
using System.Data;

namespace MidQuarterProject
{
    
    class sql
    {
        private static sql thisThing = null;
        public SQLiteConnection sqlite_conn { get; set; }
        public SQLiteCommand sqlite_cmd { get; set; }
        public SQLiteDataReader sqlite_datareader { get; set; }

        private sql() {
        }

        public static sql getSql() {
            if (thisThing == null) {
                thisThing = new sql();
            }
            return thisThing;
        }

        public void sqlInit()
        {
            if (File.Exists("MidtermAssignment.db"))
            {
                sqlite_conn = new SQLiteConnection("Data Source=MidtermAssignment.db;Version=3;Compress=True;");
                sqlite_conn.Open();
            }
            else {
                sqlite_conn = new SQLiteConnection("Data Source=MidtermAssignment.db;New=True;Version=3;Compress=True;");
                sqlite_conn.Open();
                sqlite_cmd = sqlite_conn.CreateCommand();
                sqlite_cmd.CommandText = "CREATE TABLE log(time varchar(100), type varchar(20), name varchar(100), fullpath varchar(200), newname varchar(100),ext varchar (50));";
                sqlite_cmd.ExecuteNonQuery();
            }
            

            
        }

        public void sqlAdd(FileEvents eIn) {
            sqlite_cmd = sqlite_conn.CreateCommand();
            sqlite_cmd.CommandText = "INSERT INTO log (time, type, name, fullpath, newname,ext) VALUES (?,?,?,?,?,?);";
            sqlite_cmd.Parameters.Add("@time", DbType.String).Value = eIn.changeTime;
            sqlite_cmd.Parameters.Add("@type", DbType.String).Value = eIn.ChangeType;
            sqlite_cmd.Parameters.Add("@name", DbType.String).Value = eIn.OldName;
            sqlite_cmd.Parameters.Add("@fullpath", DbType.String).Value = eIn.FullPath;
            sqlite_cmd.Parameters.Add("@newname", DbType.String).Value = eIn.Name;
            sqlite_cmd.Parameters.Add("@ext", DbType.String).Value = eIn.ext;

            sqlite_cmd.ExecuteNonQuery();
        }



        public void sqlClose() {
            sqlite_conn.Close();
        }

        internal void sqlClear()
        {
            sqlInit();

            sqlite_cmd = sqlite_conn.CreateCommand();
            string clear = "DELETE FROM log;";
            sqlite_cmd.CommandText = clear;
            sqlite_cmd.ExecuteNonQuery();
        }
    }
}
