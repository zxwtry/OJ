package nowcoder.com;

import java.util.PriorityQueue;
import java.util.Scanner;

public class 未知_移棋子 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }
    
    private static void solve(Scanner sc) {
        int n = sc.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i ++) {
            ps[i] = new P(sc.nextInt(), 0);
        }
        for (int i = 0; i < n; i ++) {
            ps[i].y = sc.nextInt();
        }
        if (n == 1) {
            System.out.println("0");
            return;
        }
        StringBuilder st = new StringBuilder("0 ");
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 2; i <= n; i ++) {
            String app = i == n ? "" : " ";
            long minSum = Long.MAX_VALUE;
            for (int k = 0; k < n; k ++) {
                pq.clear();
                for (int v = 0; v < n; v ++) {
                    pq.add(dist(ps[k], ps[v]));
                }
                long sum = 0;
                for (int t = 0; t < i; t ++) {
                    long v = pq.poll();
                    sum += v;
                }
                minSum = Math.min(minSum, sum);
            }
            st.append(minSum);
            st.append(app);
        }
        System.out.println(st.toString());
    }
    
    static long dist(P a, P b) {
        return (long)abs(a.x - b.x) + (long)abs(a.y - b.y);
    }
    
    static int abs(int a) {
        return a < 0 ? -a : a;
    }
    
    static class P {
        int x; int y;
        public P (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
}   
