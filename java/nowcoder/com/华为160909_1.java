package nowcoder.com;

import java.util.*;
public class 华为160909_1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			int a = scan.nextInt(), b = scan.nextInt();
			int c = scan.nextInt(), d = scan.nextInt();
			a = a >= 0 && a <= 150 ? a : 0;
			b = b >= 0 && b <= 150 ? b : 0;
			c = c >= 0 && c <= 150 ? c : 0;
			d = d >= 0 && d <= 150 ? d : 0;
			int sum = 0;
			System.out.println((sum = (a+b+c+d)) + " "+ sum / 4);
		}
		scan.close();
	}
}