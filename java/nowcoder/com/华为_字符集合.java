package nowcoder.com;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        华为_字符集合.java
 * @date        2017年7月13日 上午11:10:45
 * @details     
 */
public class 华为_字符集合 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }

    private static void solve(Scanner sc) {
        String l = sc.nextLine();
        //abcqweracb
        boolean[] map = new boolean[128];
        StringBuilder st = new StringBuilder();
        int ln = l.length();
        for (int i = 0; i < ln; i ++) {
            char c = l.charAt(i);
            if (! map[c]) {
                map[c] = true;
                st.append(c);
            }
        }
        System.out.println(st.toString());
    }
}
