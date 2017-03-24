package nowcoder.com;

import java.util.Scanner;

/**
 * 牛牛在二维坐标系中画了N个点，且都是整点。现在牛牛想画出一个矩形，使得这N个点都在矩形内或者在矩形上。
 * 矩形的边均平行于坐标轴。牛牛希望矩形的面积最小。请你帮助牛牛计算下最小矩形的面积。 
 * 输入描述:
 * 首先输入一个正整数N表示点的个数（2 <= N <= 50）
 *   
 * 接下来N行每行两个整数x, y，表示该点的坐标。绝对值均小于等于100.
 * 
 * 
 * 输出描述:
 * 一个整数表示最小矩形的面积。
 * 
 * 输入例子:
 * 2
 * 0 1
 * 1 0
 * 
 * 输出例子:
 * 1
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        未知_最小的矩形.java
 * @type        未知_最小的矩形
 * @date        2017年3月24日 下午1:30:00
 * @details     AC
 */
public class 未知_最小的矩形 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int xmin = Integer.MAX_VALUE;
        int xmax = Integer.MIN_VALUE;
        int ymin = Integer.MAX_VALUE;
        int ymax = Integer.MIN_VALUE;
        int x = 0, y = 0;
        for (int i = 0; i < n; i ++) {
            x = scanner.nextInt();
            y = scanner.nextInt();
             xmin = Math.min(x, xmin);
             xmax = Math.max(x, xmax);
             ymin = Math.min(y, ymin);
             ymax = Math.max(y, ymax);
        }
        System.out.println((xmax - xmin ) * (ymax - ymin ));
        scanner.close();
    }
}
