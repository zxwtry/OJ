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

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P133_CloneGraph.java
 * @type        P133_CloneGraph
 * @date        2017年5月12日 下午12:10:45
 * @details     Solution1: AC 4ms 97.59%
 * @details     Solution2: AC 9ms 39.60%
 */
public class P133_CloneGraph {
	static class Solution1 {
	    HashMap<Integer, UndirectedGraphNode> m = new HashMap<>();
	    public UndirectedGraphNode cloneGraph(UndirectedGraphNode n) {
	        if (n == null) return null;
	        UndirectedGraphNode v = m.get(n.label);
	        if (v != null) return v;
	        v = new UndirectedGraphNode(n.label);
	        m.put(n.label, v);
	        for (UndirectedGraphNode l : n.neighbors)
	            v.neighbors.add(cloneGraph(l));
	        return v;
	    }
	}
}