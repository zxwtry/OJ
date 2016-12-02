package nowcoder.com;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 	小A、小B、小C三人正组队参加现场赛。小A刚过了一道大模拟，伸出手想看看几点了，
 * 	却发现自己没有带表，队友也没有带，因为大家平时是用手机看的。小A发现现场有一个电子显示屏上面有时间。
	电子显示屏是一个7*100的点阵,当前时间为一个7*29的矩阵,在显示屏上左右滚动,详见样例
	数字0~9分别用7*6的矩阵表示如下
	
	遗憾的是，小A眼睛高度近视，需要你念出时间给他听...
	所以现在给你一个这样的点阵，输出当前时间。
	
									
	输入
	多组输入数据，第一行是数据组数T(T≤1440)。
	接下来是T组数据，每组数据是一个7*100的点阵。输入数据保证其中存在合法的7*29的点阵，详见样例。
	
	样例输入
	
	输出
	对每组数据输出一行答案，该行中输出“Case #k: result”(对应第k组数据，冒号后有空格),result为当前时间h:m。
	样例输出
	Case #1: 9:56
	Case #2: 12:37
	Case #3: 14:8
	
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
 * @file        百度17_时钟.java
 * @type        百度17_时钟
 * @date        2016年11月30日 下午10:27:04
 * @details     
 */
public class 百度17_时钟 {
	public static void main(String[] args) {
		solve1();
	}
	/**
	 * @method      solve1
	 * @parameter   
	 * @return      void
	 * @details     AC
	 * @details     就是花的时间有点多。。。
	 */
	static void solve1() {
		Scanner sc = new Scanner(System.in);
		char[][] c = new char[7][];
		int times = sc.nextInt();
		sc.nextLine();
		boolean[][] iv = new boolean[7][100];
		int[] s = new int[] {0};
		for (int timeIndex = 1; timeIndex <= times; timeIndex ++) {
			for (int i = 0; i < 7; i ++) Arrays.fill(iv[i], false);
			for (int i = 0; i < 7; i ++) c[i] = sc.nextLine().toCharArray();
			int i = 0;
			int[] arr = new int[4];
			int arrIndex = 0; 
			while ( i < 100 ) {
				s[0] = 0;
				if (c[0][i] == star && ! iv[0][i]) {
					search(c, iv, 0, i, s);
					arr[arrIndex ++] = convert(c, s[0], 0, i);
					if (arrIndex == 4) break;
					while (++ i < 100 && c[0][i] == star) {};
				} else i ++;
			}
			System.out.printf("Case #%d: %d:%d\r\n", timeIndex, arr[0]*10+arr[1], arr[2]*10+arr[3]);
		}
		sc.close();
	}
	
	private static void search(char[][] c, boolean[][] iv, int i, int j, int[] s) {
		s[0] ++;
		iv[i][j] = true;
		for (int k = 0; k < xs.length; k ++) {
			int ni = i + xs[k], nj = j + ys[k];
			if (ni > -1 && ni < c.length && nj > -1 && nj < c[ni].length && c[ni][nj] == star && ! iv[ni][nj])
				search(c, iv, ni, nj, s);
		}
	}

	final static char star = '*', blank = ' ';
	final static int[] n = new int[] {22,  7, 22, 22, 15,
									  22, 24, 12, 26, 24};
	final static int[] xs = new int[] {1, -1, 0, 0};
	final static int[] ys = new int[] {0, 0, -1, 1};

	static int convert(char[][] c, int num, int i, int j) {
		switch (num) {
		case 7:
			return 1;
		case 15:
			return 4;
		case 12:
			return 7;
		case 26:
			return 8;
		case 22:
			if (c[i + 3][j + 1] == blank) return 0;
			if (c[i + 1][j] == star) return 5;
			if (c[i + 4][j] == star) return 2;
			return 3;
		case 24:
			if (c[i + 4][j] == star) return 6;
			return 9;
		default:
			return 0;
		}
	}
}
