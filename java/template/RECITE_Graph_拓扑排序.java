package template;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_Graph_拓扑排序.java
 * @date        2017年7月31日 下午4:13:20
 * @details     http://hihocoder.com/problemset/problem/1174
 */
public class RECITE_Graph_拓扑排序 {
    static final int MAXN = 100050;
    static final int MAXM = 500050;
    static Topology topology = new Topology(MAXN, MAXM);
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }
    private static void solve(Scanner sc) {
        int t = sc.nextInt();
        while (t -- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            topology.rewind(n);
            for (int mi = 0; mi < m; mi ++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                topology.addEdge(u, v);
            }
            int[] topo = topology.topology();
            System.out.println(topo == null ? "Wrong" : "Correct");
        }
    }
    static class Topology {
        int[] vs, next, head, ind;
        int e, n;
        public Topology(int n, int m) {
            vs      =   new int[(m + 5) * 2];
            next    =   new int[(m + 5) * 2];
            head    =   new int[n + 5];
            ind     =   new int[n + 5];
            rewind(n);
        }
        private void rewind(int n) {
            Arrays.fill(head, -1);
            Arrays.fill(ind, 0);
            this.n = n;
            e = 0;
        }
        public void addEdge(int u, int v) {
            vs[e] = v;
            next[e] = head[u];
            head[u] = e ++;
            ind[v] ++;
        }
        public int[] topology() {
            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= n; i ++)
                if (ind[i] == 0) q.add(i);
            if (q.size() == 0) return null;
            int[] ans = new int[n];
            int ai = 0;
            while (! q.isEmpty()) {
                int u = q.poll();
                ans[ai ++] = u;
                for (int i = head[u]; i != -1; i = next[i]) {
                    int v = vs[i];
                    if (ind[v] >= 0) ind[v] --;
                    if (ind[v] == 0) q.add(v);
                }
            }
            return ai == n ? ans : null;
        }
    }
}
