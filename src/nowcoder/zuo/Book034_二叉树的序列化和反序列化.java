package nowcoder.zuo;

import java.util.LinkedList;
import java.util.Queue;

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
		public TreeNode recon(String pre) {
			String[] vals = pre.split("!");
			Queue<String> q = new LinkedList<>();
			for (int i = 0; i != vals.length; i ++) {
				q.offer(vals[i]);
			}
			return recon(q);
		}
		private TreeNode recon(Queue<String> q) {
			String value = q.poll();
			if (value.equals("#")) {
				return null;
			}
			TreeNode head = new TreeNode(Integer.valueOf(value));
			head.left = recon(q);
			head.right = recon(q);
			return head;
		}
	}
	static class LevelSolution {
		public String serial(TreeNode head) {
			if (head == null) {
				return "#!";
			}
			String res = head.val + "!";
			Queue<TreeNode> q = new LinkedList<>();
			q.offer(head);
			while (! q.isEmpty()) {
				head = q.poll();
				if (head.left != null) {
					res += head.left.val + "!";
					q.offer(head.left);
				} else {
					res += "#!";
				}
				if (head.right != null) {
					res += head.right.val + "!";
					q.offer(head.right);
				} else {
					res += "#!";
				}
			}
			return res;
		}
	}
}
