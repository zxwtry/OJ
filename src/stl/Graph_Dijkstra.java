package stl;

import java.util.ArrayList;
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
 * @details     
 */
public class Graph_Dijkstra {
	public static void main(String[] args) {
	}
	
	static void testWeightedGraph() {
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
//			while (! heap.isEmpty()) {
//				Vertex v = heap.poll();
//				//获取v的所有邻接点
//				List<Edge> adjEdges = v.adjEdges;
//				for (Edge e : adjEdges) {
//					Vertex adjNode = e.endVertex;
//					if (adjNode.dist > e.weight + v.dist) {
//						adjNode.dist = e.weight + v.dist;
//						adjNode.preNode = v;
//					}
//				}
////				if (v == startVertex)
////					heap.buildHeap();
//			}
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
	
}
