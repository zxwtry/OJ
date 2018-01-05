package leetcode;

/*
 * 	13. Roman to Integer  QuestionEditorial Solution  My Submissions
	Total Accepted: 99701
	Total Submissions: 240765
	Difficulty: Easy
	Given a roman numeral, convert it to an integer.
	Input is guaranteed to be within the range from 1 to 3999.
 */

public class P013_RomanToInteger {
	public static void main(String[] args) {
//		System.out.println(new Solution().romanToInt("I"));
//		System.out.println(new Solution().romanToInt("XI"));
//		System.out.println(new Solution().romanToInt("CXI"));
//		System.out.println(new Solution().romanToInt("MCXI"));
//		System.out.println(new Solution().romanToInt("MCMXCVI"));
	}
	/*
	 * 	7 ms
	 * 	81.19%
	 */
	static class Solution {
	    public int romanToInt(String s) {
	        int ans = 0, pre = 1;
	        for (int i = s.length() - 1; i != -1; i --) {
	        	char c = s.charAt(i);
	        	switch(c) {
	        	case 'I':
	        		ans += pre > 1 ? -1 : 1;
	        		pre = 1;
	        		break;
	        	case 'V':
	        		ans += pre > 5 ? -5 : 5;
	        		pre = 5;
	        		break;
	        	case 'X':
	        		ans += pre > 10 ? -10 : 10;
	        		pre = 10;
	        		break;
	        	case 'L':
	        		ans += pre > 50 ? -50 : 50;
	        		pre = 50;
	        		break;
	        	case 'C':
	        		ans += pre > 100 ? -100 : 100;
	        		pre = 100;
	        		break;
	        	case 'D':
	        		ans += pre > 500 ? -500 : 500;
	        		pre = 500;
	        		break;
	        	case 'M':
	        		ans += 1000;
	        		pre = 1000;
	        		break;
        		default:
        			break;
	        	}
	        }
	        return ans;
	    }
	}
}
