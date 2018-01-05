package nowcoder.com;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        计蒜客_复赛_百度地图城市2.java
 * @date        2017年6月10日 下午10:14:42
 * @details     OOM or TLE
 */
public class 计蒜客_复赛_百度地图城市2 {
    final static int MAX = Integer.MAX_VALUE;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] d = new int[n+1][n+1];
        int[][] q = new int[m+1][];
        for (int i = 0; i <= n; i ++) Arrays.fill(d[i], MAX);
        for (int mi = 1; mi <= m; mi ++) {
            int qz = sc.nextInt();
            q[mi] = new int[qz+1];
            for (int zi = 1; zi <= qz; zi ++)
               q[mi][zi] = sc.nextInt();
        }
        int cnt = 0;
        int m1 = sc.nextInt();
        for (int m1i = 0; m1i < m1; m1i ++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int v = sc.nextInt();
            if (d[x][y] == MAX) cnt ++;
            if (d[y][x] == MAX) cnt ++;
            d[x][y] = v;
            d[y][x] = v;
        }
        int m2 = sc.nextInt();
        for (int m2i = 0; m2i < m2; m2i ++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int v = sc.nextInt();
            for (int xi = 1; xi < q[x].length; xi ++) {
                int xx = q[x][xi];
                for (int yi = 1; yi < q[y].length; yi ++) {
                    int yy = q[y][yi];
                    if (d[xx][yy] == MAX) cnt ++;
                    if (d[yy][xx] == MAX) cnt ++;
                    d[xx][yy] = Math.min(d[xx][yy], v);
                    d[yy][xx] = Math.min(d[yy][xx], v);
                }
            }
        }
        head = new int[n+2];
        dist = new int[n+2];
        edges = new Edge[cnt + 2];

        init(n+1);
        
        for (int i = 1; i <= n; i ++) {
            for (int j = i+1; j <= n; j ++) {
                if (d[i][j] != MAX) {
                    addEdge(i, j, d[i][j]);
                    addEdge(j, i, d[i][j]);
                }
            }
        }
        int s = sc.nextInt(), t = sc.nextInt();
        System.out.println(dijkstra(s, t));
        sc.close();
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    static class Edge {
        int to, next, cost;
        public Edge(int to, int next, int cost) 
        {this.to = to; this.next = next; this.cost = cost;}
    }
    static class State {
        int cost, id;
        public State(int id, int cost) {this.cost = cost; this.id = id;}
    }
    static class Cmp implements Comparator<State> {
        public int compare(State o1, State o2) {
            return o1.cost - o2.cost;
        }
    }
    static int N, L;
    static int[] head = null;
    static int[] dist = null;
    static Edge[] edges = null;
    static void init(int n) {
        N = n;
        L = 0;
        for (int i = 0; i < n; i ++) head[i] = -1;
    }
    static void addEdge(int x, int y, int cost) {
        if (edges[L] == null) edges[L] = new Edge(y, head[x], cost);
        head[x] = L ++;
    }
    static int dijkstra(int s, int t) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        State u = new State(s, 0);
        dist[s] = 0;
        PriorityQueue<State> q = new PriorityQueue<State>(N, new Cmp());
        q.add(u);
        while (! q.isEmpty()) {
            u = q.poll();
            if (u.id == t) return dist[t];
            if (u.cost != dist[u.id]) continue;
            for (int i = head[u.id]; i != -1; i=edges[i].next) {
                if (dist[edges[i].to] > dist[u.id] + edges[i].cost) {
                    dist[edges[i].to] = dist[u.id] + edges[i].cost;
                    q.add(new State(edges[i].to, dist[edges[i].to]));
                }
            }
        }
        return -1;
    }
}
