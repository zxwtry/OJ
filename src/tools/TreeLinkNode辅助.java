package tools;


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
}
