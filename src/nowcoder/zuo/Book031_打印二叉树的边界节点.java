package nowcoder.zuo;

import tools.TreeNode辅助.TreeNode;

/*
 * 	节点数N，时间O(N)，空间O(h)，h是二叉树的高度
 * 	逆时针打印，不重复
 */

public class Book031_打印二叉树的边界节点 {
	public static void main(String[] args) {
		testEdgeSolution1();
		testEdgeSolution2();
	}
	private static void testEdgeSolution2() {
		int N = Integer.MIN_VALUE;
		int[] arr = new int[] {
			1,
			2, 3,
			1, 2, 3, 4,
			N, N, 7, N, 8, N, N, N
		};
		TreeNode head = tools.TreeNode辅助.A_生成满二叉树(arr);
		EdgeSolution2 s = new EdgeSolution2();
		s.printEdge(head);
	}
	private static void testEdgeSolution1() {
		int N = Integer.MIN_VALUE;
		int[] arr = new int[] {
			1,
			2, 3,
			1, 2, 3, 4,
			N, N, 7, N, 8, N, N, N
		};
		TreeNode head = tools.TreeNode辅助.A_生成满二叉树(arr);
		EdgeSolution1 s = new EdgeSolution1();
		s.printEdge(head);
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
	/*
	 * 	标准二：
	 * 		1,	头结点为边界节点
	 * 		2,	叶子节点为边界节点
	 * 		3,	树左边界延伸下去的路径为边界节点
	 * 		4,	树左边界延伸下去的路径为边界节点
	 */
	static class EdgeSolution2 {
		public void printEdge(TreeNode head) {
			if (head == null) {
				return;
			}
			System.out.print(head.val + " ");
			if (head.left != null && head.right == null) {
				printLeftEdge(head.left, true);
				printRightEdge(head.right, true);
			} else {
				printEdge(head.left != null ? head.left : head.right);
			}
			System.out.println();
		}
		public void printLeftEdge(TreeNode head, boolean print) {
			if (head == null) {
				return;
			}
			if (print || (head.left == null && head.right == null)) {
				System.out.print(head.val + " ");
			}
			printLeftEdge(head.left, print);
			printLeftEdge(head.right, print && head.left == null ? true : false);
		}
		public void printRightEdge(TreeNode head, boolean print) {
			if (head  == null) {
				return;
			}
			printRightEdge(head.left, print && head.right == null ? true : false);
			printRightEdge(head.right, print);
			if (print || (head.left == null && head.right == null)) {
				System.out.print(head.val + " ");
			}
		}
	}
}
