package leetcode;

/*
 * 	Given a positive integer, return its corresponding column title 
 * 	as appear in an Excel sheet.

	For example:
	
	    1 -> A
	    2 -> B
	    3 -> C
	    ...
	    26 -> Z
	    27 -> AA
	    28 -> AB 
	Credits:
	Special thanks to @ifanchu for adding this problem and creating 
	all test cases.
 */

public class P168_ExcelSheetColumnTitle {
	public static void main(String[] args) {
		Solution s = new Solution();
		for (int i = 1; i < 99; i ++) {
			System.out.println(i + "..." + s.convertToTitle(i));
		}
	}
	/*
	 * 	0 ms 12.78%
	 */
	static class Solution {
	    public String convertToTitle(int n) {
	    	n --;
	    	StringBuilder st = new StringBuilder();
	    	do {
	    		st.insert(0, (char)('A' + n % 26));
	    		n = n / 26 - 1;
	    	} while (n >= 0);
	        return st.toString();
	    }
	}
}
