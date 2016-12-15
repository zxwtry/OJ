package nowcoder.zuo;

/**
 * 	给定一个字符串s，s全部由数字字符组成，
 * 	如果s中某一个或相邻两个字符组成的子串值在1~26之间，
 * 	则这个子串可以转换为一个字母，
 * 	'1' --> 'A'
 * 	'2' --> 'B'
 * 	     |
 * 	     |
 * 	'26'--> 'Z'
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book064_数字字符串转换为字母组合的种数.java
 * @type        Book064_数字字符串转换为字母组合的种数
 * @date        2016年12月15日 下午9:42:09
 * @details     
 */
public class Book064_数字字符串转换为字母组合的种数 {
	public static void main(String[] args) {
	}
	static class Solution1 {
		public int num(String s) {
			if (s == null || s.length() == 0) return 0;
			return process(s, 0);
		}
		private int process(String s, int i) {
			if (i == s.length()) return 1;
			if (s.charAt(i) == '0') return 0;
			int res = process(s, i + 1);
			if (i + 1 < s.length() && (s.charAt(i) - '0') * 10 + s.charAt(i + 1) - '0' < 27)
				res += process(s, i + 2);
			return res;
		}
	}
	static class Solution2 {
		public int num(String s) {
			if (s == null || s.length() == 0) return 0;
			int l = s.length();
			int cur = s.charAt(l - 1) == '0' ? 0 : 1;
			int next = 1;
			int tmp = 0;
			for (int i = l - 2; i >= 0; i --) {
				if (s.charAt(i) == '0') {
					next = cur;
					cur = 0;
				} else {
					tmp = cur;
					if ((s.charAt(i)-'0') * 10 + s.charAt(i+1) - '0' < 27)
						cur += next;
					next = tmp;
				}
			}
			return cur;
		}
	}
}
