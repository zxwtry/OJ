package nowcoder.zuo;

/**
 * 	[题目]
 * 	定一个回文数：
 * 	如果一个数的绝对值是回文数，那么这个数是回文数。
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book126_判断一个数是否是回文数.java
 * @type        Book126_判断一个数是否是回文数
 * @date        2017年1月8日 下午10:14:59
 * @details     
 */
public class Book126_判断一个数是否是回文数 {
	static class Solution1 {
		public boolean isPalindrome(int n) {
			char[] c = String.valueOf(n).toCharArray();
			int sti = 0, eni = c.length - 1;
			if (c[sti] == '-') sti ++;
			while (sti < eni) {
				if (c[sti] != c[eni])
					return false;
				sti ++;
				eni --;
			}
			return true;
		}
	}
	static class Solution2 {
		public boolean isPalindrome(int n) {
			if (n == Integer.MIN_VALUE) return false;
			n = Math.abs(n);
			int h = 1;
			while (n / h >= 10)
				h *= 10;
			while (n != 0) {
				if (n / h != n % 10)
					return false;
				n = (n % h) / 10;
				h /= 100;
			}
			return true;
		}
	}
}
