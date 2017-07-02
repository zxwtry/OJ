package leetcode;

/**

Given a non-negative integer c, your task is to decide whether 
there're two integers a and b such that a2 + b2 = c.

Example 1:
Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
Example 2:
Input: 3
Output: False

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P633_SumOfSquareNumbers.java
 * @date        2017年7月2日 上午9:30:41
 * @details     Solution AC
 */
public class P633_SumOfSquareNumbers {
    static public class Solution {
        public boolean judgeSquareSum(int c) {
            for (int i = 0; ; i ++) {
                long v = (long)i * i;
                if (v > c) break;
                if (isSquare((int)(c - v))) return true;
            }
            return false;
        }
        boolean isSquare(int n) {
            int v = (int)Math.sqrt(n);
            return v * v == n;
        }
    }
}
