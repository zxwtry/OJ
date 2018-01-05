package nowcoder.com;

import java.io.File;

/*
 * 	为活跃公司文化，公司计划组织一场比赛，让员工一展才艺。现有n个员工，
 * 	欲选出不少于k人组成一支队伍，1＜=n＜ =12,1＜=k＜=n。
	每个员工有个value值，表示其对队伍水平的贡献，-1000＜=value＜=1000，
	给出一个矩阵对角线为0的对称矩阵A， A[i][j]表示i,j同在队伍中时对队伍水平的贡献，
	为取得最好成绩，公司领导希望知道水平最高的组队方式能够达到的水平和组队方案数。
	
									
	输入
	第一行为测试样例组数Te（Te＜=100）。每组测试样例的第一行是两个数 n,k，第二行为n个数，
	表示每个人对队伍水平的贡献值，接下来有n行，每行有n个数，表示构成矩阵A的元素。
	样例输入
	1
	2 1
	100 -5
	0 10
	10 0
	输出
	每组测试样例输出一行，分别为能够达到的最高水平值和组队方案数。
	样例输出
	105 1
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

import java.util.Scanner;

/**
 * @author		zxwtry
 * @email		zxwtry@qq.com
 * @project		OJ
 * @package		nowcoder.com
 * @file		百度17_比赛组队.java
 * @date		2016年11月19日 下午10:24:37
 * @details		
 */
public class 百度17_比赛组队 {
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
	 * @details     
	 */
	private static void solve2() throws Exception {
		Scanner sc = new Scanner(new File("D:/file/data/百度17_比赛组队.txt"));
		for (int times = sc.nextInt() - 1; times > -1; times --) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			int[] val = new int[n];
			int[][] A = new int[n][n];
 			for (int ni = 0; ni < n; ni ++) {
				val[ni] = sc.nextInt();
			}
 			for (int ni = 0; ni < n; ni ++) {
 				for (int nj = 0; nj < n; nj ++) {
 					A[ni][nj] = sc.nextInt();
 				}
 			}
 			int maxv = Integer.MIN_VALUE, maxn = 0;
 			for (int s = (1 << n) - 1; s > 0; s --) {
 				int kv = 0;
 				for (int ni = 0; ni < n; ni ++) {
 					if ((s >> ni) % 2 == 1) {
 						kv ++;
 					}
 				}
 				if (kv < k) {
 					continue;
 				}
 				int sv = 0;
 				for (int ni = 0; ni < n; ni ++) {
 					if ((s >> ni) % 2 == 1) {
 						sv += val[ni];
	 					for (int nj = ni + 1; nj < n; nj ++) {
	 						if ((s >> nj) % 2 == 1) {
	 							sv += A[ni][nj];
	 						}
	 					}
 					}
 				}
 				if (sv > maxv) {
 					maxv = sv;
 					maxn = 1;
 				} else if (sv == maxv) {
 					maxn ++;
 				}
 			}
 			System.out.println(maxv + " " + maxn);
		}
		sc.close();
	}
}
