package leetcode;


public class P009_PalindromeNumber {
	public static void main(String[] args) {
//		System.out.println(Integer.MAX_VALUE);
		System.out.println(new Solution3().isPalindrome(1221));
	}
	/*
	 * 	16 ms
	 * 	20.14%
	 * 
	 */
	static class Solution {
	    public boolean isPalindrome(int x) {
	    	char[] c = String.valueOf(x).toCharArray();
	    	int sta = 0, end = c.length - 1;
	        while (sta < end) {
	        	if (c[sta] == c[end]) {
	        		sta ++;
	        		end --;
	        	} else {
	        		return false;
	        	}
	        }
	        return true;
	    }
	}
	/*
	 * 	14 ms
	 * 	26.79%
	 */
	static class Solution2 {
		public boolean isPalindrome(int x) {
			if (x < 0)
				return false;
			int[] c = new int[x == 0 ? 1 : (int)Math.log10(x) + 1];
			for (int i = c.length - 1; i > -1; i --) {
				c[i] = x % 10;
				x = x / 10;
			}
	    	int sta = 0, end = c.length - 1;
	        while (sta < end) {
	        	if (c[sta] == c[end]) {
	        		sta ++;
	        		end --;
	        	} else {
	        		return false;
	        	}
	        }
			return true;
		}
	}
	/*
	 * 	75.99%
	 * 	11 ms
	 */
	static class Solution3 {
		public boolean isPalindrome(int x) {
			if (x < 0)
				return false;
			int[] c = new int[10];
			int i = 9;
			for (; i > -1; i --) {
				c[i] = x % 10;
				x = x / 10;
				if (x == 0)
					break;
			}
	    	int sta = i, end = 9;
	        while (sta < end) {
	        	if (c[sta] == c[end]) {
	        		sta ++;
	        		end --;
	        	} else {
	        		return false;
	        	}
	        }
			return true;
		}
	}
	
}
