package nowcoder.zuo;

import java.util.LinkedList;
import java.util.Queue;

import tools.TreeNode辅助.TreeNode;

public class Book034_二叉树的序列化和反序列化 {
	public static void main(String[] args) {
		testPre();
		testLevel();
	}
	private static void testLevel() {
		int N = Integer.MIN_VALUE;
		int[] arr = new int[] {
			1,
			2, 3,
			1, 2, 3, 4,
			N, N, N, N, N, N, N, N
		};
		TreeNode head = tools.TreeNode辅助.A_生成满二叉树(arr);
		LevelSolution s = new LevelSolution();
		String str = s.serial(head);
		System.out.println(str);
		TreeNode newHead = s.recon(str);
		tools.TreeNode辅助.B_按层打印(newHead);
	}
	private static void testPre() {
		int N = Integer.MIN_VALUE;
		int[] arr = new int[] {
			1,
			2, 3,
			1, 2, 3, 4,
			N, N, N, N, N, N, N, N
		};
		TreeNode head = tools.TreeNode辅助.A_生成满二叉树(arr);
		PreSolution s = new PreSolution();
		String str = s.serial(head);
		System.out.println(str);
		TreeNode newHead = s.recon(str);
		tools.TreeNode辅助.B_按层打印(newHead);
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
		public TreeNode recon(String level) {
			String[] vals = level.split("!");
			int index = 0;
			TreeNode head = vals[index].equals("#") ? null : new TreeNode(Integer.valueOf(vals[index]));
			index ++;
			Queue<TreeNode> q = new LinkedList<>();
			if (head != null) {
				q.offer(head);
			}
			TreeNode node = null;
			while (! q.isEmpty()) {
				node = q.poll();
				node.left = vals[index].equals("#") ? null : new TreeNode(Integer.valueOf(vals[index]));
				index ++;
				node.right = vals[index].equals("#") ? null : new TreeNode(Integer.valueOf(vals[index]));
				index ++;
				if (node.left != null) {
					q.offer(node.left);
				}
				if (node.right != null) {
					q.offer(node.right);
				}
			}
			return head;
		}
	}
}
