package nowcoder.com;

/*
 * 	2
5;-1,0;0,1;1,0;-1,0;0,0
1
5;0,-1;1,0;0,1;0,-1;2,2
 */
import java.util.*;
public class 华为160909_2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = Integer.parseInt(scan.nextLine());
		while (num -- > 0) {
			String line = scan.nextLine();
			String[] p1 = line.split(";");
			int n = Integer.parseInt(p1[0]);
			int[] x = new int[n];
			int[] y = new int[n];
			for (int i = 0; i < n; i ++) {
				String[] p2 = p1[i + 1].split(",");
				x[i] = Integer.parseInt(p2[0]);
				y[i] = Integer.parseInt(p2[1]);
			}
			int isDone = 0;
			for (int i = 0; isDone == 0 && i < n - 1; i ++)
				for (int j = i + 1; isDone == 0 && j < n - 1; j ++) {
					double max = Integer.MIN_VALUE;
					double max0 = cal(x, y, i, j, 0);
					for (int k = 1; k < n - 1; k ++) {
						double temp = cal(x, y, i, j, k);
						if (temp * max0 < 0)
							isDone = 1;
						max = Math.max(max, Math.abs(cal(x, y, i, j, k)));
					}
					if (cal(x, y, i, j, n - 1) > max)
						isDone = 2;
				}
			System.out.println(isDone != 0 ? 0 : 1);
		}
		scan.close();
	}
	static double cal(int[]x, int[] y, int i, int j, int k) {
		return (x[i] - x[j]) * y[k] + (y[j] - y[i]) * x[k]/Math.sqrt((double)(x[i] - x[j])*(x[i] - x[j]) +(y[j] - y[i]) * (y[j] - y[i]));
	}
}
