package nowcoder.zuo;

/**
 * 	给定一个字符串s，返回s的统计字符串，
 * 	例如："aaabbadddffc"的统计字符串为：
 * 	"a_3_b_2_a_1_d_3_f_2_c_1"
 * 
 * 	补充题目：
 * 		给定一个字符串的统计字符串cs，再给定一个index，返回
 * 		cs所代表的原始字符串上的第index个字符。
 * 		举例：
 * 			"a_1_b_100"所代表的原始字符串中第0个字符是'a'
 * 			"a_1_b_100"所代表的原始字符串中第50个字符是'b'
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book075_字符串的统计字符串.java
 * @type        Book075_字符串的统计字符串
 * @date        2016年12月18日 下午8:52:06
 * @details     
 */
public class Book075_字符串的统计字符串 {
	static class Solution1 {
		public String C(String s) {
			int pi = 0;
			StringBuilder st = new StringBuilder();
			for (int i = 0; i <= s.length(); i ++) {
				if (i != s.length() && ( i == 0 || (i != 0 && s.charAt(i) == s.charAt(i-1)))) {
				} else {
					st.append(s.charAt(pi));
					st.append('_');
					st.append(i - pi);
					if (i != s.length())
						st.append('_');
					pi = i;
				}
			}
			return st.toString();
		}
	}
	static class Solution1R {
		public char indexOrigin(String s, int index) {
			if (s == null || s.length() == 0) return 0;
			int i = 2;
			int n = 0;
			int v = 0;
			char c = '\0';
			while (i < s.length()) {
				c = s.charAt(i - 2);
				n = s.charAt(i) - '0';
				while ( i + 1 < s.length() && s.charAt(i+1) != '_' ) {
					n = n * 10 + s.charAt(i) - '0';
					i ++;
				}
				v += n;
				n = 0;
				if ( v > index) return c;
				i += 4;
			}
			return '\0';
		}
	}
}
