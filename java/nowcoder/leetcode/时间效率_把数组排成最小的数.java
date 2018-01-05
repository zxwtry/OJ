package nowcoder.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        时间效率_把数组排成最小的数.java
 * @date        2017年7月1日 下午12:18:05
 * @details     剑指Offer
 */
public class 时间效率_把数组排成最小的数 {
    static public class Solution {
        public String PrintMinNumber(int [] n) {
            int nl = n == null ? 0 : n.length;
            if (nl == 0) return "";
            String[] ss = new String[nl];
            int len = 0;
            for (int i = 0; i < nl; i ++) {
                ss[i] = String.valueOf(n[i]);
                len += ss[i].length();
            }
            Arrays.sort(ss, new Comparator<String>(){
                @Override
                public int compare(String s1, String s2) {
                    return (s1+s2).compareTo(s2+s1);
                }
            });
            char[] ans = new char[len];
            int ansIndex = 0;
            for (String s : ss) {
                for (int i = 0; i < s.length(); i ++) {
                    ans[ansIndex ++] = s.charAt(i);
                }
            }
            return new String(ans);
        }
    }
}
