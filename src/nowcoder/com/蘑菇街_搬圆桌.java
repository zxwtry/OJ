package nowcoder.com;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        蘑菇街_搬圆桌.java
 * @date        2017年7月11日 下午10:04:06
 * @details     
 */
public class 蘑菇街_搬圆桌 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            sovle(sc);
        }
        sc.close();
    }

    private static void sovle(Scanner sc) {
        double r = sc.nextInt();
        double x = sc.nextInt();
        double y = sc.nextInt();
        double x1 = sc.nextInt();
        double y1 = sc.nextInt();
        double xy = Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
        System.out.println((int)Math.ceil(xy / r / 2));
    }
}
