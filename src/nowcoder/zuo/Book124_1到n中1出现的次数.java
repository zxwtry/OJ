package nowcoder.zuo;

/**
 * 	[题目]
 * 	给定一个整数n，返回从1到n的数字中1出现的次数
 * 	
 * 	[举例]
 * 	n=5，1~n为1,2,3,4,5。那么1出现1次
 * 	n=11，1出现4次
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book124_1到n中1出现的次数.java
 * @type        Book124_1到n中1出现的次数
 * @date        2017年1月8日 下午9:19:50
 * @details     Solution1，时间O(logN)，空间O(N)
 */
public class Book124_1到n中1出现的次数 {
	static class Solution1 {
		int[] arr = new int[] {0,1,1,1,1,1,1,1,1,1,2,4,5,6,7
				,8,9,10,11,12,12,13,13,13,13,13,13,13,13,13,
				13,14,14,14,14,14,14,14,14,14,14,15,15,15,15,
				15,15,15,15,15,15,16,16,16,16,16,16,16,16,16,
				16,17,17,17,17,17,17,17,17,17,17,18,18,18,18,
				18,18,18,18,18,18,19,19,19,19,19,19,19,19,19,
				19,20,20,20,20,20,20,20,20,20};
		public int getNumOfOne(int n) {
			if (n < 0) return 0;
			if (n < arr.length) return arr[n];
			char[] cs = String.valueOf(n).toCharArray();
			int ten_2 = 1;
			for (int i = cs.length - 2; i > 0; i --)
				ten_2 = ten_2 * 10;
			int numOfOne = (cs[0]-'0') * ten_2 * (cs.length - 1);
			int small = n - ten_2 * 10 * (cs[0] - '0');
			numOfOne += getNumOfOne(small);
			if (cs[0] > '1') 
				numOfOne += ten_2 * 10;
			else
				numOfOne += 1 + small;
			return numOfOne;
		}
	}
}
