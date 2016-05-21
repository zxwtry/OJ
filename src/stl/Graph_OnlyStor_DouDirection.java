package stl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * 	适用范围：
 * 		1,	输入： {[节点],[节点],[距离]}
 * 		2,	双向
 * 	实现功能：
 * 		1,	依据节点号，得到节点对象
 * 		2,	根据节点对象能够得到所有出度和入度
 * 	符号解释：
 * 		1,	end0: 路径的某一端
 * 			end1: 路径的另一端
 * 			dist: 路径长度
 * 			neig: 节点的邻居
 * 		2,  
 * 		
 */

public class Graph_OnlyStor_DouDirection {
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
		System.out.println(nodesManager.dijkstra(1, 5));
	}
	
	
	
	static class NodesManager {
		HashMap<Integer, Node> nodes = null;
		public NodesManager() {
			nodes = new HashMap<Integer, Node>();
		}
		public void add(int end0, int end1, int dist) {
			getNodeById(end0).addNeig(end1, dist);
			getNodeById(end1).addNeig(end0, dist);
		}
		public int[] getAllNeigsAndDist(int nodeId) {
			return getNodeById(nodeId).getAllNeigsAndDist();
		}
		public int dijkstra(int nodeId0, int nodeId1) {
			if (!nodes.containsKey(nodeId0) || !nodes.containsKey(nodeId1))
				return -1;
			Set<Integer> exitNodes = new HashSet<Integer>(nodes.keySet());
			HashMap<Integer, Integer> distFromNode0 = new HashMap<Integer, Integer>(exitNodes.size());
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.add(nodeId0);
			distFromNode0.put(nodeId0, 0);
			while (exitNodes.size() > 1) {
				int nodeIdSelect = queue.poll();
				int distBase = distFromNode0.get(nodeIdSelect);
				int[] allNeigsAndDist = getAllNeigsAndDist(nodeIdSelect);
				for (int index = allNeigsAndDist.length; index > 0;) {
					int dist = allNeigsAndDist[--index];
					int neigId = allNeigsAndDist[--index];
					dijkstraAddMap(neigId, distBase+dist, distFromNode0);
					if (distFromNode0.containsKey(neigId)) {
						int newDistBase = dist + distFromNode0.get(neigId);
						if (newDistBase < distBase) {
							distFromNode0.put(nodeIdSelect, newDistBase);
							index = allNeigsAndDist.length;
						}
					}
				}
				for (int index = allNeigsAndDist.length-2; index >= 0; index -= 2)
					if (exitNodes.contains(allNeigsAndDist[index]))
						queue.add(allNeigsAndDist[index]);
				exitNodes.remove(nodeIdSelect);
			}
			return distFromNode0.get(nodeId1);
		}
		
		private void dijkstraAddMap(int neigId, int newDist, HashMap<Integer, Integer> distFromNode0) {
			if (!distFromNode0.containsKey(neigId) || newDist < distFromNode0.get(neigId))
				distFromNode0.put(neigId, newDist);
		}
		private Node getNodeById(int id) {
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
		private HashMap<Integer, Integer> neigs = null;
		public Node(int id) {
			this.id = id;
			neigs = new HashMap<Integer, Integer>();
		}
		public void addNeig(int neig, int dist) {
			if (!neigs.containsKey(neig) || neigs.get(neig) > dist)
				neigs.put(neig, dist);
		}
		public int[] getAllNeigsAndDist() {
			Set<Integer> keySet = neigs.keySet();
			int[] allNeigsAndDist = new int[keySet.size() << 1];
			int index = 0;
			for (int nodeId : keySet) {
				allNeigsAndDist[index ++] = nodeId;
				allNeigsAndDist[index ++] = neigs.get(nodeId);
			}
			return allNeigsAndDist;
		}
		public int getId() {
			return id;
		}
	}
	
	
}

