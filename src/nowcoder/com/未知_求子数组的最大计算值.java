package nowcoder.com;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        未知_求子数组的最大计算值.java
 * @date        2017年8月22日 下午11:29:38
 * @details     第一行：数组有多少个数字
 * @details     第二行：数组中N个数字，空格分割
 * @details     求：所有子数组中：最小值*子数组之和 ---的最大值
 */
public class 未知_求子数组的最大计算值 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                if (! solve(br)) {
                    break;
                }
            } catch (Throwable e) {
                break;
            }
        }
        
        br.close();
        
    }

    final static int N = 500500;
    final static int[] ps = new int[N];
    static long ans = 0;
    
    private static boolean solve(BufferedReader br) throws Exception {
        String l = br.readLine();
        if (l == null) {
            return false;
        }
        int n = Integer.parseInt(l);
        String line = br.readLine();
        int i = 0;
        int v = 0;
        for (int len = line.length(), j = 0; j <= len; j ++) {
            char c = j == len ? ' ' : line.charAt(j);
            if (c == ' ') {
                ps[i] = v;
                v = 0;
                i ++;
            } else {
                v = v * 10 + c - '0';
            }
        }
        ans = ps[0] * ps[0];
        search(0, n-1);
        System.out.println(ans);
        return true;
    }

    private static void search(int i, int j) {
        if (i > j) {
            return;
        }
        if (i == j) {
            ans = Math.max(ans, ps[i] * ps[i]);
            return;
        }
        int min = i;
        long sum = ps[i];
        for (int k = i + 1; k <= j; k ++) {
            if (ps[k] < ps[min]) {
                min = k;
            }
            sum += ps[k];
        }
        ans = Math.max(ans, ps[min] * sum);
        search(i, min - 1);
        search(min + 1, j);
    }
    
}
