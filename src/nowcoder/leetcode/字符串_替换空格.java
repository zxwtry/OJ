package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        字符串_替换空格.java
 * @date        2017年6月29日 下午9:15:25
 * @details     剑指offer
 */
public class 字符串_替换空格 {
    static public class Solution {
        public String replaceSpace(StringBuffer str) {
            int cnt = 0;
            int len = str.length();
            for (int i = 0; i < len; i ++) {
                if (str.charAt(i) == ' ') {
                    cnt ++;
                }
            }
            char[] ans = new char[len + 2 * cnt];
            int si = len - 1;
            int ai = ans.length - 1;
            while (si > -1) {
                char c = str.charAt(si);
                if (c == ' ') {
                    ans[ai --] = '0';
                    ans[ai --] = '2';
                    ans[ai --] = '%';
                    si --;
                } else {
                    ans[ai --] = c;
                    si --;
                }
            }
            return new String(ans);
        }
    }
}
