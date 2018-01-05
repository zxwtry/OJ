package stl;

/*
 * 	POJ 3617
 */

import java.util.Scanner;

public class Book1_2_2_贪心_2_2_3_字典序最小的问题 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		st0 = new StringBuilder();
		while (n -- > 0) {
			st0.append(sc.next().trim());
		}
		st1 = new StringBuilder(st0);
		st1.reverse();
		System.out.println(solve());
		sc.close();
	}
	static StringBuilder st0, st1;
	static String solve() {
		StringBuilder ans = new StringBuilder(st0.length());
		while (st0.length() > 0) {
			int com = comp(st0, st1);
			if (com < 0) {
				ans.append(st0.charAt(0));
				st0.deleteCharAt(0);
				st1.deleteCharAt(st1.length()-1);
			} else {
				ans.append(st1.charAt(0));
				st1.deleteCharAt(0);
				st0.deleteCharAt(st0.length()-1);
			}
		}
		final int ts = ans.length() / 80;
		for (int ti = 0; ti < ts; ti ++) {
			int tin = ti + 80 * (ti + 1);
			ans.insert(tin, '\n');
		}
		return ans.toString();
	}
	static int comp(StringBuilder st0, StringBuilder st1) {
		for (int sti = 0; sti < st0.length(); sti ++) {
			char c0 = st0.charAt(sti), c1 = st1.charAt(sti);
			if (c0 != c1)
				return c0 - c1;
		}
		return 0;
	}
}
