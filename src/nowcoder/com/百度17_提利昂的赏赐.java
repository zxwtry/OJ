package nowcoder.com;

/**
 * 题目描述
									
	提利昂收服山地部落之后决定犒赏士卒，本着陈力就列的原则，他决定给五种人以赏赐：
	1.作战能力＞80，并且至少有一项特殊能力的人，每人8000银鹿
	2.作战能力＞85，并且声望＞80的人，每人4000银鹿
	3.作战能力＞90，每人2000银鹿
	4.作战能力＞85的灼人部成员每人1000银鹿
	5.声望＞80的军官每人850银鹿。
	提利昂想要尽量多的犒赏部下，所以只要满足条件即可得到奖赏，
	即每人可以获得多项赏赐。请你帮他算算自己需要准备多少银鹿，
	并且他想要知道得到赏赐最多的人是谁，以及它得到的银鹿数量。
	
	
									
	输入
	第一行一个整数N，表示士卒总数。1＜=N＜=100
	接下来N行每行一个字符串s，两个整数a，b，两个字母c，d，一个整数x。
	S表示士卒的姓名，为长度小于20的字符串（不含空格）。
	a,b分别表示作战能力和声望，都是属于[0,100]的整数。
	c,d分别表示是否是军官和是否是灼人部成员，大写字母Y表示是，大写字母N表示不是，保证c,d只为Y或N。
	x表示此人拥有的特殊能力数量。0＜=x＜=10
	
	样例输入
	4
	wdd 98 83 Y N 0
	hdd 99 67 N Y 1
	ldd 93 99 N N 0
	qdd 81 97 Y N 1
	
	输出
	三行。
	第一行一个字符串，为得到赏赐最多的人的名字。
	第二行和第三行各一个整数分别表示得到赏赐最多的人得到的银鹿数和提利昂需要准备的银鹿数。
	
	S表示士卒的姓名，为长度小于20的字符串（不含空格）。
	a,b分别表示作战能力和声望，都是属于[0,100]的整数。
	c,d分别表示是否是军官和是否是灼人部成员，大写字母Y表示是，大写字母N表示不是，保证c,d只为Y或N。
	x表示此人拥有的特殊能力数量。0＜=x＜=10
	
	样例输出
	hdd
	11000
	32700
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

import java.util.Scanner;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度17_提利昂的赏赐.java
 * @type        百度17_提利昂的赏赐
 * @date        2016年11月28日 下午8:42:31
 * @details     
 */
public class 百度17_提利昂的赏赐 {
	public static void main(String[] args) {
		solve1();
	}

	/**
	 * @method      solve1
	 * @parameter   
	 * @return      void
	 * @details     AC
	 * @details     少做水题
	 */
	static void solve1() {
		Scanner sc = new Scanner(System.in);
		int max = 0, sum = 0;
		String name = null;
		for (int times = sc.nextInt(); times > 0; times --) {
			String newName = sc.next();
			int fight = sc.nextInt();
			int award = sc.nextInt();
			String isArmy = sc.next();
			String isZuoren = sc.next();
			int X = sc.nextInt();
			int newMax = 0;
			if (fight > 80 && X > 0) {
				newMax += 8000;
			}
			if (fight > 85 && award > 80) {
				newMax += 4000;
			}
			if (fight > 90) {
				newMax += 2000;
			}
			if (fight > 85 && isZuoren.equals("Y")) {
				newMax += 1000;
			}
			if (award > 80 && isArmy.equals("Y")) {
				newMax += 850;
			}
			if (newMax > max) {
				name = newName;
				max = newMax;
			}
			sum += newMax;
		}
		System.out.println(name);
		System.out.println(max);
		System.out.println(sum);
		
		sc.close();
	}
}
