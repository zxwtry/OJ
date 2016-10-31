package nowcoder.zuo;

import tools.TreeNode辅助.TreeNode;

public class Book034_二叉树的序列化和反序列化 {
	public static void main(String[] args) {
		
	}
	static class PreSolution {
		public String serial(TreeNode head) {
			if (head == null) {
				return "$!";
			}
			String res = head.val + "!";
			res += this.serial(head.left);
			res += this.serial(head.right);
			return res;
		}
	}
}
