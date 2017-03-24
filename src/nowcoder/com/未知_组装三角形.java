package nowcoder.com;

import java.util.Scanner;

/**
 * 牛牛手里有N根木棒,分别编号为1~N,现在他从N根里想取出三根木棒，使得三根木棒构成一个三角形,你能计算出牛牛有多少种取法吗?(考虑两种取法中使用的木棒编号有一个不一样就认为是不同的取法)。 
 * 输入描述:
 * 首先输入一个正整数N，接下来的一行共有N个正整数表示每个木棒的长度。
 * 
 * N ≤ 50, 木棒的长度 ≤ 10000.
 * 
 * 
 * 输出描述:
 * 输出一个整数表示方法数。
 * 
 * 输入例子:
 * 5
 * 1 2 3 4 5
 * 
 * 输出例子:
 * 3
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        未知_组装三角形.java
 * @type        未知_组装三角形
 * @date        2017年3月24日 下午1:11:53
 * @details     AC
 */
public class 未知_组装三角形 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i ++)
            arr[i] = scanner.nextInt();
        System.out.println(solve1(arr, n));
        scanner.close();
    }
    
    private static int solve1(int[] arr, int n) {
        int count = 0;
        for (int i = 0; i < n; i ++) {
            for (int j = i + 1; j < n; j ++) {
                for (int k = j + 1; k < n; k ++) {
                    int a = arr[i];
                    int b = arr[j];
                    int c = arr[k];
                    if (c > Math.abs(a - b) && c < a + b)
                        count ++;
                }
            }
        }
        return count;
    }

//    static int solve(int[] arr, int n) {
//        Arrays.sort(arr);
//        int count = 0;
//        for (int i = 0; i < n; i ++) {
//            if (i != 0 && arr[i - 1] == arr[i]) continue;
//            for (int j = i + 1; j < n; j ++) {
////                if (j != i + 1 && arr[j - 1] == arr[j]) continue;
//                int c_min = Math.max(arr[j] - arr[i] + 1, arr[j]);
//                int c_max = Math.min(arr[n - 1], arr[i] + arr[j] - 1) + 1;
//                int min_index = Arrays.binarySearch(arr, c_min);
//                min_index = min_index < 0 ? -(min_index + 1) : min_index;
//                int max_index = Arrays.binarySearch(arr, c_max);
//                max_index = max_index < 0 ? -(max_index + 1) : max_index;
//                count += max_index - min_index - 1;
//                //System.out.println(count);
//            }
//        }
//        return count;
//    }
    
}
