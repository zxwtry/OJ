package leetcode;

/**
 * 	Now you are given a string S, which represents a software license key which 
 * 	we would like to format. The string S is composed of alphanumerical characters 
 * 	and dashes. The dashes split the alphanumerical characters within the string into groups. 
 * 	(i.e. if there are M dashes, the string is split into M+1 groups). 
 * 	The dashes in the given string are possibly misplaced.

	We want each group of characters to be of length K (except for possibly the first group, 
	which could be shorter, but still must contain at least one character). 
	To satisfy this requirement, we will reinsert dashes. Additionally, 
	all the lower case letters in the string must be converted to upper case.
	
	So, you are given a non-empty string S, representing a license key to format, 
	and an integer K. And you need to return the license key formatted according to 
	the description above.
	
	Example 1:
	Input: S = "2-4A0r7-4k", K = 4
	
	Output: "24A0-R74K"
	
	Explanation: The string S has been split into two parts, each part has 4 characters.
	Example 2:
	Input: S = "2-4A0r7-4k", K = 3
	
	Output: "24-A0R-74K"
	
	Explanation: The string S has been split into three parts, each part has 3 characters 
	except the first part as it could be shorter as said above.
	Note:
	The length of string S will not exceed 12,000, and K is a positive integer.
	String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
	String S is non-empty.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P482_LicenseKeyFormatting.java
 * @type        P482_LicenseKeyFormatting
 * @date        2017年1月8日 下午4:59:02
 * @details     Solution: AC 10ms
 */
public class P482_LicenseKeyFormatting {
	static class Solution {
	    public String licenseKeyFormatting(String s, int k) {
	    	if (s == null || s.length() < 1 || k < 1) return "";
	    	int len = 0;
	    	for (int i = s.length()-1; i > -1; i --)
	    		len += s.charAt(i) == '-' ? 0 : 1;
	    	char[] arr = new char[len + (len-1)/k];
	    	int j = s.length() - 1;
	    	int count = 0;
	    	for (int i = arr.length - 1; i > -1; i --) {
	    		if (count == k) {
	    			arr[i] = '-';
	    			count = 0;
	    		} else {
	    			while (s.charAt(j) == '-') j --;
	    			char c = s.charAt(j --);
	    			arr[i] = c >= 'a' ? (char)(c-'a'+'A') : c;
	    			count ++;
	    		}
	    	}
	    	return new String(arr);
	    }
	}
}
