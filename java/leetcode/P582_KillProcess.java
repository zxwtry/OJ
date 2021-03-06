package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
    Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

    Each process only has one parent process, but may have one or more children processes. 
    This is just like a tree structure. Only one process has PPID that is 0, 
    which means this process has no parent process. All the PIDs will be distinct positive integers.
    
    We use two list of integers to represent a list of processes, where the first list contains 
    PID for each process and the second list contains the corresponding PPID.
    
    Now given the two lists, and a PID representing a process you want to kill, 
    return a list of PIDs of processes that will be killed in the end. You should 
    assume that when a process is killed, all its children processes will be killed. 
    No order is required for the final answer.
    
    Example 1:
    Input: 
    pid =  [1, 3, 10, 5]
    ppid = [3, 0, 5, 3]
    kill = 5
    Output: [5,10]
    Explanation: 
               3
             /   \
            1     5
                 /
                10
    Kill 5 will also kill 10.
    Note:
    The given kill id is guaranteed to be one of the given PIDs.
    n >= 1.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P582_KillProcess.java
 * @type        P582_KillProcess
 * @date        2017年5月14日 上午9:56:41
 * @details     Solution: AC
 */
public class P582_KillProcess {
    static public class Solution {
        public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
            HashMap<Integer, ArrayList<Integer>> m = new HashMap<>();
            int n = pid.size();
            for (int i = 0; i < n; i ++) {
                ArrayList<Integer> s = m.get(ppid.get(i));
                if (s == null) {
                    s = new ArrayList<>();
                }
                boolean put = s.size() == 0;
                s.add(pid.get(i));
                if (put) m.put(ppid.get(i), s);
            }
            ArrayList<Integer> p = m.get(kill);
            if (p == null) return Arrays.asList(kill);
            List<Integer> ans = new ArrayList<>();
            Queue<Integer> q = new LinkedList<Integer>();
            q.add(kill);
            ans.add(kill);
            while (! q.isEmpty()) {
                p = m.get(q.poll());
                if (p == null) {
                    p = new ArrayList<>();
                }
                for (int v : p) {
                    ans.add(v);
                    q.add(v);
                }
            }
            return ans;
        }
    }
}
