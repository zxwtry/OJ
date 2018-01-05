package nowcoder.zuo;

public class Book054_斐波那契系列问题的递归和动态规划 {
	public static void main(String[] args) {
	}
	
	static void debug牛记数() {
		S2牛记数 s = new S2牛记数();
		for (int times = 1; times < 33; times ++) {
			int f1 = s.f1(times);
			int f2 = s.f2(times);
			int f3 = s.f3(times);
			System.out.printf("%d...%d...%d\r\n", f1, f2, f3);
		}
	}

	static void debug进阶问题() {
		S1进阶问题 s = new S1进阶问题();
		for (int times = 1; times < 33; times ++) {
			int f1 = s.f1(times);
			int f2 = s.f2(times);
			int f3 = s.f3(times);
			System.out.printf("%d...%d...%d\r\n", f1, f2, f3);
		}
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
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book054_斐波那契系列问题的递归和动态规划.java
	 * @type        S1进阶问题
	 * @date        2016年11月25日 上午9:35:33
	 * @details     跳台阶问题
	 * @details     一次可以跳1级，或者跳2级
	 */
	static class S1进阶问题 {
		public int f1(int n) {
			if (n < 1) {
				return 0;
			}
			if (n == 1 || n == 2) {
				return n;
			}
			return f1(n - 1) + f1(n - 2);
		}
		
		public int f2(int n) {
			if (n < 1) {
				return 0;
			}
			if (n == 1 || n == 2) {
				return n;
			}
			int res = 2;
			int pre = 1;
			int tmp = 0;
			for (int i = 3; i <= n; i ++) {
				tmp = res;
				res = res + pre;
				pre = tmp;
			}
			return res;
		}
		
		/**
		 * @method      f3
		 * @parameter   
		 * @return      int
		 * @details     [S(n), S(n-1)] = [S(2), S(1)] *	|1 1|^(n-2) = (2, 1) * |1 1| ^ (n-2)
		 * @details     								|1 0|				   |1 0|
		 */
		public int f3(int n) {
			if (n < 1) {
				return 0;
			}
			if (n == 1 || n == 2) {
				return n;
			}
			int[][] base = {{1,1},{1,0}};
			int[][] res = f3_maxtrixPower(base, n - 2);
			return 2 * res[0][0] + res[1][0];
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
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book054_斐波那契系列问题的递归和动态规划.java
	 * @type        S2牛记数
	 * @date        2016年11月25日 上午9:56:01
	 * @details   　　所有的牛都不会死
	 * @details   　　成年牛，每年生一头牛
	 * @details   　　n-3年的所有牛，到第n年已经都已经成熟
	 * @details   　　f(n) = f(n-1) + f(n-3)
	 */
	static class S2牛记数 {
		public int f1(int n) {
			if (n < 1) {
				return 0;
			}
			if (n == 1 || n == 2 || n == 3) {
				return n;
			}
			return f1(n - 1) + f1(n - 3);
		}
		
		public int f2(int n) {
			if (n < 1) {
				return 0;
			}
			if (n == 1 || n == 2 || n == 3) {
				return n;
			}
			int res = 3;
			int pre = 2;
			int prepre = 1;
			int tmp1 = 0;
			int tmp2 = 0;
			for (int i = 4; i <= n; i ++) {
				tmp1 = res;
				tmp2 = pre;
				res = res + prepre;
				pre = tmp1;
				prepre = tmp2;
			}
			return res;
		}
		
		/**
		 * @method      f3
		 * @parameter   
		 * @return      int
		 * @details     [f(n), f(n-1), f(n-2)] = [f(3), f(2), f(1)] *	|1 1 0|^(n-3) = [3,2,1] * |1 1 0|^(n-3)
		 * @details     										　　　　	|0 0 1|                   |0 0 1|
		 * @details     										　　　　	|1 0 0|                   |1 0 0|
		 */
		public int f3(int n) {
			if (n < 1) {
				return 0;
			}
			if (n == 1 || n == 2 || n == 3) {
				return n;
			}
			int[][] base = {{1,1,0},{0,0,1},{1,0,0}};
			int[][] res = f3_maxtrixPower(base, n - 3);
			return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
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
