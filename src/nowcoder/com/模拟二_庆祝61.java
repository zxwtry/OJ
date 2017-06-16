package nowcoder.com;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        模拟二_庆祝61.java
 * @date        2017年6月16日 下午9:15:00
 * @details     AC
 */
public class 模拟二_庆祝61 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {//注意while处理多个case
//            int a = in.nextInt();
//            int b = in.nextInt();
//            System.out.println(a + b);
//        }
//    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {//注意while处理多个case
//            int a = in.nextInt();
//            int b = in.nextInt();
//            System.out.println(a + b);
//        }
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i ++) {
            arr[i] = in.nextInt();
        }
        int[] cpy = new int[n];
        Arrays.sort(arr);
        int ci = 0;
        for (int i = 0; i < n; i += 2) {
            cpy[ci ++] = arr[i];
        }
        ci = n-1;
        for (int i = 1; i < n; i += 2) {
            cpy[ci --] = arr[i];
        }
        int ans = 0;
        for (int i = 1; i < n; i ++) {
            ans = Math.max(Math.abs(cpy[i] - cpy[i-1]), ans);
        }
        ans = Math.max(Math.abs(cpy[n-1] - cpy[0]), ans);
        System.out.println(ans);
        in.close();
    }
}
