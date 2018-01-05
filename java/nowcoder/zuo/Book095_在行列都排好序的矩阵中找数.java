package nowcoder.zuo;

/**
 * 	给定一个有N*M的整型矩阵m和一个整数K，
 * 	m的每一行和每一列都是排好序的。
 * 	实现一个函数，判断K是否在m中
 * 	举例：
 * 		0 1 2 5
 * 		2 3 4 7
 * 		4 4 4 8
 * 		5 7 7 9
 * 	如果K为7，返回true；如果K为6返回false
 * 	要求：时间复杂度O(N+M)，空间复杂度O(1)
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book095_在行列都排好序的矩阵中找数.java
 * @type        Book095_在行列都排好序的矩阵中找数
 * @date        2016年12月26日 下午9:54:02
 * @details     
 */
public class Book095_在行列都排好序的矩阵中找数 {
	static class Solution {
		public boolean isContais(int[][] m, int K) {
			int row = 0;
			int col = m[0].length - 1;
			while (row < m.length && col > -1) {
				if (m[row][col] == K) return true;
				else if (m[row][col] > K) col --;
				else row ++;
			}
			return false;
		}
	}
}
