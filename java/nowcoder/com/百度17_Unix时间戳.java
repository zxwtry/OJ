package nowcoder.com;

import java.util.Scanner;

/**
 * Unix 时间戳（百度2017秋招真题）
	 题目描述
										
	Unix 时间戳(Unix timestamp)，或称Unix时间(Unix time)、POSIX 时间(POSIX time)，
	是一种时间表示方式，定义为从格林威治时间1970年01月01日00时00分00秒起至现在的总秒数。
	Unix时间戳不仅被使用在 Unix 系统、类 Unix 系统中，也在许多其他操作系统中被广泛采用。
	
	Moment.js 是一个 Javascript 中用来处理时间的库，给定 Unix 时间戳，
	他能够返回当前的年月日。你可以实现这个功能吗？
	
	提示：4年一闰100年不闰400年又闰
	
	
									
	输入
	输入不超过50行，每行包含一个整数 x 表示一个 Unix 时间戳。（0＜=x＜=10^10）
	样例输入
	103424
	1368864000
	
	输出
	对于每一行，返回 yyyy mm dd 格式，表示对应的年月日。
	样例输出
	1970 01 02
	2013 05 18
	
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
 * @file        百度17_Unix时间戳.java
 * @type        百度17_Unix时间戳
 * @date        2016年11月27日 下午10:00:05
 * @details     
 */
public class 百度17_Unix时间戳 {
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
		int[] ms = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int day_seconds = 24 * 60 * 60;
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			long seconds = sc.nextLong();
			int day = (int)(seconds / day_seconds);
			int year = 1970;
			int year_days = 0;
			int year_days_pre = 0;
			while (year_days <= day) {
				year_days_pre = year_days;
				if (year % 100 == 0) {
					year_days += (year % 400 == 0) ? 366 : 365;
				} else {
					year_days += (year % 4 == 0) ? 366 : 365;
				}
				year ++;
			}
			year --;
			int day_in_year = day - year_days_pre;
			if (year % 100 == 0) {
				ms[1] = (year % 400 == 0) ? 29 : 28;
			} else {
				ms[1] = (year % 4 == 0) ? 29 : 28;
			}
			int day_in_year_cut = day_in_year;
			int day_in_year_pre = day_in_year;
			int msIndex = -1;
			while (day_in_year_cut >= 0) {
				day_in_year_pre = day_in_year_cut;
				day_in_year_cut -= ms[++ msIndex];
			}
			System.out.printf("%d %02d %02d\r\n", year, (msIndex + 1) , (day_in_year_pre + 1));
		}
		sc.close();
	}
}
