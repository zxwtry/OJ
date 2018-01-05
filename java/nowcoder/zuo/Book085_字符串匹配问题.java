package nowcoder.zuo;

/**
 * 	字符串匹配问题
 * 	给定字符串s，其中不包含'.'和'*'
 * 	给定字符串p，其中可以包含'.'和'*'
 * 	'*'字符不能是p的首字符，并且任意两个'*'字符都不相邻
 * 	p中的'.'代表任何一个字符
 * 	p中的'*'代表'*'的前一个字符可以有0个或多个
 * 	举例：
 * 		s="abc", p="abc", 返回true
 * 		s="abc", p="a.c", 返回true
 * 		s="abc", p=".*", 返回true
 * 		s="", p="..*", 返回false
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book085_字符串匹配问题.java
 * @type        Book085_字符串匹配问题
 * @date        2016年12月22日 下午3:54:09
 * @details     
 */
public class Book085_字符串匹配问题 {
	static class Solution1 {
		public boolean isValid(char[] s, char[] p) {
			for (int i = 0; i < s.length; i ++)
				if (s[i] == '*' || s[i] == '.') return false;
			for (int i = 0; i < p.length; i ++) 
				if (p[i] == '*' && (i == 0 || p[i-1] == '*')) return false;
			return true;
		}
		public boolean isMatch(String str, String exp) {
			if (str == null || exp == null) return false;
			char[] s = str.toCharArray();
			char[] p = exp.toCharArray();
			return isValid(s, p) ? process(s, p, 0, 0) : false;
		}
		private boolean process(char[] s, char[] p, int si, int pi) {
			if (pi == p.length) return si == s.length;
			if (pi + 1 == p.length || p[pi + 1] != '*')
				return si != s.length && (p[pi] == s[si] || p[pi] == '.') && process(s, p, si+1, pi+1);
			while (si != s.length && (p[pi] == s[si] || p[pi] == '.')) {
				if (process(s, p, si, pi + 2)) return true;
				si ++;
			}
			return process(s, p, si, pi + 2);
		}
	}
}
