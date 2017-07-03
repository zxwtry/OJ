package nowcoder.com;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        爱奇艺_170514_C.java
 * @type        爱奇艺_170514_C
 * @date        2017年5月14日 下午10:33:31
 * @details     
 */
public class 爱奇艺_170514_C {
    
    static Integer[] is = null;
    static int[] arr = null;
    static class MC implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(arr[o1], arr[o2]);
        }
    }
    static int ans = 0;
    static boolean[] m = null;
    static int n = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        arr = new int[n+2];
        is = new Integer[n+2];
        int ai = 1;
        arr[0] = arr[n+1] = 1;
        for (ai = 1; ai <= n; ai ++) {
            arr[ai] = in.nextInt();
            is[ai] = ai;
        }
        Comparator<Integer> mc = new MC();
        Arrays.sort(is, 1, n+1, mc);
        m = new boolean[n+2];
        search(0, 0, 1);
        System.out.println(ans);
        in.close();
    }
    private static void search(int sum, int cnt, int index) {
        if (cnt == n) return;
        Integer v = null;
        for (int i = 1; i <= n; i ++) {
            if (m[is[i]]) continue;
            if (v != null && arr[v] != arr[is[i]]) break;
            if (v == null) v = is[i];
            m[is[i]] = true;
//            int nsum = arr[is[i]] * arr[is[i] - 1] * arr[is[i] + 1] + sum;
            int nsum = arr[is[i]] * getL(i) * getR(i) + sum;
            ans = Math.max(nsum, ans);
            search(nsum, cnt, i+1);
            m[is[i]] = false;
        }
    }
    static int getL(int i) {
        int v = is[i] - 1;
        while (m[v ]) v --;
        if (v < 1) v = 1;
        return arr[v];
    }
    static int getR(int i) {
        int v = is[i] + 1;
        while (m[v ]) v ++;
        if (v > n) v = n;
        return arr[v];
    }
}
