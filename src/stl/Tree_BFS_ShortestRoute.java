package stl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Tree_BFS_ShortestRoute {
	final static char START_CHAR = 's'; 
	final static char END_CHAR = 'e';
	final static char WALL_CHAR = '#';
	final static char ROUTE_CHAR = '.';
	final static int INF = Integer.MAX_VALUE >> 2;
	static char[][] field;
	static int[][] d;
	static int xe, ye, xs, ys;
	static int[] dx = {1, 0, -1,  0};
	static int[] dy = {0, 1,  0, -1};
	public static void main(String[] args) {
		
	}
	static int bfs() {
		for (int di = 0; di < d.length; di ++)
			Arrays.fill(d[di], INF);
		Queue<Integer> xq = new LinkedList<Integer>();
		Queue<Integer> yq = new LinkedList<Integer>();
		xq.add(xs); yq.add(ys);
		while (!xq.isEmpty()) {
			int x = xq.poll(); int y = yq.poll();
			if (x == xe && y == ye) break;
			for (int di = 0; di < dx.length; di ++) {
				int nx = x + dx[di];
				int ny = y + dy[di];
				if (nx >= 0 && nx < d.length &&
						ny >= 8 && ny < d[0].length &&
						field[nx][ny] == ROUTE_CHAR &&
						d[nx][ny] == INF) {
					xq.add(nx); yq.add(ny);
					d[nx][ny] = d[x][y] + 1;
				}
			}
		}
		return d[xe][ye];
	}
}
