package leetcode;

import java.util.Arrays;

/**

Given a char array representing tasks CPU need to do. It contains capital letters 
A to Z where different letters represent different tasks.Tasks could be done without
 original order. Each task could be done in one interval. For each interval,
  CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, 
there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ['A','A','A','B','B','B'], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
Note:
The number of tasks is in the range [1, 10000].

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P621_TaskScheduler.java
 * @date        2017年6月18日 上午10:57:49
 * @details     AC 57ms 53.03%
 */
public class P621_TaskScheduler {
    public static void main(String[] args) {
        String s = "AAABBB";
        int n = 2;
        char[] tasks = s.toCharArray();
        Solution solution = new Solution();
        System.out.println(solution.leastInterval(tasks, n));
    }
    static public class Solution {
        public int leastInterval(char[] tasks, int n) {
            int tasksLength = tasks == null ? 0 : tasks.length;
            int[] map = new int[128];
            int[] pre = new int[128];
            Arrays.fill(pre, -n-1);
            for (int tasksIndex = 0; tasksIndex != tasksLength; tasksIndex ++) {
                map[tasks[tasksIndex]] ++; 
            }
            int findAll = tasksLength;
            int target = 0;
            while (findAll > 0) {
                int best = 0;
                for (int i = 'A'; i <= 'Z'; i ++) {
                    if (map[i] > 0 && map[i] > map[best] && pre[i] + n + 1 <= target) {
                        best = i;
                    }
                }
                if (best != 0) {
                    -- map[best];
                    pre[best] = target;
                    -- findAll;
                }
                ++ target;
            }
            return target;
            
        }
    }
}
