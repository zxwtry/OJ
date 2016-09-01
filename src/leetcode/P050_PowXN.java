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
	/*
	 * 	2 ms
	 * 	6.16%
	 * 	多想想，怎么写出没有bug的代码
	 */
	static class Solution2 {
		public double myPow(double x, int n) {
			double ans = 0.0;
			boolean isNegative = n < 0;
			long len = 0, num = isNegative ? -(long)n : n;
			while (num != 0) {
				len ++;
				num = num >>> 1;
			}
			num = isNegative ? -(long)n : n;
			double[] help = new double[(int)(len + 13)];
			help[0] = 1;
			help[1] = x;
			long nn = 2;
			int count = 1;
			ans = x;
			while (nn < num + 1) {
				ans = ans * ans;
				help[++ count] = ans;
				nn = nn << 1;
			}
			ans = 1;
			for (int i = 1; i != help.length; i ++) {
				if (Math.abs(help[i]) < 0.00000001)
					continue;
				int times = (int)num & (1 << (i - 1));
				if (times != 0)
					ans *= help[i];
			}
			return isNegative ? 1/ans : ans;
		}
	}
}
