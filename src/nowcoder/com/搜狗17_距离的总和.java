package nowcoder.com;



/*
 * 	题目描述
									
	定义两个大于2的偶数之间的距离，为这两个数之间质数的个数。从小到大输入n个大于2的偶数，
	输出所有数两两之间距离的总和（应该有n*(n-1)/2个距离，输出总和就好)。
	
	输入
	第一行是输入偶数的个数，最小为2，最大可能到几万。之后每行为一个偶数，最小是4，最大可能是几百万，不重复的升序排列。
	样例输入
	3
	4
	6
	12
	输出
	输入数据两两间距离的总和，这应该是一个不小于0的整数。
	样例输出
	6
	时间限制
	C/C++语言：2000MS其它语言：4000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */


import java.util.Scanner;

public class 搜狗17_距离的总和 {
	public static void main(String[] args) {
		solve1();
//		int[] zs = new int[]{0, 1, 2, 3, 5, 7, 11, 13, 17, 19};
//		int v1 = 4;
//		int v2 = 8;
//		System.out.println(solve1_calc(zs, v1, v2));
	}
	static void solve1() {
		Scanner sc = new Scanner(System.in);
		int len = sc.nextInt();
		int[] arr = new int[len];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < len; i ++) {
			arr[i] = sc.nextInt();
			max = Math.max(arr[i], max);
		}
		boolean[] map = new boolean[max + 1];
		int sqrt_max = (int)Math.sqrt(max);
		for (int i = 2; i <= sqrt_max; i ++) {
			if (! map[i]) {
				int mapIndex = i + i;
				while (mapIndex <= max) {
					map[mapIndex] = true;
					mapIndex += i;
				}
			}
		}
		int count = 0;
		for (int i = 2; i <= max; i ++) {
			if ( map[i] != true ) {
				count ++;
			}
		}
		int[] zs = new int[count];
		int zsIndex = 0; 
		for (int i = 2; i <= max; i ++) {
			if ( map[i] != true ) {
				zs[zsIndex ++] = i;
			}
		}
		long ans = 0;
		int[] arrIndex = new int[len];
		for (int i = 0; i < len; i ++) {
			arrIndex[i] = solve1_index(zs, arr[i]);
		}
//		System.out.println(len);
//		tools.Utils.printArray(arr, 100);
//		tools.Utils.printArray(arrIndex, 100);
//		tools.Utils.printArray(zs, 100);
		
		for (int i = 1; i < len; i ++) {
			ans += (arrIndex[i] - arrIndex[i - 1]) * (len - i) * i;
			//一开始以为这里是杨辉三角
			//不是，这里一般通过写几个找规律就能发现
		}
//		for (int i = 0; i < len; i ++) {
//			for (int j = 0; j < i; j ++) {
//				ans += Math.abs(arrIndex[j] - arrIndex[i]);
//			}
//		}
		System.out.println(ans);
		sc.close();
	}
	static int solve1_index(int[] zs, int v) {
		int i1 = 0;
		int sti = 0, eni = zs.length - 1, mid = 0;
		if (v < zs[sti]) {
			i1 = sti - 1;
		} else if (v > zs[eni]) {
			i1 = eni;
		} else {
			while (sti < eni) {
				mid = (sti + eni + 1) / 2;
				if (zs[mid] > v) {
					eni = mid - 1;
				} else {
					sti = mid;
				}
			}
			i1 = eni;
		}
		return i1;
	}
	static int solve1_calc(int[] zs, int v1, int v2) {
		if (v1 > v2) {
			return solve1_calc(zs, v2, v1);
		}
		int i1 = 0, i2 = 0;
		int sti = 2, eni = zs.length - 1, mid = 0;
		if (v1 < zs[sti]) {
			i1 = 1;
		} else if (v1 > zs[eni]) {
			i1 = eni;
		} else {
			while (sti < eni) {
				mid = (sti + eni + 1) / 2;
				if (zs[mid] > v1) {
					eni = mid - 1;
				} else {
					sti = mid;
				}
			}
			i1 = eni;
		}
		sti = i1 > 2 ? i1 - 1 : 2;
		eni = zs.length - 1;
		if (v2 < zs[sti]) {
			i2 = sti - 1;
		} else if (v2 > zs[eni]) {
			i2 = eni;
		} else {
			while (sti < eni) {
				mid = (sti + eni + 1) / 2;
				if (zs[mid] > v2) {
					eni = mid - 1;
				} else {
					sti = mid;
				}
			}
			i2 = eni;
		}
		return i2 - i1;
	}
}
