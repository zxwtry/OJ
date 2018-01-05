package nowcoder.com;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度_罪犯转移.java
 * @date        2017年7月10日 下午12:11:00
 * @details     
 */
public class 百度_罪犯转移 {
    static int n;
    static int t;
    static int c;
    static long a[];
    static long s[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
//            solve1(sc);
            solve2(sc);
        }
        sc.close();
    }
    static void solve2(Scanner sc) {
        n = sc.nextInt();
        t = sc.nextInt();
        c = sc.nextInt();
        long[] circle = new long[c + 1];
        long sum = 0;
        int cnt = 0;
        for (int i = 0; i < n; i ++) {
            int v = sc.nextInt();
            sum += v;
            if (i + 1 - c > -1) {
                if (sum - circle[index(i + 1 - c)] < t) {
                    cnt ++;
                }
            }
            circle[index(i + 1)] = sum;
        }
        System.out.println(cnt);
    }
    static int index(int i) {
        i = i % (c + 1);
        return i < 0 ? i + c + 1 : i;
    }
    static void solve1(Scanner sc) {
        n = sc.nextInt();
        t = sc.nextInt();
        c = sc.nextInt();
        a = new long[n];
        s = new long[n + 1];
        int cnt = 0;
        for (int i = 0; i < n; i ++) {
            a[i] = sc.nextInt();
            s[i + 1] = s[i] + a[i]; 
            if (i + 1 - c >= 0 && s[i + 1] - s[i + 1 - c] <= t ) {
                cnt ++;
            }
        }
        System.out.println(cnt);
    }
}
