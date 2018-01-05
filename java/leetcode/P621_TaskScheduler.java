package leetcode;

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
 * @details     AC 11ms 92.09%
 */
public class P621_TaskScheduler {
    static class Solution {
        public int leastInterval(char[] tasks, int n) {
            if (tasks == null) return 0;
            int[] map = new int[26];
            for (char task : tasks) map[task - 'A'] ++;
            int mapMax = map[0];
            int mapMaxCount = 1;
            for (int i = 1; i < 26; i ++) {
                if (map[i] > mapMax) {
                    mapMaxCount = 1;
                    mapMax = map[i];
                } else if (map[i] == mapMax) {
                    mapMaxCount ++;
                }
            }
            return Math.max(tasks.length, (mapMax - 1) * (n + 1) + mapMaxCount);
        }
    }
}
