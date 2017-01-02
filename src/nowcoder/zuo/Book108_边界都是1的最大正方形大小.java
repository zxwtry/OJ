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
 * @details     Solution2: 时间O(N^4)，空间O(1)
 * @details     Solution3: 时间O(N^3)，空间O(N^2)
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
	static class Solution2 {
		public int maxSquareLayer(int[][] m) {
			if (m == null || m.length == 0 || m[0].length == 0) return 0; 
			int n = m.length;
			int max = 0;
			boolean isBreak = false;
			for (int i1 = 0; i1 < n; i1 ++) {
				for (int j1 = 0; j1 < n; j1 ++) {
					if (m[i1][j1] == 0) continue;
					for (int len = n - Math.max(i1, j1); len > max; len --) {
						isBreak = false;
						int i2 = i1 + len - 1;
						int j2 = j1 + len - 1;
						for (int i = i1; i <= i2 && ! isBreak; i ++) {
							if (m[i][j1] == 0 || m[i][j2] == 0) isBreak = true;
						}
						for (int j = j1; j <= j2 && ! isBreak; j ++) {
							if (m[i1][j] == 0 || m[i2][j] == 0) isBreak = true; 
						}
						if (! isBreak) max = len;
					}
				}
			}
			return max;
		}
	}
	static class Solution3 {
		public int maxSquareLayer(int[][] m) {
			if (m == null || m.length == 0 || m[0].length == 0) return 0; 
			int row = m.length;
			int col = m[0].length;
			int[][] r = new int[row][col];
			int[][] d = new int[row][col];
			setBorderMap(m, r, d);
			for (int size = Math.min(row, col); size > 0; size --) {
				if (hasSizeOfBorder(size, r, d))
					return size;
			}
			return 0;
		}
		private boolean hasSizeOfBorder(int size, int[][] r, int[][] d) {
			for (int i = 0; i < r.length - size + 1; i ++) {
				for (int j = 0; j < r[0].length - size + 1; j ++) {
					if (r[i][j] >= size && d[i][j] >= size
							&& r[i+size-1][j] >= size
							&& d[i][j+size-1] >= size)
						return true;
				}
			}
			return false;
		}
		private void setBorderMap(int[][] m, int[][] r, int[][] d) {
			int i = m.length, j = m[0].length;
			if (m[i - 1][j - 1] == 1) {
				r[i - 1][j - 1] = 1;
				d[i - 1][j - 1] = 1;
			}
			for (int k = i - 2; k > -1; k --) {
				if (m[k][j - 1] == 1) {
					r[k][j - 1] = 1;
					d[k][j - 1] = d[k + 1][j - 1] + 1;
				}
			}
			for (int k = j - 2; k > -1; k --) {
				if (m[i - 1][k] == 1) {
					r[i - 1][k] = r[i - 1][k + 1] + 1;
					d[i - 1][k] = 1;
				}
			}
			for (int k = i - 2; k > -1; k --) {
				for (int v = j - 2; v != -1; v --) {
					if (m[k][v] == 1) {
						r[k][v] = r[k][v+1] + 1;
						d[k][v] = d[k+1][v] + 1;
					}
				}
			}
		}
	}
}

