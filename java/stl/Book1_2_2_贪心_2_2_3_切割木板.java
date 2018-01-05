package stl;

import java.util.Scanner;

public class Book1_2_2_贪心_2_2_3_切割木板 {
	static long l[] = new long[20001];
	static int n;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		for (int ni = 0; ni < n; ni ++) {
			l[ni] = scanner.nextLong();
		}
		aj();
		long ans = 0;
		while (n > 1) {
			long p1 = min();
			long p2 = min();
			add(p1+p2);
			ans += p1+p2;
		}
		System.out.println(ans);
		scanner.close();
	}
	static void aj() {
		for (int nI = (n-2) >> 1; nI >= 0; nI --) {
			int mc = (nI << 1) + 1;
			if (mc + 1 < n && l[mc + 1] < l[mc])
				mc = mc + 1;
			if (l[mc] < l[nI]) {
				rev(mc, nI);
				ah(mc);
			}
		}
	}
	static long min() {
		long re = l[0];
		l[0] = l[--n];
		ah(0);
		return re;
	}
	static void ah(int ni) {
		int mc = (ni << 1) + 1;
		if (mc >= n) return;
		if (mc + 1 < n && l[mc + 1] < l[mc])
			mc = mc + 1;
		if (l[mc] < l[ni]) {
			rev(mc, ni);
			ah(mc);
		}
	}
	static void add(long d) {
		l[n++] = d;
		uj(n-1);
	}
	static void uj(int ni) {
		if (ni == 0)  return;
		int np = (ni - 1) >> 1;
		if (l[np] > l[ni]) {
			rev(np, ni);
			uj(np);
		}
	}
	static void rev(int n1, int n2) {
		long tmp = l[n1];
		l[n1] = l[n2];
		l[n2] = tmp;
	}
}
