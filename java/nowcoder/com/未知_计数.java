package nowcoder.com;

import java.util.Scanner;

public class 未知_计数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }

    private static void solve(Scanner sc) {
        long x = sc.nextLong();
        long f = sc.nextLong();
        long d = sc.nextLong();
        long p = sc.nextLong();
        long a = d / x;
        long ans = Math.min(a, f);
        if (ans < f) {
            System.out.println(ans);
        } else {
            ans = Math.max(ans, (d + p*f) / (x + p));
            System.out.println(ans);
        }
    }
}   
