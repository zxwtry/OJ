package nowcoder.zuo;

/**
 * 	给定一个字符串s，如果可以在s的任何为止添加字符，
 * 	要使s整体回文，且添加最少字符。
 * 	举例：
 * 		s="ABA"，返回"ABA"
 * 		s="AB"，返回"ABA"、"BAB"都行
 * 
 * 	进阶：
 * 		给定一个字符串s，给定s的最长回文子序列字符串p
 * 		返回添加字符最少的情况下，让s整体回文。
 * 		举例：
 * 			s="A1B21C"，p="121"
 * 			返回"AC1B2B1CA"或"CA1B2B1AC"都行
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book077_添加最少字符使字符串整体是回文串.java
 * @type        Book077_添加最少字符使字符串整体是回文串
 * @date        2016年12月20日 上午10:33:20
 * @details     Solution1原问题
 * @details     Solution2进阶问题
 */
public class Book077_添加最少字符使字符串整体是回文串 {
	static class Solution1 {
		public String getPalindrome(String s) {
			if (s == null || s.length() < 2) return s;
			char[] c = s.toCharArray();
			int[][] dp = getDP(c);
			char[] a = new char[c.length + dp[0][c.length - 1]];
			int i = 0;
			int j = c.length - 1;
			int al = 0;
			int ar = a.length - 1;
			while (i <= j) {
				if (c[i] == c[j]) {
					a[al++] = c[i++];
					a[ar--] = c[j--];
				} else if (dp[i][j-1] < dp[i+1][j]) {
					a[al++] = c[j];
					a[ar--] = c[j--];
				} else {
					a[al++] = c[i];
					a[ar--] = c[i++];
				}
			}
			return new String(a);
		}
		private int[][] getDP(char[] c) {
			int[][] dp = new int[c.length][c.length];
			for (int j = 1; j < c.length; j ++) {
				dp[j-1][j] = c[j-1] == c[j] ? 0 : 1;
				for (int i = j -2; i > -1; i --) {
					dp[i][j] = c[i] == c[j] ? dp[i+1][j-1] : Math.min(dp[i+1][j], dp[i][j-1])+1;
				}
			}
			return dp;
		}
	}
	static class Solution2 {
		public String getPalindrome(String s, String p) {
			if (s == null || s.length() == 0) return "";
			char[] cs = s.toCharArray();
			char[] cp = p.toCharArray();
			char[] a = new char[2 * cs.length - cp.length];
			int csl = 0;
			int csr = cs.length - 1;
			int cpl = 0;
			int cpr = cp.length - 1;
			int al = 0;
			int ar = a.length - 1;
			int tl = 0;
			int tr = 0;
			while (cpl <= cpr) {
				tl = csl;
				tr = csr;
				while (cs[csl] != cp[cpl]) csl ++;
				while (cs[csr] != cp[cpr]) csr --;
				set(a, al, ar, cs, tl, csl, csr, tr);
				al += csl - tl + tr - csr;
				ar -= csl - tl + tr - csr;
				a[al ++] = cs[csl ++];
				a[ar --] = cs[csr --];
				cpl ++;
				cpr --;
			}
			return new String(a);
		}
		private void set(char[] a, int al, int ar, char[] cs, int ls, int le, int as, int ae) {
			for (int i = ls; i < le; i ++) {
				a[al ++] = cs[i];
				a[ar --] = cs[i];
			}
			for (int i = ae; i > as; i --) {
				a[al++] = cs[i];
				a[ar--] = cs[i];
			}
		}
	}
}
