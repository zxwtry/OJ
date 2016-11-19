package nowcoder.com;

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
		solve1();
	}

	/**
	 * @method		solve1 
	 * @parameter	
	 * @return 		void
	 * @details 	
	 */
	private static void solve1() {
		Scanner cin = new Scanner(System.in);
		while(cin.hasNext()){
			int k = cin.nextInt();
			int n = 0;
			for(int i=0;i<k;i++){
				n = cin.nextInt();
				solve1solution(n);
			}
			
		}
		cin.close();
	}
	private static void solve1solution(int n) {
		if(n==0){
			return;
		}
		double ans = 0;
		double[][] temp = new double[n][2];
		temp[0][0] = 2;
		temp[0][1] = 1;
		ans = 2;
		if(n==1){
			System.out.println("2.0000");
			return;
		}
		for(int i=1;i<n;i++){
			temp[i][0] = temp[i-1][0] + temp[i-1][1];
			temp[i][1] = temp[i-1][0];
			ans = ans + temp[i][0]/temp[i][1];
		}
		System.out.printf("%.4f\n",ans);
		
	}
}
