package nowcoder.com;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import com.sun.xml.internal.ws.policy.sourcemodel.attach.ExternalAttachmentsUnmarshaller;

import sun.util.resources.cldr.sl.TimeZoneNames_sl;

/**
 * 小A到达了一个陌生的城市，经过几天的功课，他已经知道了整个城市的公共交通情况。
 * 所有的公交设计都是完全对称的，公共汽车都是对向开行，且线路相同，
 * 对向开行的道路是同一条道路。小A所住的宾馆附近有a个公交车站，你知道火车站附近有b个公交车站。
	小A想知道从宾馆选定附近某个公交车站出发到火车站附近某一公交车站的最近的路程是多少。
	你能帮他吗？
	
	输入
	输入数据有多组，第一行为一个正整数T(T＜=20)，表示测试数据组数，接下来包含T组测试数据。
	每组测试数据第一行包含四个整数n(1＜=n＜=1000)，m(0＜=m< =n*(n-1)/2)，a(1＜=a＜=n),
	 b(1＜=b＜=n)，分别表示城市中公交车站数、道路数目、宾馆附近公交车站数目和火车站附近的公交车站数目。
	接下来一行包含a个整数si，si(1＜=si＜=n)表示宾馆附近的a个公交车站。
	接下来一行包含b个整数ei，ei(1＜=ei＜=n)表示火车站附近的b个公交车站。
	接下来m行每行包含三个整数u, v, w, (1＜= u＜= n, 1＜= v＜= n, u不等于v, 0＜= w＜10000)
	表示结点u和结点v之间存在一条长度为w的路径。(保证没有重边)
	
	样例输入
	2
	6 5 2 2
	1 2
	3 4
	1 5 1
	2 6 4
	5 6 2
	3 5 4
	4 6 1
	6 4 2 2
	1 2
	3 4
	1 5 1
	2 5 3
	6 4 1
	6 3 2
	输出
	对于每组测试数据，输出一行"Case #x: s"，x表示第x组数据，x从1开始递增，s表示所求的最短距离，
	如果无法从宾馆通过给定的交通路线图到达火车站则输出"No answer"，详细参见给定样例。
	样例输出
	Case #1: 4
	Case #2: No answer
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 * 
 */

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度17_赶火车.java
 * @type        百度17_赶火车
 * @date        2016年11月29日 下午5:26:11
 * @details     
 */
public class 百度17_赶火车 {
	public static void main(String[] args) {
		solve1();
	}

	static void solve1() {
		Scanner sc = new Scanner(System.in);
		int times = sc.nextInt();
		HashSet<Integer> dest = new HashSet<Integer>();
		Queue<Integer> nl = new LinkedList<Integer>();
		Queue<Integer> cl = new LinkedList<Integer>();
		for (int timeIndex = 1; timeIndex <= times; timeIndex ++) {
			int n = sc.nextInt(), m = sc.nextInt(), a = sc.nextInt(), b = sc.nextInt();
			int[] as = new int[a];
			int[][] len = new int[n + 1][n + 1];
			boolean[] isTraced = new boolean[n + 1];
			dest.clear();
			for (int ai = 0; ai < a; ai ++)		as[ai] = sc.nextInt();
			for (int bi = 0; bi < b; bi ++)		dest.add(sc.nextInt());
			for (int mi = 0; mi < m; mi ++) {
				int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
				len[u][v] = w;
				len[v][u] = w;
			}
			for (int s : as) {
				Arrays.fill(isTraced, false);
				nl.add(s);
				cl.add(0);
				while (! nl.isEmpty()) {
					int nNow = nl.poll();
					int cNow = cl.poll();
					
				}
			}
		}
		sc.close();
	}
}
