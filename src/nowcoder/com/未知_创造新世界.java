package nowcoder.com;

import java.util.ArrayList;

/**
 *  众所周知计算机代码底层计算都是0和1的计算，
 *  牛牛知道这点之后就想使用0和1创造一个新世界！
 *  牛牛现在手里有n个0和m个1，给出牛牛可以创造的x种物品，
 *  每种物品都由一个01串表示。牛牛想知道当前手中的0和1可以
 *  最多创造出多少种物品。
 *  
 *  x n m
 *  
 *  3 3 1
 *  1
 *  00
 *  100
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        未知_创造新世界.java
 * @type        未知_创造新世界
 * @date        2017年3月24日 下午1:53:16
 * @details     AC
 */
public class 未知_创造新世界 {
    public static void main(String[] args) {
        solve2();
    }
    static void solve1() {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        A[] as = new A[times];
        for (int i = 0; i < times; i ++) {
            String s = scanner.next();
            int nn = 0;
            int nm = 0;
            for (int j = 0; j < s.length(); j ++) {
                if (s.charAt(j) == '0') {
                    nn ++;
                } else {
                    nm ++;
                }
            }
            A a = new A();
            a.n = nn;
            a.m = nm;
            as[i] = a;
        }
        Arrays.sort(as, new Comparator<A>() {
            @Override
            public int compare(A a1, A a2) {
                if (a1.n < a2.n) return -1;
                if (a1.n > a2.n) return 1;
                if (a1.m < a2.m) return -1;
                if (a1.m > a2.m) return 1;
                return 0;
            }
        });
        int count = 0;
        int max = 0;
        int nn = n;
        int nm = m;
        for (int i = 0; i < times; i ++) {
            if (nn < as[i].n) break;
            if (nm >= as[i].m) {
                count ++;
                nm -= as[i].m;
                nn -= as[i].n;
            }
        }
        max = Math.max(max, count);
        Arrays.sort(as, new Comparator<A>() {
            @Override
            public int compare(A a1, A a2) {
                if (a1.m < a2.m) return -1;
                if (a1.m > a2.m) return 1;
                if (a1.n < a2.n) return -1;
                if (a1.n > a2.n) return 1;
                return 0;
            }
        });
        nn = n;
        nm = m;
        count = 0;
        for (int i = 0; i < times; i ++) {
            if (nm < as[i].m) break;
            if (nn >= as[i].n) {
                count ++;
                nm -= as[i].m;
                nn -= as[i].n;
            }
        }
        max = Math.max(max, count);
        System.out.println(max);
        scanner.close();
    }
    static class A{
        int n, m;
    }
}
