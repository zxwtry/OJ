package nowcoder.zuo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book046_在二叉树中找到一个节点的后继节点.java
 * @type        Book046_在二叉树中找到一个节点的后继节点
 * @date        2016年11月22日 下午4:27:39
 * @details     
 */
public class Book046_在二叉树中找到一个节点的后继节点 {
	public static void main(String[] args) {
//		debugBookSolution1();
//		debugBookSolution2();
		compareBookSolution1And2();
	}
	
	/**
	 * @method      compareBookSolution1And2
	 * @parameter   
	 * @return      void
	 * @details     
	 */
	static void compareBookSolution1And2() {
		int layer = (int)(Math.random() * 20);
		int len = (1 << layer) - 1;
		int min = 0;
		int max = 2 * len;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(len, min, max);
		for (int index = 0; index < arr.length; index ++) {
			if (Math.random() < 0.1) {
				arr[index] = Integer.MIN_VALUE;
			}
		}
		PosNode[] ps = constructTree(arr);
		ArrayList<PosNode> list = new ArrayList<>();
		Queue<PosNode> q = new LinkedList<PosNode>();
		if (ps[0] != null) {
			q.add(ps[0]);
			list.add(ps[0]);
		}
		PosNode rootNow = null;
		while (! q.isEmpty()) {
			rootNow = q.poll();
			if (rootNow.left != null) {
				q.add(rootNow.left);
				list.add(rootNow.left);
			}
			if (rootNow.right != null) {
				q.add(rootNow.right);
				list.add(rootNow.right);
			}
		}
		BookSolution1 s1 = new BookSolution1();
		BookSolution2 s2 = new BookSolution2();
		boolean isTrue = true;
 		for (int index = 0; index < list.size(); index ++) {
 			PosNode p1 = s1.getNextPosNode(list.get(index));
 			PosNode p2 = s2.getNextPosNode(list.get(index));
 			int v1 = p1 == null ? Integer.MIN_VALUE : p1.val;
 			int v2 = p2 == null ? Integer.MIN_VALUE : p2.val;
 			System.out.println(v1 + "..." + v2);
 			isTrue &= v1 == v2;
 		}
 		System.out.println(list.size() + "..." + isTrue);
	}

	/**
	 * @method      debugBookSolution2
	 * @parameter   
	 * @return      void
	 * @details     
	 */
	static void debugBookSolution2() {
		PosNode[] ps = constructTree(new int[] {0, 1, 2, 3, 4, 5, 6});
		BookSolution2 s = new BookSolution2();
		for (int i = 0; i < ps.length; i ++) {
			PosNode next = s.getNextPosNode(ps[i]);
			System.out.println(next == null ? Integer.MIN_VALUE : next.val);
		}
	}

	/**
	 * @method      debugBookSolution1
	 * @parameter   
	 * @return      void
	 * @details     
	 */
	static void debugBookSolution1() {
		PosNode[] ps = constructTree(new int[] {0, 1, 2, 3, 4, 5, 6});
		BookSolution1 s = new BookSolution1();
		for (int i = 0; i < ps.length; i ++) {
			PosNode next = s.getNextPosNode(ps[i]);
			System.out.println(next == null ? Integer.MIN_VALUE : next.val);
		}
	}

	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book046_在二叉树中找到一个节点的后继节点.java
	 * @type        BookSolution1
	 * @date        2016年11月22日 下午4:27:52
	 * @details     一种慢，但是易于理解的方法
	 */
	static class BookSolution1 {
		
		/**
		 * @method      getNextPosNode
		 * @parameter   
		 * @return      PosNode
		 * @details     程序入口
		 */
		public PosNode getNextPosNode(PosNode node) {
			PosNode root = getRoot(node);
			PosNode[] ans = new PosNode[] {null, null};
			inOrder(root, node, ans);
			return ans[0];
		}

		/**
		 * @method      inOrder
		 * @parameter   
		 * @return      void
		 * @details     
		 */
		private void inOrder(PosNode root, PosNode node, PosNode[] ans) {
			if (root == null || ans[0] != null)		return;
			inOrder(root.left, node, ans);
			if (ans[1] != null && ans[0] == null)	ans[0] = root;
			if (root == node) ans[1] = node;
			inOrder(root.right, node, ans);
		}

		/**
		 * @method      getRoot
		 * @parameter   
		 * @return      PosNode
		 * @details     
		 */
		private PosNode getRoot(PosNode node) {
			while (node.parent != null)		node = node.parent;
			return node;
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book046_在二叉树中找到一个节点的后继节点.java
	 * @type        BookSolution2
	 * @date        2016年11月22日 下午5:19:41
	 * @details     比BookSolution1快
	 */
	static class BookSolution2 {
		
		/**
		 * @method      getNextPosNode
		 * @parameter   
		 * @return      PosNode
		 * @details     程序入口
		 */
		public PosNode getNextPosNode(PosNode node) {
			if (node == null)	return node;
			if (node.right != null) {
				return getLeftMost(node.right);
			} else {
				PosNode parent = node.parent;
				while (parent != null && parent.left != node) {
					node = parent;
					parent = node.parent;
				}
				return parent;
			}
		}

		/**
		 * @method      getLeftMost
		 * @parameter   
		 * @return      PosNode
		 * @details     
		 */
		private PosNode getLeftMost(PosNode node) {
			if (node == null)	return node;
			while (node.left != null)	node = node.left;
			return node;
		}
		
	}
	
	static class PosNode {
		public int val;
		public PosNode left;
		public PosNode right;
		public PosNode parent;
		public PosNode(int val) {
			this.val = val;
		}
	}
	
	static PosNode[] constructTree(int[] arr) {
		PosNode[] ps = new PosNode[arr.length];
		for (int index = 0; index < arr.length; index ++) {
			if (arr[index] == Integer.MIN_VALUE) {
				ps[index] = null;
			} else {
				ps[index] = new PosNode(arr[index]);
			}
		}
		for (int i = 0; i < arr.length / 2; i ++) {
			if (ps[i] != null)
			ps[i].left = ps[2 * i + 1];
			if (ps[i] != null)
			ps[i].right = ps[2 * i + 2];
			if (ps[2 * i + 2] != null)
			ps[2 * i + 2].parent = ps[i];
			if (ps[2 * i + 1] != null)
			ps[2 * i + 1].parent = ps[i];
		}
		return ps;
	}
	
	/**
	 * @method      layer
	 * @parameter   
	 * @return      void
	 * @details     
	 */
	static void layer(PosNode root) {
		Queue<PosNode> q = new LinkedList<PosNode>();
		Queue<Integer> q_layer = new LinkedList<Integer>();
		if (root != null) {
			q.add(root);
			q_layer.add(1);
		}
		PosNode rootNow = null;
		int intNow = 0;
		int intPre = -1;
		while (! q.isEmpty()) {
			rootNow = q.poll();
			intNow = q_layer.poll();
			if (intPre != intNow) {
				if (intPre != -1)
					System.out.println();
				intPre = intNow;
			}
			if (rootNow.left != null) {
				q.add(rootNow.left);
				q_layer.add(intNow + 1);
			}
			System.out.print(rootNow.val + " ");
			if (rootNow.right != null) {
				q.add(rootNow.right);
				q_layer.add(intNow + 1);
			}

		}
	}
}
