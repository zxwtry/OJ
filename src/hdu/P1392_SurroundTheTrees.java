package hdu;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     hdu
 * @file        P1392.java
 * @type        P1392
 * @date        2016年12月11日 下午4:50:39
 * @details     http://acm.hdu.edu.cn/showproblem.php?pid=1392
 * @details     AC
 */
public class P1392_SurroundTheTrees {
	final static int cut = 105;
	final static int[] xs = new int[2 * cut + 1];
	final static int[] ys = new int[2 * cut + 1];
	static int n, top;
	static int distSquare(int i , int j) {
		return ( xs[i] - xs[j] ) * ( xs[i] - xs[j] ) + ( ys[i] - ys[j] ) * ( ys[i] - ys[j] );
	}
	static int multiply(int i, int j, int k) {
		return (xs[j] - xs[i]) * (ys[k] - ys[i]) - (xs[k] - xs[i]) * (ys[j] - ys[i]);
	}
	static int cmp (int i, int j ){
		int m = multiply(0, i, j);
		if (m < 0) return 1;
		else if (m == 0 && distSquare(0, i) < distSquare(0, j)) return 1;
		else return -1;
	}
	static void qsort(int i, int j) {
		if (i < j) {
			int p = pa(i, j);
			qsort(i, p - 1);
			qsort(p + 1, j);
		}
	}
	private static int pa(int i, int j) {
		xs[2 * cut] = xs[i];
		ys[2 * cut] = ys[i];
		while (i < j) {
			while (i < j && cmp(2 * cut, j) <= 0) j --;
			xs[i] = xs[j];
			ys[i] = ys[j];
			while (i < j && cmp(i, 2 * cut) <= 0) i ++;
			xs[j] = xs[i];
			ys[j] = ys[i];
		}
		xs[i] = xs[2 * cut];
		ys[i] = ys[2 * cut];
		return i;
	}
	static void convexHull() {
		int i = 0; 
		for (; i < 3; i ++ ) {
			xs[cut+i] = xs[i];
			ys[cut+i] = ys[i];
		}
		top = 2;
		for (; i <= n; i ++) {
			int m = multiply(cut+top-1, cut+top, i);
			if (m == 0) {
				if (distSquare(cut+top-1, cut+top) < distSquare(cut+top-1, i)) {
					xs[cut+top] = xs[i];
					ys[cut+top] = ys[i];
				}
			} else  {
				while (m < 0) {
					top --;
					m = multiply(cut+top-1, cut+top, i);
				}
				top ++;
				xs[cut+top] = xs[i];
				ys[cut+top] = ys[i];
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			n = sc.nextInt();
			if (n == 0) break;
			int ymin = 0;
			for (int ni = 0; ni < n; ni ++) {
				xs[ni] = sc.nextInt();
				ys[ni] = sc.nextInt();
				if (ys[ni] < ys[ymin] || (ys[ni] == ys[ymin] && xs[ni] < xs[ymin])) ymin = ni;
			}
			swap(xs, ymin, 0);
			swap(ys, ymin, 0);
			xs[n] = xs[0];
			ys[n] = ys[0];
			qsort(1, n - 1);
			convexHull();
			double ans = 0.0;
			for (int i = cut; i < top + cut; i ++) {
				ans += Math.sqrt(distSquare(i, i + 1));
			}
			System.out.printf("%.2f\r\n", n == 2 ? ans / 2 : ans);
		}
		sc.close();
	}
	static void swap(int[] ar, int i , int j) {
		int t = ar[i];
		ar[i] = ar[j];
		ar[j] = t;
	}
}
