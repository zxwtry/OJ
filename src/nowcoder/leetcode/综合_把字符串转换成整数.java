package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        综合_把字符串转换成整数.java
 * @date        2017年7月2日 下午7:19:26
 * @details     剑指Offer
 */
public class 综合_把字符串转换成整数 {
    static public class Solution {
        public int StrToInt(String str) {
            str = str.trim();
            return (int)s(str);
        }
        long s(String v) {
            if (v == null || v.length() == 0) return 0;
            if (v.charAt(0) == '-') {
                return -s(v.substring(1));
            } else if (v.charAt(0) == '+') {
                return s(v.substring(1));
            }
            int len = v.length();
            long val = 0;
            for (int i = 0; i < len; i ++) {
                char c = v.charAt(i);
                if (c == 'e' || c == 'E') {
                    long base = s(v.substring(0, i));
                    long mi = s(v.substring(i+1, len));
                    if (base == 0 || mi == 0) return 0;
                    return (long)Math.pow(base, mi);
                } else if (c >= '0' && c <= '9') {
                    val = val * 10 + c - '0';
                } else {
                    return 0;
                }
            }
            return val;
        }
    }
}
