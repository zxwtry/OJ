package leetcode;

/*
 * 	Divide two integers without using multiplication, division and mod operator.

	If it is overflow, return MAX_INT.
 */

public class P029_DivideTwoIntegers {
	static class Solution {
	    final int sign = Integer.MIN_VALUE >> 1;
	    public int divide(int dividend, int divisor) {
	        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
	            return Integer.MAX_VALUE;
	        }
	        boolean isNegative = (dividend ^ divisor) >>> 31 == 1;
	        if (dividend > 0) {
	            dividend = - dividend;
	        }
	        if (divisor > 0) {
	            divisor = - divisor;
	        }
	        return isNegative ? - divideInternal(dividend, divisor) : 
	            divideInternal(dividend, divisor);
	    }

        private int divideInternal(int dividend, int divisor) {
            int[] val = new int[34];
            int[] tim = new int[34];
            val[0] = divisor;
            tim[0] = 1;
            int i = 1;
            for (; i < 34; i ++) {
                if (val[i - 1] < sign) {
                    i --;
                    break;
                }
                val[i] = val[i - 1] + val[i - 1];
                tim[i] = tim[i - 1] + tim[i - 1];
                if (val[i] <= dividend) {
                    break;
                }
            }
            int ans = 0;
            int v = dividend;
            while (v <= divisor) {
                while (i > -1 && val[i] < v) {
                    i --;
                }
                v -= val[i];
                ans += tim[i];
            }
            return ans;
        }
	}
}
