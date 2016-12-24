package nowcoder.zuo;

import java.util.LinkedList;
import java.util.List;

/**
 * 	给定一个矩阵m，按照之字形的方式打印这个矩阵
 * 	例如：
 * 		{1, 2, 3, 4},
 * 		{5, 6, 7, 8},
 * 		{9,10,11,12}
 * 		之字形打印的结果是：1,2,5,9,6,3,4,7,10,11,8,12
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book091_之字形打印矩阵.java
 * @type        Book091_之字形打印矩阵
 * @date        2016年12月24日 下午3:37:53
 * @details     
 */
public class Book091_之字形打印矩阵 {
	static class Solution {
		List<Integer> ans = new LinkedList<Integer>();
		public List<Integer> print(int[][] m) {
			ans.clear();
			int row = m.length;
			int col = m[0].length;
			for (int k = 1; k < row+col; k ++) {
				int i1 = k <= row ? k - 1 : row - 1;
				int j1 = k <= row ? 0 : k - row;
				int i2 = k <= col ? 0 : k - col;
				int j2 = k <= col ? k - 1 : col - 1;
				if (k % 2 == 1) {
					route(m, i1, j1, i2, j2, true);
				} else {
					route(m, i2, j2, i1, j1, false);
				}
			}
			return ans;
		}
		private void route(int[][] m, int i1, int j1, int i2, int j2, boolean t) {
			while (true) {
				ans.add(m[i1][j1]);
				if (i1 == i2) break;
				i1 = t ? i1 - 1 : i1 + 1;
				j1 = t ? j1 + 1 : j1 - 1;
			}
		}
	}
}
