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
		Solution3 s2 = new Solution3();
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(1000, 0, 26);
		char[] cArr = new char[arr.length];
		for (int i = 0; i < arr.length; i ++) {
			cArr[i] = (char) ('a' + arr[i]);
		}
		String s = new String(cArr);
		String ans1 = s1.shortestPalindrome(s);
		String ans2 = s2.shortestPalindrome(s);
		System.out.println(ans1.equals(ans2));
//		Solution3 s3 = new Solution3();
//		System.out.println(s3.shortestPalindrome("aabaa"));
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
	/*
	 * 	减少次数呗
	 * 	204 ms
	 * 	34.30% 
	 */
	static class Solution2 {
		char[] cs = null;
		int len = 0;
	    public String shortestPalindrome(String s) {
	    	if (null == s || s.length() == 0) {
	    		return "";
	    	}
	    	cs = s.toCharArray();
	    	len = cs.length;
	    	int startIndexOfCopy = getMaxLenOfPalindrome();
	    	System.out.println(startIndexOfCopy);
	    	System.out.println(len);
	    	char[] ans = new char[2 * len - startIndexOfCopy];
	    	System.arraycopy(cs, 0, ans, len - startIndexOfCopy, cs.length);
	    	for (int i = len - startIndexOfCopy - 1; i > -1; i --) {
	    		ans[i] = cs[len - 1 - i];
	    	}
	    	return new String(ans);
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
	
	/*
	 * 	还是没有将Manacher的思想用上。。。
	 * 	其实没必要构造填充式
	 * 	总是TLE
	 */
	static class Solution3 {
		final char SHARP = '#';
		char[] cs = null;
		int originLength = 0;
		int manacherLength = 0;
	    public String shortestPalindrome(String s) {
	    	if (null == s || s.length() == 0) {
	    		return "";
	    	}
	    	originLength = s.length();
	    	manacherLength = getManacherIndexFromOriginIndex(originLength);
	    	cs = new char[manacherLength];
	    	for (int i = 0; i < manacherLength; i ++) {
	    		cs[i] = i % 2 == 0 ? SHARP : s.charAt(getOriginIndexFromManacherIndex(i));
	    	}
	    	int startIndexOfCopy = getMaxLenOfPalindrome();
	    	if (startIndexOfCopy == originLength) {
	    		return s;
	    	}
	    	char[] ans = new char[2 * originLength - startIndexOfCopy];
	    	for (int i = 0; i < originLength; i ++) {
	    		ans[originLength - startIndexOfCopy + i] = cs[getManacherIndexFromOriginIndex(i)];
	    	}
	    	for (int i = originLength - startIndexOfCopy - 1; i > -1; i --) {
	    		ans[i] = cs[getManacherIndexFromOriginIndex(originLength - 1 - i)];
	    	}
	    	return new String(ans);
	    }
		int getOriginIndexFromManacherIndex(int manacherIndex) {
			return (manacherIndex - 1) / 2;
		}
		int getManacherIndexFromOriginIndex(int originIndex) {
			return originIndex * 2 + 1;
		}
		int getMaxLenOfPalindrome() {
			int ans = 0;
			int[] lengthOfExpand = new int[manacherLength];
			int pr = 0, preIndex = 0;
			for (int index = 1; index < manacherLength; index ++) {
				if (pr > manacherLength - 2) {
					break;
				}
				if (index >= pr) {
					int l = index, r = index;
					while (-- l > -1 && ++ r < manacherLength && cs[l] == cs[r]) {}
					lengthOfExpand[index] = (r - l - 1) / 2;
					preIndex = index;
				} else {
					int mirrorIndex = preIndex * 2 - index;
					int circleRight = lengthOfExpand[mirrorIndex] + index;
					if (circleRight == manacherLength - 1) {
						int l = index, r = index;
						while (-- l > -1 && ++ r < manacherLength && cs[l] == cs[r]) {}
						lengthOfExpand[index] = (r - l - 1) / 2;
						preIndex = index;
					} else if (circleRight < manacherLength - 1) {
						lengthOfExpand[index] = circleRight - index;
					} else {
						lengthOfExpand[index] = pr - index;
					}
				}
				if (lengthOfExpand[index] == index) {
					ans = Math.max(ans, index);
				}
			}
			return ans;
		}
	}
}
