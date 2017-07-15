package nowcoder.com;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        美团_奇数位丢弃.java
 * @date        2017年7月15日 下午10:14:33
 * @details     
 */
public class 美团_奇数位丢弃 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }

    private static void solve(Scanner sc) {
        int n = sc.nextInt();
//        realCalc(n);
        int c = 1;
        while (true) {
            int nc = 1 + 2 * c;
            if (nc > n) {
                break;
            }
            c = nc;
        }
        System.out.println(c);
    }
    
    static void realCalc(int n) {
        boolean[] a = new boolean[n + 1];
        int cnt = 0;
        while (true) {
            cnt = 0;
            int res = 0;
            for (int i = 0; i < a.length; i ++) {
                if (! a[i]) {
                    cnt ++;
                    if (cnt % 2 == 1) {
                        a[i] = true;
                    } else {
                        res ++;
                    }
                }
            }
            if (res == 1) break;
        }
        for (int i = 0; i < a.length; i ++) {
            if (! a[i]) {
                System.out.println(i);
                return;
            }
        }
        System.out.println("error");
    }
    
}
