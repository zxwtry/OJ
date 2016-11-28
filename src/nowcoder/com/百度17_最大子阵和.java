package nowcoder.com;

import java.util.Scanner;

/**
 * 最大子阵和（百度2017秋招真题）
	 题目描述
										
	给出一个n行m列的二维矩阵A[m,n]，其每个元素的取值范围是[-1000,1000]，
	其中1＜=n＜=100,1＜=m＜=100。求出p,q,r,s，满足条件1＜=p＜=q＜=n,1＜=r＜=s＜=m
	且p＜=i＜=q,r＜=j＜=s的(i,j)对应的A[i,j]之和最大。
	
	若(p,q,r,s)有多个解，输出最大子阵和即可。
	
	输入
	第一行表示测试样例的组数Te（Te＜=10）。
	对于每组测试样例，其第一行包含两个数n和m，其中1＜=n＜=100,1＜=m＜=100，
	接下来n行，每行有m个数，表示A[i,j]中的元素，满足-1000＜=A[i,j]＜=1000。
	
	样例输入
	1
	1 5
	2 3 -19 1 1
	输出
	对每组测试数据，单独输出一行答案。
	样例输出
	5
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
 * @file        百度17_最大子阵和.java
 * @type        百度17_最大子阵和
 * @date        2016年11月27日 下午10:03:48
 * @details     
 */
public class 百度17_最大子阵和 {
	public static void main(String[] args) {
		try {
			solve1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @method      solve1
	 * @parameter   
	 * @return      void
	 * @details     AC
	 */
	static void solve1() throws Exception {
		Scanner sc = new Scanner(System.in);
		int times = sc.nextInt();
		for (int timesIndex = 1; timesIndex <= times; timesIndex ++) {
			int n = sc.nextInt(), m = sc.nextInt();
			int[][] arr = new int[n][m];
			for (int i = 0; i < n; i ++) {
				for (int j = 0; j < m; j ++) {
					arr[i][j] = sc.nextInt();
				}
			}
			int[][] sum = new int[n + 1][m + 1];
			for (int i = 1; i <= n; i ++) {
				for (int j = 1; j <= m; j ++) {
					sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + arr[i - 1][j - 1];
				}
			}
			int ans = Integer.MIN_VALUE;
			for (int i1 = 0; i1 <= n; i1 ++) {
				for (int j1 = 0; j1 <= m; j1 ++) {
					for (int i2 = i1 + 1; i2 <= n; i2 ++) {
						for (int j2 = j1 + 1; j2 <= m; j2 ++) {
							ans = Math.max(ans, sum[i1][j1] + sum[i2][j2] - sum[i1][j2] - sum[i2][j1]);
						}
					}
				}
			}
			System.out.println(ans);
		}
		sc.close();
	}
}
