package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UndirectedGraphNode辅助 {
	public static class UndirectedGraphNode {
		public int label;
		public List<UndirectedGraphNode> neighbors;
		public UndirectedGraphNode(int label) {
			this.label = label; 
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	}
	/*
	 * 	具体说明，查看P133_CloneGraph
	 */
	public static UndirectedGraphNode A_从序列化字符串中反序列化出无向图(String str) {
		HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();
		UndirectedGraphNode root = null;
		String[] str_cut_sharp = str.split("#");
		for (String str_comma : str_cut_sharp) {
			String[] str_cut_comma = str_comma.split(",");
			int label_now = Integer.parseInt(str_cut_comma[0]);
			List<UndirectedGraphNode> neighbors = null;
			if (! map.containsKey(label_now)) {
				UndirectedGraphNode node_now = new UndirectedGraphNode(label_now);
				neighbors = node_now.neighbors;
				map.put(label_now, node_now);
				if (root == null) {
					root = node_now;
				}
			} else {
				neighbors = map.get(label_now).neighbors;
			}
			for (int i = 1; i < str_cut_comma.length; i ++) {
				int label_neighbor = Integer.parseInt(str_cut_comma[i]);
				if (map.containsKey(label_neighbor)) {
					neighbors.add(map.get(label_neighbor));
				} else {
					UndirectedGraphNode node_neighbor = new UndirectedGraphNode(label_neighbor);
					map.put(label_neighbor, node_neighbor);
					neighbors.add(node_neighbor);
				}
			}
		}
		return root;
	}
}
