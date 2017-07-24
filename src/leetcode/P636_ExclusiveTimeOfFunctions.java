package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**

Given the running logs of n functions that are executed in a nonpreemptive 
single threaded CPU, find the exclusive time of these functions.

Each function has a unique id, start from 0 to n-1. A function may be called 
recursively or by another function.

A log is a string has this format : function_id:start_or_end:timestamp. 
For example, "0:start:0" means function 0 starts from the very beginning of time 0.
 "0:end:0" means function 0 ends to the very end of time 0.

Exclusive time of a function is defined as the time spent within this function, 
the time spent by calling other functions should not be considered as 
this function's exclusive time. You should return the exclusive time of 
each function sorted by their function id.

Example 1:
Input:
n = 2
logs = 
["0:start:0",
 "1:start:2",
 "1:end:5",
 "0:end:6"]
Output:[3, 4]
Explanation:
Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1. 
Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time. 
So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.
Note:
Input logs will be sorted by timestamp, NOT log id.
Your output should be sorted by function id, which means the 0th element of 
your output corresponds to the exclusive time of function 0.
Two functions won't start or end at the same time.
Functions could be called recursively, and will always end.
1 <= n <= 100

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P636_ExclusiveTimeOfFunctions.java
 * @date        2017年7月16日 上午10:24:49
 * @details     Solution1: AC 
 * @details     Solution2: AC 99.63%
 */
public class P636_ExclusiveTimeOfFunctions {
    public static void main(String[] args) {
        int n = 2;
        List<String> logs = Arrays.asList("0:start:0",
         "1:start:2",
         "1:end:5",
         "0:end:6");
        tools.Utils.printArray(new Solution2().exclusiveTime(n, logs), 9, 5);
        System.out.println();
    }

    static public class Solution1 {
        public int[] exclusiveTime(int n, List<String> logs) {
            int[] ans = new int[n];
            int pre = -1;
            Stack<Integer> stk = new Stack<>();
            for (String log : logs) {
                String[] sp = log.split(":");
                int id = Integer.parseInt(sp[0]);
                int now = Integer.parseInt(sp[2]);
                if (stk.size() > 0) {
                    ans[stk.peek()] += now - pre - 1;
                }
                if (sp[1].equals("start")) {
                    stk.add(id);
                } else {
                    stk.pop();
                }
                ans[id]++;
                pre = now;
            }
            return ans;
        }
    }
    
    static class Solution2 {
        static final int START = 1;
        static final int END = 2;
        static final int START_LEN = 5;
        static final int END_LEN = 3;
        public int[] exclusiveTime(int n, List<String> logs) {
            Stack<Integer> stk = new Stack<Integer>();
            int[] ans = new int[n];
            int pre = -1;
            for (String log : logs) {
                int[] solve = solveLog(log);
//                tools.Utils.printArray(solve, 5, 9);
                if (! stk.isEmpty()) {
                    ans[stk.peek()] += solve[2] - pre  - 1;
                }
                if (solve[1] == START) {
                    stk.add(solve[0]);
                } else {
                    stk.pop();
                }
                ans[solve[0]] ++;
                pre = solve[2];
            }
            return ans;
        }
        private int[] solveLog(String log) {
            int[] solve = new int[3];
            int ln = log.length();
            int i = 0;
            for (; i < ln; i ++) {
                char c = log.charAt(i);
                if (c == ':') break;
                solve[0] = solve[0] * 10 + c - '0';
            }
            i ++;
            if (log.charAt(i) == 's') {
                solve[1] = START;
                i += START_LEN;
            } else {
                solve[1] = END;
                i += END_LEN;
            }
            i ++;
            for (; i < ln; i ++) {
                char c = log.charAt(i);
                solve[2] = solve[2] * 10 + c - '0';
            }
            return solve;
        }
    }    
}
