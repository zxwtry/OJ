package hiho;

/*
 * 	AC
 * 	之前没有AC的问题是:
 * 		输入是以下标 1 开始的。
 */


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N99_骑士问题_三个骑士走到一起 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = Integer.parseInt(scanner.nextLine().trim());
		while (num -- > 0) {
			String line = scanner.nextLine();
			String lines[] = line.split(" ");
			for (int ipi = 0; ipi < NUM_PLAYER; ipi ++) {
				String string = lines[ipi];
				ip[ipi][0] = string.charAt(0)-'A';
				ip[ipi][1] = string.charAt(1)-'1';
			}
			System.out.println(solve());
		}
		scanner.close();
	}
	final static int[] xd = {2,2,1,1,-1,-1,-2,-2};
	final static int[] yd = {1,-1,2,-2,2,-2,1,-1};
	final static int SIZE_CHESS = 8;
	final static int NUM_PLAYER = 3;
	static int[][][] sp = new int[NUM_PLAYER][SIZE_CHESS][SIZE_CHESS]; 
	static int[][] ip = new int[NUM_PLAYER][2];
	static void bfs(int[][] f, int x, int y) {
		for (int fi = 0; fi < f.length; fi ++)
			Arrays.fill(f[fi], -1);
		f[x][y] = 0;
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		qx.add(x); qy.add(y);
		while(!qx.isEmpty()) {
			int xn = qx.poll(), yn = qy.poll();
			for(int di = 0; di < xd.length; di ++) {
				int xx = xn + xd[di], yy = yn + yd[di];
				if (xx >= 0 && xx < SIZE_CHESS && yy >= 0 && yy < SIZE_CHESS
						&& f[xx][yy] == -1) {
					f[xx][yy] = f[xn][yn] + 1;
					qx.add(xx); qy.add(yy);
				}
			}
		}
	}
	static int solve() {
		for (int spi = 0; spi < NUM_PLAYER; spi ++) {
			bfs(sp[spi], ip[spi][0], ip[spi][1]); 
		}
		int ans = Integer.MAX_VALUE >> 2;
		for (int spx = 0; spx < SIZE_CHESS; spx ++)
			for (int spy = 0; spy < SIZE_CHESS; spy ++) {
				boolean isSkip = false;
				int sigma = 0;
				for (int ni = 0; ni < NUM_PLAYER; ni ++) {
					sigma += sp[ni][spx][spy];
					if (sp[ni][spx][spy] == -1)
						isSkip |= true;
				}
				if (isSkip)
					continue;
				if (ans > sigma)
					ans = sigma;
			}
		return ans;
	}
}