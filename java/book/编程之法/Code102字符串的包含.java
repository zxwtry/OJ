package book.编程之法;

import java.util.Arrays;

public class Code102字符串的包含 {
	static boolean[] bls = new boolean[26];
	public static void main(String[] args) {
		solve("ABCDEF".toCharArray(), "AAAANBBBBFFF".toCharArray());
	}
	static void solve(char[] ch1, char[] ch2) {
		Arrays.fill(bls, false);
		for (int in1 = ch1.length-1; in1 >= 0; in1 --)
			bls[ch1[in1]-'A'] = true;
		boolean mat = true;
		for (int in2 = ch2.length-1; in2 >= 0; in2 --)
			mat &= bls[ch2[in2]-'A'];
		System.out.println(mat);
	}
}
