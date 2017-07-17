package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P636_ExclusiveTimeOfFunctions.java
 * @date        2017年7月16日 上午10:24:49
 * @details     
 */
public class P636_ExclusiveTimeOfFunctions {
    public static void main(String[] args) {
        int n = 2;
        List<String> logs = Arrays.asList("0:start:0",
 "1:start:2",
 "1:end:5",
 "0:end:6");
        System.out.println(new Solution().exclusiveTime(n, logs));
    }

    static public class Solution {
        public int[] exclusiveTime(int n, List<String> logs) {
            int[] ans = new int[n];
            int pre = -1;
            int[] stack = new int[logs.size() + 2];
            int p = 0;
            for (String log : logs) {
                String[] sp = log.split(":");
                int id = Integer.parseInt(sp[0]);
                int now = Integer.parseInt(sp[2]);
                if (p > 0) {
                    ans[stack[p - 1]] += now - pre - 1;
                }
                if (sp[1].equals("start")) {
                    stack[p++] = id;
                } else {
                    p--;
                }
                ans[id]++;
                pre = now;
            }
            return ans;
        }
    }
    static public class Solution2 {
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
}
