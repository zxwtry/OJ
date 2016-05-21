package stl;

import java.util.HashMap;

/*
 * 	适用范围：
 * 		1,	输入： {[节点],[节点],[距离]}
 * 		2,	双向
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
	}

	
	static class NodeManager {
		HashMap<Integer, Node> nodes = null;
		public NodeManager() {
			nodes = new HashMap<Integer, Node>();
		}
		public void add(int sour, int dest, int dist) {
			getById(sour).addOU(dest, dist).addIN(dest, dist);
			getById(dest).addIN(sour, dist).addOU(sour, dist);
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
		public Node addIN(int sour, int dist) {
			if (!in.containsKey(sour) || in.get(sour) > dist)
				in.put(sour, dist);
			return this;
		}
		public Node addOU(int dest, int dist) {
			if (!ou.containsKey(dest) || ou.get(dest) > dist)
				ou.put(dest, dist);
			return this;
		}
	}
}