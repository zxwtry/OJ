package nowcoder.com;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度_AStar_度度熊保护村庄.java
 * @date        2017年8月5日 下午12:13:36
 * @details     
 */
public class 百度_AStar_度度熊保护村庄 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }

    static int MAXNM = 505;
    static final P[] ns = new P[MAXNM];
    static final P[] ms = new P[MAXNM * 2];
    static int n, m;
    static int[][] minMax = new int[4][2];
    
    private static void solve(Scanner sc) {
        n = sc.nextInt();
        for (int i = 0; i < minMax.length; i ++) {
            minMax[i][0] = Integer.MAX_VALUE;
            minMax[i][1] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < n; i ++) {
            setP(ns, i, sc.nextInt(), sc.nextInt());
            minMax[0][0] = Math.min(minMax[0][0], ns[i].x);
            minMax[0][1] = Math.max(minMax[0][1], ns[i].x);
            minMax[1][0] = Math.min(minMax[1][0], ns[i].y);
            minMax[1][1] = Math.max(minMax[1][1], ns[i].y);
        }
        m = sc.nextInt();
        int minI = 0;
        for (int i = 0; i < m; i ++) {
            setP(ms, i, sc.nextInt(), sc.nextInt());
            if (ms[i].x < ms[minI].x || (ms[i].x == ms[minI].x && 
                    ms[i].y < ms[minI].y)) minI = i;
            minMax[2][0] = Math.min(minMax[2][0], ms[i].x);
            minMax[2][1] = Math.max(minMax[2][1], ms[i].x);
            minMax[3][0] = Math.min(minMax[3][0], ms[i].y);
            minMax[3][1] = Math.max(minMax[3][1], ms[i].y);
        }
        if (minMax[0][0] <= minMax[2][0] || minMax[0][1] >= minMax[2][1]) {
            System.out.println("ToT");
            return;
        }
        if (minMax[1][0] <= minMax[3][0] || minMax[1][1] >= minMax[3][1]) {
            System.out.println("ToT");
            return;
        }
        swap(ms, 0, minI);
        Arrays.sort(ms, 1, m);
        
    }
    
    static void setP(P[] ps, int i, int x, int y) {
        if (ps[i] == null) {
            ps[i] = new P(x, y);
        } else {
            ps[i].x = x;
            ps[i].y = y;
        }
    }
    
    static void swap(P[] ps, int i, int j) {
        P t = ps[i];
        ps[i] = ps[j];
        ps[j] = t;
    }

    static int multiply(P p1, P p2, P p3) {
        return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
    }
    static int distSquare(P p1, P p2) {
        return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
    }
    
    static class P implements Comparable<P> {
        int x, y;
        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(P o) {
            int m = multiply(ms[0], this, o);
            if (m < 0) return 1;
            else if (m == 0 && distSquare(ms[0], this) > distSquare(ms[0], o)) return 1;
            else return -1;
        }
        @Override
        public String toString() {
            return this.x+"..."+this.y;
        }
    }
}
