package leetcode;

/**
 *  Given an array of integers and an integer k, you need to find the total number of continuous
 *   subarrays whose sum equals to k.
    
    Example 1:
    Input:nums = [1,1,1], k = 2
    Output: 2
    Note:
    The length of the array is in range [1, 20,000].
    The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P560_SubarraySumEqualsK.java
 * @type        P560_SubarraySumEqualsK
 * @date        2017年4月30日 上午9:40:41
 * @details     
 */
public class P560_SubarraySumEqualsK {
    public static void main(String[] args) {
        int[] n = {1, 6, 2, 5, 3, 4};
        System.out.println(new Solution().subarraySum(n, 9));
        
    }
    static public class Solution {
        public int subarraySum(int[] n, int k) {
            int nn = n == null ? 0 : n.length;
            if (nn == 0) return 0;
            long[] s = new long[nn+1];
            s[0] = 0;
            for (int si = 0; si < nn; si ++) {
                s[si+1] = s[si] + n[si];
            }
            int cnt = 0;
            for (int i = 0; i <= nn; i ++) {
                for (int j = i+1; j <= nn; j ++) {
                    if (s[j] - s[i] == k) cnt ++;
                }
            }
            return cnt;
        }
    }
}
