package nowcoder.com;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/*
 * 题目描述
										
	小A所在的城市中有n个路口，有m条道路(双向)连接着不同的路口。小A想知道从路口s到路口t的“特征距离”。
	问题是小A计算距离的方法较为特殊：当她选择一条从s到t的路径(包含若干条道路)后，
	定义该路径的特征距离为所有经过的道路中长度最长的那条道路的长度。现在给定相关信息，
	现在小A只关心从s到t的最短路，你能求出所有的最短路中特征距离最长的那一条吗？
	
									
	输入
	输入中有多组数据，第一行是一个正整数T(T<=10)，表示测试数据的组数。接下来包含T组测试数据。
	每组测试数据的第一行包含四个整数n(1＜=n＜=1000)，m(0＜=m＜=n*(n-1)/2)，s 和 t(1＜=s＜=n, 1＜=t＜=n)，
	分别表示图中节点数、边数、 起点和终点。
	接下来的m行中，每行包含三个整数u, v, w, (1＜=u＜=n,1＜=v＜= n, u不等于v, 0＜=w＜=1)
	表示结点u和结点v之间存在一条长度为w(1＜=w＜=10000的道路(保证没有重复)。
	
	样例输入
	2
	4 5 1 4
	1 2 1
	1 3 3
	2 3 2
	2 4 3
	3 4 1
	5 4 1 5
	1 2 1
	2 3 2
	1 3 4
	4 5 3
	输出
	对于每一组测试数据，输出一行"Case #x: s"，其中 x表示第x组数据，x从1开始递增。
	s表示题目描述中定义的最短路径的特征距离，如果不存在最短路则输出"No answer"，详见给定样例。
	样例输出
	Case #1: 3
	Case #2: No answer
	
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度17_特征距离.java
 * @type        百度17_特征距离
 * @date        2016年11月21日 下午4:09:32
 * @details     
 */

public class 百度17_特征距离 {
	public static void main(String[] args) {
		try {
//			solve1();
//			solve2();
			solve3();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @method      solve3
	 * @parameter   
	 * @return      void
	 * @details     对于solve1的改进，不用HashMap之后AC
	 */
	private static void solve3() throws Exception {
		Scanner sc = new Scanner(new File("D:/file/data/百度17_特征距离.txt"));
		int TIME = sc.nextInt();
		for (int times = 1; times <= TIME; times ++) {
			int n = sc.nextInt(), m = sc.nextInt(), s = sc.nextInt(), t = sc.nextInt();
			int[][] map = new int[n + 1][n + 1];
			for (int mi = 0; mi < m; mi ++) {
				int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
				map[u][v] = w;
				map[v][u] = w;
			}
			int[] val = new int[] {Integer.MAX_VALUE, 0};
			boolean[] isTravled = new boolean[n+1];
			solve3search(n, s, t, 0, 0, val, map, isTravled);
			System.out.print("Case #"+times+": ");
			if (val[0] == Integer.MAX_VALUE && val[1] == 0) {
				System.out.println("No answer");
			} else {
				System.out.println(val[1]);
			}
		}
		sc.close();
	}

	/**
	 * @method      solve3search
	 * @parameter   
	 * @return      void
	 * @details     
	 */
	private static void solve3search(int n, int now, int target, int count, int max, int[] val, int[][] map, boolean[] isTravled) {
		if (count > val[0])	return;
		for (int next = 1; next <= n; next ++) {
			if (map[now][next] != 0 && ! isTravled[next]) {
				int w = map[now][next];
				int thismax = Math.max(max, w);
				if (next == target) {
					if (count + w < val[0]) {
						val[0] = count + w;
						val[1] = thismax;
					} else if (count + w == val[0] && thismax > val[1]) {
						val[1] = thismax;
					}
				} else {
					isTravled[next] = true;
					solve3search(n, next, target, count + w, thismax, val, map, isTravled);
					isTravled[next] = false;
				}
			}
		}
	}

	/**
	 * @method      solve2
	 * @parameter   
	 * @return      void
	 * @details     
	 */
	static void solve2() throws Exception {
		Scanner scan = new Scanner(new File("D:/file/data/百度17_特征距离.txt"));
		int T, n, m, s, t, u, v, w, result;
		int[][] graph;
		while(scan.hasNext()){
			T = scan.nextInt();
			for(int i = 0; i < T; i++){
				n = scan.nextInt();
				m = scan.nextInt();
				s = scan.nextInt();
				t = scan.nextInt();
				graph = new int[n][n];
				solve2initGaph(graph);
				for(int j = 0; j < m; j++){
					u = scan.nextInt();
					v = scan.nextInt();
					w = scan.nextInt();
					graph[u-1][v-1] = w;
					graph[v-1][u-1] = w;
				}
				result = solve2getSpecialDis(graph, s-1, t-1);
				if(result == -1){
					System.out.println("Case #" + (i+1) + ": No answer");
				}else{
					System.out.println("Case #" + (i+1) + ": " + result);
				}
			}
		}
		scan.close();
	}
	
	static int solve2getSpecialDis(int[][] graph, int start, int end){
		//存储已经找到最短路径的点
		boolean[] min_vertex = new boolean[graph.length];
		min_vertex[start] = true;
		//初始化存储距离的向量
		int[] temp_arr = new int[graph.length];
		//存储路径
		int[] posi = new int[graph.length];
		for(int i = 0; i < temp_arr.length; i++){
			temp_arr[i] = graph[start][i];
			if(temp_arr[i] != -1){
				posi[i] = start;
			}else{
				posi[i] = -1;
			}
		}
		
		boolean isFound = false;
		while(!isFound){
			//找最近点
			int temp_index = -1, temp_num = -1;
			for(int i = 0; i < temp_arr.length; i++){
				if(min_vertex[i]) continue;
				if(temp_arr[i] != -1){
					if(temp_num == -1){
						temp_num = temp_arr[i];
						temp_index = i;
					}else if(temp_arr[i] < temp_num){
						temp_num = temp_arr[i];
						temp_index = i;
					}
				}
			}
			if(temp_index == end){
				isFound = true;
				continue;
			}else if(temp_index == -1){
				return -1;
			}
			min_vertex[temp_index] = true;
			
			
			
			//更新一次存储距离的数组
			for(int i = 0; i < temp_arr.length; i++){
				if(min_vertex[i]) continue;
				if(graph[temp_index][i] != -1){
					if(temp_arr[i] == -1 || temp_arr[temp_index] + graph[temp_index][i] < temp_arr[i]){
						temp_arr[i] = temp_arr[temp_index] + graph[temp_index][i];
						posi[i] = temp_index;
					}
				}
			}
			
		}
		
		int max_dis = 0, pre = posi[end], cur = end;
		while(pre != start){
			if(graph[pre][cur] > max_dis){
				max_dis = graph[pre][cur];
			}
			cur = pre;
			pre = posi[cur];
		}
		if(graph[pre][cur] > max_dis) max_dis = graph[pre][cur];
		return max_dis;
	}
	
	static void solve2initGaph(int[][] graph){
		for(int i = 0; i < graph.length; i++){
			for(int j = 0; j < graph.length; j++){
				graph[i][j] = -1;
			}
		}
	}

	/**
	 * @method      solve1
	 * @parameter   
	 * @return      void
	 * @details     TLE了
	 */
	static void solve1() throws Exception {
		Scanner sc = new Scanner(new File("D:/file/data/百度17_特征距离.txt"));
		int TIME = sc.nextInt();
		for (int times = 1; times <= TIME; times ++) {
			int n = sc.nextInt(), m = sc.nextInt(), s = sc.nextInt(), t = sc.nextInt();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for (int mi = 0; mi < m; mi ++) {
				int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
				map.put(Math.min(u, v)+"#"+Math.max(u, v), w);
			}
			int[] val = new int[] {Integer.MAX_VALUE, 0};
			boolean[] isTravled = new boolean[n+1];
			solve1search(n, s, t, 0, 0, val, map, isTravled);
			System.out.print("Case #"+times+": ");
			if (val[0] == Integer.MAX_VALUE && val[1] == 0) {
				System.out.println("No answer");
			} else {
				System.out.println(val[1]);
			}
		}
		sc.close();
	}

	/**
	 * @method      solve1search
	 * @parameter   
	 * @return      void
	 * @details     
	 */
	private static void solve1search(int n, int now, int taget, int count, int max, int[] val, HashMap<String, Integer> map, boolean[] isTravled) {
		if (count > val[0])	return;
		for (int next = 1; next <= n; next ++) {
			String key = Math.min(now, next)+"#"+Math.max(now, next);
			if (map.containsKey(key) && ! isTravled[next]) {
				int w = map.get(key);
				int thismax = Math.max(max, w);
				if (next == taget) {
					if (count + w < val[0]) {
						val[0] = count + w;
						val[1] = thismax;
					} else if (count + w == val[0] && thismax > val[1]) {
						val[1] = thismax;
					}
				} else {
					isTravled[next] = true;
					solve1search(n, next, taget, count + w, thismax, val, map, isTravled);
					isTravled[next] = false;
				}
			}
		}
	}
}
