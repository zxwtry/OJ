package hiho;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     hiho
 * @file        题库1369_网络流.java
 * @date        2017年7月30日 下午12:10:27
 * @details     AC http://hihocoder.com/problemset/problem/1369
 */
public class 题库1369_网络流一_Ford_Fulkerson算法 {
    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("e:/file/data/hiho.题库1369_网络流一_Ford_Fulkerson算法.data"));
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }
    static int n, m;
    static int MAXN = 510;
    static int[][] edge = new int[MAXN][MAXN];
    static int[] a = new int[MAXN];
    static int[] path = new int[MAXN];
    private static void solve(Scanner sc) {
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i <= n; i ++)
            for (int j = 0; j <= n; j ++)
                edge[i][j] = 0;
        for (int i = 0; i < m; i ++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edge[u][v] += w;
        }
        int sum = 0, flow = 0;
        while (true) {
            flow = findPath();
            if (flow == 0) break;
            for (int u = n; u != 1; u = path[u]) {
                edge[path[u]][u] -= flow;
                edge[u][path[u]] += flow;
            }
            sum += flow;
        }
        System.out.println(sum);
    }
    private static int findPath() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        Arrays.fill(a, 0);
        a[1] = Integer.MAX_VALUE;
        while (! q.isEmpty()) {
            int u = q.peek();
            if (u == n) return a[n];
            q.poll();
            for (int v = 1; v <= n; v ++) {
                if (a[v] == 0 && edge[u][v] > 0) {
                    path[v] = u;
                    a[v] = a[u] < edge[u][v] ? a[u] : edge[u][v];
                    q.add(v);
                }
            }
        }
        return 0;
    }
}
