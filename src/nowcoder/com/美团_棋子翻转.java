package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        美团_棋子翻转.java
 * @date        2017年7月7日 下午9:24:38
 * @details     
 */
public class 美团_棋子翻转 {
    static public class Flip {
        int r;
        int c;
        public int[][] flipChess(int[][] a, int[][] f) {
            r = a == null ? 0 : a.length;
            if (r == 0) return a;
            c = a[0] == null ? 0 : a[0].length;
            if (c == 0) return a;
            if (f == null) return a;
            for (int[] ff : f) {
                s(a, ff[0] - 1, ff[1] - 1);
            }
            return a;
        }
        void s(int[][] a, int i, int j) {
            flip(a, i - 1, j);
            flip(a, i + 1, j);
            flip(a, i, j - 1);
            flip(a, i, j + 1);
        }
        void flip(int[][] a, int i, int j) {
            if (i < 0 || i >= r) return;
            if (j < 0 || j >= c) return;
            if (a[i][j] == 1) {
                a[i][j] = 0;
            } else {
                a[i][j] = 1;
            }
        }
    }
}
