package nowcoder.com;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        小米_小米Git.java
 * @date        2017年7月5日 下午9:13:25
 * @details     
 */
public class 小米_小米Git {
    static public class Solution {
        /**
         * 返回git树上两点的最近分割点
         * 
         * @param matrix 接邻矩阵，表示git树，matrix[i][j] == '1'
         *  当且仅当git树中第i个和第j个节点有连接，节点0为git树的跟节点
         * @param indexA 节点A的index
         * @param indexB 节点B的index
         * @return 整型
         */
        int rs, cs;
        String[] m;
        boolean[] v;
        int[] ic;
        int[] jc;
        int[] rc;
        public int getSplitNode(String[] m, int i, int j) {
            rs = m == null ? 0 : m.length;
            if (rs == 0) return 0;
            cs = m[0] == null ? 0 : m[0].length();
            if (cs == 0) return 0;
            if (i < 0 || i >= cs) return 0;
            if (j < 0 || j >= cs) return 0;
            if (i == 0 || j == 0) return 0;
            this.m = m;
            v = new boolean[cs];
            this.ic = new int[cs];
            this.jc = new int[cs];
            this.rc = new int[cs];
            LinkedList<Integer> ll = new LinkedList<>();
            
            ll.clear();
            ll.add(0);
            Arrays.fill(v, false);
            v[0] = true;
            fc(rc, i, ll);
            
            ll.clear();
            ll.add(i);
            Arrays.fill(v, false);
            v[i] = true;
            fill(ic, i, ll);
            
            ll.clear();
            Arrays.fill(v, false);
            v[j] = true;
            ll.add(j);
            fill(jc, j, ll);
            int max = ic[0] + jc[0];
            int ans = 0;
            for (int k = 1; k < cs; k ++) {
                if (ic[k] != 0 && jc[k] != 0) {
                    int newMax = ic[k] + jc[k];
                    if (max > newMax) {
                        ans = k;
                        max = newMax;
                    } else if (max == newMax) {
                    }
                }
            }
            return ans;
        }
        private void fc(int[] c, int i, LinkedList<Integer> ll) {
            int cnt = 1;
            while (ll.size() != 0) {
                int size = ll.size();
                for (int k = 0; k < size; k ++) {
                    int now = ll.poll();
                    c[now] = cnt;
                    String s = m[now];
                    for (int t = 0, len = s.length(); t < len; t ++) {
                        if (s.charAt(t) == '1' && ! v[t]) {
                            v[t] = true;
                            ll.add(t);
                        }
                    }
                }
                cnt ++;
            }
        }
        private void fill(int[] c, int i, LinkedList<Integer> ll) {
            int cnt = 1;
            while (ll.size() != 0) {
                int size = ll.size();
                boolean has0 = false;
                for (int k = 0; k < size; k ++) {
                    int now = ll.poll();
                    c[now] = cnt;
                    String s = m[now];
                    for (int t = 0, len = s.length(); t < len; t ++) {
                        if (s.charAt(t) == '1' && ! v[t] && rc[t] < rc[now]) {
                            v[t] = true;
                            ll.add(t);
                        }
                    }
                    has0 |= now == 0;
                }
                if (has0) return;
                cnt ++;
            }
        }
    }
}
