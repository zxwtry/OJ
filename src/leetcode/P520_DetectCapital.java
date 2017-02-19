package leetcode;

/**
 *  Given a word, you need to judge whether the usage of capitals in it is right or not.
 *
 *  We define the usage of capitals in a word to be right when one of the following cases holds:
 *  
 *  All letters in this word are capitals, like "USA".
 *  All letters in this word are not capitals, like "leetcode".
 *  Only the first letter in this word is capital if it has more than one letter, like "Google".
 *  Otherwise, we define that this word doesn't use capitals in a right way.
 *  Example 1:
 *  Input: "USA"
 *  Output: True
 *  Example 2:
 *  Input: "FlaG"
 *  Output: False
 *  Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P520_DetectCapital.java
 * @type        P520_DetectCapital
 * @date        2017年2月19日 上午10:32:09
 * @details     Solution1: AC 37 ms
 */
public class P520_DetectCapital {
    static class Solution1 {
        public boolean detectCapitalUse(String word) {
            boolean allUpperFlag = true;
            boolean allLowerFlag = true;
            boolean firstUpperLaterLowerFlag = true;
            for (int index = 0; index < word.length(); index ++) {
                char c = word.charAt(index);
                boolean isUpper = isUpper(c);
                if (index == 0) {
                    firstUpperLaterLowerFlag &= isUpper;
                } else {
                    firstUpperLaterLowerFlag &= ! isUpper;
                }
                allUpperFlag &= isUpper;
                allLowerFlag &= !isUpper;
                if (allUpperFlag || allLowerFlag || firstUpperLaterLowerFlag) {}
                else return false;
            }
            return true;
        }
        private boolean isUpper(char c) {
            return c >= 'A' && c <= 'Z';
        }
    }
}
