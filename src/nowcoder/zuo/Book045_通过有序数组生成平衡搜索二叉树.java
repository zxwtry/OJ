package nowcoder.zuo;

import java.util.ArrayList;
import java.util.Arrays;

import tools.TreeNode辅助.TreeNode;

/*
 * 	给定一个有序数组sortArr，已知其中没有重复值，用这个有序数组
 * 	生成一棵平衡搜索二叉树，并且该搜索二叉树中序遍历的结果与sortArr一致
 */

public class Book045_通过有序数组生成平衡搜索二叉树 {
	public static void main(String[] args) {
		compareMyAndBook();
	}
	
	static void compareMyAndBook() {
		int n = (int) ( Math.random() * 10000 );
		int min = 0;
		int max = n * 3;
		int[] arr = tools.Random随机生成器.A_生成一个不重复随机数据(n, min, max);
		Arrays.sort(arr);
		MySolution ms = new MySolution();
		BookSolution bs = new BookSolution();
		TreeNode headMs = ms.generate(arr);
		TreeNode headBs = bs.generate(arr);
		boolean isSame = tools.TreeNode辅助.D_head1和head2是不是值拓扑相同的树(headMs, headBs);
		System.out.println(isSame);
	}

	static void debugMySolution() {
		int n = (int) ( Math.random() * 10000 );
		int min = 0;
		int max = n * 3;
		int[] arr = tools.Random随机生成器.A_生成一个不重复随机数据(n, min, max);
		Arrays.sort(arr);
		MySolution s = new MySolution();
		TreeNode head = s.generate(arr);
		int[] arr2 = s.judgeArrInOrder(head);
		boolean isTrue = true;
		for (int i = 0; i < arr.length; i ++) {
			isTrue &= arr[i] == arr2[i];
		}
		System.out.println(isTrue);
		System.out.println(s.isBST(head));
	}

	static class MySolution {
		public TreeNode generate(int[] sortArr) {
			if (sortArr == null || sortArr.length == 0) {
				return null;
			}
			int sti = 0;
			int eni = sortArr.length - 1;
			return generate(sortArr, sti, eni);
		}
		TreeNode generate(int[] sortArr, int sti, int eni) {
			if (sti > eni) {
				return null;
			}
			int mid = (sti + eni) / 2;
			TreeNode head = new TreeNode(sortArr[mid]);
			head.left = generate(sortArr, sti, mid - 1);
			head.right = generate(sortArr, mid + 1, eni);
			return head;
		}
		public int[] judgeArrInOrder(TreeNode head) {
			if (head == null) {
				return new int[0];
			}
			ArrayList<Integer> inOrder = new ArrayList<>();
			inOrderInternal(head, inOrder);
			int[] arr = new int[inOrder.size()];
			for (int i = 0; i < arr.length; i ++) {
				arr[i] = inOrder.get(i);
			}
			return arr;
		}
		private void inOrderInternal(TreeNode head, ArrayList<Integer> inOrder) {
			if (head.left != null) {
				inOrderInternal(head.left, inOrder);
			}
			inOrder.add(head.val);
			if (head.right != null) {
				inOrderInternal(head.right, inOrder);
			}
		}
		public boolean isBST(TreeNode head) {
			if (head == null) {
				return true;
			}
			boolean[] isBalance = new boolean[]{true};
			int lenLeft = treeNodeLength(head.left, isBalance);
			int lenRight = 0;
			if (isBalance[0]) {
				lenRight = treeNodeLength(head.right, isBalance);
			}
			return isBalance[0] ? Math.abs(lenLeft - lenRight) < 2 : false;
		}
		private int treeNodeLength(TreeNode head, boolean[] isBalance) {
			if (! isBalance[0] || head == null) {
				return 0;
			}
			int lenLeft = treeNodeLength(head.left, isBalance);
			int lenRight = 0;
			if (isBalance[0]) {
				lenRight = treeNodeLength(head.right, isBalance);
			}
			isBalance[0] = Math.abs(lenLeft - lenRight) < 2;
			return Math.max(lenLeft, lenRight) + 1;
		}
	}
	
	static class BookSolution {
		public TreeNode generate(int[] sortArr) {
			if (sortArr == null || sortArr.length == 0) {
				return null;
			}
			return generate(sortArr, 0, sortArr.length - 1);
		}
		private TreeNode generate(int[] sortArr, int sti, int eni) {
			if (sti > eni) {
				return null;
			}
			int mid = (sti + eni) / 2;
			TreeNode head = new TreeNode(sortArr[mid]);
			head.left = generate(sortArr, sti, mid - 1);
			head.right = generate(sortArr, mid + 1, eni);
			return head;
		}
	}
}