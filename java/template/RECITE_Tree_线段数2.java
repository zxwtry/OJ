package template;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_Tree_线段数2.java
 * @date        2017年7月25日 下午7:06:45
 * @details     AC http://hihocoder.com/problemset/problem/1077?sid=1136890
 */
public class RECITE_Tree_线段数2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            if (! solve(br) ) {
                break;
            }
        }
    }

    static int[] min = new int[6000000];
    static int[] ws = new int[1000050];
    
    private static boolean solve(BufferedReader br) throws Exception {
        String nL = br.readLine();
        if (nL == null) return false;
        int n = 0;
        for (int i = 0; i < nL.length(); i ++) {
            n = n * 10 + nL.charAt(i) - '0';
        }
        String oL = br.readLine();
        int wi = 0;
        int v = 0;
        for (int i = 0; i < oL.length(); i ++) {
            char c = oL.charAt(i);
            if (c == ' ') {
                ws[wi ++] = v;
                v = 0;
            } else {
                v = v * 10 + c - '0';
            }
        }
        ws[wi ++] = v;
        init(ws, 0, n - 1, min, 0);
        int m = 0;
        String mL = br.readLine();
        for (int i = 0; i < mL.length(); i ++) {
            m = m * 10 + mL.charAt(i) - '0';
        }
        int[] vs = new int[5];
        int vi  = 0;
        for (int i = 0; i < m; i ++) {
            String vL = br.readLine();
            v = 0;
            vi = 0;
            for (int k = 0; k < vL.length(); k ++) {
                char c = vL.charAt(k);
                if (c == ' ') {
                    vs[vi ++] = v;
                    v = 0;
                } else {
                    v = v * 10 + c - '0';
                }
            }
            if (vi == 2)
            vs[vi ++] = v;
            int a = vs[0], b = vs[1], c = vs[2];
            if (a == 0) {
                System.out.println(query(min, 0, 0, n-1, b - 1, c - 1));
            } else {
                update(min, 0, n-1, b - 1, c);
            }
        }
        return true;
    }

    private static void update(int[] min, int mlow, int mhigh, int index, int value) {
        int mi = getMI(0, mlow, mhigh, index);
        min[mi] = value;
        while (true) {
            if (mi == 0) break;
            mi = (mi - 1) / 2;
            min[mi] = Math.min(min[2 * mi + 1], min[2 * mi + 2]);
        }
    }

    private static int getMI(int i, int mlow, int mhigh, int index) {
        if (mlow == mhigh) return i;
        int m = mlow + (mhigh - mlow) / 2;
        if (index <= m) return getMI(i * 2 + 1, mlow, m, index);
        return getMI(i * 2 + 2, m + 1, mhigh, index);
    }

    private static int query(int[] min, int mi, int mlow, int mhigh, int qlow, int qhigh) {
        if (qlow > qhigh) return Integer.MAX_VALUE;
        if (mlow == qlow && mhigh == qhigh) return min[mi];
        int m = mlow + (mhigh - mlow) / 2;
        if (qhigh <= m) return query(min, mi * 2 + 1, mlow, m, qlow, qhigh);
        if (qlow > m) return query(min, mi * 2 + 2, m + 1, mhigh, qlow, qhigh);
        return Math.min(
                query(min, mi * 2 + 1, mlow, m, qlow, m), 
                query(min, mi * 2 + 2, m + 1, mhigh, m + 1, qhigh));
    }

    private static int init(int[] ws, int i, int j, int[] min, int mi) {
        if (i > j) return Integer.MAX_VALUE;
        if (i == j) {
            min[mi] = ws[i];
            min[mi * 2 + 1] = Integer.MAX_VALUE;
            min[mi * 2 + 2] = Integer.MAX_VALUE;
            return ws[i];
        }
        int m = i + (j - i) / 2;
        min[mi] = Math.min(
                init(ws, i, m, min, mi * 2 + 1), 
                init(ws, m+1, j, min, mi * 2 + 2));
        return min[mi];
    }
}
