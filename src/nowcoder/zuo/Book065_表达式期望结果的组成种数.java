package nowcoder.zuo;

/**
 * 	给定一个只由0(false), 1(true), &(逻辑与), |(逻辑或)和^(异或)
 * 	五种字符组成的字符串e，再给定一个布尔值d。返回e能够有多少种组合方式达到d的结果。
 * 	举例：
 * 		e="1^0|0|1" d=false
 * 		只由1^((0|0)|1)和1^(0|(0|1))的组合可以得到false，返回 2
 * 		e="1" d=false
 * 		没有组合可以得到false，返回0
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book065_表达式期望结果的组成种数.java
 * @type        Book065_表达式期望结果的组成种数
 * @date        2016年12月15日 下午10:05:10
 * @details     
 */
public class Book065_表达式期望结果的组成种数 {
	public static void main(String[] args) {
	}
	static class Solution1 {
		public boolean isValid(String e) {
			if ((e.length() & 0x1) == 0) return false;
			for (int i = 0; i < e.length(); i += 2)
				if (e.charAt(i) != '1' && e.charAt(i) != '0') return false;
			for (int i = 1; i < e.length(); i += 2)
				if (e.charAt(i) != '&' && e.charAt(i) != '|' && e.charAt(i) != '^') return false;
			return true;
		}
		public int num(String e, boolean d) {
			if (e == null || e.length() == 0) return 0;
			if (! isValid(e)) return 0;
			return p(e, d, 0, e.length()-1);
		}
		private int p(String e, boolean d, int l, int r) {
			if (l == r) {
				if (e.charAt(l) == '1')
					return d ? 1 : 0;
				else
					return d ? 0 : 1;
			}
			int res = 0;
			if (d) {
				for (int i = l+1; i < r; i += 2) {
					switch (e.charAt(i)) {
					case '&':
						res += p(e, true, l, i-1) * p(e, true, i+1, r);
						break;
					case '|':
						res += p(e, true, l, i-1) * p(e, false, i+1, r);
						res += p(e, false, l, i-1) * p(e, true, i+1, r);
						res += p(e, true, l, i-1) * p(e, true, i+1, r);
						break;
					case '^':
						res += p(e, true, l, i-1) * p(e, false, i+1, r);
						res += p(e, false, l, i-1) * p(e, true, i+1, r);
						break;
					}
				}
			} else {
				for (int i = l+1; i < r; i += 2) {
					switch (e.charAt(i)) {
					case '&':
						res += p(e, false, l, i-1) * p(e, true, i+1, r);
						res += p(e, true, l, i-1) * p(e, false, i+1, r);
						res += p(e, false, l, i-1) * p(e, false, i+1, r);
						break;
					case '|':
						res += p(e, false, l, i-1) * p(e, false, i+1, r);
						break;
					case '^':
						res += p(e, true, l, i-1) * p(e, true, i+1, r);
						res += p(e, false, l, i-1) * p(e, false, i+1, r);
						break;
					}
				}
			}
			return res;
		}
	}
}
