package leetcode;

/**

Given an array consisting of n integers, find the contiguous subarray
 whose length is greater than or equal to k that has the maximum average value. 
 And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation:
when length is 5, maximum average value is 10.8,
when length is 6, maximum average value is 9.16667.
Thus return 12.75.
Note:
1 <= k <= n <= 10,000.
Elements of the given array will be in range [-10,000, 10,000].
The answer with the calculation error less than 10-5 will be accepted.

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P644_MaximumAverageSubarrayII.java
 * @date        2017年7月24日 下午12:13:55
 * @details     Solution1: TLE
 * @details     Solution2: AC 
 */
public class P644_MaximumAverageSubarrayII {
    public static void main(String[] args) {
        int[] ns = {1,12,-5,-6,50,3};
        int k = 6;
//        int[] ns = {-1};
//        int k = 1;
        System.out.println(new Solution1().findMaxAverage(ns, k));
        System.out.println(new Solution2().findMaxAverage(ns, k));
    }
    static public class Solution1 {
        public double findMaxAverage(int[] ns, int k) {
            int nn = ns == null ? 0 : ns.length;
            if (nn == 0) return 0.0;
            long[] sum = new long[nn + 1];
            for (int i = 0; i < nn; i ++) {
                sum[i + 1] = sum[i] + ns[i];
            }
            double max = Integer.MIN_VALUE;
            for (int i = k; i <= nn; i ++) {
                for (int j = i - k; j > -1; j --) {
                    max = Math.max(max, ((double)(sum[i] - sum[j])) / (i - j));
                }
            }
            return max;
        }
    }
    static public class Solution2 {
        static final double PRECISION = 0.0000001;
        public double findMaxAverage(int[] ns, int k) {
            int nn = ns == null ? 0 : ns.length;
            double[] cs = new double[nn + 1];
            double pre = Integer.MAX_VALUE;
            double left = -20000;
            double right = 20000;
            while (true) {
                double now = (left + right) / 2;
                if (Math.abs(pre - now) < PRECISION) {
                    break;
                }
                if (isNowBelow(cs, ns, nn, k, now)) {
                    left = now;
                } else {
                    right = now;
                }
                pre = now;
            }
            return pre;
        }
        private boolean isNowBelow(double[] cs, int[] ns, int nn, int k, double now) {
            for (int i = 0; i < nn; i ++) {
                cs[i + 1] = cs[i] + ns[i] - now;
            }
            double min = Double.POSITIVE_INFINITY;
            for (int i = k; i <= nn; i ++) {
                min = Math.min(min, cs[i - k]);
                if (cs[i] - min >= 0) return true;
            }
            return false;
        }
    }
}
