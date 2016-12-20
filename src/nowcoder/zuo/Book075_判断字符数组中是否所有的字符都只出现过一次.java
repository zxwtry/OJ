package nowcoder.zuo;

/**
 * 	给定一个字符类数组cs[]，判断cs中是否所有的字符都只出现过一次。
 * 	要求：
 * 		1，	实现一种时间O(N)的方法。
 * 		2，	在空间O(1)的基础上，实现时间最小的方法。 
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book075_判断字符数组中是否所有的字符都只出现过一次.java
 * @type        Book075_判断字符数组中是否所有的字符都只出现过一次
 * @date        2016年12月20日 上午8:58:45
 * @details     Solution1 时间O(N)
 */
public class Book075_判断字符数组中是否所有的字符都只出现过一次 {
	static class Solution1 {
		public boolean isOnlyOne(char[] cs) {
			boolean[] isHasOne = new boolean[256];
			for (char c : cs) {
				if (! isHasOne[c]) isHasOne[c] = true;
				else return false;
			}
			return true;
		}
	}
}
