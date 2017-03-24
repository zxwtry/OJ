package nowcoder.com;

import java.util.Scanner;

/**
 * 牛牛想在[a, b]区间内找到一些数满足可以被一个整数c整除,现在你需要帮助牛牛统计区间内一共有多少个这样的数满足条件？ 
 * 输入描述:
 * 首先输入两个整数a,b,（-5*10^8 ≤ a ≤ b ≤ 5*10^8)
 * 接着是一个正整数c（1 <= c <= 1000）
 * 
 * 
 * 输出描述:
 * 输出一个整数表示个数。
 * 
 * 输入例子:
 * 0 14 5
 * 
 * 输出例子:
 * 3
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        未知_找整除.java
 * @type        未知_找整除
 * @date        2017年3月24日 下午12:19:00
 * @details     AC
 */
public class 未知_找整除 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        if (a <= 0 && b >= 0) {
            System.out.println(solve(1, -a, c) + 1 + solve(1, b, c));
        } else {
            System.out.println(solve(a, b, c));
        }
        scanner.close();
    }
    private static int solve(int a, int b, int c) {
        if (a > b) return 0;
        int v = b - a;
        int count = b % c == 0 ? 1 : 0;
        count += v / c;
        return count;
    }
}
