package tools;

import java.util.LinkedList;
import java.util.Queue;

public class TreeLinkNode辅助 {
	public static class TreeLinkNode {
		public int val;
		public TreeLinkNode left, right, next;
		public TreeLinkNode(int x) { val = x; }
	}
	public static TreeLinkNode A_生成满二叉树(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		if (arr.length == 1) {
			return new TreeLinkNode(arr[0]);
		}
		int judge = arr.length + 1, count_1 = 0;
		while (judge != 0) {
			count_1 += (judge & 1) == 1 ? 1 : 0;
			judge = judge >>> 1;
		}
		if (count_1 != 1) {
			System.out.println("输入不是一个满二叉树");
			return null;
		}
		TreeLinkNode[] tree_arr = new TreeLinkNode[arr.length];
		for (int i = 0; i < arr.length; i ++) {
			tree_arr[i] = arr[i] == Integer.MIN_VALUE ? null : new TreeLinkNode(arr[i]);
		}
		for (int i = (arr.length - 2) / 2; i > -1; i --) {
			if (tree_arr[i] != null) {
				tree_arr[i].left = tree_arr[2 * i + 1];
				tree_arr[i].right = tree_arr[2 * i + 2];
			}
		}
		return tree_arr[0];
	}
	public static void B_按层打印(TreeLinkNode root) {
		if (root == null) {
			System.out.println("输入的TreeNode为null");
			return;
		}
		Queue<TreeLinkNode> q = new LinkedList<TreeLinkNode>();
		Queue<Integer> q_int = new LinkedList<Integer>();
		q.add(root);
		q_int.add(1);
		int pre_int = -1;
		while (! q.isEmpty()) {
			TreeLinkNode root_now = q.poll();
			int q_now = q_int.poll();
			if (q_now != pre_int) {
				if (pre_int == -1)
					System.out.printf("第%02d层\t",q_now);
				else
					System.out.printf("\r\n第%02d层\t",q_now);
				pre_int = q_now;
			}
			System.out.print(root_now.val+String.format("(%d)", root_now.next == null ? 0 : root_now.next.val)+"\t");
			if (root_now.left != null) {
				q.add(root_now.left);
				q_int.add(q_now + 1);
			}
			if (root_now.right != null) {
				q.add(root_now.right);
				q_int.add(q_now + 1);
			}
		}
		System.out.println();
	}
}
