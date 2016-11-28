package nowcoder.com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 格子距离（百度2017秋招真题）
	 题目描述
										
	有一个n*m的格子图，每个格子最多与其周围的四个相邻，不相邻的格子之间互相不可达。设一个4*6的格子图坐标如下：
	  123456
	1######
	2######
	3######
	4######
	
	则(2,3)的格子与(1,3),(3,3),(2,2),(2,4)相邻。
	格子与格子之间存在特殊的墙，阻止两个相邻的格子的移动。若(2,3)存在一堵左侧的墙，
	则（2,3）将无法直接到达(2,2)，但(2,2)仍能到达(2,3)。
	现给出每个格子周围墙的情况，求从给定的点(S,T)出发，到达每一个格子最少经过多少个格子。
	
	
									
	输入
	第一行为一个正整数T(T＜=20)，表示测试数据的组数。
	对于每一组数据，第一行为n,m,S,T，(1＜=n＜=100, 1＜=m＜=100) (1＜=S＜=100) (1＜=T＜=100)。
	后面有n行，每行m个数字，第i行第j列的数字x表示格子(i,j)周围墙的情况。
	x的二进制表示中，从低位到高位数第1,2,3,4位分别表示该格子在上、下、左、右方向是否有墙。
	若有则该位为1，否则为0。
	
	样例输入
	2
	3 3 1 1
	8 0 0
	2 0 0
	1 4 0
	2 2 1 1
	0 0
	0 0
	
	输出
	对于每组数据，先输出一行"Case %:"，其中%代表第i组数据。每组数据输出n行，每行包含m个整数，第i行第j个整数代表从（S,T）出发到（i,j）的最小路程。若不能到达，该位置为-1。
	样例输出
	Case 1:
	0 3 4
	1 2 3
	-1 3 4
	Case 2:
	0 1
	1 2
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
 * @file        百度17_格子距离.java
 * @type        百度17_格子距离
 * @date        2016年11月27日 下午10:01:25
 * @details     
 */
public class 百度17_格子距离 {
	public static void main(String[] args) {
		solve2();
	}

	static void solve2() {
		Scanner sc = new Scanner(System.in);
		int[] suri = new int[] {-1, 1, 0, 0};
		int[] surj = new int[] {0, 0, -1, 1};
		int caseNum = sc.nextInt();
		for (int caseIndex = 1; caseIndex <= caseNum; caseIndex ++) {
			int n = sc.nextInt(), m = sc.nextInt(), ns = sc.nextInt() - 1, ms = sc.nextInt() - 1;
			int[][] w = new int[n][m];
			for (int ni = 0; ni < n; ni ++)
				for (int mi = 0; mi < m; mi ++)
					w[ni][mi] = sc.nextInt();
			int[][] min = new int[n][m];
			for (int ni = 0; ni < n; ni ++)
				Arrays.fill(min[ni], -1);
			min[ns][ms] = 0;
			LinkedList<Integer> nl = new LinkedList<Integer>();
			LinkedList<Integer> ml = new LinkedList<Integer>();
			nl.add(ns); ml.add(ms);
			while (! nl.isEmpty()) {
				int ni = nl.poll();
				int mi = ml.poll();
				for (int i = 0; i < suri.length; i ++) {
					int newni = ni + suri[i];
					int newmi = mi + surj[i];
					if (((w[ni][mi] >> i) & 0x1) == 0 && newni > -1 && newmi > -1 && 
							newni < n && newmi < m && min[newni][newmi] == -1) {
						min[newni][newmi] = min[ni][mi] + 1;
						nl.add(newni);
						ml.add(newmi);
					}
				}
			}
			System.out.println("Case "+caseIndex+":");
			for (int[] v : min) {
				for (int index = 0; index < v.length - 1; index ++)
					System.out.print(v[index] + " ");
				System.out.println(v[v.length - 1]);
			}
		}
		sc.close();
		
	}

	/**
	 * @method      solve1
	 * @parameter   
	 * @return      void
	 * @details     AC
	 */
	static void solve1() {
		Scanner sc = new Scanner(System.in);
		int[] suri = new int[] {-1, 1, 0, 0};
		int[] surj = new int[] {0, 0, -1, 1};
		int caseNum = sc.nextInt();
		for (int caseIndex = 1; caseIndex <= caseNum; caseIndex ++) {
			int n = sc.nextInt(), m = sc.nextInt(), ns = sc.nextInt() - 1, ms = sc.nextInt() - 1;
			int[][] w = new int[n][m];
			for (int ni = 0; ni < n; ni ++)
				for (int mi = 0; mi < m; mi ++)
					w[ni][mi] = sc.nextInt();
			int[][] min = new int[n][m];
			for (int ni = 0; ni < n; ni ++)
				Arrays.fill(min[ni], -1);
			min[ns][ms] = 0;
			@SuppressWarnings("unchecked")
			List<Integer>[] lists = new List[] {new ArrayList<Integer>(), new ArrayList<Integer>()};
			lists[0].add(ns * 10000 + ms);
			solve1_search(w, min, lists, 0, suri, surj);
			System.out.println("Case "+caseIndex+":");
			for (int[] v : min) {
				for (int index = 0; index < v.length - 1; index ++)
					System.out.print(v[index] + " ");
				System.out.println(v[v.length - 1]);
			}
					
		}
		sc.close();
	}

	static void solve1_search(int[][] w, int[][] min, List<Integer>[] lists, int layer, int[] suri, int[] surj) {
		for (int v : lists[0]) {
			int ni = v / 10000, mi = v % 10000;
			int wv = w[ni][mi];
			for (int i = 0; i < 4; i ++) {
				int newni = ni + suri[i], newmi = mi + surj[i];
				if ((wv & (1 << i)) == 0 && newni > -1 && newni < w.length && newmi > -1 
						&& newmi < w[0].length && min[newni][newmi] == -1) {
					min[newni][newmi] = min[ni][mi] + 1;
					lists[1].add(newni * 10000 + newmi);
				}
			}
		}
		if (lists[1].size() != 0) {
			List<Integer> temp = lists[0];
			lists[0] = lists[1];
			lists[1] = temp;
			lists[1].clear();
			solve1_search(w, min, lists, layer, suri, surj);
		}
	}
	
}
