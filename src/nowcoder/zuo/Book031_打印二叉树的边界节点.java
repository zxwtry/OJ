package nowcoder.zuo;

import tools.TreeNode辅助.TreeNode;

/*
 * 	节点数N，时间O(N)，空间O(h)，h是二叉树的高度
 * 	逆时针打印，不重复
 */

public class Book031_打印二叉树的边界节点 {
	public static void main(String[] args) {
		
	}
	/*
	 * 	标准一:
	 * 		1,	头节点为边界节点
	 * 		2,	叶子节点为边界节点
	 * 		3,	如果节点在其所在的层中是最左或者最右，那么也是边界节点
	 */
	static class EdgeSolution1 {
		public void printEdge(TreeNode head) {
			if (head == null) {
				return;
			}
			int height = getHeight(head, 0);
			TreeNode[][] edgeMap = new TreeNode[height][2];
			setEdgeMap(head, 0, edgeMap);
			// 打印左边界
			for (int i = 0; i != height; i ++) {
				System.out.print(edgeMap[i][0].val + " ");
			}
			// 打印既不是左边界，也不是右边界的叶子节点
			printLeafNotInMap(head, 0, edgeMap);
			for (int i = height - 1; i != -1; i --) {
				if (edgeMap[i][0] != edgeMap[i][1]) {
					System.out.println(edgeMap[i][1].val + " ");
				}
			}
		}
		private void setEdgeMap(TreeNode head, int i, TreeNode[][] edgeMap) {
			if (head == null) {
				return;
			}
			edgeMap[i][0] = edgeMap[i][0] == null ? head : edgeMap[i][0];
			edgeMap[i][1] = head;
			setEdgeMap(head.left, i + 1, edgeMap);
			setEdgeMap(head.right, i + 1, edgeMap);
		}
		private void printLeafNotInMap(TreeNode head, int i, TreeNode[][] edgeMap) {
			if (head == null) {
				return;
			}
			if (head.left == null && head.right == null && head != edgeMap[i][0] && head != edgeMap[i][1]) {
				System.out.print(head.val + " ");
			}
			printLeafNotInMap(head.left, i + 1, edgeMap);
			printLeafNotInMap(head.right, i + 1, edgeMap);
		}
		private int getHeight(TreeNode head, int i) {
			if (head == null) {
				return 1;
			}
			return Math.max(getHeight(head.left, i + 1), getHeight(head.right, i + 1));
		}
	}
}
