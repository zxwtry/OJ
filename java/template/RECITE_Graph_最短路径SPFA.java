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
 * @file        RECITE_Graph_最短路径SPFA.java
 * @date        2017年7月26日 下午3:25:32
 * @details     AC http://hihocoder.com/problemset/problem/1081
 * @details     AC http://hihocoder.com/problemset/problem/1093
 */
public class RECITE_Graph_最短路径SPFA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }

    static void solve(Scanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();
        int t = sc.nextInt();
        SPFA spfa = new SPFA(n, m);
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            spfa.addEdge(u, v, w);
            spfa.addEdge(v, u, w);
        }
        System.out.println(spfa.spfa(s, t));
    }

    static class SPFA {
        public int[] vs, next, ws, head, dis;
        public int e;
        public boolean[] vis;
        public final int inf = Integer.MAX_VALUE / 4;

        public SPFA(int n, int m) {
            vs      =   new int[(m + 5) * 2];
            ws      =   new int[(m + 5) * 2];
            next    =   new int[(m + 5) * 2];
            head    =   new int[n + 5];
            dis     =   new int[n + 5];
            vis     =   new boolean[n + 5];
            rewind();
        }

        public void rewind() {
            e = 0;
            Arrays.fill(vis, false);
            Arrays.fill(head, -1);
            Arrays.fill(dis, inf);
        }

        public void addEdge(int u, int v, int w) {
            vs[e] = v;
            ws[e] = w;
            next[e] = head[u];
            head[u] = e++;
        }

        public int spfa(int s, int t) {
            Queue<Integer> q = new LinkedList<Integer>();
            dis[s] = 0;
            vis[s] = true;
            q.add(s);
            while (q.size() > 0) {
                int x = q.poll();
                vis[x] = false;
                for (int i = head[x]; i != -1; i = next[i]) {
                    int v = vs[i];
                    int d = dis[x] + ws[i];
                    if (d < dis[v]) {
                        dis[v] = d;
                        if (vis[v] == false) {
                            vis[v] = true;
                            q.add(v);
                        }
                    }
                }
            }
            return dis[t];
        }
    }
}
