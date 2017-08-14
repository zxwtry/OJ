package nowcoder.com;

import java.util.Scanner;

public class 未知_DP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }
    
    static int[][] m = new int[12][100005];
    static int MOD = 1000000007;
    
    static {
        for (int j = 0; j < m[0].length; j ++) {
            m[1][j] = 1;
        }
    }
    
    private static void solve(Scanner sc) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        long ps = k;
        long ns = 0;
        for (int i = 2; i <= n; i ++) {
            ns = 0;
            for (int j = 1; j <= k; j ++) {
                long remain = k - j;
                long boom = remain / j;
                long nows = ps;
                for (int v = 1; v <= boom; v ++) {
                    nows  -= m[i - 1][j + v * j];
                    if (nows < 1) {
                        nows = nows + MOD;
                    }
                }
                nows = (nows + MOD) % MOD;
                m[i][j] = (int)nows;
                ns += nows;
                ns = (ns + MOD) % MOD;
            }
            ps = ns;
        }
        System.out.println(ps);
    }
}   
