package nowcoder.com;

import java.util.Arrays;
import java.util.Scanner;


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        计蒜客_复赛_百度地图城市.java
 * @date        2017年6月10日 下午10:14:25
 * @details     OOM or TLE
 */
public class 计蒜客_复赛_百度地图城市 {
    final static int MAX = Integer.MAX_VALUE;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] d = new int[n+1][];
        for (int i = 0; i <= n; i ++)
            d[i] = new int[i];
        int[][] q = new int[m+1][];
        for (int i = 0; i <= n; i ++) Arrays.fill(d[i], MAX);
        for (int mi = 1; mi <= m; mi ++) {
            int qz = sc.nextInt();
            q[mi] = new int[qz+1];
            for (int zi = 1; zi <= qz; zi ++)
               q[mi][zi] = sc.nextInt();
        }
        int m1 = sc.nextInt();
        for (int m1i = 0; m1i < m1; m1i ++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int v = sc.nextInt();
            d[Math.max(x, y)][Math.min(x, y)] = v;
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
                    d[Math.max(xx, yy)][Math.min(xx, yy)] = 
                            Math.min(d[Math.max(xx, yy)][Math.min(xx, yy)], v);
                }
            }
        }
        
//        tools.Utils.A_打印二维数组(q);
//        System.out.println("=================");
//        tools.Utils.A_打印二维数组(d);
        
        int[][] NEXT = new int[n + 1][n + 1];
        int[] DS = new int[n + 1];
        int[] HEIN = new int[n + 1];
        int[] HEAP = new int[n + 1];
        int HI = 0;
        int startIndex = sc.nextInt();
        int endIndex = sc.nextInt();
        for (int i = 1; i <= n; i ++) {
            int prej = 0;
            NEXT[i][0] = -1;
            for (int j = 1; j <= n; j ++) {
                if (d[i][j] != MAX) {
                    NEXT[i][prej] = j;
                    NEXT[i][j] = -1;
                    prej = j;
                }
            }
        }
        
        int min = MAX;
        int s = startIndex;
        for (int di = 1; di <= n; di ++) DS[di] = di == s ? 0 : MAX;
        HI = 0;
        for (int hi = 1; hi <= n; hi ++)
            if (hi != s) { HEAP[++HI] = hi; HEIN[hi] = HI; }
        while (true) {
            if (s == endIndex) { min=Math.min(min, DS[s]); break;}
            int next = NEXT[s][0];
            while (next != -1) {
                if (DS[next] > DS[s] + d[s][next]) {
                    DS[next] = DS[s] + d[s][next];
                    //heapUp
                    int i = HEIN[next];
                    int p = i / 2;
                    while (p != 0) {
                        if (DS[HEAP[p]] > DS[HEAP[i]]) {
                            swap(HEIN, HEAP[p], HEAP[i]);
                            swap(HEAP, p, i);
                        } else break;
                        i = p; p = i / 2;
                    }
                }
                next = NEXT[s][next];
            }
            if (HI == 0) break;
            s = HEAP[1];
            HEAP[1] = HEAP[HI--];
            //heapDn
            int i = 1, c = 2;
            while (c <= HI) {
                if (c+1<=HI && DS[HEAP[c+1]] < DS[HEAP[c]]) c++;
                if (DS[HEAP[i]] > DS[HEAP[c]]) {
                    swap(HEIN, HEAP[c], HEAP[i]);
                    swap(HEAP, c, i);
                } else break;
                i = c;
                c = 2 * i;
            }
        }
    
        System.out.println(min==MAX?-1:min);

        sc.close();
    }
    
    static int access(int[][] d, int i, int j) {
        return d[Math.max(i, j)][Math.min(i, j)];
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
