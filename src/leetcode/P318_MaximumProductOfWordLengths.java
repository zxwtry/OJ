package leetcode;

/**
 * 	Given a string array words, find the maximum value of 
 * 	length(word[i]) * length(word[j]) where the two words 
 * 	do not share common letters. You may assume that each 
 * 	word will contain only lower case letters. If no such 
 * 	two words exist, return 0.
 *
 *	Example 1:
 *	Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 *	Return 16
 *	The two words can be "abcw", "xtfn".
 *	
 *	Example 2:
 *	Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 *	Return 4
 *	The two words can be "ab", "cd".
 *	
 *	Example 3:
 *	Given ["a", "aa", "aaa", "aaaa"]
 *	Return 0
 *	No such pair of words.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P318_MaximumProductOfWordLengths.java
 * @type        P318_MaximumProductOfWordLengths
 * @date        2017年1月4日 下午10:18:10
 * @details     Solution1: AC 47ms 34.38%
 * @details     Solution2: AC 30ms 74.44%
 */
public class P318_MaximumProductOfWordLengths {
	static class Solution1 {
	    public int maxProduct(String[] wordArray) {
	        int[][] wordMapArray = new int[wordArray.length][26];
	        for (int wordArrayIndex = 0; wordArrayIndex < wordArray.length; wordArrayIndex ++) {
	            String word = wordArray[wordArrayIndex];
	            int[] wordMap = wordMapArray[wordArrayIndex];
	            for (int wordIndex = word.length() - 1; wordIndex > -1; wordIndex --)
	                wordMap[word.charAt(wordIndex) - 'a'] ++;
	        }
	        int maxProduct = 0;
	        for (int wordArrayIndexRow = 0; wordArrayIndexRow < wordArray.length; wordArrayIndexRow ++) {
	            for (int wordArrayIndexCol = wordArrayIndexRow + 1; wordArrayIndexCol < wordArray.length; wordArrayIndexCol ++) {
	                int[] wordMapRow = wordMapArray[wordArrayIndexRow], wordMapCol = wordMapArray[wordArrayIndexCol];
	                boolean isNotContainsSame = true;
	                for (int wordMapIndex = 0; isNotContainsSame && wordMapIndex < 26; wordMapIndex ++)
	                    isNotContainsSame &= (wordMapRow[wordMapIndex] == 0 || wordMapCol[wordMapIndex] == 0);
	                if (isNotContainsSame) {
	                    maxProduct = Math.max(maxProduct, 
	                            wordArray[wordArrayIndexRow].length() * wordArray[wordArrayIndexCol].length());
	                }
	            }
	        }
 	        return maxProduct;
	    }
	}
	static class Solution2 {
	    public int maxProduct(String[] wordArray) {
	        int[] wordSignArray = new int[wordArray.length];
	        for (int wordArrayIndex = 0; wordArrayIndex < wordArray.length; wordArrayIndex ++) {
	            String word = wordArray[wordArrayIndex];
	            for (int wordIndex = 0; wordIndex < word.length(); wordIndex ++)
	                wordSignArray[wordArrayIndex] |= (1 << (word.charAt(wordIndex) - 'a'));
	        }
	        int maxProduct = 0;
	        for (int wordArrayIndexRow = 0; wordArrayIndexRow < wordArray.length; wordArrayIndexRow ++) {
	            for (int wordArrayIndexCol = wordArrayIndexRow + 1; wordArrayIndexCol < wordArray.length; wordArrayIndexCol ++) {
	                if ((wordSignArray[wordArrayIndexRow] & wordSignArray[wordArrayIndexCol]) == 0)
	                    maxProduct = Math.max(maxProduct, 
	                            wordArray[wordArrayIndexRow].length() *  wordArray[wordArrayIndexCol].length());
	            }
	        }
	        return maxProduct;
	    }
	}
}
