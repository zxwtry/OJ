package stl;

/*
 * 	1,	题意：
 * 		给n个完全相同的数，分到m个容器，可以有0
 * 			称为：n的m划分
 * 
 * 	2,	dp含义：
 * 		dp[m][n]：n的m划分 --> 总个数
 * 
 * 	3,	错误递归式：
 * 		a,	n选出k	放到1号盒子
 * 		b,	n-k放到m-1个盒子
 * 		这个是错误的，原因：
 * 			1号盒子从1 --> (n-m)遍历
 * 			同样，2号盒子从1 --> ?遍历
 * 			一直到m号盒子
 * 			<<<<<>>>>>
 * 			这样1 --> m号盒子是没有任何关系的。
 * 			这里是不对的。
 * 
 * 	4,	正确的理解：
 * 		a,	{1} >= {2} >= ... >= {m}
 * 			这样能够保证部分正确
 * 		b,	 需要剔除的是相等情况
 * 		c,	每次减1，看有什么变化
 * 				从{m} --> {1}依次变成0
 * 				如果{m} == 1
 * 					[i][j] --> [][j-1]
 */

public class Book1_2_3_动态规划_2_3_3_划分数 {
	public static void main(String[] args) {
		System.out.println(hfs(3, 4, 100));
	}
	static int hfs(int m, int n, int w) {
		int[][] dps = new int[m+1][n+1];
		dps[0][0] = 1;
		for (int dpi = 1; dpi < m+1; dpi ++)
			for (int dpj = 0; dpj < n+1; dpj ++)
				dps[dpi][dpj] = dpi <= dpj ? (dps[dpi-1][dpj]
						+dps[dpi][dpj-dpi])%w : dps[dpi-1][dpj];
		return dps[m][n];
	}
}
