package template;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        EditDistSep.java
 * @type        EditDistSep
 * @date        2017年5月19日 下午10:30:08
 * @details     
 */
public class EditDistSep {
    public static int editDistance(char[] c1, char[] c2, int delete, int insert, int replace) {
        int n1 = c1 == null ? 0 : c1.length;
        int n2 = c2 == null ? 0 : c2.length;
        if (n1 < n2) return editDistance(c2, c1, delete, insert, replace);
        int[][] d = new int[2][n2+1];
        int INF = Integer.MAX_VALUE / 3;
        for (int i1 = 0; i1 <= n1; i1 ++) {
            for (int i2 = 0; i2 <= n2; i2 ++) {
                if (i1 == 0 && i2 == 0) continue;
                int min = INF;
                if (i1-1 >= 0) min = Math.min(min, d[0][i2] + delete);
                if (i2-1 >= 0) min = Math.min(min, d[1][i2-1] + insert);
                if (i1-1 >= 0 && i2-1 >= 0) min = Math.min(min, 
                        d[0][i2-1] + (c1[i1-1] == c2[i2-1] ? 0 : replace));
                d[1][i2] = min;
            }
            swap(d);
        }
        return d[0][n2];
    }
    public static void swap(int[][] d) {
        int[] t = d[0];
        d[0] = d[1];
        d[1] = t;
    }
}
