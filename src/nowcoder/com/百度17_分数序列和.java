package nowcoder.com;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * 	题目描述
									
	有一个分数序列 2/1,3/2,5/3,8/5,13/8,21/13,.... 求这个分数序列的前n项之和。
	
									
	输入
	测试数据有多组，其第一行为一个正整数k(0＜k＜=90)，表示测试数据的组数。每组测试数据为一行，为单个正整数n（0＜n＜=90）。
	样例输入
	1 
	2
	输出
	每组测试数据单独输出有一行：分数序列的和（精确到小数点后4位）。
	样例输出
	3.5000
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

/**
 * @author		zxwtry
 * @email		zxwtry@qq.com
 * @project		OJ
 * @package		nowcoder.com
 * @file		百度17_分数序列和.java
 * @date		2016年11月19日 下午10:29:17
 * @details		
 */
public class 百度17_分数序列和 {
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
	 * @details     AC
	 * @details     这种题目要的就是速度
	 */
	private static void solve2() throws Exception {
		Scanner sc = new Scanner(new File("D:/file/data/百度17_分数序列和.txt"));
		ArrayList<Double> list = new ArrayList<Double>();
		list.add(0.0);
		list.add(2.0);
		list.add(1.5+list.get(1));
		list.add(5.0/3.0+list.get(2));
		list.add(8.0/5.0+list.get(3));
		list.add(13.0/8.0+list.get(4));
		double a = 13, b = 8, tmp = 0;
		for (int times = sc.nextInt() - 1; times > -1; times --) {
			int v = sc.nextInt();
			if (list.size() > v) {
				System.out.printf("%.4f\n", list.get(v));
			} else {
				for (int k = v - list.size(); k > -1; k --) {
					tmp = a;
					a = a + b;
					b = tmp;
					list.add(list.get(list.size() - 1) + a/b);
				}
				System.out.printf("%.4f\n", list.get(v));
			}
		}
		sc.close();
	}

}
