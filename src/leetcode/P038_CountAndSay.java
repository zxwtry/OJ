package leetcode;

public class P038_CountAndSay {
	/*
	 * 	2 ms
	 * 	85.40%
	 */
	static class Solution {
		public String countAndSay(int n) {
			if (n < 1) {
				return null;
			}
			String result = "1";
			for (int i = 2; i <= n; i++) {
				result = countAndSay(result);
			}
			return result;
		}
		public String countAndSay(String str) {
			StringBuilder builder = new StringBuilder(128);
			int count = 1;
			for (int i = 1; i < str.length(); i++) {
				if (str.charAt(i) == str.charAt(i - 1)) {
					count++;
				} else {
					builder.append(count);
					builder.append(str.charAt(i - 1));
					count = 1;
				}
			}
			builder.append(count);
			builder.append(str.charAt(str.length() - 1));
			return builder.toString();
		}
	}
}