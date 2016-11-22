package nowcoder.zuo;

import tools.TreeNode辅助.TreeNode;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book047_在二叉树中找到两个节点的最近公共祖先.java
 * @type        Book047_在二叉树中找到两个节点的最近公共祖先
 * @date        2016年11月22日 下午6:01:06
 * @details     
 */

public class Book047_在二叉树中找到两个节点的最近公共祖先 {
	public static void main(String[] args) {
		debugMySolution();
	}
	
	/**
	 * @method      debugMySolution
	 * @parameter   
	 * @return      void
	 * @details     
	 */
	static void debugMySolution() {
		int layer = 13;
		int len = (1 << layer) - 1;
		int[] arr = new int[len];
		for (int index = 0; index < len; index ++) {
			arr[index] = index;
		}
		TreeNode[] ns = new TreeNode[arr.length];
		for (int index = 0; index < arr.length; index ++) {
			ns[index] = new TreeNode(arr[index]);
		}
		for (int index = 0; index < arr.length / 2; index ++) {
			ns[index].left = ns[index * 2 + 1];
			ns[index].right = ns[index * 2 + 2];
		}
		MySolution s = new MySolution();
		TreeNode ans = s.lowestAncestor(ns[0], ns[1 << (layer - 1)], ns[arr.length - 1]);
		System.out.println(ans.val);
	}

	static class MySolution {
		public TreeNode lowestAncestor(TreeNode root, TreeNode t1, TreeNode t2) {
			if (root == null || t1 == root || t2 == root)	return root;
			if (t1 == null || t2 == null)	return t1 == null ? t2 : t1;
			TreeNode left = lowestAncestor(root.left, t1, t2);
			TreeNode right= lowestAncestor(root.right, t1, t2);
			if (left != null && right != null) {
				return root;
			}
			return left != null ? left : right;
		}
	}
	
}
