package nowcoder.zuo;

/**
 * 	给定字符串s1和s2，求s1的子串中&&包含s2所有的字符 中最小子串的长度
 * 	举例：
 * 		s1="abcde"，s2="ac"，返回3 ("abc")
 * 		s1="12345"，s2="344"，返回0 (没有两个4)
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book083_最小包含子串的长度.java
 * @type        Book083_最小包含子串的长度
 * @date        2016年12月21日 下午5:53:13
 * @details     
 */
public class Book083_最小包含子串的长度 {
	static class Solution {
		public int minLength(String s1, String s2) {
			if (s1 == null || s2 == null || s1.length() < s2.length()) return 0;
			int[] map = new int[256];
			for (int i = 0; i < s2.length(); i ++) map[s2.charAt(i)] ++;
			int l = 0, r = 0, m = s2.length(), a = Integer.MAX_VALUE;
			while (r != s1.length()) {
				map[s1.charAt(r)] --;
				if (map[s1.charAt(r)] >= 0) m --;
				if (m == 0) {
					while (map[s1.charAt(l)] < 0) map[s1.charAt(l++)] ++;
					a = Math.min(a, r-l+1);
					m ++;
					map[s1.charAt(l++)] ++;
				}
				r ++;
			}
			return a == Integer.MAX_VALUE ? 0 : a;
		}
	}
}
