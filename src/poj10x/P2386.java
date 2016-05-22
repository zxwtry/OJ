package poj10x;

import java.util.Scanner;

/*
 * 	找水汪数目
 */
public class P2386 {
	static char[][] field;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		field = new char[scanner.nextInt()][];
		scanner.nextInt();
		for (int ix = 0; ix < field.length; ix ++) {
			field[ix] = scanner.next().trim().toCharArray();
		}
		solve();
		scanner.close();
	}
	static void dfs(int x, int y) {
		field[x][y] = '.';
		for (int dx = -1; dx <= 1; dx ++) {
			for (int dy = -1; dy <= 1; dy ++) {
				int nx = x + dx;
				int ny = y + dy;
				if (nx >= 0 && nx < field.length &&
					ny >= 0 && ny < field[0].length &&
					field[nx][ny] == 'W')
					dfs(nx, ny);
			}
		}
	}
	static void solve() {
		int ans = 0;
		for (int x = 0; x < field.length; x ++)
			for (int y = 0; y <field[0].length; y ++)
				if (field[x][y] == 'W') {
					dfs(x, y);
					ans ++;
				}
		System.out.printf("%d\n\n", ans);
	}
}
