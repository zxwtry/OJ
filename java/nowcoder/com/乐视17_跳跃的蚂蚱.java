package nowcoder.com;



/*
 * 										
	小B对脑筋急转弯问题很有兴趣，她觉得这种问题的挑战能够反映一个人的应急反应能力。
	她正在开发一个智力测试的游戏，游戏的主角是一个蚂蚱。蚂蚱最初位于0点处，可以在直线上向正向或反向两个方向跳跃。
	比较特别的是，蚂蚱每次跳跃的距离比前一次跳跃多一个单位，第一次跳跃的距离为一个单位。
	
	小B的问题是，如果让蚂蚱跳跃到x处，需要经过多少次跳跃，你能解决这个问题吗？
									
	输入
	输入中有多组测试数据。每组测试数据为单独的一行，包含一个整数x（-10^9 =＜x =＜10^9）。
	样例输入
	2
	6
	0
	
	输出
	对每组测试数据，在单独的行中输出蚂蚱最少需要跳跃的次数。
	样例输出
	3
	3
	0
	
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
*/

import java.util.Scanner;

public class 乐视17_跳跃的蚂蚱 {
	public static void main(String[] args) {
		solve2();
	}
	/*
	 * 	AC
	 * 	原理不清楚
	 * 	但这样做就是对的。
	 */
	static void solve2() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int v = Math.abs(sc.nextInt());
			int j = 1;
			int n = 0;
			while (n < v) {
				n += j;
				j ++;
			}
			if ((n - v) % 2 == 0) {
				System.out.println(j - 1);
			} else {
				while ((n - v) % 2 != 0) {
					n += j;
					j ++;
				}
				System.out.println(j - 1);
			}
		}
		sc.close();
	}
	static void solve1() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			solve1Internal(Math.abs(sc.nextInt()));
		}
		sc.close();
	}
	static void solve1Internal(int v) {
		final char[] record = new char[10000000];
		char nowTimes = 0;
		int maxIndex = 0;
		record[1] = (char)1;
		if (v == 0) {
			System.out.println(0);
			return;
		}
		while (record[v] == 0) {
			nowTimes ++;
			for (int i = 1; i <= maxIndex; i ++) {
				if (record[i] != 0) {
					int cutIndex = Math.abs(i - nowTimes), addIndex = i + nowTimes;
					if (cutIndex > -1 && cutIndex < record.length && record[cutIndex] == 0) {
						record[cutIndex] = nowTimes;
					}
					if (addIndex > -1 && addIndex < record.length && record[addIndex] == 0) {
						record[addIndex] = nowTimes;
					}
				}
			}
			maxIndex += nowTimes;
		}
		System.out.println((int)record[v]);
	}
}
