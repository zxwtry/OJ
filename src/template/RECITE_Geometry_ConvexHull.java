package template;

import java.util.Scanner;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        Geometry_ConvexHull.java
 * @type        Geometry_ConvexHull
 * @date        2016年12月5日 上午9:15:16
 * @details     凸包
 * @details     这里采用的测试是:http://acm.hdu.edu.cn/showproblem.php?pid=1392
 */
public class RECITE_Geometry_ConvexHull {
	final static int cut = 102;
	final static double[] xs = new double[cut*2 + 1];	//  0~cut-1     输入
	final static double[] ys = new double[cut*2 + 1];	//cut~cut*2-1凸包	
	final static double D0 = 0.000000001;
	static int n, top;
	static double distSquare(int i, int j) {		//距离平方
		return (xs[i] - xs[j]) * (xs[i] - xs[j]) + (ys[i] - ys[j]) * (ys[i] - ys[j]);
	}
	static double multiply(int i, int j, int k) {	//叉积 (ij X ik)
		return (xs[j] - xs[i]) * (ys[k] - ys[i]) - (ys[j] - ys[i]) * (xs[k] - xs[i]);
	}
	static int cmp(int i, int j) {					//比较
		double m = multiply(0, i, j);
		if (m < -D0) return 1;
		else if (Math.abs(m) <= D0 && distSquare(0, i) < distSquare(0, j)) return 1;
		else return -1;
	}
	static void convexHull() {						//凸包
		int i = 0;
		for (; i < 3; i ++) {
			xs[i+cut] = xs[i];
			ys[i+cut] = ys[i];
		}
		top = 2;
		for (; i <= n; i ++) {
			while (multiply(top-1+cut, top+cut, i) <= D0) top --;
			top ++;
			xs[top+cut] = xs[i];
			ys[top+cut] = ys[i];
		}
	}
	static void swap(double[] arr, int i, int j) {
		double t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	static void qsort(int i, int j) {
		if (i < j) {
			int p = pa(i, j);
			qsort(i, p - 1);
			qsort(p + 1, j);
		}
	}
	private static int pa(int i, int j) {
		xs[2*cut] = xs[i];
		ys[2*cut] = ys[i];
		while (i < j) {
			while (i < j && cmp(2*cut, j) <= 0) j --;
			xs[i] = xs[j]; ys[i] = ys[j];
			while (i < j && cmp(i, 2*cut) <= 0) i ++;
			xs[j] = xs[i]; ys[j] = ys[i];
		}
		xs[i] = xs[2*cut];
		ys[i] = ys[2*cut];
		return i;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			n = sc.nextInt();
			if (n == 0) break;
			int ymin = 0;
			for (int ni = 0; ni < n; ni ++) {
				xs[ni] = sc.nextDouble();
				ys[ni] = sc.nextDouble();
				if (ys[ni] < ys[ymin] || (ys[ni] == ys[ymin] && xs[ni] < xs[ymin])) {
					ymin = ni;
				}
			}
			swap(xs, 0, ymin);
			swap(ys, 0, ymin);
			qsort(1, n-1);
			xs[n]=xs[0];
			ys[n]=ys[0];
			convexHull();
			double len = 0.0;
			for (int i = cut; i < top + cut; i ++)
				len += Math.sqrt(distSquare(i, i + 1));
			System.out.printf("%.2f\r\n", n == 2 ? len/2 : len);
		}
		sc.close();
	}
}
