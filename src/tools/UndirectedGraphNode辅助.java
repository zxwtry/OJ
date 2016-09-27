package tools;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraphNode辅助 {
	public static class UndirectedGraphNode {
		public int label;
		public List<UndirectedGraphNode> neighbors;
		public UndirectedGraphNode(int label) {
			this.label = label; 
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	}
}
