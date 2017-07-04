package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

You are given several logs that each log contains a unique id and timestamp. 
Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, 
for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.

Design a log storage system to implement the following functions:

void Put(int id, string timestamp): Given a log's unique id and timestamp, 
store the log in your storage system.


int[] Retrieve(String start, String end, String granularity): 
Return the id of logs whose timestamps are within the range from start to end. 
Start and end all have the same format as timestamp. However,
 granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", 
 end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs 
 within the range from Jan. 1st 2017 to Jan. 2nd 2017.

Example 1:
put(1, "2017:01:01:23:59:59");
put(2, "2017:01:01:22:59:59");
put(3, "2016:01:01:00:00:00");
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); 
// return [1,2,3], because you need to return all logs within 2016 and 2017.
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); 
// return [1,2], because you need to return all logs start from 2016:01:01:01 to 
 * 2017:01:01:23, where log 3 is left outside the range.
Note:
There will be at most 300 operations of Put or Retrieve.
Year ranges from [2000,2017]. Hour ranges from [00,23].
Output for Retrieve has no order required.

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P635_DesignLogStorageSystem.java
 * @date        2017年7月2日 上午9:38:26
 * @details     
 */
public class P635_DesignLogStorageSystem {
    static public class LogSystem {
        Map<Integer, String> map = new HashMap<>();
        public LogSystem() {

        }

        public void put(int id, String timestamp) {
            map.put(id, timestamp);
        }

        public List<Integer> retrieve(String s, String e, String gra) {
            s = process(s, gra, "0000:00:00:00:00:00");
            e = process(e, gra, "9999:12:31:23:59:59");
            List<Integer> result = new ArrayList<>();
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (s.compareTo(entry.getValue()) <= 0 && e.compareTo(entry.getValue()) >= 0) {
                    result.add(entry.getKey());
                }
            }
            return result;
        }

        private String process(String s, String gra, String remain) {
            switch (gra) {
                case "Year":
                    return s.substring(0, 4) + remain.substring(4);
                case "Month":
                    return s.substring(0, 7) + remain.substring(7);
                case "Day":
                    return s.substring(0, 10) + remain.substring(10);
                case "Hour":
                    return s.substring(0, 13) + remain.substring(13);
                case "Minute":
                    return s.substring(0, 16) + remain.substring(16);
                case "Second":
                    return s.substring(0, 19) + remain.substring(19);
            }
            throw new RuntimeException();
        }
    }
}
