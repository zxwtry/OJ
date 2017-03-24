package nowcoder.com;

import java.util.Scanner;

/**
 * 牛牛有两个字符串（可能包含空格）,牛牛想找出其中最长的公共连续子串,希望你能帮助他,并输出其长度。 
 * 输入描述:
 * 输入为两行字符串（可能包含空格），长度均小于等于50.
 * 输出描述:
 * 输出为一个整数，表示最长公共连续子串的长度。
 * 
 * 输入例子:
 * abcde
 * abgde
 * 
 * 输出例子:
 * 2
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        未知_最长公共连续子串.java
 * @type        未知_最长公共连续子串
 * @date        2017年3月24日 下午12:08:15
 * @details     AC
 */
public class 未知_最长公共连续子串 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        System.out.println(solve(s1, s2));
        scanner.close();
    }
    private static int solve(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        int max = 0;
        int[][] m = new int[n1 + 1][n2 + 1];
        for (int i1 = 0; i1 < n1; i1 ++) {
            for (int i2 = 0; i2 < n2; i2 ++) {
                if (s1.charAt(i1) == s2.charAt(i2)) {
                    m[i1 + 1][i2 + 1] = m[i1][i2] + 1;
                    max = Math.max(max, m[i1 + 1][i2 + 1]);
                }
            }
        }
        return max;
    }
}
