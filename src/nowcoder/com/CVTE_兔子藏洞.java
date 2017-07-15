package nowcoder.com;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        CVTE_兔子藏洞.java
 * @date        2017年7月15日 上午9:28:37
 * @details     
 */
public class CVTE_兔子藏洞 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }

    private static void solve(Scanner sc) {
        int x = sc.nextInt() - 1;
        int n = sc.nextInt();
        boolean[] arr = new boolean[20];
        arr[x] = true;
        int cnt = 1;
        int poi = x;
        for (int i = 2; i <= n; i ++) {
            poi += i;
            poi = poi % arr.length;
            if (! arr[poi]) {
                cnt ++;
                arr[poi] = true;
            }
            if (cnt == arr.length) {
                break;
            }
        }
        if (cnt == arr.length) {
            System.out.println(-1);
        } else {
            StringBuilder st = new StringBuilder();
            for (int i = 0; i < arr.length; i ++) {
                if (! arr[i]) {
                    st.append(i + 1);
                    st.append(' ');
                }
            }
            System.out.println(st.toString());
        }
    }
}
