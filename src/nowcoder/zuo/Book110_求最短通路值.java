package nowcoder.zuo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 	[题目]
 * 	用一个整型矩阵m标识一个网络，1代表有路，0代表无路，
 * 	每一个位置只要不越界，都有上下左右4个方向，求最左上角到最右下角的最短通路值。
 * 	
 * 	[举例]
 * 	m为：
 * 	{
 * 		{1,0,1,1,1},
 * 		{1,0,1,0,1},
 * 		{1,1,1,0,1},
 * 		{0,0,0,0,1},
 * 	}
 * 	通路只有一条，由12个1构成，返回12
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book110_求最短通路值.java
 * @type        Book110_求最短通路值
 * @date        2017年1月3日 下午9:32:35
 * @details     
 */
public class Book110_求最短通路值 {
	static class Solution1 {
		public int minPathValue(int[][] m) {
			if (m == null || m.length == 0 || m[0].length == 0 ||
					m[0][0] != 1 || m[m.length - 1][m[0].length - 1] != -1)
				return 0;
			int res = 0;
			int row = m.length, col = m[0].length;
			int[][] map = new int[row][col];
			map[0][0] = 1;
			Queue<Integer> rq = new LinkedList<Integer>();
			Queue<Integer> cq = new LinkedList<Integer>();
			rq.add(0);
			cq.add(0);
			int r = 0, c = 0;
			while (! rq.isEmpty()) {
				r = rq.poll();
				c = cq.poll();
				if (r == row - 1 && c == col - 1)
					return map[r][c];
				walk(map[r][c], r-1, c, m, map, rq, cq);
				walk(map[r][c], r+1, c, m, map, rq, cq);
				walk(map[r][c], r, c-1, m, map, rq, cq);
				walk(map[r][c], r, c+1, m, map, rq, cq);
			}
			return res;
		}
		private void walk(int pre, int toR, int toC, int[][] m, int[][] map, Queue<Integer> rq, Queue<Integer> cq) {
			if (toR < 0 || toR == m.length || toC < 0 || toC == m[0].length 
					|| m[toR][toC] != 1 || map[toR][toC] != 0) return;
			map[toR][toC] = pre+1;
			rq.add(toR);
			cq.add(toC);
		}
	}
}
