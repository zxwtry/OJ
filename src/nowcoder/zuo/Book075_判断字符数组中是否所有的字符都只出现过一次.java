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
 * @details     Solution2 时间O(N*logN) 空间O(1)
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
	static class Solution2 {
		public boolean isOnlyOne(char[] cs) {
			int p = 0, c = 0, ci = 0;
			char pre = 0;
			for (ci = (cs.length - 2) / 2; ci >= 0; ci --) {
				p = ci;
				c = 2 * p + 1;
				while (c < cs.length) {
					if (c+1 < cs.length && cs[c+1] < cs[c]) c++;
					if (cs[p] > cs[c]) {
						swap(cs, p, c);
					} else break;
					p = c;
					c = 2 * p + 1;
				}
			}
			ci = cs.length - 1;
			pre = cs[0];
			while (ci > 0) {
				cs[0] = cs[ci];
				p = 0;
				c = 2 * p + 1;
				while (c < ci) {
					if (c+1 < ci && cs[c+1] < cs[c]) c++;
					if (cs[p] > cs[c]) {
						swap(cs, p, c);
					} else break;
					p = c;
					c = 2 * p + 1;
				}
				ci --;
				if (pre == cs[0]) return false;
				pre = cs[0];
			}
			return true;
		}
		void swap(char[] cs, int i, int j) {
			char t = cs[i];
			cs[i] = cs[j];
			cs[j] = t;
		}
	}
}
