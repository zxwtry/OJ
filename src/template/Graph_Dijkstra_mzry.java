package template;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        Graph_Dijkstra.java
 * @type        Graph_Dijkstra
 * @date        2016年12月2日 下午4:53:35
 * @details     根据李昀代码修改而来
 */
public class Graph_Dijkstra_mzry {
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
	static final int MAXN = 100, MAXM = 1000;
	static int N, L;
	static int[] head = new int[MAXN];
	static int[] dist = new int[MAXN];
	static Edge[] edges = new Edge[MAXM];
	static void init(int n) {
		N = n;
		L = 0;
		for (int i = 0; i < n; i ++) head[i] = -1;
	}
	static void addEdge(int x, int y, int cost) {
		if (edges[L] == null) edges[L] = new Edge(y, head[x], cost);
		head[x] = L ++;
	}
	static int dijkstra(int s, int t) {
		Arrays.fill(dist, Integer.MAX_VALUE);
		State u = new State(s, 0);
		dist[s] = 0;
		PriorityQueue<State> q = new PriorityQueue<State>(N, new Cmp());
		q.add(u);
		while (! q.isEmpty()) {
			u = q.poll();
			if (u.id == t) return dist[t];
			if (u.cost != dist[u.id]) continue;
			for (int i = head[u.id]; i != -1; i=edges[i].next) {
				if (dist[edges[i].to] > dist[u.id] + edges[i].cost) {
					dist[edges[i].to] = dist[u.id] + edges[i].cost;
					q.add(new State(edges[i].to, dist[edges[i].to]));
				}
			}
		}
		return -1;
	}
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new File("D:/code/data/Graph_Dijkstra.txt"));
		int n = sc.nextInt(), m = sc.nextInt();
		init(n);
		for (int i = 0; i < m; i ++) {
			int x = sc.nextInt(), y = sc.nextInt(), z = sc.nextInt();
			addEdge(x, y, z);
			addEdge(y, x, z);
		}
		int s = sc.nextInt(), t = sc.nextInt();
		System.out.println(dijkstra(s, t));
		sc.close();
	}
}
