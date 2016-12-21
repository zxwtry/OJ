package nowcoder.zuo;

/**
 * 	给定一个整数N，求由"0"字符和"1"字符组成的长度为N的所有字符串中，
 * 	满足"0"字符左边必有"1"字符的字符串数量
 * 	举例：
 * 		N=1	只由"0"和"1"组成，长度为1的所有的字符串中，"0"和"1"。
 * 			只有字符串"1"满足要求
 * 		N=2									 "00"、"01"、"10"、"11"	
 * 			只有"10"和"11"满足要求
 * 		N=3		"000"、"001"、"010"、"011"、"100"、"101"、"110"、"111"
 * 			只有"101"、"110"、"111"满足要求d
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book080_0左边必有1的二进制字符串数量.java
 * @type        Book080_0左边必有1的二进制字符串数量
 * @date        2016年12月21日 下午5:13:05
 * @details     
 */
public class Book080_0左边必有1的二进制字符串数量 {
	static class Solution1 {
		public int getNum(int n) {
			if (n < 1) return 0;
			return p(1, n);
		}
		private int p(int i, int n) {
			if (i == n-1) return 2;
			if (i == n) return 1;
			return p(i+1, n) + p(i+2, n);
		}
	}
	static class Solution2 {
		public int getNum(int n) {
			if (n < 1) return 0;
			else if (n == 1) return 1;
			else if (n == 2) return 2;
			int a = 1, b = 2, c = 0;
			for (int i = 3; i <= n; i ++) {
				c = a + b;
				a = b;
				b = c;
			}
			return c;
		}
	}
}
