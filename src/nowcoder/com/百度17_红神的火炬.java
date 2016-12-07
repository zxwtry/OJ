package nowcoder.com;

import java.util.Scanner;

/**
 * 题目描述
									
	信奉淹神的维克塔利昂攻占了一个信奉红神的小岛，他想毁掉岛上祭祀红神的巨型火炬。
	但是他手下的红袍僧阻止了他，于是他放弃了这个决定。但是一岛不容二神，
	因此他决定把火炬围起来，禁止人们祭祀。
	每个火炬可以视为一个圆柱，半径都为R。
	所有的火炬围成一个凸多边形。
	维克塔利昂决定用绳子把他们都围起来。但他不是一个精于计算的人，
	请你帮他算算他至少需要多长的绳子。
	
									
	输入
	第一行一个整数n(1＜=n＜=100)表示火炬数量，还有一个实数R表示火炬半径。
	接下来n行给出n个火炬中心点的坐标，保证坐标按逆时针顺序给出。
	坐标绝对值不超过100.
	样例输入
	4 1
	0.0 0.0
	2.0 0.0
	2.0 2.0
	0.0 2.0
	输出
	一个实数，绳子长度，精确到小数点后两位。
	样例输出
	14.28
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度17_红神的火炬.java
 * @type        百度17_红神的火炬
 * @date        2016年11月29日 上午9:23:20
 * @details     
 */
public class 百度17_红神的火炬 {
	public static void main(String[] args) {
		solve1();
	}

	/**
	 * @method      solve1
	 * @parameter   
	 * @return      void
	 * @details     AC
	 */
	static void solve1() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		double R = sc.nextDouble();
		double[] x = new double[n];
		double[] y = new double[n];
		for (int ni = 0; ni < n; ni ++) {
			x[ni] = sc.nextDouble();
			y[ni] = sc.nextDouble();
		}
		double dist = 0.0;
		for (int i2 = 0; i2 < n; i2 ++) {
			int i3 = i2 == n - 1 ? 0 : i2 + 1;
			dist += Math.sqrt((x[i2] - x[i3]) * (x[i2] - x[i3]) + (y[i2] - y[i3]) * (y[i2] - y[i3]));
		}
		dist += 2 * Math.PI * R;
		System.out.printf("%.2f\r\n", dist);
		sc.close();
	}
}
