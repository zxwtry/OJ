package nowcoder.zuo;

import tools.TreeNode辅助.TreeNode;

public class Book032_如何较为直观地打印二叉树 {
	public static void main(String[] args) {
		
	}
	static class PrintSolution {
		public void printTree(TreeNode head) {
			System.out.print("Binary Tree: ");
			printInOrder(head, 0, "H", 17);
			System.out.println();
		}
		private void printInOrder(TreeNode head, int height, String to, int len) {
			if (head == null) {
				return;
			}
			printInOrder(head.right, height + 1, "v", len);
			String val = to+head.val+to;
			int lenM = val.length();
			int lenL = (len - lenM) / 2;
			int lenR = len - lenM -lenL;
			val = getSpace(lenL) + val + getSpace(lenR);
			System.out.println(getSpace(height * len) + val);
			printInOrder(head.left, height + 1, "^", len);
		}
		private String getSpace(int lenR) {
			String space = " ";
			StringBuffer st = new StringBuffer("");
			for (int i = 0; i < lenR; i ++) {
				st.append(space);
			}
			return st.toString();
		}
	}
}