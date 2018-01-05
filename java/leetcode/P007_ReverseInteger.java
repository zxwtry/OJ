package leetcode;

/*
 * 	7. Reverse Integer  QuestionEditorial Solution  My Submissions
Total Accepted: 156921
Total Submissions: 659315
Difficulty: Easy
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321
 */

public class P007_ReverseInteger {
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(new Solution4().reverse(21474));
	}
	/*
	 * 	代码最快，最低效的解法
	 * 	10.28%
	 * 	6 ms
	 */
	static class Solution {
	    public int reverse(int x) {
	    	boolean isNegative = false;
	    	if (x < 0) {
	    		x = -x;
	    		isNegative = true;
	    	}
	    	StringBuilder st = new StringBuilder(String.valueOf(x));
	    	try {
	    		return isNegative ? -Integer.parseInt(st.reverse().toString()) : Integer.parseInt(st.reverse().toString());
	    	} catch (Exception e) {
	    		return 0;
	    	}
	    }
	}
	/*
	 * 	20.05%
	 * 	3ms
	 */
	static class Solution2 {
		public int reverse(int x) {
			boolean isNegative = false;
			if (x < 0) {
				x = -x;
				isNegative = true;
			}
			int ans = 0, tmp = ans;
			while (x > 0) {
				if (ans > 214748364 || (ans == 214748364 && (x % 10) > 7))
					return 0;
				tmp = ans * 10 + (x % 10);
				ans = tmp;
				x = x / 10;
			}
			return isNegative ? -ans : ans;
		}
	}
	/*
	 * 	判断溢出的方法更改为轮子哥的。
	 * 	20.05%
	 * 	3ms
	 */
	static class Solution3 {
		public int reverse(int x) {
			boolean isNegative = false;
			if (x < 0) {
				x = -x;
				isNegative = true;
			}
			int ans = 0, tmp = ans;
			while (x > 0) {
				tmp = ans * 10 + (x % 10);
				if ((tmp - (x % 10)) % 10 == 0 && (tmp - (x % 10)) / 10 == ans)
					ans = tmp;
				else
					return 0;
				x = x / 10;
			}
			return isNegative ? -ans : ans;
		}
	}
	/*
	 * 	人生苦短，何不用long
	 * 	20.05% 
	 * 	3ms
	 * 	再也不优化了
	 */
	static class Solution4 {
		public int reverse(int x) {
			boolean isNegative = false;
			if (x < 0) {
				x = -x;
				isNegative = true;
			}
			long ans = 0;
			while (x > 0) {
				ans = ans * 10 + (x % 10);
				x = x / 10;
			}
			if ( (ans >> 31) != 0 )
				return 0;
			return isNegative ? -(int)ans : (int)ans;
		}
	}
}
