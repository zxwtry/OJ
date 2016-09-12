package leetcode;

import java.util.HashMap;

/*
 * 	87. Scramble String  QuestionEditorial Solution  My Submissions
	Total Accepted: 52267
	Total Submissions: 189615
	Difficulty: Hard
	Given a string s1, we may represent it as a binary tree
	 by partitioning it to two non-empty substrings recursively.
	
	Below is one possible representation of s1 = "great":
	
	    great
	   /    \
	  gr    eat
	 / \    /  \
	g   r  e   at
	           / \
	          a   t
	To scramble the string, we may choose any non-leaf node and 
	swap its two children.
	
	For example, if we choose the node "gr" and swap its two children, 
	it produces a scrambled string "rgeat".
	
	    rgeat
	   /    \
	  rg    eat
	 / \    /  \
	r   g  e   at
	           / \
	          a   t
	We say that "rgeat" is a scrambled string of "great".
	
	Similarly, if we continue to swap the children of nodes "eat" and "at",
	 it produces a scrambled string "rgtae".
	
	    rgtae
	   /    \
	  rg    tae
	 / \    /  \
	r   g  ta  e
	       / \
	      t   a
	We say that "rgtae" is a scrambled string of "great".
	
	Given two strings s1 and s2 of the same length, 
	determine if s2 is a scrambled string of s1.
 */

/*
 * 	两个相邻交换：
 * 	abcde
 * 	bacde
 * 	acbde
 * 	abdce
 * 	abced
 * 	
 * 	
 */

public class P087_ScrambleString {
	public static void main(String[] args) {
		System.out.println(new Solution2().isScramble("abb", "bba"));
		System.out.println(new Solution2().isScramble("abb", "bab"));
		System.out.println(new Solution2().isScramble("abb", "abb"));
		System.out.println(new Solution2().isScramble("rea", "rae"));
		System.out.println(new Solution2().isScramble("great", "traeg"));
		System.out.println(new Solution2().isScramble("great", "ragte"));
		System.out.println(new Solution2().isScramble("hobobyrqd", "hbyorqdbo"));
		System.out.println(new Solution2().isScramble("obyrqd", "yorqdb"));
		System.out.println(new Solution2().isScramble("obym", "yomb"));
	}

	/*
	 * WA
	 */
	static class Solution {
		char[] c1 = null, c2 = null;
		HashMap<Character, Integer> m3 = new HashMap<>();

		public boolean isScramble(String s1, String s2) {
			if (s1 == null || s2 == null)
				return s1 == s2;
			if (s1.length() != s2.length())
				return false;
			if (s1.length() == 0)
				return true;
			c1 = s1.toCharArray();
			c2 = s2.toCharArray();
			return judge(0, c1.length - 1, 0, c2.length - 1);
		}

		private boolean judge(int sti1, int eni1, int sti2, int eni2) {
			if (sti1 - eni1 != sti2 - eni2)
				return false;
			if (sti1 > eni1) {
				return false;
			} else if (sti1 == eni1) {
				return c1[sti1] == c2[sti2];
			} else if (sti1 + 1 == eni1) {
				return (c1[sti1] == c2[sti2] && c1[eni1] == c2[eni2]) || (c1[sti1] == c2[eni2] && c1[eni1] == c2[sti2]);
			}
			m3.clear();
			int i = eni1 - sti1;
			for (; i > -1; i--) {
				char c = c1[sti1 + i];
				if (m3.containsKey(c)) {
					int c1_val = m3.get(c);
					if (c1_val == -1) {
						m3.remove(c);
					} else {
						m3.put(c, c1_val + 1);
					}
				} else {
					m3.put(c, 1);
				}
				c = c2[sti2 + i];
				if (m3.containsKey(c)) {
					int c2_val = m3.get(c);
					if (c2_val == 1) {
						m3.remove(c);
					} else {
						m3.put(c, c2_val - 1);
					}
				} else {
					m3.put(c, -1);
				}
				if (m3.size() == 0)
					break;
			}
			if (-1 == i)
				return false;
			if (0 == i) {
				m3.clear();
				int j = 0;
				for (; j <= eni1 - sti1; j++) {
					char c = c1[sti1 + j];
					if (m3.containsKey(c)) {
						int c1_val = m3.get(c);
						if (c1_val == -1) {
							m3.remove(c);
						} else {
							m3.put(c, c1_val + 1);
						}
					} else {
						m3.put(c, 1);
					}
					c = c2[sti2 - j + eni1 - sti1];
					if (m3.containsKey(c)) {
						int c2_val = m3.get(c);
						if (c2_val == 1) {
							m3.remove(c);
						} else {
							m3.put(c, c2_val - 1);
						}
					} else {
						m3.put(c, -1);
					}
					if (m3.size() == 0)
						break;
				}
				if (j > eni1 - sti1) {
					return false;
				} else if (j == eni1 - sti1) {
					if (c1[sti1] != 'o' || c2[sti2] != 'y')
						return false;
					char temp = c1[sti1];
					c1[sti1] = c1[eni1];
					c1[eni1] = temp;
					return judge(sti1, eni1, sti2, eni2);
				}
				return judge(sti1, sti1 + j, eni2 - j, eni2) && judge(sti1 + j + 1, eni1, sti2, eni2 - j - 1);
			}
			return judge(sti1 + i, eni1, sti2 + i, eni2) && judge(sti1, sti1 + i - 1, sti2, sti2 + i - 1);
		}
	}

	/*
	 * WA
	 */
	static class Solution2 {
		char[] c1 = null, c2 = null;
		HashMap<Character, Integer> map = new HashMap<>();

		public boolean isScramble(String s1, String s2) {
			if (s1 == null || s2 == null)
				return s1 == s2;
			if (s1.length() != s2.length())
				return false;
			if (s1.length() == 0)
				return true;
			c1 = s1.toCharArray();
			c2 = s2.toCharArray();
			return judge(0, c1.length - 1, 0, c2.length - 1);
		}

		private boolean judge(int sti1, int eni1, int sti2, int eni2) {
			if (sti1 - eni1 != sti2 - eni2)
				return false;
			if (sti1 > eni1) {
				return false;
			} else if (sti1 == eni1) {
				return c1[sti1] == c2[sti2];
			} else if (sti1 + 1 == eni1) {
				return (c1[sti1] == c2[sti2] && c1[eni1] == c2[eni2]) || (c1[sti1] == c2[eni2] && c1[eni1] == c2[sti2]);
			}
			map.clear();
			int i = eni1 - sti1;
			for (; i > -1; i--) {
				char c = c1[sti1 + i];
				if (map.containsKey(c)) {
					int c1_val = map.get(c);
					if (c1_val == -1) {
						map.remove(c);
					} else {
						map.put(c, c1_val + 1);
					}
				} else {
					map.put(c, 1);
				}
				c = c2[sti2 + i];
				if (map.containsKey(c)) {
					int c2_val = map.get(c);
					if (c2_val == 1) {
						map.remove(c);
					} else {
						map.put(c, c2_val - 1);
					}
				} else {
					map.put(c, -1);
				}
			}
			if (map.size() != 0)
				return false;
			for (int j = 0; j < eni1 - sti1; j++) {
				if ((judge(sti1, sti1 + j, sti2, sti2 + j) && judge(sti1 + j + 1, eni1, sti2 + j + 1, eni2))
						|| (judge(sti1, sti1 + j, eni2 - j, eni2) && judge(eni1 - j, eni1, sti2, sti2 + j)))
					return true;
			}
			return false;
		}
	}

	/*
	 * 	AC
	 * 	23 ms
	 */
	static class Solution3 {
		public boolean isScramble(String s1, String s2) {
			int len = s1.length();
			if (len != s2.length()) {
				return false;
			}
			if (len == 0) {
				return true;
			}

			char[] c1 = s1.toCharArray();
			char[] c2 = s2.toCharArray();

			boolean[][][] result = new boolean[len][len][len];
			for (int i = 0; i < len; ++i) {
				for (int j = 0; j < len; ++j) {
					result[0][i][j] = (c1[i] == c2[j]);
				}
			}

			for (int k = 2; k <= len; ++k) {
				for (int i = len - k; i >= 0; --i) {
					for (int j = len - k; j >= 0; --j) {
						boolean r = false;
						for (int m = 1; m < k && !r; ++m) {
							r = (result[m - 1][i][j] && result[k - m - 1][i + m][j + m])
									|| (result[m - 1][i][j + k - m] && result[k - m - 1][i + m][j]);
						}
						result[k - 1][i][j] = r;
					}
				}
			}

			return result[len - 1][0][0];
		}
	}
}
