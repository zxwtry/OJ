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
        for (int i = 0; i < m; i ++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            spfa.addEdge(a, b, c);
            spfa.addEdge(b, a, c);
        }
        System.out.println(spfa.spfa(s, t));
    }

    static class SPFA{
        public int[] v,next,w,head,dis;
        public int e;
        public boolean[] vis;
        public final int inf = (int)(1e9);
        
        public SPFA(int n,int m){
            head = new int[n+5];
            v = new int[(m+5)<<1];
            next = new int[(m+5)<<1];
            w = new int[(m+5)<<1];
            dis = new int[n+5];
            vis = new boolean[n+5];
            rewind();
        }
        
        public void rewind() {
            e=0;
            Arrays.fill(vis, false);
            Arrays.fill(head, -1);
            Arrays.fill(dis, inf);
        }
        
        public void addEdge(int U,int V,int W){
            v[e] = V;
            next[e] = head[U];
            w[e] = W;
            head[U] = e++;
        }
        
        public int spfa(int s,int t){
            Queue<Integer> q = new LinkedList<Integer>();
            dis[s] = 0; vis[s]=true;
            q.add(s);
            while(q.size() > 0){
                int x = q.poll(); 
                vis[x] = false;
                for(int i = head[x]; i != -1; i = next[i]){
                    int nxt = v[i];
                    int d = dis[x] + w[i];
                    if(d < dis[nxt]){
                        dis[nxt] = d;
                        if(vis[nxt] == false){
                            vis[nxt] = true;
                            q.add(nxt);
                        }
                    }
                }
            }
            return dis[t];
        }
    }
}
