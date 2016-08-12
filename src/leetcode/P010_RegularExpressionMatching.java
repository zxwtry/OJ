package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;


public class P010_RegularExpressionMatching {
	public static void main(String[] args) {
		System.out.println("abcd*".indexOf("."));
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
			for (int i = lenp - 1; i != 0; i --) {
				char c = p.charAt(i);
				if (c == '.') {
					map[i] = last_star ? dot_star_sign : dot_sign;
					dot_count ++;
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
				int j = 0, iend = zip_count.size();
				for (int i = 0; i != iend; i ++) {
					switch()
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
}
