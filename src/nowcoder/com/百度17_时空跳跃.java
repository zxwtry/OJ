package nowcoder.com;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 提姆以为公主在跑向他身边，但公主却是在逃离。
	提姆终于来到了公主的身边，但是公主却消失了。
	提姆决定用时空跳跃的本领回到这趟旅途的起点。
	提姆已经累了，所以他想尽量少地使用时空跳跃。
	提姆的所在的时间点为0，提姆要去的地方为L。
	提姆每次跳跃可以在时间轴上移动[a,b]的距离。
	但是由于仙女座能量的影响，有些时间点被扭曲了。提姆希望尽量少走被扭曲的时间点。
	请你帮助提姆确定最少经过多少被扭曲的时间点可以到达L（已知L点和0点没有被影响）。
	当提姆到达L或L之后的位置，提姆就达到了目的
	
	
									
	输入
	输入的第一行有一个正整数L（1＜= L＜= 10^9）。表示提姆要去的地方。
	第二行三个整数a,b,n.分别表示提姆一次跳跃在时间轴上移动的最小距离和最大距离以及被扭曲的时间点的个数。
	1＜=a＜=b＜=10，1＜=n＜=100
	第三行n个整数，分别表示被扭曲的时间点的位置
	
	样例输入
	10
	2 3 5
	2 3 5 6 7
	输出
	一个整数，表示提姆的最少经过的被扭曲的时间点个数。（数据保证有解）
	样例输出
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
 * @file        百度17_时空跳跃.java
 * @type        百度17_时空跳跃
 * @date        2016年11月29日 上午11:28:59
 * @details     
 */
public class 百度17_时空跳跃 {
	public static void main(String[] args) {
		solve1();
	}

	static void solve1() {
		final int cut = 10 * 9 - 10 - 9 + 1;
		Scanner sc = new Scanner(System.in);
		int L = sc.nextInt();
		int a = sc.nextInt(), b = sc.nextInt(), n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i ++)
			arr[i] = sc.nextInt();
		Arrays.sort(arr);
		int thisCut = 0;
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(arr[0]);
		for (int index = 1; index < n; index ++) {
			if (arr[index] - arr[index - 1] > cut) {
				thisCut += arr[index] - arr[index - 1] - cut;
			}
			set.add(arr[index] - thisCut);
		}
		L -= thisCut;
		int[] rec = new int[L + b];
		Arrays.fill(rec, -1);
		rec[0] = 0;
		for (int base = 0; base < L + b; base ++) {
			if (rec[base] != -1) {
				for (int add = a; add <= b; add ++) {
					int baseAdd = base + add;
					if (baseAdd >= L+b)	continue;
					if (rec[baseAdd] == -1) {
						rec[baseAdd] = set.contains(baseAdd) ? 1 : 0;
						rec[baseAdd] += rec[base];
					} else {
						rec[baseAdd] = Math.min(rec[baseAdd], rec[base] + (set.contains(baseAdd) ? 1 : 0));
					}
				}
			}
		}
		int ans = Integer.MAX_VALUE;
		for (int i = L; i < L+b; i ++)	ans = Math.min(ans, rec[i] == -1 ? ans : rec[i]);
		System.out.println(ans);
		sc.close();
	}
}
