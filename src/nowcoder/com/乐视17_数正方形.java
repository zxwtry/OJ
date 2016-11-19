package nowcoder.com;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 										
	小B正在做一个关于图像理解方面的研究，她的目标是识别图像中的轮廓。
	当前阶段，她希望能够识别正方形。图像用一个矩阵表示，矩阵的每个元素对应于图像中的一个像素点，
	值为0或1，0表示背景，1表示前景。需要寻找的正方形必须满足线宽为单像素，且大小至少为2x2。
	她希望你能帮她找出图像中满足如下条件的两类正方形：
	正方形的边与矩阵边缘平行；
	正方形的边与矩阵对角线平行；
	
	如以下矩阵中只有一个第一类正方形：
	0000000
	0111100
	0100100
	0100100
	0111100
	以下的矩阵中只有一个第二类正方形：
	0000000
	0010000
	0101000
	0010000
	0000000
	
	此外，不管何种类型的正方形，其每条边必须等长，且不能有任何边或顶点的像素与不属于该正方形的像素相连接。
	
	
									
	输入
	输入中有多组测试数据。第一行为一个整数t（1 =＜t =＜10000），表示测试数据的组数，
	接下来是t组测试数据。每组测试数据的第一行为两个整数n和m（2＜=n, m＜=250），
	n和m分别为矩阵的行数和列数，接下来是n行数据，每行由m个0或1构成。
	每个测试输入中的字符数不超过10^6个。
	
	样例输入
	3
	8 8
	00010001
	00101000
	01000100
	10000010
	01000100
	00101000
	11010011
	11000011
	10 10
	1111111000
	1000001000
	1011001000
	1011001010
	1000001101
	1001001010
	1010101000
	1001001000
	1000001000
	1111111000
	12 11
	11111111111
	10000000001
	10111111101
	10100000101
	10101100101
	10101100101
	10100000101
	10100000101
	10111111101
	10000000001
	11111111111
	00000000000
	
	输出
	对每组测试数据，在单独的行中输出矩阵中符合要求的正方形的数量。
	样例输出
	1
	2
	3
	
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

public class 乐视17_数正方形 {
	public static void main(String[] args) {
		solve1();
//		solve2();
	}
	static boolean[][] traveled = null;
	static int count = 0;
	static void solve1() {
		Scanner sc = new Scanner(System.in);
		int times = Integer.parseInt(sc.nextLine().trim());
		while (times -- > 0) {
			count = 0;
			String[] parts = sc.nextLine().trim().split(" ");
			int row = Integer.parseInt(parts[0]);
			int col = Integer.parseInt(parts[parts.length - 1]);
			char[][] cs = new char[row][col];
			traveled = new boolean[row][col];
			for (int i = 0; i < row; i ++) {
				cs[i] = sc.nextLine().trim().toCharArray();
			}
			for (int i = 1; i < (row - 1) * 2; i ++) {
				for (int j = 1; j < (col - 1) * 2; j ++) {
					int i1 = (i - 1) / 2, i2 = i1 + 1;
					int j1 = (j - 1) / 2, j2 = j1 + 1;
					if (cs[i1][j1] == '1' && cs[i1][j2] == '1' && cs[i2][j1] == '1' && cs[i2][j2] == '1') {
						int[] surI = new int[] {(i-3)/2, (i-3)/2, (i-3)/2, (i-3)/2,
												(i+3)/2, (i+3)/2, (i+3)/2, (i+3)/2,
												i1, i1, i2, i2};
						int[] surJ = new int[] {(j-3)/2, (j-1)/2, (j+1)/2, (j+3)/2,
												(j-3)/2, (j-1)/2, (j+1)/2, (j+3)/2,
												j1 - 1, j2 + 1, j1 - 1, j2 + 1};
						boolean findOne = true;
						for (int suri = 0; findOne && suri < surI.length; suri ++) {
							for (int surj = 0; findOne && surj < surJ.length; surj ++) {
								if (surI[suri] > -1 && surI[suri] < row && surJ[surj] > -1 && surJ[surj] < col) {
									if (cs[surI[suri]][surJ[surj]] == '1') {
										findOne = false;
									}
								}
							}
						}
						count += findOne ? 1 : 0;
						traveled[i1][j1] = true;
						traveled[i1][j2] = true;
						traveled[i2][j1] = true;
						traveled[i2][j2] = true;
					}
				}
			}
			for (int i = 0; i < row; i ++) {
				for (int j = 0; j < col; j ++) {
					if (cs[i][j] != '0' && ! traveled[i][j]) {
						boolean find = false;
						if (! find)
							find |= solve1searchLean(cs, i, j, i + 1, j + 1, i, j, 1, 0);
						if (! find)
							find |= solve1searchLean(cs, i, j, i - 1, j + 1, i, j, 0, 1);
						if (! find)
							find |= solve1searchLean(cs, i, j, i - 1, j - 1, i, j, 1, 0);
						if (! find)
							find |= solve1searchLean(cs, i, j, i + 1, j - 1, i, j, 0, 1);
						if (! find)
							find |= solve1searchLine(cs, i, j, i + 1, j, i, j, 1, 0);
						if (! find)
							find |= solve1searchLine(cs, i, j, i, j + 1, i, j, 0, 1);
						if (! find)
							find |= solve1searchLine(cs, i, j, i - 1, j, i, j, 1, 0);
						if (! find)
							find |= solve1searchLine(cs, i, j, i, j - 1, i, j, 0, 1);
						count += find ? 1 : 0;
					}
				}
			}
			System.out.println(count);
		}
		sc.close();
	}
	
	private static boolean solve1searchLine(char[][] cs, int preI, int preJ, int nowI, int nowJ, int stI, int stJ, int coI, int coJ) {
		if (nowI == stI && nowJ == stJ) {
			return true;
		}
		if (nowI < 0 || nowI >= cs.length || nowJ < 0 || nowJ >= cs[0].length || cs[nowI][nowJ] != '1' || traveled[nowI][nowJ]) {
			return false;
		}
		int[] is = new int[] {nowI + 1, nowI - 1, nowI, nowI};
		int[] js = new int[] {nowJ, nowJ, nowJ + 1, nowJ - 1};
		int count = 0;
		int selectIndex = -1;
		for (int index = 0; index < is.length; index ++) {
			if (is[index] > -1 && is[index] < cs.length && js[index] > -1 && js[index] < cs[0].length
					&& (is[index] != preI || js[index] != preJ) && cs[is[index]][js[index]] == '1') {
				count ++;
				selectIndex = index;
			}
		}
		int[] is2 = new int[] {nowI + 1, nowI + 1, nowI - 1, nowI - 1};
		int[] js2 = new int[] {nowJ - 1, nowJ + 1, nowJ - 1, nowJ + 1};
		for (int i = 0; i < is2.length; i ++) {
			for (int j = 0; j < js2.length; j ++) {
				if (is2[i] > -1 && is2[i] < cs.length && js2[j] > -1 && js2[j] < cs[0].length && cs[is2[i]][js2[j]] == '1') {
					return false;
				}
			}
		}
		if (count == 1) {
			int newI = is[selectIndex], newJ = js[selectIndex];
			if (selectIndex == 0 || selectIndex == 1) {
				coI ++;
			} else {
				coJ ++;
			}
			return solve1searchLine(cs, nowI, nowJ, newI, newJ, stI, stJ, coI, coJ);
		} else {
			return false;
		}
	}

	private static boolean solve1searchLean(char[][] cs, int preI, int preJ, int nowI, int nowJ, int stI, int stJ, int coI, int coJ) {
		if (nowI == stI && nowJ == stJ) {
			return true;
		}
		if (nowI < 0 || nowI >= cs.length || nowJ < 0 || nowJ >= cs[0].length || cs[nowI][nowJ] != '1' || traveled[nowI][nowJ]) {
			return false;
		}
		int[] is = new int[] {nowI + 1, nowI + 1, nowI - 1, nowI - 1};
		int[] js = new int[] {nowJ - 1, nowJ + 1, nowJ - 1, nowJ + 1};
		int count = 0;
		int selectIndex = -1;
		for (int index = 0; index < is.length; index ++) {
			if (is[index] > -1 && is[index] < cs.length && js[index] > -1 && js[index] < cs[0].length
					&& (is[index] != preI || js[index] != preJ) && cs[is[index]][js[index]] == '1') {
				count ++;
				selectIndex = index;
			}
		}
		int[] is2 = new int[] {nowI + 1, nowI - 1, nowI, nowI};
		int[] js2 = new int[] {nowJ, nowJ, nowJ + 1, nowJ - 1};
		for (int i = 0; i < is2.length; i ++) {
			for (int j = 0; j < js2.length; j ++) {
				if (is2[i] > -1 && is2[i] < cs.length && js2[j] > -1 && js2[j] < cs[0].length && cs[is2[i]][js2[j]] == '1') {
					return false;
				}
			}
		}
		if (count == 1) {
			int newI = is[selectIndex], newJ = js[selectIndex];
			if (selectIndex == 2 || selectIndex == 1) {
				coI ++;
			} else {
				coJ ++;
			}
			return solve1searchLean(cs, nowI, nowJ, newI, newJ, stI, stJ, coI, coJ);
		} else {
			return false;
		}
	}
	
	
	/**
	 * @method		solve2 
	 * @parameter	void
	 * @return 		void
	 * @details 	第二个Solution
	 */
	static void solve2() {
		char[] lu = new char[] { '0', '0', '0', '0', '1', '1', '0', '1', 0 };
		char[] ld = new char[] { '0', '1', 0, '0', '1', '1', '0', '0', '0' };
		char[] ru = new char[] { '0', '0', '0', '1', '1', '0', 0, '1', '0' };
		char[] rd = new char[] { 0, '1', '0', '1', '1', '0', '0', '0', '0' };
		char[] ude = new char[] { 0, '0', 0, '1', '1', '1', 0, '0', 0 };
		char[] lre = new char[] { 0, '1', 0, '0', '1', '0', 0, '1', 0 };
		char[] u = new char[] { '0', '0', '0', '0', '1', '0', '1', '0', '1' };
		char[] d = new char[] { '1', '0', '1', '0', '1', '0', '0', '0', '0' };
		char[] l = new char[] { '0', '0', '1', '0', '1', '0', '0', '0', '1' };
		char[] r = new char[] { '1', '0', '0', '0', '1', '0', '1', '0', '0' };
		char[] rdde = new char[] { '1', '0', '0', '0', '1', '0', '0', '0', '1' };
		char[] ldde = new char[] { '0', '0', '1', '0', '1', '0', '1', '0', '0' };
		Scanner scanner = new Scanner(System.in);
		for (int t = scanner.nextInt(); t > 0; t--) {
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			char[][] a = new char[n + 2][m + 2];
			scanner.nextLine();
			Arrays.fill(a[0], '0');
			for (int i = 0; i < n; i++) {
				Arrays.fill(a[i + 1], '0');
				char[] cs = scanner.nextLine().toCharArray();
				for (int j = 0; j < m; j++) {
					a[i + 1][j + 1] = cs[j];
				}
			}
			Arrays.fill(a[n + 1], '0');
			int count = 0;
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					if (solve2ch(a, i, j, lu)) {
						int x, y;
						for (x = 1; solve2ch(a, i, j + x, ude)
								&& solve2ch(a, i + x, j, lre); x++)
							;
						for (y = 1; solve2ch(a, i + y, j + x, lre)
								&& solve2ch(a, i + x, j + y, ude); y++)
							;
						if (x == y && solve2ch(a, i + y, j, ld)
								&& solve2ch(a, i, j + x, ru)
								&& solve2ch(a, i + y, j + x, rd)) {
							count++;
						}
					}
					if (solve2ch(a, i, j, u)) {
						int x, y;
						for (x = 1; solve2ch(a, i + x, j + x, rdde)
								&& solve2ch(a, i + x, j - x, ldde); x++)
							;
						for (y = 1; solve2ch(a, i + x + y, j + x - y, ldde)
								&& solve2ch(a, i + x + y, j - x + y, rdde); y++)
							;
						if (x == y && solve2ch(a, i + x, j + x, r)
								&& solve2ch(a, i + x, j - x, l)
								&& solve2ch(a, i + x + y, j, d)) {
							count++;
						}
					}
				}
			}
			System.out.println(count);
			scanner.close();
		}
	}
	private static boolean solve2ch(char[][] a, int i, int j, char[] c) {
		return (a[i - 1][j - 1] == c[0] || c[0] == 0)
				&& (a[i - 1][j] == c[1] || c[1] == 0)
				&& (a[i - 1][j + 1] == c[2] || c[2] == 0)
				&& (a[i][j - 1] == c[3] || c[3] == 0) && a[i][j] == c[4]
				&& (a[i][j + 1] == c[5] || c[5] == 0)
				&& (a[i + 1][j - 1] == c[6] || c[6] == 0)
				&& (a[i + 1][j] == c[7] || c[7] == 0)
				&& (a[i + 1][j + 1] == c[8] || c[8] == 0);
	}
}