package poj10x;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P1024 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numOfTest = scanner.nextInt();
		boolean ok;
		int w, h, cnt, steps;
		String path;
		Grid[][] grid = null;
		Queue<Pair> q = new LinkedList<Pair>();
		int x, y, desx, desy, x2, y2, i;
		while (numOfTest -- > 0) {
			w = scanner.nextInt();
			h = scanner.nextInt();
			grid = new Grid[h][w];
			for (y = 0; y < h ; ++ y) {
				for (x = 0; x < w; ++ x) {
					grid[y][x] = new Grid();
	                grid[y][x].inpath = false;
	                grid[y][x].uwal = false;
	                grid[y][x].rwal = false;
	                grid[y][x].scnt = -1;
	                grid[y][x].dcnt = -1;
				}
			}
			path = scanner.next();
			x = 0; y = 0;
			grid[0][0].inpath = true;
			steps = path.length();
			for (i = 0; i < steps; ++ i) {
				switch (path.charAt(i)) {
				case 'U': ++ y; break;
				case 'D': -- y; break;
				case 'L': -- x; break;
				case 'R': ++ x; break;
				}
				grid[y][x].inpath = true;
			}
			desx = x; desy = y;
			cnt = scanner.nextInt();
	        for (i = 0; i < cnt; ++i) {
	            x = scanner.nextInt(); y = scanner.nextInt();
	            x2 = scanner.nextInt(); y2 = scanner.nextInt();
	            if (x == x2)
	                if (y + 1 == y2) grid[y][x].uwal = true;
	                else grid[y2][x].uwal = true;
	            else
	                if (x + 1 == x2) grid[y][x].rwal = true;
	                else grid[y][x2].rwal = true;
	        }
	        q.add(new Pair(0, 0));
	        grid[0][0].scnt = 0;
	        while (! q.isEmpty() ) {
	        	Pair pair = q.poll();
	        	y = pair.data1; x = pair.data2;
	        	if (y < h-1 && grid[y][x].uwal == false && grid[y+1][x].scnt == -1) {
	        		grid[y+1][x].scnt = grid[y][x].scnt + 1;
	        		q.add(new Pair(y+1, x));
	        	}
	            if (0 < y && grid[y - 1][x].uwal == false && grid[y - 1][x].scnt == -1)
	            {
	                grid[y - 1][x].scnt = grid[y][x].scnt + 1;
	                q.add(new Pair(y - 1, x));
	            }
	            if (0 < x && grid[y][x - 1].rwal == false && grid[y][x - 1].scnt == -1)
	            {
	                grid[y][x - 1].scnt = grid[y][x].scnt + 1;
	                q.add(new Pair(y, x - 1));
	            }
	            if (x < w - 1 && grid[y][x].rwal == false && grid[y][x + 1].scnt == -1)
	            {
	                grid[y][x + 1].scnt = grid[y][x].scnt + 1;
	                q.add(new Pair(y, x + 1));
	            }
	        }
	        // 求各点到终点的最小步数（BFS）
	        q.add(new Pair(desy, desx));
	        grid[desy][desx].dcnt = 0;
	        while(! q.isEmpty() ) {
	            y = q.peek().data1; x = q.peek().data2;
	            if (y < h - 1 && grid[y][x].uwal == false && grid[y + 1][x].dcnt == -1)
	            {
	                grid[y + 1][x].dcnt = grid[y][x].dcnt + 1;
	                q.add(new Pair(y + 1, x));
	            }
	            if (0 < y && grid[y - 1][x].uwal == false && grid[y - 1][x].dcnt == -1)
	            {
	                grid[y - 1][x].dcnt = grid[y][x].dcnt + 1;
	                q.add(new Pair(y - 1, x));
	            }
	            if (0 < x && grid[y][x - 1].rwal == false && grid[y][x - 1].dcnt == -1)
	            {
	                grid[y][x - 1].dcnt = grid[y][x].dcnt + 1;
	                q.add(new Pair(y, x - 1));
	            }
	            if (x < w - 1 && grid[y][x].rwal == false && grid[y][x + 1].dcnt == -1)
	            {
	                grid[y][x + 1].dcnt = grid[y][x].dcnt + 1;
	                q.add(new Pair(y, x + 1));
	            }
	            q.poll();
	        }

	        // 判断路径是否唯一最短，以及墙是否多余
	        ok = true;
	        for (y = 0; y < h && ok; ++y)
	            for (x = 0; x < w && ok; ++x)
	            {
	                if (grid[y][x].scnt == -1 || grid[y][x].dcnt == -1)
	                    ok = false;     // 是否有封闭区域
	                if (y < h - 1 && grid[y][x].uwal
	                        && grid[y][x].scnt + grid[y + 1][x].dcnt + 1 > steps
	                        && grid[y][x].dcnt + grid[y + 1][x].scnt + 1 > steps)
	                    ok = false;     // 是否上墙多余
	                if (x < w - 1 && grid[y][x].rwal
	                        && grid[y][x].scnt + grid[y][x + 1].dcnt + 1 > steps
	                        && grid[y][x].dcnt + grid[y][x + 1].scnt + 1 > steps)
	                    ok = false;     // 是否右墙多余
	                if (!grid[y][x].inpath && grid[y][x].scnt + grid[y][x].dcnt <= steps)
	                    ok = false;     // 是否存在更短路径或另一最短路径
	            }
	        if(ok) System.out.println( "CORRECT" );
	        else System.out.println("INCORRECT" );
	        
		}
		scanner.close();
	}
	static class Pair {
		int data1, data2;
		public Pair() {}
		public Pair(int data1, int data2) {
			this.data1 = data1;
			this.data2 = data2;
		}
	}
	static class Grid {
		boolean inpath;		//是否是路径方格
		boolean uwal;		//是否有上墙
		boolean rwal;		//是否有右墙
		int scnt;			//到原点步数
		int dcnt;			//到终点步数
	}
}