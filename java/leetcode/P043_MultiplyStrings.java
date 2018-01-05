package leetcode;

/**
 *  Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P043_MultiplyStrings.java
 * @date        2017年8月23日 上午9:18:20
 * @details     AC
 */
public class P043_MultiplyStrings {
    static class Solution {
        public String multiply(String num1, String num2) {
            int n1n = num1 == null ? 0 : num1.length();
            int n2n = num2 == null ? 0 : num2.length();
            if (n1n == 0 || n2n == 0) {
                return "0";
            }
            /**
             * 结果最长可能为：n1n + n2n
             */
            char[] ans = new char[n1n + n2n];
            for (int n1i = n1n - 1; n1i > -1; n1i --) {
                for (int n2i = n2n - 1; n2i > -1; n2i --) {
                    /**
                     *  n1n - 1 - n1i + n2n - 1 - n2i
                     *  n1n + n2n + 1 减去上式
                     *  得到：ansIndex = n1i + n2i + 1
                     */
                    int ansIndex = n1i + n2i + 1;
                    int ansValue = (num1.charAt(n1i) - '0') * 
                            (num2.charAt(n2i) - '0');
                    ans[ansIndex] += ansValue;
                }
                int carry = 0;
                for (int ansIndex = ans.length - 1; ansIndex > -1; ansIndex --) {
                    carry += ans[ansIndex];
                    ans[ansIndex] = (char)(carry % 10);
                    carry /= 10;
                }
            }
            int ansIndex = 0;
            while (ansIndex < ans.length && ans[ansIndex] == 0) {
                ansIndex ++;
            }
            for (int j = ansIndex; j < ans.length; j ++) {
                ans[j] = (char) ('0' + ans[j]);
            }
            if (ansIndex == ans.length) {
                return "0";
            }
            return new String(ans, ansIndex, ans.length - ansIndex);
        }
    }
}