package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        字符串_正则表达式匹配.java
 * @date        2017年7月9日 下午10:06:18
 * @details     
 */
public class 字符串_正则表达式匹配 {
    static public class Solution {
        public boolean match(char[] s, char[] p) {
            int sn = s == null ? 0 : s.length;
            int pn = p == null ? 0 : p.length;
            if (pn == 0) return sn == 0;
            boolean[][] m = new boolean[sn + 1][pn + 1];
            m[0][0] = true;
            for (int pi = 0; pi < pn; pi ++) {
                if (p[pi] == '*') {
                    m[0][pi + 1] = m[0][pi - 1];
                }
            }
            for (int si = 0; si < sn; si ++) {
                char sc = s[si];
                for (int pi = 0; pi < pn; pi ++) {
                    char pc = p[pi];
                    if (pc == '*') {
                        if (p[pi - 1] == '.' || p[pi - 1] == sc) {
                            m[si + 1][pi + 1] = 
                                    m[si][pi + 1] |    //[0, si-1] 和 [0, pi]     //匹配多次
                                    m[si][pi - 1] |    //[0, si-1] 和 [0, pi-2]   //匹配1次
                                    m[si + 1][pi - 1]; //[0, si+0] 和 [0, pi-2]   //匹配0次
                        } else {
                            m[si + 1][pi + 1] = m[si + 1][pi - 1];      //匹配0次
                        }
                    } else {
                        if (sc == pc || pc == '.') {
                            m[si + 1][pi + 1] = m[si][pi];
                        } else {
                            m[si + 1][pi + 1] = false;
                        }
                    }
                }
            }
            return m[sn][pn];
        }
    }
}
