package nowcoder.zuo;

import java.util.HashMap;
import java.util.HashSet;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
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
//		debugMySolution();
//		debugRecord1();
	}
	
	/**
	 * @method      debugRecord1
	 * @parameter   
	 * @return      void
	 * @details     
	 */
	static void debugRecord1() {
		int layer = 9;
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
		Record1 r = new Record1(ns[0]);
		System.out.println(r.query(ns[(1 << (layer- 1)) - 1], ns[ns.length - 1]).val + "..." + ((1 << (layer- 1)) - 2));
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

	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book047_在二叉树中找到两个节点的最近公共祖先.java
	 * @type        MySolution
	 * @date        2016年11月22日 下午8:34:46
	 * @details     时间复杂度O(N)，N是节点数
	 * @details     但是每一次都是这样的复杂度，多次查询没有优化。
	 */
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
	
	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book047_在二叉树中找到两个节点的最近公共祖先.java
	 * @type        Record1
	 * @date        2016年11月22日 下午9:42:47
	 * @details     
	 */
	static class Record1 {
		private HashMap<TreeNode, TreeNode> map = null;
		public Record1(TreeNode head) {
			map = new HashMap<TreeNode, TreeNode>();
			if (head != null) {
				map.put(head, null);
				setMap(head);
			}
		}
		private void setMap(TreeNode head) {
			if (head.left != null) {
				map.put(head.left, head);
				setMap(head.left);
			}
			if (head.right != null) {
				map.put(head.right, head);
				setMap(head.right);
			}
		}
		public TreeNode query(TreeNode t1, TreeNode t2) {
			HashSet<TreeNode> path = new HashSet<TreeNode>();
			while (map.containsKey(t1)) {
				path.add(t1);
				t1 = map.get(t1);
			}
			while (! path.contains(t2)) {
				t2 = map.get(t2);
			}
			return t2;
		}
	}
	
}
