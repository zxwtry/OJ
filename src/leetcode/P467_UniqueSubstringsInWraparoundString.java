package leetcode;

/**
 *  Consider the string s to be the infinite wraparound string
 * 	of "abcdefghijklmnopqrstuvwxyz", so s will look like this:
 *  "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 *	
 *	Now we have another string p. Your job is to find out 
 *	how many unique non-empty substrings of p are present in s.
 *	In particular, your input is the string p and you need to
 *	output the number of different non-empty substrings of p in the string s.
 *	
 *	Note: p consists of only lowercase English letters and the size of p might be over 10000.
 *	
 *	Example 1:
 *	Input: "a"
 *	Output: 1
 *	
 *	Explanation: Only the substring "a" of string "a" is in the string s.
 *	Example 2:
 *	Input: "cac"
 *	Output: 2
 *	Explanation: There are two substrings "a", "c" of string "cac" in the string s.
 *	Example 3:
 *	Input: "zab"
 *	Output: 6
 *	Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" 
 *	of string "zab" in the string s.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P467_UniqueSubstringsInWraparoundString.java
 * @type        P467_UniqueSubstringsInWraparoundString
 * @date        2016年12月4日 下午8:29:13
 * @details     Solution1: WA
 * @details     Solution2: AC 24ms 44.69%
 * @details     Solution3: AC 35ms 11.17%
 */
public class P467_UniqueSubstringsInWraparoundString {
	static class Solution1 {
	    public int findSubstringInWraproundString(String p) {
	        boolean[] map = new boolean[26];
	        int count = 0;
	        int mapIndex = 0;
	        for (int index = p.length() - 1; index > -1; index --) {
	            mapIndex = p.charAt(index) - 'a';
	            if (! map[mapIndex]) {
	                count ++;
	                map[mapIndex] = true;
	            }
	        }
	        int answer = 1;
	        while (count > 1) {
	            answer *= count --;
	        }
	        return answer;
	    }
	}
	static class Solution2 {
	    public int findSubstringInWraproundString(String p) {
	        int[] count = new int[26];
	        int maxLengthCur = 0; 
	        for (int i = 0; i < p.length(); i++) {
	            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 
	                    || (p.charAt(i - 1) - p.charAt(i) == 25)))
	                maxLengthCur++;
	            else maxLengthCur = 1;
	            int index = p.charAt(i) - 'a';
	            count[index] = Math.max(count[index], maxLengthCur);
	        }
	        int sum = 0;
	        for (int i = 0; i < 26; i++) sum += count[i];
	        return sum;
	    }
	}
}
