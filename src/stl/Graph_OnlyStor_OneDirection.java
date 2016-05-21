package stl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import com.sun.javafx.collections.SortableList;

/*
 * 	适用范围：
 * 		1,	输入： {[出发节点],[目的节点],[距离]}
 * 		2,	单向
 * 	实现功能：
 * 		1,	依据节点号，得到节点对象
 * 		2,	根据节点对象能够得到所有出度和入度
 * 	符号解释：
 * 		1,	sour: 路径出发点
 * 			dest: 路径目的点
 * 			dist: 路径长度
 * 		
 */

public class Graph_OnlyStor_OneDirection {
	public static void main(String[] args) {
		NodesManager nodesManager = new NodesManager();
		nodesManager.add(1, 2, 7);
		nodesManager.add(1, 3, 9);
		nodesManager.add(1, 6, 14);
		nodesManager.add(2, 4, 15);
		nodesManager.add(3, 4, 11);
		nodesManager.add(3, 6, 2);
		nodesManager.add(4, 5, 6);
		nodesManager.add(6, 5, 9);
		System.out.println(nodesManager.dijkstra(6, 6));
	}

	
	static class NodesManager {
		HashMap<Integer, Node> nodes = null;
		public NodesManager() {
			nodes = new HashMap<Integer, Node>();
		}
		public void add(int sour, int dest, int dist) {
			getById(sour).addOU(dest, dist);
			getById(dest).addIN(sour, dist);
		}
		public int dijkstra(int sourId, int destId) {
			if (!nodes.containsKey(sourId) || !nodes.containsKey(destId))
				return -1;
			Set<Integer> existNodes = new HashSet<Integer>(nodes.keySet());
			HashMap<Integer, Integer> distFromSourId = new HashMap<Integer, Integer>(nodes.size());
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.add(sourId);
			while (existNodes.size() > 1) {
				
			}
			return 0;
		}
		private Node getById(int id) {
			if (nodes.containsKey(id)) {
				return nodes.get(id);
			} else {
				Node node = new Node(id);
				nodes.put(id, node);
				return node;
			}
		}
	}

	static class Node {
		private int id;
		private HashMap<Integer, Integer> in = null;
		private HashMap<Integer, Integer> ou = null;
		public Node(int id) {
			this.id = id;
			in = new HashMap<Integer, Integer>();
			ou = new HashMap<Integer, Integer>();
		}
		public void addIN(int sour, int dist) {
			if (!in.containsKey(sour) || in.get(sour) > dist)
				in.put(sour, dist);
		}
		public void addOU(int dest, int dist) {
			if (!ou.containsKey(dest) || ou.get(dest) > dist)
				ou.put(dest, dist);
		}
		public int getId() {
			return id;
		}
	}
}