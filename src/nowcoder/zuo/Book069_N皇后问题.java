package nowcoder.zuo;

/**
 * 	N皇后问题是指在N*N的棋盘上要摆N个皇后
 * 	要求任何两个皇后不同行、不同列、不在同一条斜线上。
 * 	给定一个整数n，返回n皇后的摆法有多少种。
 * 	举例：
 * 		n=1，返回1
 * 		n=2，返回0
 * 		n=3，返回0
 * 		n=8，返回92
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book069_N皇后问题.java
 * @type        Book069_N皇后问题
 * @date        2016年12月16日 下午9:36:33
 * @details     
 */
public class Book069_N皇后问题 {
	public static void main(String[] args) {
		debug();
	}
	private static void debug() {
		for (int n = 1; n < 10; n ++) {
			Solution1 s1 = new Solution1();
			Solution2 s2 = new Solution2();
			System.out.println(s1.num(n) +"..."+ s2.num(n));
		}
	}
	static class Solution1 {
		public int num(int n) {
			if (n < 1) return 0;
			int[] record = new int[n];
			return process(0, record, n);
		}
		private int process(int i, int[] record, int n) {
			if (i == n) return 1;
			int res = 0;
			for (int j = 0; j < n; j ++) {
				if (isValid(record, i, j)) {
					record[i] = j;
					res += process(i + 1, record, n);
				}
			}
			return res;
		}
		private boolean isValid(int[] record, int i, int j) {
			for (int k = 0; k < i; k ++)
				if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k))
					return false;
			return true;
		}
	}
	static class Solution2 {
		public int num(int n) {
			if (n < 1 || n > 32) return 0;
			int upperLim = n == 32 ? -1 : (1 << n) - 1;
			return process(upperLim, 0, 0, 0);
		}
		private int process(int upperLim, int colLim, int leftDiaLim, int rightDiaLim) {
			if (colLim == upperLim) return 1;
			int pos = 0;
			int mostRightone = 0;
			pos = upperLim & (~(colLim | leftDiaLim | rightDiaLim));
			int res = 0;
			while (pos != 0) {
				mostRightone = pos & (~pos + 1);
				pos = pos - mostRightone;
				res += process(upperLim, colLim | mostRightone, 
						(leftDiaLim | mostRightone) << 1, 
						(rightDiaLim | mostRightone) >>> 1);
			}
			return res;
		}
	}
}
