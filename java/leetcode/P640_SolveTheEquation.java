package leetcode;

/**

Solve a given equation and return the value of x in the form of string "x=#value". 
The equation contains only '+', '-' operation, the variable x and its coefficient.

If there is no solution for the equation, return "No solution".

If there are infinite solutions for the equation, return "Infinite solutions".

If there is exactly one solution for the equation, we ensure that the value of x 
is an integer.

Example 1:
Input: "x+5-3+x=6+x-2"
Output: "x=2"
Example 2:
Input: "x=x"
Output: "Infinite solutions"
Example 3:
Input: "2x=x"
Output: "x=0"
Example 4:
Input: "2x+3x-6x=x+2"
Output: "x=-1"
Example 5:
Input: "x=x+2"
Output: "No solution"

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P640_SolveTheEquation.java
 * @date        2017年7月9日 上午9:37:30
 * @details     Solution AC
 */
public class P640_SolveTheEquation {
    static public class Solution {
        final String NO_SOLUTION = "No solution";
        final String INF_SOLUTION = "Infinite solutions";
        public String solveEquation(String s) {
            int sn = s == null ? 0 : s.length();
            if (sn == 0) return NO_SOLUTION;
            int indexOfE = s.indexOf('=');
            long[] l = solve(s, 0, indexOfE - 1);
            long[] r = solve(s, indexOfE + 1, sn - 1);
            if (l == null || r == null) return NO_SOLUTION;
            if (l[0] == r[0] && l[1] == r[1]) return INF_SOLUTION;
            if (l[0] == r[0] && l[1] != r[1]) return NO_SOLUTION;
            return "x="+((r[1] - l[1]) / (l[0] - r[0]));
        }
        long[] solve(String s, int i, int j) {
            boolean isN = false;
            long[] ans = new long[] {0, 0};
            long v = 0;
            boolean cv = false;
            boolean isX = false;
            for (int k = i; k <= j; k ++) {
                char c = s.charAt(k);
                if (c == '-') {
                    if (! isX) ans[1] += isN ? -v : v;
                    isN = true;
                    v = 0;
                    cv = false;
                    isX = false;
                } else if (c == '+') {
                    if (! isX) ans[1] += isN ? -v : v;
                    isN = false;
                    v = 0;
                    cv = false;
                    isX = false;
                } else if (c >= '0' && c <= '9') {
                    v = v * 10 + c - '0';
                    cv = true;
                } else if (c == 'x') {
                    if (v == 0) v = cv ? 0 : (isN ? -1 : 1);
                    else v = isN ? -v : v;
                    ans[0] += v;
                    isX = true;
                }
            }
            if (! isX) ans[1] += isN ? -v : v;
            return ans;
        }
    }
}

