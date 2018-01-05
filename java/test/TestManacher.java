package test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     test
 * @file        TestManacher.java
 * @type        TestManacher
 * @date        2017年5月12日 上午11:37:40
 * @details     javac RECITE_String_Manacher.java
 * @details     nohup java RECITE_String_Manacher > RECITE_String_Manacher.out &
 * @details     nohup java RECITE_String_Manacher > RECITE_String_Manacher.out &
 * @details     all: 2000000
 * @details     tc: 2000000
 * @details     fc: 0
 */
public class TestManacher {
    public static void main(String[] args) throws IOException {
        int all = 2000000;
        int tc = 0, fc = 0;
        for (int i = 0; i < all; i ++) {
            String ans = test();
            if (ans != null) {
                FileWriter fw = new FileWriter(String.valueOf(new Date().getTime()));
                fw.write(ans);
                fw.flush();
                fw.close();
                fc ++;
            } else tc ++;
        }
        System.out.println("all: " + all);
        System.out.println("tc: " + tc);
        System.out.println("fc: " + fc);
    }
    static String test() {
        String s = generate();
        int[] m1 = standard(s);
        int[] m2 = manacher(s);
        boolean allSame = true;
        for (int i = 0; allSame && i < m1.length; i ++)
            allSame &= m1[i] == m2[i];
        if (! allSame) return s;
        return null;
    }
    static String generate() {
        int len = 10000 + (int)(5000 * Math.random());
        return tools.StringUtils.A_生成随机数组A_Z(len);
    }
    static char access(String s, int i) {
        return i % 2 == 0 ? '#' : s.charAt(i / 2);
    }
    static int[] standard(String s) {
        int sn = 2 * (s == null ? 0 : s.length()) + 1;
        int m[] = new int[sn], left = 0, right = 0;
        for (int i = 0; i < sn; i ++) {
            left = right = i;
            while (left-1 > -1 && right+1 < sn && access(s, left-1) == access(s, right+1)) {
                left --;
                right ++;
            }
            m[i] = (right - left) / 2;
        }
        return m;
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
