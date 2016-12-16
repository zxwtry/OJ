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
		debug();
	}
	private static void debug() {
		int n = 21;
		char[] cs = new char[n];
		for (int ni = 0; ni < n; ni ++) {
			if (ni % 2 == 0) {
				cs[ni] = Math.random() > 0.5 ? '1' : '0';
			} else {
				double a = Math.random();
				if (a < 0.33) {
					cs[ni] = '&';
				} else if (a < 0.67) {
					cs[ni] = '|';
				} else {
					cs[ni] = '^';
				}
			}
		}
		String e = String.valueOf(cs);
		boolean d = false;
		Solution1 s1 = new Solution1();
		Solution2 s2 = new Solution2();
		System.out.println(e);
		System.out.println(s1.num(e, d) +"..."+ s2.num(e, d));
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
	static class Solution2 {
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
			int[][] t = new int[e.length()][e.length()];
			int[][] f = new int[e.length()][e.length()];
			t[0][0] = e.charAt(0) == '0' ? 0 : 1;
			f[0][0] = e.charAt(0) == '1' ? 0 : 1;
			for (int i = 2; i < e.length(); i += 2) {
				t[i][i] = e.charAt(i) == '0' ? 0 : 1;
				f[i][i] = e.charAt(i) == '1' ? 0 : 1;
				for (int j = i - 2; j >= 0; j -= 2) {
					for (int k = j; k < i; k += 2) {
						if (e.charAt(k+1) == '&') {
							t[j][i] += t[j][k] * t[k+2][i];
							f[j][i] += (f[j][k] + t[j][k]) * f[k+2][i] + f[j][k] * t[k+2][i];
						} else if (e.charAt(k+1) == '|') {
							t[j][i] += (f[j][k] + t[j][k]) * t[k+2][i] + t[j][k] * f[k+2][i];
							f[j][i] += f[j][k] * f[k+2][i];
						} else {
							t[j][i] += f[j][k] * t[k+2][i] + t[j][k] * f[k+2][i];
							f[j][i] += f[j][k] * f[k+2][i] + t[j][k] * t[k+2][i];
						}
					}
				}
			}
			return d ? t[0][t.length-1] : f[0][f.length-1];
		}
	}
}
