package leetcode;

public class P014_LongestCommonPrefix {
	public static void main(String[] args) {
	}
	/*
	 * 	2 ms
	 * 	65.22%
	 */
	static class Solution {
		public String longestCommonPrefix(String[] strs) {
			int minlen = Integer.MAX_VALUE, temp = 0;
			if (strs == null || strs.length == 0)
				return "";
			for (int i = 0; i != strs.length; i ++) {
				if (strs[i] == null || (temp = strs[i].length()) == 0)
					return "";
				minlen = minlen > temp ? temp : minlen;
			}
			int len = 0;
			char c = '\0';
			for (; len != minlen; len ++) {
				c = strs[0].charAt(len);
				for (int i = 1; i != strs.length; i ++) {
					if (c != strs[i].charAt(len))
						return len == 0 ? "" : strs[0].substring(0, len);
				}
			}
			return len == 0 ? "" : strs[0].substring(0, len);
		}
	}
}
