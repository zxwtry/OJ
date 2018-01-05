package nowcoder.zuo;

/**
 * 	如果一个字符串s，把字符串s前面任意的部分挪到后面
 * 	形成的字符串叫座s的旋转词。
 * 	例如：
 * 		s="12345"
 * 		s的旋转词有："12345"、"23451"、"34512"、"45123"、"51234"
 * 	给定两个字符串a和b，判断a和b是否互为旋转词。
 * 
 * 	举例：
 * 		a="cdab"，b="abcd"，返回true
 * 		a="1ab2"，b="ab12"，返回false
 * 		a="2ab1"，b="ab12"，返回true
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book072_判断两个字符串是否互为旋转词.java
 * @type        Book072_判断两个字符串是否互为旋转词
 * @date        2016年12月17日 下午9:52:14
 * @details     
 */
public class Book072_判断两个字符串是否互为旋转词 {
	static class Solution {
		public boolean isRotation(String a, String b) {
			if (a == null || b == null || a.length() != b.length()) return false;
			return kmp(a, b+b) != -1;
		}
		private int kmp(String p, String s) {
			int[] next = getNext(p);
			int pi = 0;
			for (int si = 0; si <= s.length() -  p.length(); si ++) {
				while (pi < p.length() && p.charAt(pi) == s.charAt(si + pi)) pi ++;
				if (pi == p.length()) return si;
				pi = next[pi];
			}
			return -1;
		}
		private int[] getNext(String p) {
			int[] next = new int[p.length() + 1];
			next[0] = -1;
			int bi = -1, fi = 0;
			for (; fi < p.length(); fi ++) {
				if (bi == -1 || p.charAt(bi) == p.charAt(fi)) {
					bi ++;
					fi ++;
					next[fi] = bi;
				} else {
					bi = next[bi];
				}
			}
			next[0] = 0;
			return next;
		}
	}
}
