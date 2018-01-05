package template;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_String_Manacher.java
 * @type        RECITE_String_Manacher
 * @date        2016年12月7日 上午11:47:10
 * @details     https://leetcode.com/problems/longest-palindromic-substring/
 */
public class RECITE_String_Manacher {
    static char access(String s, int i) {
        return i % 2 == 0 ? '#' : s.charAt(i / 2);
    }
    static int[] manacher(String s) {
        int sn = 2 * (s == null ? 0 : s.length()) + 1;
        int[] m = new int[sn];
        int ti = 0, ci = 0, mi = 0, li = 0, ri = 0;
        for (int i = 0; i < sn; i ++) {
            mi = 2 * ci - i;
            if (i >= ti || m[mi] + i == ti) {
                li = ri = i;
                while (li-1 > -1 && ri+1 < sn && access(s, li-1) == access(s, ri+1)) {
                    li --;
                    ri ++;
                }
                ci = i;
                ti = ri;
                m[i] = (ri - li) / 2;
            } else m[i] = Math.min(ti - i, m[mi]);
        }
        return m;
    }
}
