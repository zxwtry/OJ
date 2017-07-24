package leetcode;

/**

Given a string, your task is to count how many 
palindromic substrings in this string.

The substrings with different start indexes or 
end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Note:
The input string length won't exceed 1000.

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P647_PalindromicSubstrings.java
 * @date        2017年7月23日 上午10:04:29
 * @details     
 */
public class P647_PalindromicSubstrings {
    public static void main(String[] args) {
        String s = "abcdefedcba";
//        String s = "abc";
        System.out.println(new Solution().countSubstrings(s));
    }
    static public class Solution {
        public int countSubstrings(String s) {
            int[] m = manacher(s);
//            tools.Utils.printArray(m, m.length, 5);
            int ans = 0;
            for (int v : m) {
                ans += (v + 1) / 2;
            }
            return ans;
        }
        int[] manacher(String s) {
            int sn = 2 * (s == null ? 0 : s.length()) + 1;
            int[] m = new int[sn];
            int ti = 0, ci = 0, mi = 0, li = 0, ri = 0;
            for (int i = 0; i < sn; i ++) {
                mi = 2 * ci - i;
                if (i >= ti || m[mi] + i == ti) {
                    li = ri = i;
                    while (li-1 > -1 && ri+1 < sn && access(s, li-1) == access(s, ri+1)) {
                        li --;
                        ri ++;
                    }
                    ci = i;
                    ti = ri;
                    m[i] = (ri - li) / 2;
                } else m[i] = Math.min(ti - i, m[mi]);
            }
            return m;
        }
        char access(String s, int i) {
            return i % 2 == 0 ? '#' : s.charAt(i / 2);
        }
    }
}
