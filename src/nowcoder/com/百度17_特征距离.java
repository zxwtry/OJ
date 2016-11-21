package nowcoder.com;

import java.io.File;
import java.util.Scanner;

/*
 * 题目描述
										
	小A所在的城市中有n个路口，有m条道路(双向)连接着不同的路口。小A想知道从路口s到路口t的“特征距离”。
	问题是小A计算距离的方法较为特殊：当她选择一条从s到t的路径(包含若干条道路)后，
	定义该路径的特征距离为所有经过的道路中长度最长的那条道路的长度。现在给定相关信息，
	现在小A只关心从s到t的最短路，你能求出所有的最短路中特征距离最长的那一条吗？
	
									
	输入
	输入中有多组数据，第一行是一个正整数T(T<=10)，表示测试数据的组数。接下来包含T组测试数据。
	每组测试数据的第一行包含四个整数n(1＜=n＜=1000)，m(0＜=m＜=n*(n-1)/2)，s 和 t(1＜=s＜=n, 1＜=t＜=n)，
	分别表示图中节点数、边数、 起点和终点。
	接下来的m行中，每行包含三个整数u, v, w, (1＜=u＜=n,1＜=v＜= n, u不等于v, 0＜=w＜=1)
	表示结点u和结点v之间存在一条长度为w(1＜=w＜=10000的道路(保证没有重复)。
	
	样例输入
	2
	4 5 1 4
	1 2 1
	1 3 3
	2 3 2
	2 4 3
	3 4 1
	5 4 1 5
	1 2 1
	2 3 2
	1 3 4
	4 5 3
	输出
	对于每一组测试数据，输出一行"Case #x: s"，其中 x表示第x组数据，x从1开始递增。
	s表示题目描述中定义的最短路径的特征距离，如果不存在最短路则输出"No answer"，详见给定样例。
	样例输出
	Case #1: 3
	Case #2: No answer
	
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
 * @file        百度17_特征距离.java
 * @type        百度17_特征距离
 * @date        2016年11月21日 下午4:09:32
 * @details     
 */

public class 百度17_特征距离 {
	public static void main(String[] args) {
		
	}
	
	/**
	 * @method      solve1
	 * @parameter   
	 * @return      void
	 * @details     
	 */
	static void solve1() throws Exception {
		Scanner sc = new Scanner(new File("D:/file/data/百度17_特征距离.txt"));
		int T = sc.nextInt();
		for (int t = 1; t <= T; t ++) {
			
		}
		sc.close();
	}
}
