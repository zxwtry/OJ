package template;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_String_Manacher.java
 * @type        RECITE_String_Manacher
 * @date        2016年12月7日 上午11:47:10
 * @details     https://leetcode.com/problems/longest-palindromic-substring/
 */
public class RECITE_String_Manacher {
	static class Solution {
		char[] m = null;
		int[] r = null;
		int mei = -1;
		final char sign = '#';
		public String longestPalindrome(String s) {
			init(s);
			int[] is = new int[] {-1, -1};
			manacher(is);
			char[] ans = new char[(is[1] - is[0]) / 2];
			int ai = -1;
			for (int mi = is[0]; mi <= is[1]; mi ++) {
				if (mi % 2 == 1)
					ans[++ ai] = m[mi];
			}
			return new String(ans);
		}
		private void manacher(int[] is) {
			int mti = 0; 		//maxTouchedIndex
			int lci = 0;		//lastCircleIndex
			int mpl = 0;		//maxPalindromeIndex
			int mis = 0;		//mi symmetry mi关于lci的对称点
			for (int mi = 0; mi <= mei; mi ++) {
				if (mti >= mei) break;
				mis = 2 * lci - mi;
				if (mi >= mti || r[mis] + mi == mti) {
					int li = mi, ri = mi;
					while (li-1 > -1 && ri+1 <= mei && m[li-1] == m[ri+1]) {
						li --;
						ri ++;
					}
					mti = ri;
					lci = mi;
					r[mi] = (ri - li) / 2;
					if (mpl < r[mi]) {
						mpl = r[mi];
						is[0] = li;
						is[1] = ri;
					}
				} else if (r[mis] + mi < mti) {
					r[mi] = r[mis];
				} else {
					r[mi] = mti - mi;
				}
			}
		}
		private void init(String s) {
			m = new char[s.length() * 2 + 1];
			r = new int[m.length];
			for (int si = 0; si < s.length(); si ++) {
				m[++ mei] = sign;
				m[++ mei] = s.charAt(si);
			}
			m[++ mei] = sign;
		}
	}
}
