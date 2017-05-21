package leetcode;

import java.util.HashMap;
import java.util.Map.Entry;

/**
We define a harmonious array is an array where the difference between
 its maximum value and its minimum value is exactly 1.

Now, given an integer array, you need to find the length of its longest
 harmonious subsequence among all its possible subsequences.

Example 1:
Input: [1,3,2,2,5,2,3,7]
Output: 5
Explanation: The longest harmonious subsequence is [3,2,2,2,3].
Note: The length of the input array will not exceed 20,000.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P594_LongestHarmoniousSubsequence.java
 * @type        P594_LongestHarmoniousSubsequence
 * @date        2017年5月21日 上午9:31:35
 * @details     
 */
public class P594_LongestHarmoniousSubsequence {
    public static void main(String[] args) {
        int[] n = {1,3,2,2,4,2,3,4};
//        n = new int[] {1, 1, 1, 1};
        n = new int[] {0,3,1,3,3,3,0,1,0,2,0,3,1,3,-3,2,0,3,1,2,2,-3,2,2,3,3};
        System.out.println(new Solution2().findLHS(n));
    }
    static public class Solution {
        public int findLHS(int[] n) {
            int nn = n == null ? 0 : n.length;
            if (nn == 0) return 0;
            int[] a_1 = new int[nn];
            int[] a1 = new int[nn];
            int v_1 = 0, v1 = 0, v = 0;
            int max = 0;
            for (int i = 0; i < nn; i ++) {
                v_1 = 0;
                v1 = 0;
                v = n[i];
                for (int j = 0; j < i; j ++) {
                    switch (v - n[j]) {
                    case -1:
                        v1 = Math.max(v1, a_1[j] + 1);
                        break;
                    case 0:
                        v_1 = Math.max(v_1, a_1[j] == 0 ? 0 : a_1[j] + 1);
                        v1 = Math.max(v1, a1[j] == 0 ? 0 : a1[j] + 1);
                        break;
                    case 1:
                        v_1 = Math.max(v_1, a1[j] + 1);
                        break;
                    default:
                        break;
                    }
                }
                a_1[i] = v_1;
                a1[i] = v1;
                System.out.printf("%d\t%d\n", v_1, v1);
                max = Math.max(max, Math.max(v_1, v1));
            }
            return max == 0 ? 0 : max+1;
        }
    }
    static public class Solution2 {
        public int findLHS(int[] n) {
            HashMap<Integer, Integer> m = new HashMap<>();
            for (int v : n)
                m.put(v, m.getOrDefault(v, 0) + 1);
            int ans = 0;
            for (Entry<Integer, Integer> e : m.entrySet()) {
                Integer v1 = m.get(e.getKey() + 1);
                if (v1 != null) ans = Math.max(e.getValue() + v1, ans);
                Integer v2 = m.get(e.getKey() - 1);
                if (v2 != null) ans = Math.max(e.getValue() + v2, ans);
            }
            return ans;
        }
    }
}
