package template;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_Graph_Prim0.java
 * @type        RECITE_Graph_Prim0
 * @date        2016年12月11日 下午3:48:14
 * @details     http://acm.hdu.edu.cn/showproblem.php?pid=1233
 * @details     这份代码不使用堆，时间复杂度O(v*v)
 */
public class RECITE_Graph_Prim {
	final static int max = Integer.MAX_VALUE;
	final static int maxn = 103;
	final static int[][] ws = new int[maxn][maxn];
	final static int[] lc = new int[maxn];
	final static boolean[] iv = new boolean[maxn];
	static int prim(int n, int v) {
		int tc = 0, i = 0, j = 0, mv = 0;
		for (i = 1; i <= n; i ++){
			iv[i] = i == v; 
			lc[i] = ws[v][i];
		}
		for (i = 2; i <= n; i ++) {
			mv = max;
			for (j = 1; j <= n; j ++)
				if (! iv[j] && lc[j] < mv) {
					mv = lc[j];
					v = j;
				}
			iv[v] = true;
			tc += mv;
			for (j = 1; j <= n; j ++)
				if (! iv[j] && ws[v][j] < lc[j])
					lc[j] = ws[v][j];
		}
		return tc;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int n = sc.nextInt();
			if (n == 0) break;
			for (int ni = (n-1)*n/2; ni > 0; ni --) {
				int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
				ws[u][v] = w;
				ws[v][u] = w;
			}
			System.out.println(prim(n, 1));
		}
		sc.close();
	}
}
