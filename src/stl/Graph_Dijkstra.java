package stl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     stl
 * @file        Graph_Dijkstra.java
 * @type        Graph_Dijkstra
 * @date        2016年11月30日 上午11:59:18
 * @details     根据一个网贴更改,他的代码由明显问题
 */
public class Graph_Dijkstra {
	public static void main(String[] args) {
		debugweightedGraph5();
	}
	
	static void debugweightedGraph5() {
//		String[] start = new String[] {"1", "2", "3", "4", "5", "1", "2", "2", "4"};
//		String[] end = new String[] {"5", "6", "5", "6", "6", "3", "3", "4", "5"};
//		int[] weight = new int[] {1, 4, 4, 1, 2, 2, 2, 2, 1};
		String[] start = new String[] {"1", "2", "3", "4", "5"};
		String[] end = new String[] {"5", "6", "5", "6", "6"};
		int[] weight = new int[] {1, 4, 4, 1, 2};
		weightedGraph5(start, end, weight, "2");
	}

	static void debugWeightedGraph4() {
		String[] start = new String[] {"1", "2", "3", "4", "5", "1", "2", "2", "4"};
		String[] end = new String[] {"5", "6", "5", "6", "6", "3", "3", "4", "5"};
		int[] weight = new int[] {1, 4, 4, 1, 2, 2, 2, 2, 1};
		WeightedGraph4 w = new WeightedGraph4(start, end, weight, "1");
		w.dijkstra();
		w.print();
	}

	static void debugWeightedGraph3() {
		String[] start = new String[] {"1", "2", "3", "4", "5"};
		String[] end = new String[] {"5", "6", "5", "6", "6"};
		int[] weight = new int[] {1, 4, 4, 1, 2};
		WeightedGraph3 w = new WeightedGraph3(start, end, weight, "2");
		w.dijkstra();
		w.print();
	}

	static void debugWeightedGraph2() {
		String[] start = new String[] {"1", "2", "3", "4", "5"};
		String[] end = new String[] {"5", "6", "5", "6", "6"};
		int[] weight = new int[] {1, 4, 4, 1, 2};
		WeightedGraph2 w = new WeightedGraph2(start, end, weight, "1");
		w.dijkstra();
		w.print();
	}

	static void debugWeightedGraph() {
		StringBuilder st = new StringBuilder();
		st.append(String.format("%d,%s,%s,%d\n", 1, "顶点1", "顶点5", 1));
		st.append(String.format("%d,%s,%s,%d\n", 1, "顶点2", "顶点6", 4));
		st.append(String.format("%d,%s,%s,%d\n", 1, "顶点3", "顶点5", 4));
		st.append(String.format("%d,%s,%s,%d\n", 1, "顶点4", "顶点6", 1));
		st.append(String.format("%d,%s,%s,%d\n", 1, "顶点5", "顶点6", 2)); 
		WeightedGraph g = new WeightedGraph(st.toString());
		g.dijkstra();
		g.showDistance();
	}

	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     stl
	 * @file        Graph_Dijkstra.java
	 * @type        WeightedGraph
	 * @date        2016年12月1日 下午8:22:59
	 * @details     一篇博客中的实现代码
	 */
	static class WeightedGraph {
		private Map<String, Vertex> weightedGraph = null;
		private Vertex startVertex = null;
		
		public WeightedGraph(String graphContent) {
			weightedGraph = new LinkedHashMap<String, Vertex>();
			buildGraph(graphContent);
		}
		
		public void dijkstra() {
			BinaryHeap<Vertex> heap = new BinaryHeap<Vertex>();
			init(heap);
			Vertex v = startVertex;
			while (true) {
				//获取v的所有邻接点
				List<Edge> adjEdges = v.adjEdges;
				for (Edge e : adjEdges) {
					Vertex adjNode = e.endVertex;
					if (adjNode.dist > e.weight + v.dist) {
						adjNode.dist = e.weight + v.dist;
						adjNode.preNode = v;
					}
				}
				if (heap.isEmpty())	break;
				heap.buildHeap();
				v = heap.poll();
			}
			startVertex.preNode = null;
		}
		
		public void showDistance() {
			for (Vertex v : weightedGraph.values()) {
				printPath(v);
				System.out.println();
				System.out.println("顶点 " + v.vertexLabel + " 到源点  " + startVertex.vertexLabel + " 的距离: " + v.dist);
			}
		}
		
		//打印源点到end顶点的最短路径
		private void printPath(Vertex end) {
			if (end.preNode != null)
				printPath(end.preNode);
			System.out.print(end.vertexLabel + "--->");
		}

		private void init(BinaryHeap<Vertex> heap) {
			//源点到其自身的距离为0
			startVertex.dist = 0;
			for (Vertex v : weightedGraph.values())
				if (startVertex != v)
					heap.insert(v);
			heap.buildHeap();
		}

		private void buildGraph(String graphContent) {
			String[] lines = graphContent.split("\n");
			String startNodeLabel, endNodeLabel;
			Vertex startNode, endNode;
			int weight;
			for (int i = 0; i < lines.length; i ++) {
				String[] nodesInfo = lines[i].split(",");
				startNodeLabel = nodesInfo[1];
				endNodeLabel = nodesInfo[2];
				weight = Integer.valueOf(nodesInfo[3]);
				endNode = weightedGraph.get(endNodeLabel);
				if (endNode == null) {
					endNode = new Vertex(endNodeLabel);
					weightedGraph.put(endNodeLabel, endNode);
				}
				startNode = weightedGraph.get(startNodeLabel);
				if (startNode == null) {
					startNode = new Vertex(startNodeLabel);
					weightedGraph.put(startNodeLabel, startNode);
				}
				//对于无向图而言，起点和终点都要添加边
				//对于有向图而言，只有起点需要添加边
				endNode.adjEdges.add(new Edge(weight, startNode));
				startNode.adjEdges.add(new Edge(weight, endNode));
			}
			//总是以文件中第一行第二列的那个标识顶点作为源点
			startVertex = weightedGraph.get(lines[0].split(",")[1]);
		}

		private class Edge {
		    private int weight;//边的权值(带权图)
		    private Vertex endVertex;
		    public Edge(int weight, Vertex endVertex) {
		        this.weight = weight;
		        this.endVertex = endVertex;
		    }
		}//Edge
		
		private class Vertex implements Comparable<Vertex> {
	        private String vertexLabel;//顶点标识
	        private List<Edge> adjEdges;//顶点的所有邻接边(点)
	        private int dist;//顶点到源点的最短距离
	        private Vertex preNode;//追溯最短路径
	        public Vertex(String vertexLabel){
	            this.vertexLabel = vertexLabel;
	            adjEdges = new LinkedList<Edge>();
	            dist = Integer.MAX_VALUE;
	            preNode = null;
	        }
	        @Override
	        public int compareTo(Vertex v) {
	            if(this.dist >  v.dist)
	                return 1;
	            else if(this.dist < v.dist)
	                return -1;
	            return 0;
	        }
	    }//Vertex
		
		private class BinaryHeap<T extends Comparable<? super T>> {
			private ArrayList<T> list;
			public BinaryHeap() {
				list = new ArrayList<T>();
			}
			public void insert(T t) {
				list.add(t);
			}
			public void buildHeap() {
				for (int i = list.size() / 2 - 1; i > -1; i --) {
					heapDown(i);
				}
			}
			private void heapDown(int i) {
				int c = 2 * i + 1;
				for (; 2 * i + 1 < list.size(); i = c) {
					c = 2 * i + 1;
					if (c < list.size() - 1 && list.get(c + 1).compareTo(list.get(c)) < 0)	c ++;
					if (list.get(c).compareTo(list.get(i)) < 0) {
						T tmp = list.get(i);
						list.set(i, list.get(c));
						list.set(c, tmp);
					} else {
						break;
					}
				}
			}
			public T poll() {
				if (list == null || list.size() == 0)	return null;
				T res = list.get(0);
				list.set(0, list.get(list.size() - 1));
				list.remove(list.size() - 1);
				heapDown(0);
				return res;
			}
			public boolean isEmpty() {
				return list == null || list.size() == 0;
			}
		}//BinaryHeap
		
	}//WeightedGraph
	
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     stl
	 * @file        Graph_Dijkstra.java
	 * @type        WeightedGraph2
	 * @date        2016年12月1日 下午8:23:48
	 * @details     照着WeightedGraph写的,更加符合自己的习惯
	 */
	static class WeightedGraph2 {
		Vertex startVertex = null;
		Map<String, Vertex> map = new LinkedHashMap<String, Vertex>();
		public WeightedGraph2(String[] start, String[] end, int[] weight, String startLabel) {
			for (int i = 0; i < start.length; i ++) {
				Vertex st = map.get(start[i]);
				if (st == null) {
					st = new Vertex(start[i]);
					map.put(start[i], st);
				}
				Vertex en = map.get(end[i]);
				if (en == null) {
					en = new Vertex(end[i]);
					map.put(end[i], en);
				}
				st.neighbors.add(new Edge(weight[i], en));
				en.neighbors.add(new Edge(weight[i], st));
			}
			startVertex = map.get(startLabel);
		}
		
		public void dijkstra() {
			BinaryHeap<Vertex> heap = new BinaryHeap<Vertex>();
			startVertex.dist = 0;
			for (Vertex v : map.values())
				if (v != startVertex)
					heap.insert(v);
			Vertex v = startVertex;
			while (true) {
				for (Edge e : v.neighbors) {
					if (e.endVertex.dist > v.dist + e.weight) {
						e.endVertex.dist = v.dist + e.weight;
						e.endVertex.pre = v;
					}
				}
				if (heap.isEmpty())	break;
				heap.buildHeap();
				v = heap.poll();
			}
			startVertex.pre = null;
		}
		
		public void print() {
			for (Vertex v : map.values())
				System.out.println("从 " + startVertex.label + " 到  " + v.label + " 的距离是:" + v.dist);
		}
		
		class BinaryHeap <T extends Comparable<? super T> >{
			ArrayList<T> heap = new ArrayList<T>();
			void insert(T t) {heap.add(t);}
			void buildHeap() {
				for (int i = heap.size() / 2 - 1; i > -1; i --)
					heapDown(i);
			}
			private void heapDown(int i) {
				int c = 2 * i + 1;
				while (c < heap.size()) {
					if (c + 1 < heap.size() && heap.get(c + 1).compareTo(heap.get(c)) < 0) c ++;
					if (heap.get(c).compareTo(heap.get(i)) < 0) {
						T t = heap.get(c);
						heap.set(c, heap.get(i));
						heap.set(i, t);
					} else break;
					i = c;
					c = 2 * i + 1;
				}
			}
			boolean isEmpty() {return heap == null || heap.size() == 0;}
			T poll() {
				T t = heap.get(0);
				heap.set(0, heap.get(heap.size() - 1));
				heap.remove(heap.size() - 1);
				heapDown(0);
				return t;
			}
		}
		class Vertex implements Comparable<Vertex> {
			String label = null;
			int dist = Integer.MAX_VALUE;
			Vertex pre = null;
			List<Edge> neighbors = new LinkedList<Edge>();
			public Vertex(String label) {this.label = label;}
			@Override
			public int compareTo(Vertex o) {
				return this.dist - o.dist;
			}
		}
		class Edge {
			int weight;
			Vertex endVertex;
			public Edge(int weight, Vertex endVertex) {
				this.weight = weight;
				this.endVertex = endVertex;
			}
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     stl
	 * @file        Graph_Dijkstra.java
	 * @type        WeightedGraph3
	 * @date        2016年12月1日 下午10:06:59
	 * @details     在Dijkstra中貌似有不适用Heap的方法
	 * @details     尽力减少新建对象的量,来避免时间和空间的浪费.
	 * @details     这里有一个问题:就是查找最小dist的点,复杂度有点高O(N)
	 */
	static class WeightedGraph3 {
		private Vertex startVertex = null;
		private Map<String, Vertex> map = new HashMap<String, Vertex>();
		public WeightedGraph3(String[] start, String[] end, int[] weight, String startLabel) {
			for (int i = 0; i < start.length; i ++) {
				Vertex st = map.get(start[i]);
				if (st == null) {
					st = new Vertex(start[i]);
					map.put(start[i], st);
				}
				Vertex en = map.get(end[i]);
				if (en == null) {
					en = new Vertex(end[i]);
					map.put(end[i], en);
				}
				st.neighbors.add(en);
				st.weights.add(weight[i]);
				en.neighbors.add(st);
				en.weights.add(weight[i]);
			}
			startVertex = map.get(startLabel);
		}
		public void print() {
			for (Vertex v : map.values())
				System.out.println("从 " + startVertex.label + " 到  " + v.label + " 的距离是:" + v.dist);
		}
		public void dijkstra() {
			startVertex.dist = 0;
			Vertex v = startVertex;
			for (int index = map.size(); index > 0; index --) {
				Iterator<Vertex> itV = v.neighbors.iterator();
				Iterator<Integer> itW = v.weights.iterator();
				while (itV.hasNext()) {
					Vertex itv = itV.next();
					int itw = itW.next();
					if (itv.dist > itw + v.dist) {
						itv.dist = itw + v.dist;
						itv.pre = v;
					}
				}
				v.isPolled = true;
				Vertex minV = null;
				for (Vertex ve : map.values()) {
					if (! ve.isPolled) {
						if (minV == null)
							minV = ve;
						else
							minV = minV.dist > ve.dist ? ve : minV;
					}
				}
				if (minV == null)	break;
				v = minV;
			}
		}
		static class Vertex {
			String label = null;
			int dist = Integer.MAX_VALUE;
			Vertex pre = null;
			boolean isPolled = false;
			LinkedList<Vertex> neighbors = new LinkedList<Vertex>();
			LinkedList<Integer> weights = new LinkedList<Integer>();
			public Vertex(String label) {this.label = label;}
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     stl
	 * @file        Graph_Dijkstra.java
	 * @type        WeightedGraph4
	 * @date        2016年12月2日 上午9:12:16
	 * @details     实现使用heapUp来完成堆的更新.
	 */
	static class WeightedGraph4 {
		private HashMap<String, Vertex> map = new HashMap<String, Vertex>();
		private Vertex startVertex = null;
		public WeightedGraph4(String[] start, String[] end, int[] weight, String startLabel) {
			for (int i = 0; i < start.length; i ++) {
				Vertex st = map.get(start[i]);
				if (st == null) {
					st = new Vertex(start[i]);
					map.put(start[i], st);
				}
				Vertex en = map.get(end[i]);
				if (en == null) {
					en = new Vertex(end[i]);
					map.put(end[i], en);
				}
				st.neighbors.add(en);
				st.weights.add(weight[i]);
				en.neighbors.add(st);
				en.weights.add(weight[i]);
			}
			startVertex = map.get(startLabel);
		}
		void print() {
			for (Vertex v : map.values())
				System.out.println("从 " + startVertex.label + " 到  " + v.label + " 的距离是:" + v.dist);
		}
		void dijkstra() {
			startVertex.dist = 0;
			BinaryHeap heap = new BinaryHeap();
			for (Vertex v : map.values())  if (startVertex != v) heap.insert(v);
			Vertex v = startVertex;
			while (true) {
				Iterator<Vertex> itV = v.neighbors.iterator();
				Iterator<Integer> itW = v.weights.iterator();
				while (itV.hasNext()) {
					Vertex nv = itV.next();
					int nw = itW.next();
					if (nv.dist > v.dist + nw) {
						nv.dist = v.dist + nw;
						nv.pre = v;
						heap.heapUp(nv.indexInHeap);
					}
				}
				if (heap.isEmpty())	break;
//				heap.buildHeap();
				v = heap.poll();
			}
		}
		static class BinaryHeap {
			ArrayList<Vertex> list = new ArrayList<Vertex>();
			void insert(Vertex v) {v.indexInHeap = list.size(); list.add(v);}
			void buildHeap() {for (int i = list.size() / 2 - 1; i > -1; i --)  heapDown(i);}
			private void heapDown(int i) {
				int c = 2 * i + 1;
				while (c < list.size()) {
					if (c + 1 < list.size() && list.get(c + 1).compareTo(list.get(c)) < 0) c ++;
					if (list.get(i).compareTo(list.get(c)) > 0) {
						Vertex t = list.get(c);
						list.set(c, list.get(i));
						list.set(i, t);
					} else break;
					i = c;
					c = 2 * i + 1;
				}
			}
			public Vertex poll() {
				Vertex t = list.get(0);
				list.set(0, list.get(list.size() - 1));
				list.get(0).indexInHeap = 0;
				list.remove(list.size() - 1);
				return t;
			}
			boolean isEmpty() {return list == null || list.size() == 0;}
			void heapUp(int i) {
				int p = (i - 1) / 2;
				while (i != 0) {
					if (list.get(p).compareTo(list.get(i)) > 0) {
						int tmp = list.get(p).indexInHeap;
						list.get(p).indexInHeap = list.get(i).indexInHeap;
						list.get(i).indexInHeap = tmp;
						Vertex t = list.get(p);
						list.set(p, list.get(i));
						list.set(i, t);
					} else break;
					i = p;
					p = (i - 1)/ 2;
				}
			}
		}
		static class Vertex implements Comparable<Vertex>{
			String label = null;
			LinkedList<Vertex> neighbors = new LinkedList<Vertex>();
			LinkedList<Integer> weights = new LinkedList<Integer>();
			int dist = Integer.MAX_VALUE;
			int indexInHeap = -1;
			Vertex pre = null;
			public Vertex(String label) {this.label = label;}
			public int compareTo(Vertex o) {return this.dist - o.dist;}
		}
	}
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     stl
	 * @file        Graph_Dijkstra.java
	 * @type        WeightedGraph5
	 * @date        2016年12月2日 上午10:56:40
	 * @details     实现一个方法,做完全部工作
	 * @details     weightedGraph5是一个静态方法
	 * @details     Vertex是一个是一个类
	 */
	static void weightedGraph5(String[] s, String[] e, int[] w, String sl) {
		HashMap<String, Vertex> m = new HashMap<String, Vertex>();
		ArrayList<Vertex> h = new ArrayList<Vertex>();
		for (int i = 0; i < s.length; i ++) {
			Vertex st = m.get(s[i]);
			if (st == null) { st = new Vertex(s[i]); m.put(s[i], st); }
			Vertex en = m.get(e[i]);
			if (en == null) { en = new Vertex(e[i]); m.put(e[i], en); }
			st.ns.add(en); st.ws.add(w[i]);
			en.ns.add(st); en.ws.add(w[i]);
		}
		Vertex sv = m.get(sl); sv.dist = 0;
		for (Vertex v : m.values()) if (v != sv) { v.ih=h.size(); h.add(v);}
		Vertex v = sv;
		while(true) {
			Iterator<Vertex> itV = v.ns.iterator();
			Iterator<Integer> itW = v.ws.iterator();
			while (itV.hasNext()) {
				Vertex nv = itV.next(); int nw = itW.next();
				if (nv.dist > v.dist + nw) {
					nv.dist = v.dist + nw;
					int i = nv.ih;
					int p = (i - 1) / 2;
					while (i !=0 ) {
						if (h.get(p).dist - h.get(i).dist > 0) {
							int tmp = h.get(p).ih;
							h.get(p).ih = h.get(i).ih;
							h.get(i).ih = tmp;
							Vertex t = h.get(p); h.set(p, h.get(i)); h.set(i, t);
						} else break;
						i = p; p = (i - 1)/ 2;
					}
				}
			}
			if (h.size() == 0) break;
			v = h.get(0); h.set(0, h.get(h.size() - 1));
			h.get(0).ih = 0; h.remove(h.size() - 1);
			int i = 0, c = 1;
			while (true) {
				c = 2*i+1;
				if (c >= h.size()) break;
				if (c+1 < h.size() && h.get(c+1).dist < h.get(c).dist) c ++;
				if (h.get(i).dist > h.get(c).dist) {
					int tmp = h.get(c).ih;
					h.get(c).ih = h.get(i).ih;
					h.get(i).ih = tmp;
					Vertex t = h.get(c); h.set(c, h.get(i)); h.set(i, t);
				} else break;
				i = c;
			}
			
		}
		for (Vertex tmp : m.values()) 
			{System.out.println("从 " + sl + " 到 " + tmp.label +" 的距离是: " + tmp.dist);}
	}
	static class Vertex{
		String label = null;
		int dist = Integer.MAX_VALUE, ih = -1;
		LinkedList<Vertex> ns = new LinkedList<Vertex>();
		LinkedList<Integer> ws = new LinkedList<Integer>();
		public Vertex(String label) { this.label = label; }
	}
}
