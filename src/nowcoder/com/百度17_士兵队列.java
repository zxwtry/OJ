package nowcoder.com;

import java.util.Scanner;

/**
 * 题目描述
										
	一队士兵在操场上排成一列，士兵总数为n，士兵按照队伍从前往后的顺序从1到n依次编号。
	每个士兵有各自的身高，第i个士兵的身高为ai。
	士兵列队完毕后，将军走到队列的最前面。因为身高不一，有些士兵可能被前面身高更高的挡住了，
	这样将军就看不到他们。将军能看到某个士兵当且仅当他的身高严格大于他前面的所有士兵。
	问将军一共能看到多少个士兵。
	
									
	输入
	第一行输入一个整数T（T＜=100），表示测试数据的组数。
	每组数据第一行输入一个数n（1=＜n＜=10000）表示士兵的个数，
	第二行n个整数a1,a2,...,an（0=＜ai＜=1000000000），依次表示每一个士兵的身高。
	样例输入
	3
	4
	1 2 3 4
	3
	1 1 1
	4
	1 1 3 2
	
	输出
	对于每组数据，输出一行，将军能看到的士兵数。
	样例输出
	4
	1
	2
	
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度17_士兵队列.java
 * @type        百度17_士兵队列
 * @date        2016年11月28日 下午7:54:17
 * @details     
 */
public class 百度17_士兵队列 {
	public static void main(String[] args) {
		solve1();
	}

	/**
	 * @method      solve1
	 * @parameter   
	 * @return      void
	 * @details     AC
	 * @details     时间：O(N)
	 * @details     空间：O(1)
	 */
	static void solve1() {
		Scanner sc = new Scanner(System.in);
		for (int times = sc.nextInt(); times > 0; times --) {
			int n = sc.nextInt();
			int max = Integer.MIN_VALUE;
			int count = 0;
			for (int index = 0; index < n; index ++) {
				int v = sc.nextInt();
				if (v > max) {
					max = v;
					count ++;
				}
			}
			System.out.println(count);
			
		}
		sc.close();
	}
}
