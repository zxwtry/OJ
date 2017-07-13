package nowcoder.com;

import java.util.Arrays;
import java.util.Scanner;
 
/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        华为_数独.java
 * @date        2017年7月13日 下午10:23:29
 * @details     
 */
public class 华为_数独 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }
 
    static boolean[] c = new boolean[10];
    static char[] one = new char[18];
    static int[][] m = new int[9][9];
    
    private static void solve(Scanner sc) {
        for (int i = 0; i < 9; i ++) {
            String line = sc.nextLine();
            for (int j = 0; j < 9; j ++) {
                m[i][j] = line.charAt(2 * j) - '0';
            }
        }
       search(m, 0, 0);
        for (int i = 0; i < 9; i ++) {
            int oi = 0;
            for (int j = 0; j < 9; j ++) {
                one[oi ++] = (char)('0' + m[i][j]);
                one[oi ++] = j == 8 ? '\n' : ' ';
            }
            System.out.print(new String(one));
        }
    }
     
    private static boolean search(int[][] m, int i, int j) {
        if (j == 9) return search(m, i + 1, 0);
        if (i == 9) return true;
        if (m[i][j] != 0) return search(m, i, j + 1);
        int[] si = calc(m, i, j);
        if (si.length == 0) return false;
        for (int k = 0; k < si.length; k ++) {
            m[i][j] = si[k];
            if (search(m, i, j + 1)) return true;
        }
        m[i][j] = 0;
        return false;
    }
     
    private static int[] calc(int[][] m, int i, int j) {
        int cnt = 0;
        int ii = (i / 3) * 3;
        int jj = (j / 3) * 3;
        Arrays.fill(c, false);
        for (int mi = 0; mi < 3; mi ++) {
            for (int mj = 0; mj < 3; mj ++) {
                if (m[ii + mi][jj + mj] != 0) {
                    c[m[ii + mi][jj + mj]] = true;
                }
            }
        }
        for (int k = 0; k < 9; k ++) {
            if (m[k][j] != 0) {
                c[m[k][j]] = true;
            }
        }
        for (int k = 0; k < 9; k ++) {
            if (m[i][k] != 0) {
                c[m[i][k]] = true;
            }
        }
        for (int k = 1; k <= 9; k ++) cnt += c[k] ? 0 : 1;
        int[] ans = new int[cnt];
        if (cnt == 0) return ans;
        int ai = 0;
        for (int k = 1; k <= 9; k ++) {
            if (! c[k]) {
                ans[ai ++] = k;
            }
        }
        return ans;
    }
}