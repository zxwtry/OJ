package leetcode;

import java.util.ArrayList;


public class P010_RegularExpressionMatching {
	public static void main(String[] args) {
//		System.out.println("0, true : " + new Solution().isMatch("aabb", "aab*"));
//		System.out.println("1, true : " + new Solution().isMatch("aabb", "a*bb"));
//		System.out.println("2, true : " + new Solution().isMatch("aabb", ".*bb"));
//		System.out.println("3, true : " + new Solution().isMatch("aabb", ".*"));
//		System.out.println("4, true : " + new Solution().isMatch("aabb", ".*b"));
//		System.out.println("5, true : " + new Solution().isMatch("aabb", ".*bb"));
//		System.out.println("6, false : " + new Solution().isMatch("aabb", ".*bbb"));
//		System.out.println("7, true : " + new Solution().isMatch("aabb", "a.*b"));
//		System.out.println("8, true : " + new Solution().isMatch("aabb", "a.*bb"));
//		System.out.println(new Solution2().isMatch("abc", "ab*"));
//		System.out.println(new Solution2().isMatch("abc", "ab."));
//		System.out.println(new Solution2().isMatch("abc", "a.."));
//		System.out.println(new Solution2().isMatch("abc", "..."));
//		System.out.println(new Solution2().isMatch("abc", "a.."));
//		System.out.println(new Solution2().isMatch("abc", ".*"));
		System.out.println(new Solution2().isMatch("abbbcbc", "a.*c"));
//		System.out.println(new Solution2().isMatch("abc", "..."));
	}
	static class Solution {
		private final int dot_sign = 1;
		private final int star_sign = 2;
		private final int dot_star_sign = 3;
		private final int common_star_sign = 4;
		private final int common_sign = 0;
		public boolean isMatch(String s, String p) {
			StringBuilder st = new StringBuilder(p);
			int lens = s.length(), lenp = st.length();
			int[] map = new int[lenp];
			int dot_count = 0, star_count = 0;
			boolean last_star = false;
			for (int i = lenp - 1; i != -1; i --) {
				char c = p.charAt(i);
				if (c == '.') {
					map[i] = last_star ? dot_star_sign : dot_sign;
					dot_count ++;
					if (dot_count == 1) {}
					last_star = false;
				} else if (c == '*') {
					map[i] = star_sign;
					star_count ++;
					last_star = true;
				} else {
					map[i] = last_star ? common_star_sign : common_sign;
					last_star = false;
				}
			}
			if (star_count == 0) {
				return isMatchOnlyDot(s, p, lens, lenp);
			} else {
				ArrayList<Character> zip_value = new ArrayList<Character>();
				ArrayList<Integer> zip_count = new ArrayList<Integer>();
				char value = s.charAt(0); int count = 1;
				for (int i = 1; i != lens; i ++) {
					char now = s.charAt(i);
					if (value == now) {
						count ++;
					} else {
						zip_value.add(value);
						zip_count.add(count);
						value = now;
						count = 1;
					}
				}
				zip_value.add(value);
				zip_count.add(count);
				/*
				 * 	null　	代表任意字符
				 * 	0		代表任意个字符
				 */
				ArrayList<Character> exp_value = new ArrayList<Character>();
				ArrayList<Integer> exp_count = new ArrayList<Integer>();
				boolean isStarEnded = true;
				for (int i = 0; i != lenp; i ++) {
					char now = p.charAt(i);
					switch (map[i]) {
					case common_sign:
						if (isStarEnded) {
							value = p.charAt(i);
							count = 1;
						} else if (value == now) {
							count ++;
						} else if (value != now) {
							exp_value.add(value);
							exp_count.add(count);
							value = now;
							count = 1;
						}
						isStarEnded = false;
						break;
					case common_star_sign:
						if (!isStarEnded) {
							exp_value.add(value);
							exp_count.add(count);
						}
						exp_value.add(now);
						exp_count.add(0);
						isStarEnded = true;
						break;
					case dot_sign:
						if (!isStarEnded) {
							exp_value.add(value);
							exp_count.add(count);
						}
						exp_value.add(null);
						exp_count.add(1);
						isStarEnded = true;
						break;
					case dot_star_sign:
						if (!isStarEnded) {
							exp_value.add(value);
							exp_count.add(count);
						}
						exp_value.add(null);
						exp_count.add(0);
						isStarEnded = true;
						break;
					default:
						break;
					}
				}
				if (!isStarEnded) {
					exp_value.add(value);
					exp_count.add(count);
				}
				int j = 0, iend = zip_count.size();
				for (int i = 0; i != iend; i ++) {
					if (exp_value.get(j) == null) {
						if (exp_count.get(j) == 1)
							j ++;
					} else {
						if (zip_value.get(i) == exp_value.get(j)) {
							if (zip_count.get(i) < exp_count.get(j))
								return false;
							else if (zip_count.get(i) == exp_count.get(j) || exp_count.get(j) == 0)
								j ++;
							else
								return false;
						} else
							return false;
					}
				}
			}
			return true;
		}
		boolean isMatchOnlyDot(String s, String p, int lens, int lenp) {
			if (lens != lenp)
				return false;
			for (int i = lens - 1; i != -1; i --) {
				char sc = s.charAt(i), pc = p.charAt(i);
				if (pc != '.' && sc != pc) {
					return false;
				}
			}
			return true;
		}
	}
	/*
	 * 	33.84% 
	 * 	97ms
	 */
	static class Solution2 {
		public boolean isMatch(String s, String p) {
			if (s == null)
				return p == null;
			if (p == null)
				return s == null;
			return isMatch(s, 0, s.length(), p, 0, p.length());
		}
		private boolean isMatch(String s, int i, int I, String p, int j, int J) {
			if (getChar(p, j, J) == '\0')	return getChar(s, i, I) == '\0';
			if (getChar(p, j + 1, J) == '*') {
				while (getChar(s, i, I) == getChar(p, j, J) || (getChar(p, j, J) == '.' && getChar(s, i, I) != '\0')) {
					if (isMatch(s, i ++, I, p, j + 2, J))
						return true;
				}
				return isMatch(s, i, I, p, j + 2, J);
			} else {
				if (getChar(s, i, I) == getChar(p, j, J) || (getChar(p, j, J) == '.' && getChar(s, i, I) != '\0'))
					return isMatch(s, i + 1, I, p, j + 1, J);
				return false;
			}
		}
		private char getChar(String str, int i, int I) {
			if (i >= I)
				return '\0';
			else
				return str.charAt(i);
		}
	}
}
