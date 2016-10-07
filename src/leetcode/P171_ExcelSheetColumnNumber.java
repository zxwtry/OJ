package leetcode;

public class P171_ExcelSheetColumnNumber {
	public static void main(String[] args) {
		int count = 0;
		for (int i = 0; i < 9000; i ++) {
			P168_ExcelSheetColumnTitle.Solution s1 = new P168_ExcelSheetColumnTitle.Solution();
			int input = (int) ( Math.random() * 100000 );
			String inputString = s1.convertToTitle(input);
			int ans = new Solution().titleToNumber(inputString);
			if (input != ans) {
				count ++;
			}
		}
		System.out.println(count);
	}
	/*
	 * 	2 ms 54.85%
	 */
	static class Solution {
	    public int titleToNumber(String s) {
	    	int ans = 0;
	    	for (int i = 0; i < s.length() ; i ++) {
	    		char c = s.charAt(i);
	    		ans = ans * 26 + (c - 'A' + 1);
	    	}
	        return ans;
	    }
	}
}
