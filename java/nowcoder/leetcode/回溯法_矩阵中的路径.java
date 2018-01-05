package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        回溯法_矩阵中的路径.java
 * @date        2017年7月4日 下午10:04:13
 * @details     剑指Offer
 */
public class 回溯法_矩阵中的路径 {
    static public class Solution {
        char[] m;
        int rs;
        int cs;
        int sl;
        char[] s;
        boolean[][] v;
        boolean ans;

        public boolean hasPath(char[] m, int rs, int cs, char[] s) {
            this.m = m;
            this.rs = rs;
            this.cs = cs;
            this.s = s;
            this.v = new boolean[rs][cs];
            this.ans = false;
            this.sl = s == null ? 0 : s.length;
            for (int ri = 0; ri < rs; ri++) {
                for (int ci = 0; ci < cs; ci++) {
                    tra(ri, ci, 0);
                    if (ans)
                        return true;
                }
            }
            return false;
        }

        void tra(int ri, int ci, int si) {
            if (si == sl) {
                ans = true;
                return;
            }
            if (ri < 0 || ri >= rs)
                return;
            if (ci < 0 || ci >= cs)
                return;
            if (v[ri][ci])
                return;
            if (s[si] == acc(ri, ci)) {
                v[ri][ci] = true;
                tra(ri + 1, ci, si + 1);
                tra(ri, ci + 1, si + 1);
                tra(ri - 1, ci, si + 1);
                tra(ri, ci - 1, si + 1);
                v[ri][ci] = false;
            }
        }
        char acc(int ri, int ci) {
            return m[cs * ri + ci];
        }
    }
}
