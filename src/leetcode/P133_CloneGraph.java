package leetcode;

import java.util.HashMap;
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
//		UndirectedGraphNode root_origin = tools.UndirectedGraphNode辅助.generate_from_serialized_string("0,1,2,3#1,2,3#2,2#3");
//		tools.UndirectedGraphNode辅助.print_node(root_origin);
//		System.out.println("================================");
//		Solution s = new Solution();
//		UndirectedGraphNode root_ghost = s.cloneGraph(root_origin);
//		tools.UndirectedGraphNode辅助.print_node(root_ghost);
	}
	/*
	 * 	16 ms
	 * 	3.99%
	 */
	static class Solution {
		HashMap<Integer, UndirectedGraphNode> map_origin = new HashMap<>();
		HashMap<Integer, UndirectedGraphNode> map_ghost = new HashMap<>();
	    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
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
}
