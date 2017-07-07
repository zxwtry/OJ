package nowcoder.com;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        微软_NumericKeypad.java
 * @date        2017年7月7日 上午8:47:20
 * @details     
 */
public class 微软_NumericKeypad {
    static final int MAXX = 4;
    static final int MAXY = 3;
    static char[][] m = {
        {'\0', '0', '\0'},
        {'9', '8', '7'},
        {'6', '5', '4'},
        {'3', '2', '1'}, 
    };
    static int[] nx = {0, 3, 3, 3, 2, 2, 2, 1, 1, 1};
    static int[] ny = {1, 2, 1, 0, 2, 1, 0, 2, 1, 0};
    static char[][][] c = {
        {"".toCharArray(), "0".toCharArray(), "0".toCharArray()},
        {"9".toCharArray(), "089".toCharArray(), "0789".toCharArray()},
        {"69".toCharArray(), "05689".toCharArray(), "0456789".toCharArray()},
        {"369".toCharArray(), "0235689".toCharArray(), "0123456789".toCharArray()},
    };
    //x只能减      y只能减
    static boolean allEqual = true;
    static int sl = 0;
    static char[] ans = null;
    static boolean done = false;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i ++) {
            String s = sc.next();
            allEqual = true;
            done = false;
            sl = s.length();
            ans = new char[sl];
            solve(s, 0, MAXX - 1, MAXY - 1);
            System.out.println(new String(ans));
        }
        sc.close();
    }
    private static boolean solve(String s, int si, int x, int y) {
        if (done) return true;
        if (si == sl) {
            done = true;
            return true;
        }
        char[] cxy = c[x][y];
        char sc = s.charAt(si);
        if (allEqual) {
            int cxyi = Arrays.binarySearch(cxy, sc);
            if (cxyi < 0) {
                cxyi = (-cxyi) - 2;
                allEqual = false;
                if (cxyi < 0) return false;
            }
            for (int k = cxyi; k > -1; k --) {
                ans[si] = cxy[k];
                if (solve(s, si+1, nx[ans[si] - '0'], ny[ans[si] - '0'])) {
                    return true;
                }
            }
            return false;
        } else {
            ans[si] = cxy[cxy.length - 1];
            return solve(s, si+1, x, y);
        }
    }
}
