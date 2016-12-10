package template;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_Geometry_ConvexHull2.java
 * @type        RECITE_Geometry_ConvexHull2
 * @date        2016年12月9日 下午10:17:10
 * @details     
 */
public class RECITE_Geometry_ConvexHull2 {
	final static int cut = 105;
	final static P[] p = new P[2 * cut + 1];
	static int top, n;
	public static void main(String[] args) throws FileNotFoundException {
//		Scanner sc = new Scanner(new File("D:/code/data/Template_ConvexHull.txt"));
		Scanner sc = new Scanner(System.in);
		while (true) {
			n = sc.nextInt();
			if (n == 0) break;
			int imin = 0;
			for (int i = 0; i < n; i ++) {
				int x = sc.nextInt(), y = sc.nextInt();
				set(i, x, y);
				if (y < p[imin].y || (y == p[imin].y && x < p[imin].x)) imin=i;
			}
			swap(0, imin);
			Arrays.sort(p, 1, n);
			set(n, p[0].x, p[0].y);
			convexHull();
			double ans = 0.0;
			for (int i = cut; i < top+cut; i ++)
				ans += Math.sqrt(distSquare(p[i], p[i+1]));
			System.out.printf("%.2f\r\n", n == 2 ? ans / 2 : ans);
		}
		sc.close();
	}
	private static void convexHull() {
		int i = 0;
		for (; i < 3; i ++) set(cut+i, p[i].x, p[i].y);
		top = 2;
		for (; i <= n; i ++) {
			int m = multiply(p[cut+top-1], p[cut+top], p[i]);
			if (m == 0) {
				if (distSquare(p[cut+top-1], p[cut+top]) < distSquare(p[cut+top-1], p[i]))
					set(cut+top, p[i].x, p[i].y);
			} else {
				while (m < 0) {
					top --;
					m = multiply(p[cut+top-1], p[cut+top], p[i]);
				}
				top ++;
				set(cut+top, p[i].x, p[i].y);
			}
		}
	}
	static void swap(int i, int j) {
		P t = p[i];
		p[i] = p[j];
		p[j] = t;
	}
	static void set(int i, int x, int y) {
		if (p[i] == null) {
			p[i] = new P(x, y);
		} else {
			p[i].x = x;
			p[i].y = y;
		}
	}
	static int multiply(P p1, P p2, P p3) {
		return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
	}
	static int distSquare(P p1, P p2) {
		return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
	}
	static class P implements Comparable<P>{
		int x, y;
		public P(int x, int y) {this.x = x; this.y = y;}
		@Override
		public int compareTo(P o) {
			int m = multiply(p[0], this, o);
			if (m < 0) return 1;
			else if (m == 0 && distSquare(p[0], this) < distSquare(p[0], o)) return 1;
			else return -1;
		}
		@Override
		public String toString() {
			return this.x+"..."+this.y;
		}
	}
}
