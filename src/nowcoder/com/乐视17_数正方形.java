package nowcoder.com;

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
	}

	static void solve1() {
		Scanner sc = new Scanner(System.in);
		int times = Integer.parseInt(sc.nextLine().trim());
		while (times -- > 0) {
			String[] parts = sc.nextLine().trim().split(" ");
			int row = Integer.parseInt(parts[0]);
			int col = Integer.parseInt(parts[parts.length - 1]);
			char[][] cs = new char[row][col];
			for (int i = 0; i < row; i ++) {
				cs[i] = sc.nextLine().trim().toCharArray();
			}
			for (int i = 1; i < (row - 1) * 2; i += 2) {
				for (int j = 1; j < (col - 1) * 2; j += 2) {
					int i1 = (i + 1) / 2, i2 = (i - 1) / 2;
					int j1 = (j + 1) / 2, j2 = (j - 1) / 2;
					if (cs[i1][j1] == '1' && cs[i2][j1] == '1' && cs[i1][j2] == '1' && cs[i2][j2] == '1') {
						cs[i1][j1] = '0';
						cs[i2][j1] = '0';
						cs[i1][j2] = '0';
						cs[i2][j2] = '0';
					}
				}
			}
			int i = 0, j = 0;
			boolean findOne = false;
			do {
				findOne = false;
				for (; i < row; i ++) {
					for (; j < col; j ++) {
						if (cs[i][j] != '0') {
							findOne = true;
							solve1searchLean(cs, i, j, i + 1, j + 1, i, j);
							solve1searchLean(cs, i, j, i - 1, j + 1, i, j);
							solve1searchLean(cs, i, j, i - 1, j - 1, i, j);
							solve1searchLean(cs, i, j, i + 1, j - 1, i, j);
							solve1searchLine(cs, i, j, i + 1, j, i, j, 1, 0);
							solve1searchLine(cs, i, j, i, j + 1, i, j, 0, 1);
							solve1searchLine(cs, i, j, i - 1, j, i, j, 1, 0);
							solve1searchLine(cs, i, j, i, j - 1, i, j, 0, 1);
						}
					}
				}
			} while (findOne);
		}
		sc.close();
	}
	
	private static boolean solve1searchLine(char[][] cs, int preI, int preJ, int nowI, int nowJ, int stI, int stJ, int coI, int coJ) {
		if (nowI == stI && nowJ == stJ) {
			System.out.println(coI + "..." + coJ);
			return true;
		}
		if (nowI < 0 || nowI >= cs.length || nowJ < 0 || nowJ >= cs[0].length || cs[nowI][nowJ] != '1') {
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
		if (count == 1) {
			int newI = is[selectIndex], newJ = js[selectIndex];
			if (selectIndex == 0 || selectIndex == 1) {
				coI ++;
			} else {
				coJ ++;
			}
			boolean ans = false;
			if (! ans && (newI + 1 != nowI || newJ != nowJ)) {
				solve1searchLine(cs, newI, newJ, newI + 1, newJ, stI, stJ, coI, coJ);
			}
			if (! ans && (newI - 1 != nowI || newJ != nowJ)) {
				solve1searchLine(cs, newI, newJ, newI - 1, newJ, stI, stJ, coI, coJ);
			}
			if (! ans && (newI != nowI || newJ + 1 != nowJ)) {
				solve1searchLine(cs, newI, newJ, newI, newJ + 1, stI, stJ, coI, coJ);
			}
			if (! ans && (newI != nowI || newJ - 1 != nowJ)) {
				solve1searchLine(cs, newI, newJ, newI, newJ - 1, stI, stJ, coI, coJ);
			}
			return ans;
		} else {
			return false;
		}
	}

	private static void solve1searchLean(char[][] cs, int i1, int j1, int i2, int j2, int sti, int stj) {
		
	}
	
}