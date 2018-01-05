package nowcoder.com;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 小A到达了一个陌生的城市，经过几天的功课，他已经知道了整个城市的公共交通情况。
 * 所有的公交设计都是完全对称的，公共汽车都是对向开行，且线路相同，
 * 对向开行的道路是同一条道路。小A所住的宾馆附近有a个公交车站，你知道火车站附近有b个公交车站。
	小A想知道从宾馆选定附近某个公交车站出发到火车站附近某一公交车站的最近的路程是多少。
	你能帮他吗？
	
	输入
	输入数据有多组，第一行为一个正整数T(T＜=20)，表示测试数据组数，接下来包含T组测试数据。
	每组测试数据第一行包含四个整数n(1＜=n＜=1000)，m(0＜=m< =n*(n-1)/2)，a(1＜=a＜=n),
	 b(1＜=b＜=n)，分别表示城市中公交车站数、道路数目、宾馆附近公交车站数目和火车站附近的公交车站数目。
	接下来一行包含a个整数si，si(1＜=si＜=n)表示宾馆附近的a个公交车站。
	接下来一行包含b个整数ei，ei(1＜=ei＜=n)表示火车站附近的b个公交车站。
	接下来m行每行包含三个整数u, v, w, (1＜= u＜= n, 1＜= v＜= n, u不等于v, 0＜= w＜10000)
	表示结点u和结点v之间存在一条长度为w的路径。(保证没有重边)
	
	样例输入
	2
	6 5 2 2
	1 2
	3 4
	1 5 1
	2 6 4
	5 6 2
	3 5 4
	4 6 1
	6 4 2 2
	1 2
	3 4
	1 5 1
	2 5 3
	6 4 1
	6 3 2
	输出
	对于每组测试数据，输出一行"Case #x: s"，x表示第x组数据，x从1开始递增，s表示所求的最短距离，
	如果无法从宾馆通过给定的交通路线图到达火车站则输出"No answer"，详细参见给定样例。
	样例输出
	Case #1: 4
	Case #2: No answer
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 * 
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度17_赶火车.java
 * @type        百度17_赶火车
 * @date        2016年11月29日 下午5:26:11
 * @details     
 */
public class 百度17_赶火车 {
	static final int M = Integer.MAX_VALUE;
	public static void main(String[] args) {
		try {
//			long t1 = System.currentTimeMillis();
			solve1();
			solve2();
			solve3();
			solve4();
//			long t2 = System.currentTimeMillis();
//			System.out.println(t2 - t1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	static class Edge {
		int to, next, cost;
		public Edge(int to, int next, int cost) 
		{this.to = to; this.next = next; this.cost = cost;}
	}
	static class State {
		int cost, id;
		public State(int id, int cost) {this.cost = cost; this.id = id;}
	}
	static class Cmp implements Comparator<State> {
		public int compare(State o1, State o2) {
			return o1.cost - o2.cost;
		}
	}
	static final int MAXN = 1020, MAXM = MAXN*(MAXN-1)/2;
	static int N, L;
	static int[] head = new int[MAXN];
	static int[] dist = new int[MAXN];
	static Edge[] edges = new Edge[MAXM];
	static void init(int n) {
//		for (; L > -1; L --) edges[L] = null;
		N = n;
		L = 0;
		for (int i = 0; i < n; i ++) head[i] = -1;
	}
	static void addEdge(int x, int y, int cost) {
		if (edges[L] == null) edges[L] = new Edge(y, head[x], cost);
		else {
			edges[L].to = y;
			edges[L].next = head[x];
			edges[L].cost = cost;
		}
		head[x] = L ++;
	}
	static int dijkstra(int s, HashSet<Integer> t) {
		for (int ni = 0; ni <= N; ni ++) dist[ni] = M;
		State u = new State(s, 0);
		dist[s] = 0;
		PriorityQueue<State> q = new PriorityQueue<State>(N, new Cmp());
		q.add(u);
		while (! q.isEmpty()) {
			u = q.poll();
			if (t.contains(u.id)) return dist[u.id];
			if (u.cost != dist[u.id]) continue;
			for (int i = head[u.id]; i != -1; i=edges[i].next) {
				if (dist[edges[i].to] > dist[u.id] + edges[i].cost) {
					dist[edges[i].to] = dist[u.id] + edges[i].cost;
					q.add(new State(edges[i].to, dist[edges[i].to]));
				}
			}
		}
		return M;
	}
	static int dijkstra(int s, int t) {
		Arrays.fill(dist, M);
		State u = new State(s, 0);
		dist[s] = 0;
		PriorityQueue<State> q = new PriorityQueue<State>(N, new Cmp());
		q.add(u);
		while (! q.isEmpty()) {
			u = q.poll();
			if (t==u.id) return dist[u.id];
			if (u.cost != dist[u.id]) continue;
			for (int i = head[u.id]; i != -1; i=edges[i].next) {
				if (dist[edges[i].to] > dist[u.id] + edges[i].cost) {
					dist[edges[i].to] = dist[u.id] + edges[i].cost;
					q.add(new State(edges[i].to, dist[edges[i].to]));
				}
			}
		}
		return M;
	}
	static void solve3() throws Exception {
		Scanner sc = new Scanner(new File("D:/code/data/百度17_赶火车.txt"));
		int times = sc.nextInt();
		HashSet<Integer> set = new HashSet<Integer>();
		for (int timeIndex = 1; timeIndex <= times; timeIndex ++) {
			int n = sc.nextInt(), m = sc.nextInt(), a = sc.nextInt(), b = sc.nextInt();
			init(n);
			set.clear();
			int[] as = new int[a];
			for (int ai = 0; ai < a; ai ++) as[ai] = sc.nextInt();
			for (int bi = 0; bi < b; bi ++) set.add(sc.nextInt());
			for (int mi = 0; mi < m; mi ++) {
				int x = sc.nextInt(), y = sc.nextInt(), z = sc.nextInt();
				addEdge(x, y, z);
				addEdge(y, x, z);
			}
			int ans = M;
			for (int ai : as)
				ans = Math.min(ans, dijkstra(ai, set));
//				for (int bi : set)
//					ans = Math.min(ans, dijkstra(ai, bi));
			System.out.println("Case #"+timeIndex+": "+(ans==M?"No answer":ans));
		}
		sc.close();
	}

	static void solve2() throws Exception {
		Scanner scanner = new Scanner(new File("D:/code/data/百度17_赶火车.txt"));

        int t = scanner.nextInt();

        for (int i = 1; i <= t; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            int[] start = new int[a];
            int[] end = new int[b];

            for (int j = 0; j < a; j++) {
                start[j] = scanner.nextInt();
            }
            for (int j = 0; j < b; j++) {
                end[j] = scanner.nextInt();
            }

            int[][] edges = new int[n][n];

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    edges[j][k] = Integer.MAX_VALUE;
                }
            }

            for (int j = 0; j < m; j++) {
                int s = scanner.nextInt() - 1;
                int e = scanner.nextInt() - 1;
                int length = scanner.nextInt();
                edges[s][e] = length;
                edges[e][s] = length;
            }

            int min = Integer.MAX_VALUE;
            for (int each : start) {
                int eachMin = solve2_dijkstra(n, each - 1, end, new int[n], new int[n], edges);
                min = Math.min(min, eachMin);
            }
            if (min == Integer.MAX_VALUE) {
                System.out.println(String.format("Case #%d: No answer", i));
            } else {
                System.out.println(String.format("Case #%d: %d", i, min));
            }
        }
        scanner.close();
	}
	private static int solve2_dijkstra(int vexCount, int start, int[] target, int[] prev, int[] dist, int[][] edges) {
        boolean[] visited = new boolean[vexCount];

        for (int i = 0; i < vexCount; i++) {
            visited[i] = false;
            prev[i] = 0;
            dist[i] = edges[start][i];
        }

        visited[start] = true;
        dist[start] = 0;

        int k = 0;
        for (int i = 1; i < vexCount; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < vexCount; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    k = j;
                }
            }
            visited[k] = true;

            for (int j = 0; j < vexCount; j++) {
                int newDis = (edges[k][j] == Integer.MAX_VALUE ? Integer.MAX_VALUE : (min + edges[k][j]));
                if (!visited[j] && (newDis < dist[j])) {
                    dist[j] = newDis;
                    prev[j] = k;
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int each : target) {
            res = Math.min(res, dist[each - 1]);
        }
        return res;
    }
	

	static void solve1() throws Exception {
		Scanner sc = new Scanner(new File("D:/code/data/百度17_赶火车_31.txt"));
		int times = sc.nextInt();
		for (int timeIndex = 1; timeIndex <= times; timeIndex ++) {
			int n = sc.nextInt(), m = sc.nextInt(), a = sc.nextInt(), b = sc.nextInt();
			ArrayList<V> h = new ArrayList<V>(n);
			HashMap<Integer, V> ma = new HashMap<Integer, V>(2 * m);
			int[] as = new int[a], bs = new int[b];
			for (int ai = 0; ai < a; ai ++)		as[ai] = sc.nextInt();
			for (int bi = 0; bi < b; bi ++)		bs[bi] = sc.nextInt();
			for (int mi = 0; mi < m; mi ++) {
				int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
				V s = ma.get(u), e = ma.get(v); 
				if (s == null) {s = new V(); ma.put(u, s);}
				if (e == null) {e = new V(); ma.put(v, e);}
				s.ns.add(e); s.ws.add(w);
				e.ns.add(s); e.ws.add(w);
			}
			int min = M;
			for (int ai : as) {
				V sv = ma.get(ai);
				for (V v : ma.values()) if (v != sv) {v.ih = h.size(); v.d = M; h.add(v);};
				sv.d = 0;
				while (true) {
					if (sv.d == M) break;
					Iterator<V> iV = sv.ns.iterator();
					Iterator<Integer> iW = sv.ws.iterator();
					while(iV.hasNext()) {
						V nv = iV.next();
						int nw = iW.next();
						if (nv.d > sv.d + nw) {
							nv.d = sv.d + nw;
							int i = nv.ih;
							int p = (i - 1) / 2;
							System.out.println("#####" + h.size());
							while (i != 0) {
								System.out.println(i+"..."+p);
								if (h.get(p).d > h.get(i).d) swap(h, i, p); else break;
								i = p; p = (i - 1)/ 2;
							}
						}
					}
					if (h.size() == 0) break;
					sv = h.get(0); h.set(0, h.get(h.size() - 1));
					h.get(0).ih = 0; h.remove(h.size() - 1);
					int i = 0, c = 1;
					while (c < h.size()) {
						if (c+1 < h.size() && h.get(c+1).d < h.get(c).d) c ++;
						if (h.get(i).d > h.get(c).d) swap(h, i, c);
						i = c; c = 2*i+1;
					}
				}
				for (int bi : bs) min = Math.min(min, ma.get(bi) == null ? M : ma.get(bi).d);
			}
			System.out.println("Case #"+timeIndex+": "+(min==M?"No answer":min));
		}
		sc.close();
	}
	static void swap(ArrayList<V> h, int i, int j) {
		int is = h.get(j).ih; h.get(j).ih = h.get(i).ih;
		h.get(i).ih = is;
		V vs = h.get(j); h.set(j, h.get(i)); h.set(i, vs);
	}
	static class V {
		int d = M, ih = -1;
		LinkedList<V> ns = new LinkedList<V>();
		LinkedList<Integer> ws = new LinkedList<Integer>();
	}
	
	
	final static int maxN = 1005;
	final static int[][] WS = new int[maxN][maxN];
	final static int[][] NEXT = new int[maxN][maxN];
	final static int[] AS = new int[maxN];
	final static HashSet<Integer> BS = new HashSet<Integer>();
	final static int[] HEAP = new int[maxN];
	final static int[] HEIN = new int[maxN];
	final static int[] DS = new int[maxN];
	static int HI = 0;
	static void solve4() throws Exception {
		Scanner sc = new Scanner(new File("D:/code/data/百度17_赶火车.txt"));
		int times = sc.nextInt();
		for (int timeIndex = 1; timeIndex <= times; timeIndex ++) {
			int n = sc.nextInt(), m = sc.nextInt(), a = sc.nextInt(), b = sc.nextInt();
			for (int i = 1; i <= n; i ++)
				for (int j = 1; j <= n; j ++)
					WS[i][j] = M;
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
					if (WS[i][j] != M) {
						NEXT[i][prej] = j;
						NEXT[i][j] = -1;
						prej = j;
					}
				}
			}
			
			int min = M;
			for (int ai = 0; ai < a; ai ++) {
				int s = AS[ai];
				for (int di = 1; di <= n; di ++) DS[di] = di == s ? 0 : M;
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
						swap(HEIN, HEAP[c], HEAP[i]);
						swap(HEAP, c, i);
						i = c;
						c = 2 * i;
					}
				}
			}
			System.out.println("Case #"+timeIndex+": "+(min==M?"No answer":min));
		}
		sc.close();
	}
	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
