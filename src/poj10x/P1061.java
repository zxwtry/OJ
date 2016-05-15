package poj10x;

import java.util.Scanner;

public class P1061 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long step = 3, girth = 18;
		System.out.println("x: " + x +"   y: " +y +"   ans:" + exgcd(step, girth));
//		exgcd(step, girth);
		scanner.close();
	}

	static long x, y;
	private static long exgcd(long step, long girth) {
		if (girth == 0) {
			x = 1;
			y = 0;
			return step;
		}
		long r = exgcd(girth, step % girth);
		long t = x;
		x = y;
		y = t - step / girth * y;
		return r;
	}
}
