package nowcoder.com;

import java.util.Scanner;

/**
 * 内存检查（百度2017秋招真题）
	 题目描述
										
	有一个含n字节的连续内存区域可能存在问题，工程师手中有一种检查软件，
	软件每次运行可以检查一段连续内存区间。由于检查的区间长度越长，
	要花费的时间就越多，因此工程师希望能够在运行最多m次程序的情况下，
	每次检查的区间长度最大值最小，且检查的区间的并集包含了所有出现的"1"。
	现给出内存的情况（0代表该字节不需要检查，1代表该字节需要检查），求最小的区间最大长度。
	
									
	输入
	第一行为一个正整数T（T＜=25）表示有T组数据。
	接下来每组数据中，第一行为两个正整数n,m(n＜=100000,m＜=n)，表示内存总长n个字节，检查软件运行的次数上限m。
	第二行为一个长度为n的01串，表示待检查的内存区域的情况。0代表不需要检查，1代表需要检查。
	
	样例输入
	2
	7 3
	1100101
	3 1
	101
	输出
	对于每组数据，输出一行"Case %: A"，其中%表示第几组数据，A表示该组数据的答案。
	样例输出
	Case 1: 2
	Case 2: 3
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
 * @file        百度17_内存检查.java
 * @type        百度17_内存检查
 * @date        2016年11月27日 下午10:02:42
 * @details     
 */
public class 百度17_内存检查 {
	public static void main(String[] args) {
		solve1();
	}

	/**
	 * @method      solve1
	 * @parameter   
	 * @return      void
	 * @details     
	 */
	static void solve1() {
		Scanner sc = new Scanner(System.in);
		int times = sc.nextInt();
		for (int timesIndex = 1; timesIndex <= times; timesIndex ++) {
			int len = sc.nextInt(), num = sc.nextInt();
			String s = sc.next();
			int[] arr = new int[len];
			int numOf1 = 0;
			int lenSti = -1;
			int lenEni = 0;
			for (int index = 0; index < arr.length; index ++) {
				if (s.charAt(index) == '1') {
					arr[index] = 1;
					numOf1 ++;
					if (lenSti == -1) lenSti = index;
					lenEni = index;
				} else {
					arr[index] = 0;
				}
			}
			System.out.print("Case " + timesIndex + ": "); 
			if (numOf1 == 0) {
				System.out.println(0);
			} else if (numOf1 <= num) {
				System.out.println(1);
			} else {
				int stm = (numOf1 - 1) / num + 1;
				int enm = (lenEni - lenSti + 1) / num;
				while (stm < enm) {
					int mim = (stm + enm) / 2;
					boolean isCaped = solve1_isCaped(arr, mim, lenSti, lenEni, num);
					if (isCaped) {
						enm = mim;
					} else {
						stm = mim + 1;
					}
				}
				System.out.println(stm);
			}
						
		}
		sc.close();
	}
	private static boolean solve1_isCaped(int[] arr, int partLen, int sti, int eni, int num) {
		int i = sti;
		while (i <= eni) {
			i += partLen;
			while (i <= eni && arr[i] == 0) i ++;
			num --;
		}
		return num >= 0;
	}
}
