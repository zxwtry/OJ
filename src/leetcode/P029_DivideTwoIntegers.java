package leetcode;

/*
 * 	Divide two integers without using multiplication, division and mod operator.

	If it is overflow, return MAX_INT.
 */

public class P029_DivideTwoIntegers {
	public static void main(String[] args) {
//		System.out.println(new Solution1().divide(2147483647, 2));
//		System.out.println(new Solution1().divide(2147483647, 2));
		System.out.println(new Solution2().divide(9, 0));
	}
	/*
	 * 	这个方法会TLE
	 */
	static class Solution1 {
		public int divide(int dividend, int divisor) {
	        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1))
	        	return Integer.MAX_VALUE;
	        if (Math.abs(divisor) == 1)
	        	return divisor == 1 ? dividend : - dividend;
	        int ans = 0;
	        while (dividend >= divisor) {
	        	dividend -= divisor;
	        	ans ++;
	        }
	        return ans;
	    }
	}
	/*
	 * 	正确的思路应该是:
	 * 		使用类似二分的方法来计算
	 * 		但是暴力右移是无法得到正确结果的。
	 * 		1,	dividend = 求和(i * 2 ^ k)
	 * 		2,	一次一次地减去最大的2 ^ k就行了。
	 * 	3 ms 
	 * 	10.48%
	 */
	static class Solution2 {
		public int divide(int dividend, int divisor) {
			if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1))
				return Integer.MAX_VALUE;
			boolean isNegative = (dividend ^ divisor) >>> 31 == 1;
			int ans = (int)divide(Math.abs((long)dividend), Math.abs((long)divisor));
			return isNegative ? - ans : ans;
		}
		public long divide(long n, long d) {
			long ans = 0;
			while (n >= d) {
				int count = 1;
				long val = d;
				while (val + val < n) {
					count = count + count;
					val = val + val;
				}
				n -= val;
				ans += count;
			}
			return ans;
		}
	}
}
