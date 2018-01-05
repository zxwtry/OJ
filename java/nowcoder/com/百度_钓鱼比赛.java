package nowcoder.com;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度_钓鱼比赛.java
 * @date        2017年7月11日 下午8:58:16
 * @details     
 */
public class 百度_钓鱼比赛 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }
    
    //cc/ss/equal
    final static String SMALL = "ss";
    final static String LARGE = "cc";
    final static String EQUAL = "equal";
    final static double NE_ZERO = -0.000001;
    final static double PO_ZERO = 0.000001;
    
    static void solve(Scanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int t = sc.nextInt();
        sc.nextLine();
        double sum = 0;
        double target = 0;
        for (int ni = 1; ni <= n; ni ++) {
            String line = sc.nextLine();
            String[] ss = line.split(" ");
            for (int mi = 1; mi <= m; mi ++) {
                double now = Double.parseDouble(ss[mi - 1]);
                if (x == ni && y == mi) {
                    target = now;
                }
                sum += now;
            }
        }
        double avgDou = sum / m / n;
        double cut = target - avgDou;
        if (cut < NE_ZERO) {
            System.out.println(SMALL);
            double pri = 1 - Math.pow(1 - avgDou, t);
            System.out.printf("%.2f\n", pri);
        } else if (cut > PO_ZERO) {
            System.out.println(LARGE);
            double pri = 1 - Math.pow(1 - target, t);
            System.out.printf("%.2f\n", pri);
        } else {
            System.out.println(EQUAL);
            double pri = 1 - Math.pow(1 - target, t);
            System.out.printf("%.2f\n", pri);
        }
    }
}
