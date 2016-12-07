package template;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     stl
 * @file        Graph_Dijkstra.java
 * @type        Graph_Dijkstra
 * @date        2016年11月30日 上午11:59:18
 * @details     来源是一个网贴，对nowcoder.com.百度17_赶火车测试。
 * @details     自己认为比较好的一份代码。
 */
public class RECITE_Graph_Dijkstra0 {
	public static void main(String[] args) {
		try {
			solve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	final static int MAX = Integer.MAX_VALUE;
	final static int MAXN = 1005;
	final static int[][] WS = new int[MAXN][MAXN];
	final static int[][] NEXT = new int[MAXN][MAXN];
	final static int[] AS = new int[MAXN];
	final static HashSet<Integer> BS = new HashSet<Integer>();
	final static int[] HEAP = new int[MAXN];
	final static int[] HEIN = new int[MAXN];
	final static int[] DS = new int[MAXN];
	static int HI = 0;
	static void solve() throws Exception {
		Scanner sc = new Scanner(new File("D:/code/data/百度17_赶火车.txt"));
		int times = sc.nextInt();
		for (int timeIndex = 1; timeIndex <= times; timeIndex ++) {
			int n = sc.nextInt(), m = sc.nextInt(), a = sc.nextInt(), b = sc.nextInt();
			for (int i = 1; i <= n; i ++)
				for (int j = 1; j <= n; j ++)
					WS[i][j] = MAX;
			BS.clear();
			for (int ai = 0; ai < a; ai ++) AS[ai] = sc.nextInt();
			for (int bi = 0; bi < b; bi ++) BS.add(sc.nextInt());
			for (int mi = 0; mi < m; mi ++) {
				int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
				WS[u][v] = w; WS[v][u] = w;
			}
			for (int i = 1; i <= n; i ++) {
				int prej = 0;
				NEXT[i][0] = -1;
				for (int j = 1; j <= n; j ++) {
					if (WS[i][j] != MAX) {
						NEXT[i][prej] = j;
						NEXT[i][j] = -1;
						prej = j;
					}
				}
			}
			
			int min = MAX;
			for (int ai = 0; ai < a; ai ++) {
				int s = AS[ai];
				for (int di = 1; di <= n; di ++) DS[di] = di == s ? 0 : MAX;
				HI = 0;
				for (int hi = 1; hi <= n; hi ++)
					if (hi != s) { HEAP[++HI] = hi; HEIN[hi] = HI; }
				while (true) {
					if (BS.contains(s))	{ min=Math.min(min, DS[s]); break;}
					int next = NEXT[s][0];
					while (next != -1) {
						if (DS[next] > DS[s] + WS[s][next]) {
							DS[next] = DS[s] + WS[s][next];
							//heapUp
							int i = HEIN[next];
							int p = i / 2;
							while (p != 0) {
								if (DS[HEAP[p]] > DS[HEAP[i]]) {
									swap(HEIN, HEAP[p], HEAP[i]);
									swap(HEAP, p, i);
								} else break;
								i = p; p = i / 2;
							}
						}
						next = NEXT[s][next];
					}
					if (HI == 0) break;
					s = HEAP[1];
					HEAP[1] = HEAP[HI--];
					//heapDn
					int i = 1, c = 2;
					while (c <= HI) {
						if (c+1<=HI && DS[HEAP[c+1]] < DS[HEAP[c]]) c++;
						if (DS[HEAP[i]] > DS[HEAP[c]]) {
							swap(HEIN, HEAP[c], HEAP[i]);
							swap(HEAP, c, i);
						} else break;
						i = c;
						c = 2 * i;
					}
				}
			}
			System.out.println("Case #"+timeIndex+": "+(min==MAX?"No answer":min));
		}
		sc.close();
	}
	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
