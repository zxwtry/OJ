package nowcoder.zuo;

public class Book054_斐波那契系列问题的递归和动态规划 {
	public static void main(String[] args) {
		debug原问题();
	}
	
	static void debug原问题() {
		S0原问题 s = new S0原问题();
		for (int times = 1; times < 33; times ++) {
			int f1 = s.f1(times);
			int f2 = s.f2(times);
			int f3 = s.f3(times);
			System.out.printf("%d...%d...%d\r\n", f1, f2, f3);
		}
	}

	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book054_斐波那契系列问题的递归和动态规划.java
	 * @type        S原问题
	 * @date        2016年11月24日 下午10:15:20
	 * @details     1, 1, 2, 3, 5, 8, 13 ...
	 * @details     完成原问题
	 */
	static class S0原问题 {
		public int f1 (int n ) {
			if (n < 1) return 0;
			if (n == 1 || n == 2)
				return 1;
			return f1(n - 1 ) + f1(n - 2);
		}
		
		public int f2 (int n) {
			if (n < 1) return 0;
			if (n == 1 || n == 2)
				return 1;
			int res = 1;
			int pre = 1;
			int tmp= 0;
			for (int i = 3; i <= n; i ++) {
				tmp = res;
				res += pre;
				pre = tmp;
			}
			return res;
		}
		
		/**
		 * @method      f3
		 * @parameter   
		 * @return      int
		 * @details     [F(n),F(n-1)]=[F(n-1),F(n-2)] *	|1 1| = (1,1) * (|1 1|) ^ (n-2)
		 * @details     								|1 0|          	(|1 0|)
		 */
		public int f3(int n) {
			if (n < 1) return 0;
			if (n == 1 || n == 2)
				return 1;
			int [][] base = {{1,1},{1,0}};
			int[][] res = f3_maxtrixPower(base , n - 2);
			return res[0][0] + res[1][0];
		}

		private int[][] f3_maxtrixPower(int[][] m, int p) {
			int[][ ] res = new int[m.length][m[0].length];
			//先把res设为单位矩阵，相当于整数中的1
			for (int i = 0; i < res.length; i++)
				res[i][i] = 1;
			int[][] tmp = m;
			for (; p!=0; p >>=1) {
				if ((p &1) != 0)
					res = f3_multiMatrix(res, tmp);
				tmp = f3_multiMatrix(tmp, tmp);
			}
			
			return res;
		}

		private int[][] f3_multiMatrix(int[][] m1, int[][] m2) {
			int[][] res = new int[m1.length][m2[0].length];
			for (int i = 0; i < m1.length; i ++) {
				for (int j = 0; j < m2[0].length; j ++) {
					for (int k = 0; k < m2.length; k ++) {
						res[i][j] += m1[i][k] * m2[k][j];
					}
				}
			}
			return res;
		}
	}
	
	
}
