package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        知识迁移能力_左旋转字符串.java
 * @date        2017年7月2日 上午8:56:21
 * @details     剑指Offer
 */
public class 知识迁移能力_左旋转字符串 {
    static public class Solution {
        public String LeftRotateString(String s,int n) {
            int len = s == null ? 0 : s.length();
            if (len < 2) return s;
            n = n % len;
            if (n == 0) return s;
            return s.substring(n) + s.substring(0, n);
        }
    }
}
