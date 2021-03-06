package nowcoder.com;

import java.util.Scanner;

/*
 * 	题目描述
									
	有些蚂蚁在一条水平线上爬行，每只蚂蚁的速率都是1cm/s。
	蚂蚁爬到水平线的任何一个端点时会立刻掉下来，
	当两只蚂蚁碰到一起时他们都会立刻调头向相反方向移动。
	已知蚂蚁们在水平线上的初始位置，但不幸的是，它们初始的运动方向是不知道的。
	请计算所有蚂蚁从水平线上掉下来可能所需的最早和最晚的时间。
	
	
									
	输入
	第一行有一个整数，表示有多少组测试数据。
	每组测试数据以两个整数开始：水平线长度(单位cm)和蚂蚁数，随后的一行中有n个整数，
	表示每只蚂蚁距水平线左端点的距离。所有的输入的整数都小于1000000而且用空格隔开。
	
	样例输入
	2
	10 3
	2 6 7
	214 7
	11 12 7 13 176 23 191
	输出
	对于每组测试数据，在同一行输出两个整数，
	第一个表示所有蚂蚁从水平线上掉下来的最早时间，第二个表示最晚时间。
	样例输出
	4 8
	38 207
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

/*
 * 	
 */

public class 百度17_爬行的蚂蚁 {
	public static void main(String[] args) {
//		solve1();
		solve2();
	}
	
	/**
	 * @method		solve1 
	 * @parameter	void
	 * @return 		void
	 * @details 	WA
	 * @details 	没有想清楚逻辑
	 */
	static void solve1() {
		Scanner sc = new Scanner(System.in);
		int timesOfTest = sc.nextInt();
		while (timesOfTest -- > 0) {
			int len = sc.nextInt();
			int num = sc.nextInt();
			if (len < num) {
				System.out.println(0);
			}
			int[] arr = new int[num];
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (int numi = 0; numi < num; numi ++) {
				arr[numi] = sc.nextInt();
				min = Math.min(arr[numi], min);
				max = Math.max(arr[numi], max);
			}
			
		}
		sc.close();
	}
	
	/**
	 * @method		solve2 
	 * @parameter	void
	 * @return 		void
	 * @details 	AC
	 */
	private static void solve2() {
		Scanner sc = new Scanner(System.in);
		for (int i = sc.nextInt() - 1; i > -1; i --) {
			int min = 0, max = 0;
			int len = sc.nextInt();
			for (int j = sc.nextInt() - 1; j > -1; j --) {
				int tem = sc.nextInt();
				min = Math.max(min, Math.min(tem, len - tem));
				max = Math.max(max, Math.max(tem, len - tem));
			}
			System.out.println(min + " " + max);
		}
		sc.close();
	}
}
