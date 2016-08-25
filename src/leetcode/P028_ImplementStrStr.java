package leetcode;

/*
 * 	Implement strStr().

	Returns the index of the first occurrence of needle in haystack,
	 or -1 if needle is not part of haystack.
 */

public class P028_ImplementStrStr {
	public static void main(String[] args) {
		System.out.println(new Solution1().strStr("abcabcabc", "cab"));
	}
	/*
	 * 	不就是KMP
	 * 	好久没有写KMP了
	 * 	4 ms
	 * 	55.58%
	 */
	static class Solution1 {
		public int strStr(String haystack, String needle) {
			if (needle == null || needle.length() == 0)
				return 0;
			if (haystack == null || haystack.length() < needle.length())
				return -1;
			char[] s = haystack.toCharArray(), p = needle.toCharArray();
			int[] next = getNext(p);
			int si = 0, pi = 0;
			while (si != s.length) {
				if (-1 == pi || p[pi] == s[si]) {
					pi ++;   si ++;
				} else {
					pi = next[pi];
				}
				if (pi == p.length)
					return si - p.length;
			}
			return -1;
		}
		public int[] getNext(char[] p) {
			int[] next = new int[p.length];
			next[0] = -1;
			int cur = 0, pre = -1;
			while (cur != p.length - 1) {
				if (pre == -1 || p[cur] == p[pre]) {
					cur ++;   pre ++;
					next[cur] = pre;
				} else
					pre = next[pre];
			}
			return next;
		}
	}
}
