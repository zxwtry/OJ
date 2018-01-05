package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        知识迁移能力_翻转单词顺序列.java
 * @date        2017年7月2日 上午9:02:11
 * @details     剑指Offer
 */
public class 知识迁移能力_翻转单词顺序列 {
    static public class Solution {
        public String ReverseSentence(String str) {
            int len = str == null ? 0 : str.length();
            char[] cs = str.toCharArray();
            reverse(cs, 0, len - 1);
            int pi = 0;
            for (int i = 0; i < len; i ++) {
                char c = cs[i];
                if (c == ' ') {
                    reverse(cs, pi, i - 1);
                    pi = i + 1;
                } else if (i == len - 1) {
                    reverse(cs, pi, i);
                }
            }
            return new String(cs);
        }
        void reverse(char[] cs, int i, int j) {
            while (i < j) {
                swap(cs, i ++, j --);
            }
        }
        void swap(char[] cs, int i, int j) {
            char t = cs[i];
            cs[i] = cs[j];
            cs[j] = t;
        }
    }
}
