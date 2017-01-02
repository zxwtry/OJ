package nowcoder.zuo;

/**
 * 	[题目]
 * 	给定一个N*N的矩阵matix，
 * 	在这个矩阵中，只有0和1两种值，
 * 	返回边框全是1的最大正方形的边长长度
 * 	
 * 	[举例]
 * 	{
 * 		{0,1,1,1,1},
 * 		{0,1,0,0,1},
 * 		{0,1,0,0,1},
 * 		{0,1,1,1,1},
 * 		{0,1,0,1,1},
 * 	}
 * 	其中，边框全是1的最大正方形的大小为4*4，所以返回4
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book108_边界都是1的最大正方形大小.java
 * @type        Book108_边界都是1的最大正方形大小
 * @date        2017年1月2日 下午9:21:05
 * @details     Solution1: 时间O(N^4)，空间O(1)
 */
public class Book108_边界都是1的最大正方形大小 {
	static class Solution1 {
		public int maxSquareLayer(int[][] m) {
			if (m == null || m.length == 0 || m[0].length == 0) return 0; 
			int n = m.length;
			int max = 0;
			boolean isBreak = false;
			for (int i1 = 0; i1 < n; i1 ++) {
				for (int j1 = 0; j1 < n; j1 ++) {
					if (m[i1][j1] == 0) continue;
					if (n - Math.max(i1, j1) < max) continue;
					for (int len = n - Math.max(i1, j1); len > max; len --) {
						isBreak = false;
						for (int k = 1; k < 2 * len - 1 && ! isBreak; k ++) {
							int addi = k >= len ? len - 1 : k;
							int addj = k >= len ? k - len + 1 : 0;
							if (m[addi + i1][addj + j1] != 1 || m[addj + i1][addi + j1] != 1)
								isBreak = true;
						}
						if (! isBreak) max = len;
					}
				}
			}
			return max;
		}
	}
}

