package nowcoder.com;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        未知_平衡数.java
 * @type        未知_平衡数
 * @date        2017年3月24日 下午1:36:19
 * @details     AC
 */
public class 未知_平衡数 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //while (scanner.hasNextInt()) {
            int val = scanner.nextInt();
            if (val <= 9) {
                System.out.println("NO");
            } else {
                char[] cs = String.valueOf(val).toCharArray();
                int v1 = 0;
                int v2 = 0;
                for (int i = 1; i < cs.length; i ++) {
                    v1 = 1;
                    for (int j = 0; j < i; j ++)
                        v1 *= cs[j] - '0';
                    v2 = 1;
                    for (int j = i; j < cs.length; j ++)
                        v2 *= cs[j] - '0';
                    if (v1 == v2) {
                        System.out.println("YES");
                        break;
                    }
                }
                if (v1 != v2)
                    System.out.println("NO");
            }
        //}
        scanner.close();
    }
}
