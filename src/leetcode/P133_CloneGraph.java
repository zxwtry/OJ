package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 	Clone an undirected graph. Each node in the graph contains a label
 * 	 and a list of its neighbors.


	OJ's undirected graph serialization:
	Nodes are labeled uniquely.
	
	We use # as a separator for each node, and , as a separator for node label
	 and each neighbor of the node.
	As an example, consider the serialized graph {0,1,2#1,2#2,2}.
	
	The graph has a total of three nodes, and therefore contains three parts 
	as separated by #.
	
	First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
	Second node is labeled as 1. Connect node 1 to node 2.
	Third node is labeled as 2. Connect node 2 to node 2 (itself),
	 thus forming a self-cycle.
	Visually, the graph looks like the following:
	
	       1
	      / \
	     /   \
	    0 --- 2
	         / \
	         \_/
 */

import tools.UndirectedGraphNode辅助.UndirectedGraphNode;

public class P133_CloneGraph {
	public static void main(String[] args) {
		UndirectedGraphNode root_origin = tools.UndirectedGraphNode辅助.A_从序列化字符串中反序列化出无向图("0,1#1,2#2,3#3");
		tools.UndirectedGraphNode辅助.B_以邻接表的方式打印无向图(root_origin);
		System.out.println("================================");
		Solution2 s = new Solution2();
		UndirectedGraphNode root_ghost = s.cloneGraph(root_origin);
		tools.UndirectedGraphNode辅助.B_以邻接表的方式打印无向图(root_ghost);
	}
	/*
	 * 	16 ms
	 * 	3.99%
	 */
	static class Solution {
		HashMap<Integer, UndirectedGraphNode> map_origin = new HashMap<>();
		HashMap<Integer, UndirectedGraphNode> map_ghost = new HashMap<>();
	    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
	    	if (node == null) {
	    		return null;
	    	}
	    	Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
	    	q.add(node);
	    	while (! q.isEmpty()) {
	    		UndirectedGraphNode node_now = q.poll();
	    		if (map_origin.containsKey(node_now.label)) {
	    			continue;
	    		} else {
	    			map_origin.put(node_now.label, node_now);
	    		}
	    		for (UndirectedGraphNode node_neighbor : node_now.neighbors) {
	    			q.add(node_neighbor);
	    		}
	    	}
	    	for (int key_origin : map_origin.keySet()) {
	    		map_ghost.put(key_origin, new UndirectedGraphNode(key_origin));
	    	}
	    	for (int key_origin : map_origin.keySet()) {
	    		UndirectedGraphNode node_ghost = map_ghost.get(key_origin);
	    		List<UndirectedGraphNode> list_now = map_origin.get(key_origin).neighbors;
	    		for (UndirectedGraphNode node_now : list_now) {
	    			node_ghost.neighbors.add(map_ghost.get(node_now.label));
	    		}
	    	}
	        return map_ghost.get(node.label);
	    }
	}
	/*
	 * 	15 ms
	 * 	5.90%
	 * 	很奇怪，更加好的复制方法竟然想不出来。。。
	 */
	static class Solution2 {
		HashMap<Integer, UndirectedGraphNode> map_ghost = new HashMap<>();
		HashSet<UndirectedGraphNode> set_origin = new HashSet<>();
	    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
	    	if (node == null) {
	    		return null;
	    	}
	    	Queue<UndirectedGraphNode> q = new LinkedList<>();
	    	q.add(node);
	    	while (! q.isEmpty()) {
	    		UndirectedGraphNode root_now_ghost = null;
	    		UndirectedGraphNode root_now_origin = q.poll();
	    		if (set_origin.contains(root_now_origin)) {
	    			continue;
	    		} else {
	    			set_origin.add(root_now_origin);
	    		}
	    		if (map_ghost.containsKey(root_now_origin.label)) {
	    			root_now_ghost = map_ghost.get(root_now_origin.label);
	    		} else {
	    			root_now_ghost = new UndirectedGraphNode(root_now_origin.label);
	    			map_ghost.put(root_now_ghost.label, root_now_ghost);
	    		}
	    		List<UndirectedGraphNode> list = root_now_ghost.neighbors;
	    		for (UndirectedGraphNode neighbor : root_now_origin.neighbors) {
	    			UndirectedGraphNode neighbor_ghost = null;
	    			if (map_ghost.containsKey(neighbor.label)) {
	    				neighbor_ghost = map_ghost.get(neighbor.label);
	    				list.add(neighbor_ghost);
	    			} else {
	    				neighbor_ghost = new UndirectedGraphNode(neighbor.label);
	    				map_ghost.put(neighbor_ghost.label, neighbor_ghost);
	    				list.add(neighbor_ghost);
	    			}
	    			q.add(neighbor);
	    		}
	    	}
	    	return map_ghost.get(node.label);
	    }
	}
}