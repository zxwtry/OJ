package leetcode;

/**
 *  Given a string, find the first non-repeating character in it and return it's index. 
 *  If it doesn't exist, return -1.
 *  
 *  Examples:
 *  
 *  s = "leetcode"
 *  return 0.
 *  
 *  s = "loveleetcode",
 *  return 2.
 *  Note: You may assume the string contain only lowercase letters.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P387_FirstUniqueCharacterInAString.java
 * @type        P387_FirstUniqueCharacterInAString
 * @date        2017年3月16日 下午8:25:02
 * @details     Solution1: AC 37ms 42.43%
 */
public class P387_FirstUniqueCharacterInAString {
    static class Solution1 {
        public int firstUniqChar(String s) {
            int[] map = new int[26];
            int n = s.length();
            for (int i = 0; i < n; i ++)
                map[s.charAt(i) - 'a'] ++;
            for (int i = 0; i < n; i ++)
                if (map[s.charAt(i) - 'a'] > 1)
                    return i;
            return -1;
        }
    }
}
