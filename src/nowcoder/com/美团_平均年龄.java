package nowcoder.com;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        美团_平均年龄.java
 * @date        2017年7月10日 上午11:18:58
 * @details     
 */
public class 美团_平均年龄 {
    static int Y;
    static double x;
    static int N;
    public static void main(String[] args) {
        //W Y x N
        
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            sc.nextInt();
            Y = sc.nextInt();
            x = 1 - sc.nextDouble();
            N = sc.nextInt();
            solve1();
//            solve2();
        }
        sc.close();
    }
    
    static void solve2() {
        double y = Y;
        for (int i = 0; i < N; i ++) {
            y = one2(y);
        }
        System.out.println((int)Math.ceil(y));
    }
    
    static double one2(double y) {
        return x * (y + 1) + (1 - x) * 21;
    }
    
    static void solve1() {
        double m = (21 - 20 * x) / (x - 1);
        double ans = Math.pow(x, N) * (Y + m) - m;
        System.out.println((int)(Math.ceil(ans)));
    }
    
}
