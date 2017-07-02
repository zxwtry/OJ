package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        字符串_表示数值的字符串.java
 * @date        2017年7月2日 下午8:34:30
 * @details     剑指Offer
 */
public class 字符串_表示数值的字符串 {
    static public class Solution {
        public boolean isNumeric(char[] str) {
            int len = str == null ? 0 : str.length;
            if (len == 0) return false;
            return n(str, 0, len - 1);
        }
        boolean n(char[] cs, int i, int j) {
            for (int k = i; k <= j; k ++) {
                if (cs[k] == 'e' || cs[k] == 'E') {
                    return v(cs, i, k - 1) && vnoPoi(cs, k + 1, j);
                }
            }
            return v(cs, i, j);
        }
        boolean v(char[] cs, int i, int j) {
            if (i > j) return false;
            if (cs[i] == '+') {
                return vPos(cs, i+1, j);
            } else if (cs[i] == '-') {
                return vPos(cs, i+1, j);
            }
            return vPos(cs, i, j);
        }
        boolean vPos(char[] cs, int i, int j) {
            if (i > j) return false;
            boolean hasDot = false;
            for (int k = i; k <= j; k ++) {
                if (cs[k] >= '0' && cs[k] <= '9') {
                    
                } else if (cs[k] == '.') {
                    if (hasDot) return false;
                    hasDot = true;
                } else {
                    return false;
                }
            }
            return true;
        }
        boolean vnoPoi(char[] cs, int i, int j) {
            if (i > j) return false;
            if (cs[i] == '+') {
                return noPoi(cs, i+1, j);
            } else if (cs[i] == '-') {
                return noPoi(cs, i+1, j);
            }
            return noPoi(cs, i, j);
        }
        boolean noPoi(char[] cs, int i, int j) {
            if (i > j) return false;
            for (int k = i; k <= j; k ++) {
                if (cs[k] >= '0' && cs[k] <= '9') {
                    
                } else if (cs[k] == '.') {
                    return false;
                } else {
                    return false;
                }
            }
            return true;
        }
    }
}
