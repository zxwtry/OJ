package nowcoder.zuo;

/**
 * 	[题目]
 * 	给定一个矩阵m，其中的值有正、有负、有0，返回子矩阵的最大累计和。
 * 	
 * 	[举例]
 * 	{
 * 		{-90, 48, 78},
 * 		{64, -40, 64},
 * 		{-81, -7, 66},
 * 	}
 * 	其中，最大累加和的子矩阵为：
 * 	48 78
 * 	-40 64
 * 	-7 66
 * 	返回累加和209
 * 
 * 	[举例]
 * 	{
 * 		{-2, -2, -2},
 * 		{-1, 1, 1},
 * 		{-3, -4, -5},
 * 	}
 * 	返回最大累加和2
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book104_子矩阵的最大累加和问题.java
 * @type        Book104_子矩阵的最大累加和问题
 * @date        2017年1月1日 下午8:14:03
 * @details     Solution1: 时间O((N*M)^3)，空间O(1)
 */
public class Book104_子矩阵的最大累加和问题 {
	static class Solution1 {
		public int getMaxSubMatSum(int[][] m) {
			if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) return 0;
			int row = m.length, col = m[0].length;
			int max = Integer.MIN_VALUE;
			for (int i1 = 0; i1 < row; i1 ++) {
				for (int j1 = 0; j1 < col; j1 ++) {
					for (int i2 = i1; i2 < row; i2 ++) {
						for (int j2 = j1; j2 < col; j2 ++) {
							int sum = 0;
							for (int i = i1; i <= i2; i ++) {
								for (int j = j1; j <= j2; j ++) {
									sum += m[i][j];
								}
							}
							max = Math.max(max, sum);
						}
					}
				}
			}
			return max;
		}
	}
	static class Solution2 {
		public int getMaxSubMatSum(int[][] m) {
			if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) return 0;
			int max = Integer.MIN_VALUE;
			int cur = 0;
			int[] s = null;
			for (int i = 0; i < m.length; i ++) {
				s = new int[m[0].length];
				for (int j = i; j < m.length; j ++) {
					cur = 0;
					for (int k = 0; k < s.length; k ++) {
						s[k] += m[j][k];
						cur += s[k];
						max = Math.max(max, cur);
						cur = cur < 0 ? 0 : cur;
					}
				}
			}
			return max;
		}
	}
}
