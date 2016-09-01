package leetcode;

public class P050_PowXN {
	public static void main(String[] args) {
//		double ans1 = new Solution1().myPow(1.00000, 2147483647);
//		double ans2 = new Solution2().myPow(1.00000, 2147483647);
//		System.out.println(ans1);
//		System.out.println(ans2);
		double num1 = 2.00000;
		int times = -2147483648;
		double ans1 = new Solution1().myPow(num1, times);
		double ans2 = new Solution2().myPow(num1, times);
		System.out.println(ans1);
		System.out.println(ans2);
	}
	/*
	 * 	1 ms
	 * 	37.87%
	 */
	static class Solution1 {
	    public double myPow(double x, int n) {
	        return Math.pow(x, n);
	    }
	}
}
