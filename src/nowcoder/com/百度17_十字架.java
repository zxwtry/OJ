package nowcoder.com;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/*
 * 小A很喜欢十字架，并喜欢用各种方式产生十字架的样子。小A不满足简单的十字架输出效果，所以重新定义了一种高大上的十字架输出方式。
	给一个n(1≤n≤10) , 输出对应的图形，具体形式如下：
	1.当n=1的时候，输出一个小写字母"o" :
	o
	2.当n≥2的时候，复制n=n-1时候的图像，并且在n-1图像的上、下、左、右都粘贴一个一模一样的图形。
	如n=2时，在原图"o"的上下左右都粘贴一个"o",所以最后输出的是这样一个图形：
	 o 
	ooo
	 o
									
	输入
	包含多组数据，第一行是数据组数T(T≤10)，随后有T行，每组数据占一行，其中包含一个数字n(1 ≤ n ≤ 10) 。 
	
	样例输入
	2
	2
	3 
	输出
	首先每行输出：“Case #x:”，x表示这是第几组数据，在随后的行中输出对应图形。
	注意每行输出字符数应该与o处于最右的那一行的字符数相同；如果对应位置没有字母o，就在那一个位置输出一个空格。
	样例输出
	Case #1:
	 o
	ooo
	 o
	Case #2: 
	       o    
	      ooo   
	       o    
	    o  o  o 
	   ooooooooo
	    o  o  o 
	       o    
	      ooo   
	       o   
	
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

/**
 * @author 		zxwtry
 * @email 		zxwtry@qq.com
 * @project 	OJ
 * @package 	nowcoder.com
 * @file 		百度17_十字架.java
 * @date 		2016年11月19日 下午10:20:46
 * @details		
 */
public class 百度17_十字架 {
	public static void main(String[] args) {
		try {
			solve2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @method      solve2
	 * @parameter   
	 * @return      void
	 * @details     像这种看起来很麻烦，其实不烦也不难的题目，考场上一定要快速拿分啊！！！
	 */
	private static void solve2() throws Exception {
		Scanner sc = new Scanner(new File("D:/file/data/百度17_十字架.txt"));
		int[] arr = new int[sc.nextInt()];
		int max = 0;
		HashMap<Integer, Integer> sts = new HashMap<Integer, Integer>();
		for (int index = 0; index < arr.length; index ++) {
			arr[index] = sc.nextInt();
			max = Math.max(max, arr[index]);
			sts.put(arr[index], 0);
		}
		int len = 1;
		for (int i = 0; i < max; i ++) {
			len *= 3;
		}
		char[][] cs = new char[len][len];
		for (int i = 0; i < len; i ++) {
			for (int j = 0; j < len; j ++) {
				cs[i][j] = ' ';
			}
		}
		int sti = len / 2, eni = len / 2;
		cs[sti][sti] = 'o';
		if (sts.containsKey(1)) {
			sts.put(1, sti);
		}
		for (int mi = 1; mi <= max; mi ++) {
			int length = eni - sti + 1;;
			for (int i = sti; i <= eni; i ++) {
				System.arraycopy(cs[i], sti, cs[i], eni + 1, length);
				System.arraycopy(cs[i], sti, cs[i], sti - length, length);
				System.arraycopy(cs[i], sti, cs[i - length], sti, length);
				System.arraycopy(cs[i], sti, cs[i + length], sti, length);
			}
			if (sts.containsKey(mi)) {
				sts.put(mi, sti);
			}
			sti -= length;
			eni += length;
		}
		for (int index = 0; index < arr.length; index ++) {
			sti = sts.get(arr[index]);
			eni = len - 1 - sti;
			System.out.println("Case #"+(index + 1)+":");
			for (int i = sti; i <= eni; i ++) {
				for (int j = sti; j <= eni; j ++) {
					System.out.print(cs[i][j]);
				}
				System.out.println();
			}
		}
		sc.close();
	}

	/**
	 * @method 		solve1
	 * @parameter	
	 * @return 		void
	 * @details
	 */
	static void solve1() {
		Scanner cin = new Scanner(System.in);
		while (cin.hasNextInt()) {
			int roundNum = cin.nextInt();
			for (int i = 0; i < roundNum; i++) {
				int n = cin.nextInt();
				if (n == 1) {
					System.out.println("Case #" + (i + 1) + ":");
					System.out.println("o");
					continue;
				}
				char[][] tmp = new char[1][1];
				tmp[0][0] = 'o';
				int len = 1;
				char[][] result = tmp;
				for (int j = 0; j < n - 1; j++) {
					len = 3 * tmp.length;
					int tmpLen = tmp.length;
					result = new char[len][len];
					for (int a = 0; a < len; a++) {
						for (int b = 0; b < len; b++) {
							if ((b < (tmpLen) || b >= 2 * tmpLen) && (a < (tmpLen) || a >= 2 * tmpLen)) {
								result[a][b] = ' ';
							} else {
								result[a][b] = tmp[a % tmpLen][b % tmpLen];
							}
						}
					}
					tmp = result;
				}
				System.out.println("Case #" + (i + 1) + ":");
				for (int j = 0; j < len; j++) {
					for (int k = 0; k < len; k++) {
						System.out.print(result[j][k]);
					}
					System.out.println();
				}
			}
		}
		cin.close();
	}
}
