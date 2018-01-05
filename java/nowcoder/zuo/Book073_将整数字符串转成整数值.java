package nowcoder.zuo;

/**
 * 	给定一个字符串s，如果s符合日常书写的整数形式，
 * 	并且属于32位整数的范围，返回s所代表的整数值，否则，返回0
 * 	举例：
 * 		s="123"，返回123
 * 		s="023"，因为不符合日常的书写习惯，返回0
 * 		s="A13"，返回0
 * 		s="0"  ，返回0
 * 		s="2147483647"，返回2147483647
 * 		s="2147483648"，返回2147483648
 * 		s="-123"，返回-123
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book073_将整数字符串转成整数值.java
 * @type        Book073_将整数字符串转成整数值
 * @date        2016年12月17日 下午10:10:46
 * @details     
 */
public class Book073_将整数字符串转成整数值 {
	static class Solution {
		private boolean isValid(char[] cs) {
			if (cs[0] != '-' && (cs[0] < '0' || cs[0] > '9')) return false;
			if (cs[0] == '-' && (cs.length == 1 || cs[1] == '0')) return false;
			if (cs[0] == '0' && cs.length > 1) return false;
			for (int i = 1; i < cs.length; i ++)
				if (cs[i] < '0' || cs[i] > '9')
					return false;
			return true;
		}
		public int convert(String s) {
			if (s == null || s.length() == 0) return 0;
			char[] cs = s.toCharArray();
			if (! isValid(cs)) return 0;
			boolean p = cs[0] == '-' ? false : true;
			int minq = Integer.MIN_VALUE / 10;
			int minr = Integer.MIN_VALUE % 10;
			int res = 0;
			int cur = 0;
			for (int i = p ? 0 : 1 ; i < cs.length; i ++) {
				cur = '0' - cs[i];
				if (res < minq || (res == minq && cur < minr))
					return 0;
				res = res * 10 + cur;
			}
			if (p && res == Integer.MIN_VALUE) return 0;
			return p ? -res : res;
		}
	}
}
