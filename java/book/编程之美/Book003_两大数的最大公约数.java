package book.编程之美;

/**
 * 	两个大数的最大公约数
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     book.编程之美
 * @file        Book003_两大数的最大公约数.java
 * @type        Book003_两大数的最大公约数
 * @date        2017年1月24日 下午11:35:14
 * @details     
 */
public class Book003_两大数的最大公约数 {
	static class Solution1 {
		public long gcd(long x, long y) {
			return y == 0l ? x : gcd(y, x % y);
		}
	}
	static class Solution2 {
		public long gcd(long x, long y) {
			if (x < y) return gcd(y, x);
			if (y == 0) return x;
			return gcd(y, x - y);
		}
	}
	static class Solution3 {
		public long gcd(long x, long y) {
			if (x < y) return gcd(y, x);
			if (y == 0) return x;
			if (isEven(x)) {
				if (isEven(y)) {
					return gcd(x >> 1, y >> 1) << 1;
				} else {
					return gcd(x >> 1, y);
				}
			} else {
				if (isEven(y)) {
					return gcd(x, y >> 1);
				} else {
					return gcd(y, x - y);
				}
			}
 		}
		public boolean isEven(long x) {
			return (x & 0x1) == 0;
		}
	}
	static class Solution4 {
		public long gcd(long x, long y) {
			long t = 0;
			int leftCount = 0;
			while (true) {
				if (x < y) {
					t = x;
					x = y;
					y = t;
				}
				if (y == 0) return x << leftCount;
				if (isEven(x)) {
					if (isEven(y)) {
						x = x >> 1;
						y = y >> 1;
						leftCount ++;
					} else {
						x = x >> 1;
					}
				} else {
					if (isEven(y)) {
						y = y >> 1;
					} else {
						t = x - y;
						x = y;
						y = t;
					}
				}
			}
		}
		public boolean isEven(long x) {
			return (x & 0x1) == 0;
		}
	}
}
