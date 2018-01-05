package nowcoder.leetcode;

import java.util.ArrayList;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        画图让抽象形象化_顺时针打印矩阵.java
 * @date        2017年6月30日 上午10:42:34
 * @details     剑指Offer
 */
public class 画图让抽象形象化_顺时针打印矩阵 {
    static public class Solution {
        public ArrayList<Integer> printMatrix(int [][] m) {
            int r = m == null ? 0 : m.length;
            if (r == 0) return new ArrayList<Integer>(0);
            int l = m[0] == null ? 0 : m[0].length;
            if (l == 0) return new ArrayList<Integer>(0);
            ArrayList<Integer> a = new ArrayList<Integer>(r * l);
            int i = 0, j = 0;
            int I = r - 1, J = l - 1;
            while (i < I && j < J) {
                s(a, i, j, I, J, m);
                i ++;
                j ++;
                I --;
                J --;
            }
            if (i == I) {
                for (int k = j; k <= J; k ++) a.add(m[i][k]);
            } else if (j == J) {
                for (int k = i; k <= I; k ++) a.add(m[k][j]);
            }
            return a;
        }
        void s(ArrayList<Integer> a, int i, int j, int I, int J, int[][] m) {
            int k = 0;
            for (k = j; k < J; k ++) a.add(m[i][k]);
            for (k = i; k < I; k ++) a.add(m[k][J]);
            for (k = J; k > j; k --) a.add(m[I][k]);
            for (k = I; k > i; k --) a.add(m[k][j]);
        }
    }
}
