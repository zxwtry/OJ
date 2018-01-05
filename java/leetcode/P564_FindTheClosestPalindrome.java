package leetcode;

import java.util.Arrays;

/**
 *  Given an integer n, find the closest integer (not including itself), which is a palindrome.

    The 'closest' is defined as absolute difference minimized between two integers.
    
    Example 1:
    Input: "123"
    Output: "121"
    Note:
    The input n is a positive integer represented by string, whose length will not exceed 18.
    If there is a tie, return the smaller one as answer.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P564_FindTheClosestPalindrome.java
 * @type        P564_FindTheClosestPalindrome
 * @date        2017年4月23日 上午10:25:25
 * @details     Solution: AC 34ms 55.56%
 */
public class P564_FindTheClosestPalindrome {
    public static void main(String[] args) {
        String[] ns = {
                "123",
                "121",
                "111",
                "101",
                "1001",
                "1111",
                "90",
                "100",
                "1000",
                "2990",
                "999",
                "3452",
                "1",
                "342",
        };
        for (String n : ns)
        System.out.println(n + "\t\t" + new Solution().nearestPalindromic(n));
    }
    static class Solution {
        public String nearestPalindromic(String n) {
            long nv = Long.parseLong(n);
            long bv = bigger(nv+1);
            long sv = smaller(nv-1);
            return Long.toString(bv-nv >= nv-sv ? sv : bv);
        }
        private long smaller(long v) {
            String s = Long.toString(v);
            char[] c = s.toCharArray();
            int n = c.length;
            setHalf(c, n);
            for (int i = n/2; i < n; i ++) {
                int cut = s.charAt(i) - c[i];
                if (cut > 0) {
                    return Long.parseLong(new String(c));
                } else if (cut < 0) {
                    for (int j = (n-1)/2; j >= 0; j --) {
                        c[j] --;
                        if (c[j] < '0') c[j] = '9';
                        else break;
                    }
                    if (c[0] == '0') {
                        if (n == 1) return 0l;
                        c = new char[n-1];
                        Arrays.fill(c, '9');
                        return Long.parseLong(new String(c));
                    }
                    setHalf(c, n);
                    return Long.parseLong(new String(c));
                }
            }
            return v;
        }
        private long bigger(long v) {
            String s = Long.toString(v);
            char[] c = s.toCharArray();
            int n = c.length;
            setHalf(c, n);
            for (int i = n/2; i < n; i ++) {
                int cut = s.charAt(i) - c[i];
                if (cut < 0) {
                    return Long.parseLong(new String(c));
                } else if (cut > 0) {
                    for (int j = (n-1)/2; j >= 0; j --) {
                        c[j] ++;
                        if (c[j] > '9') c[j] = '0';
                        else break;
                    }
                    setHalf(c, n);
                    return Long.parseLong(new String(c));
                }
            }
            return v;
        }
        private void setHalf(char[] c, int n) {
            int i = 0, j = n-1;
            while (i < j) {
                c[j] = c[i];
                i ++;
                j --;
            }
        }
    }
}
