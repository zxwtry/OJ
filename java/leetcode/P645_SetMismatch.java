package leetcode;

/**

The set S originally contains numbers from 1 to n. But unfortunately, 
due to the data error, one of the numbers in the set got duplicated to
 another number in the set, which results in repetition of one number 
 and loss of another number.

Given an array nums representing the data status of this set after the error. 
Your task is to firstly find the number occurs twice and then find the number 
that is missing. Return them in the form of an array.

Example 1:
Input: nums = [1,2,2,4]
Output: [2,3]
Note:
The given array size will in the range [2, 10000].
The given array's numbers won't have any order.

 */


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P645_SetMismatch.java
 * @date        2017年7月23日 上午9:30:26
 * @details     
 */
public class P645_SetMismatch {
    public static void main(String[] args) {
        int[] n = {1, 2, 2, 4};
        tools.Utils.printArray(new Solution().findErrorNums(n), n.length, 4);
    }
    static public class Solution {
        public int[] findErrorNums(int[] n) {
            int nn = n == null ? 0 : n.length;
            if (nn < 1) return new int[] {1, 1};
            int[] map = new int[nn];
            int[] ans = new int[2];
            for (int i = 0; i < nn; i ++) {
                int v = n[i];
                if (map[v - 1] != 0) {
                    ans[0] = v;
                } else {
                    map[v - 1] = v;
                }
            }
            for (int i = 0; i < nn; i ++) {
                if (map[i] == 0) {
                    ans[1] = i + 1;
                }
            }
            return ans;
        }
    }
}
