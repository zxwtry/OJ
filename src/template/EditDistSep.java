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
    
    //return [delete times, insert times, replace times]
    public static int[] editDistance2(char[] c1, char[] c2, int delete, int insert, 
            int replace) throws Exception {
        int n1 = c1 == null ? 0 : c1.length;
        int n2 = c2 == null ? 0 : c2.length;
        if (n1 < n2) return editDistance2(c2, c1, delete, insert, replace);
        int[][] deleteCount = new int[2][n2+1];
        int[][] insertCount = new int[2][n2+1];
        int[][] replaceCount = new int[2][n2+1];
        int INF = Integer.MAX_VALUE / 3;
        for (int i1 = 0; i1 <= n1; i1 ++) {
            for (int i2 = 0; i2 <= n2; i2 ++) {
                if (i1 == 0 && i2 == 0) continue;
                int deleteRec = INF, insertRec = INF;
                int replaceRec = INF, minRec = INF;
                if (i1-1 >= 0) {
                    deleteRec = deleteCount[0][i2] * delete + insertCount[0][i2] * insert 
                            + replaceCount[0][i2] * replace;
                    deleteRec += delete;
                    minRec = Math.min(minRec, deleteRec);
                }
                if (i2-1 >= 0) {
                    insertRec = deleteCount[1][i2-1] * delete + insertCount[1][i2-1] * insert
                            + replaceCount[1][i2-1] * replace;
                    insertRec += insert;
                    minRec = Math.min(minRec, insertRec);
                }
                if (i1-1 >= 0 && i2-1 >= 0) {
                    replaceRec = deleteCount[0][i2-1] * delete + insertCount[0][i2-1] * insert
                            + replaceCount[0][i2-1] * replace;
                    replaceRec += (c1[i1-1] == c2[i2-1] ? 0 : replace);
                    minRec = Math.min(minRec, replaceRec);
                }
                if (minRec == deleteRec) {
                    deleteCount[1][i2] = deleteCount[0][i2] + 1;
                    insertCount[1][i2] = insertCount[0][i2];
                    replaceCount[1][i2] = replaceCount[0][i2];
                } else if (minRec == insertRec) {
                    deleteCount[1][i2] = deleteCount[1][i2-1];
                    insertCount[1][i2] = insertCount[1][i2-1] + 1;
                    replaceCount[1][i2] = replaceCount[1][i2-1];
                } else if (minRec == replaceRec) {
                    deleteCount[1][i2] = deleteCount[0][i2-1];
                    insertCount[1][i2] = insertCount[0][i2-1];
                    replaceCount[1][i2] = replaceCount[0][i2-1] + (c1[i1-1] == c2[i2-1] ? 0 : 1);
                } else {
                    throw new Exception("error at three not equal");
                }
            }
            swap(deleteCount);
            swap(insertCount);
            swap(replaceCount);
        }
        return new int[] {deleteCount[0][n2], insertCount[0][n2], replaceCount[0][n2]};
    }
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
