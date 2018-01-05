package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        美团_拜访.java
 * @date        2017年7月7日 下午9:48:50
 * @details     
 */
public class 美团_拜访 {
    static public class Visit {
        public int countPath(int[][] m, int r, int c) {
            if (r < 2 || c < 2) return r == 0 ? 0 : 1;
            int si = -1, sj = -1;
            int ei = -1, ej = -1;
            for (int i = 0; i < r; i ++) {
                for (int j = 0; j < c; j ++) {
                    if (m[i][j] == 1) {
                        si = i;
                        sj = j;
                    } else if (m[i][j] == 2) {
                        ei = i;
                        ej = j;
                    }
                }
            }
            int istep = si < ei ? 1 : -1;
            int jstep = sj < ej ? 1 : -1;
            int[][] cc = new int[r][c];
            cc[si][sj] = 1;
            for (int i = si; i != ei + istep; i += istep) {
                for (int j = sj; j != ej + jstep; j += jstep) {
                    if (m[i][j] == -1) continue;
                    if (i == si && j == sj) continue;
                    int pi = i - istep;
                    int pj = j - jstep;
                    int iv = (pi >= 0 && pi < r) ? cc[pi][j] : 0;
                    int jv = (pj >= 0 && pj < c) ? cc[i][pj] : 0;
                    cc[i][j] = iv + jv;
                }
            }
            return cc[ei][ej];
        }
    }
}
