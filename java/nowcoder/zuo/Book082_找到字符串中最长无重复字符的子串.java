package nowcoder.zuo;

/**
 * 	给定一个字符串s，返回字符串s中最长无重复字符的子串
 * 	举例：
 * 		s="abcd"，返回4
 * 		s="aabcb"，返回3
 */
 
/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book082_找到字符串中最长无重复字符的子串.java
 * @type        Book082_找到字符串中最长无重复字符的子串
 * @date        2016年12月21日 下午5:45:53
 * @details     
 */
public class Book082_找到字符串中最长无重复字符的子串 {
	static class Solution {
		public int maxUnique(String s) {
			if (s == null || s.length() == 0) return 0;
			int[] map = new int[256];
			int a = 0;
			for (int i = 0; i < s.length(); i ++) {
				if (map[s.charAt(i)] == 0) {
					a ++;
				}
				map[s.charAt(i)] ++;
			}
			return a;
		}
	}
}
