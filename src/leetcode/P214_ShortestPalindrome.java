package leetcode;

/*
 * 	Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
 * 	Find and return the shortest palindrome you can find by performing this transformation.

	For example:
	
	Given "aacecaaa", return "aaacecaaa".
	
	Given "abcd", return "dcbabcd".
 */

public class P214_ShortestPalindrome {
	public static void main(String[] args) {
		Solution s1 = new Solution();
		String s = "aabba";
		System.out.println(s1.shortestPalindrome(s));
	}
	/*
	 * 	213 ms
	 * 	33.68%
	 * 	这个时间，也是醉了。。。
	 */
	static class Solution {
		char[] cs = null;
		int len = 0;
	    public String shortestPalindrome(String s) {
	    	if (null == s || s.length() == 0) {
	    		return "";
	    	}
	    	cs = s.toCharArray();
	    	len = cs.length;
	    	int startIndexOfCopy = getMaxLenOfPalindrome();
	    	char[] cCopyed = new char[len - startIndexOfCopy];
	    	System.arraycopy(cs, startIndexOfCopy, cCopyed, 0, cCopyed.length);
	    	reverse(cCopyed);
	    	char[] ans = new char[2 * len - startIndexOfCopy];
	    	System.arraycopy(cCopyed, 0, ans, 0, cCopyed.length);
	    	System.arraycopy(cs, 0, ans, len - startIndexOfCopy, cs.length);
	    	return new String(ans);
	    }
		private void reverse(char[] cCopyed) {
			int i = 0, j = cCopyed.length - 1;
			while (i < j) {
				char temp = cCopyed[i];
				cCopyed[i] = cCopyed[j];
				cCopyed[j] = temp;
				i ++;
				j --;
			}
		}
		private int getMaxLenOfPalindrome() {
			for (int ans = cs.length - 1; ans > -1; ans --) {
				if (isPalindrome(0, ans)) {
					return ans + 1;
				}
			}
			return 1;
		}
		private boolean isPalindrome(int i, int j) {
			while (i < j) {
				if (cs[i] != cs[j]) {
					return false;
				}
				i ++;
				j --;
			}
			return true;
		}
	}
}
