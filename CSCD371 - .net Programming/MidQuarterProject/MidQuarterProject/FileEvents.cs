using System;
using System.Collections.Generic;
using System.Dynamic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MidQuarterProject
{
    class FileEvents
    {                
        public string changeTime { get; set; }
        public string ChangeType { get; set; }
        public string Name { get; set; }
        public string FullPath { get; set; }
        public string OldName { get; set; }
        public string ext { get; set; }


        public FileEvents(DateTime getTime, WatcherChangeTypes changeType, string name, string fullPath, string NewNameIn, string extIn)
        {
            this.changeTime = getTime.ToString();
            this.ChangeType = changeType.ToString();
            this.Name = name;
            this.FullPath = fullPath;
            this.OldName = NewNameIn;
            this.ext = extIn;
        }

        public FileEvents(string getTime, string changeType, string name, string fullPath, string NewNameIn, string extIn)
        {
            this.changeTime = getTime.ToString();
            this.ChangeType = changeType.ToString();
            this.Name = NewNameIn;
            this.FullPath = fullPath;
            this.OldName = name;
            this.ext = extIn;
        }
    }
}
