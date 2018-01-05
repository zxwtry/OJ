package nowcoder.zuo;

/**
 * 	给定一个字符串数组ss，再给定两个字符串s1和s2，
 * 	返回在ss中s1和s2的最小距离。
 * 	举例：
 * 		ss=["1", "3", "3", "3", "2", "3", "1"]，s1="1"，s2="2"，返回2
 * 		ss=["CD"]，s1="CD"，s2="AB"，返回-1
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book076_数组中两个字符串的最小距离.java
 * @type        Book076_数组中两个字符串的最小距离
 * @date        2016年12月20日 上午10:14:21
 * @details     
 */
public class Book076_数组中两个字符串的最小距离 {
	static class Solution {
		public int minDist(String[] ss, String s1, String s2) {
			if (s1 == null || s2 == null || ss == null || ss.length == 0) return -1;
			if (s1.equals(s2)) return 0;
			int l1 = -1, l2 = -1;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < ss.length; i ++) {
				if (ss[i].equals(s1)) {
					min = Math.min(min, l2 == -1 ? min : i - l2);
					l1 = i;
				}
				if (ss[i].equals(s2)) {
					min = Math.min(min, l1 == -1 ? min : i - l1);
					l2 = i;
				}
			}
			return min == Integer.MAX_VALUE ? - 1 : min;
		}
	}
}
