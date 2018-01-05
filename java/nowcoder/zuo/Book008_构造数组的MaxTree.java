package nowcoder.zuo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

import tools.TreeNode辅助.TreeNode;

/*
 * 	一个数组的MaxTree定义如下：
 * 		数组必须没有重复元素
 * 		MaxTree是一棵二叉树，数组的每一个值对应一个二叉树节点
 * 		包括MaxTree树在内且在其中的每一颗子树上，值最大的节点都是树的头
 * 	给定一个没有重复元素的数组arr，写出生成这个数组的MaxTree函数
 * 	如果数组长度N，要求时间复杂度O(N)，额外空间复杂度O(N)
 */
/*
 * 	
 */
public class Book008_构造数组的MaxTree {
	public static void main(String[] args) {
		test();
	}
	private static void test() {
		int n = 1000;
		int min = 0;
		int max = n * n;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
//		arr = new int[] {1, 1, 1, 1, 1, 1, 1};
		Solution s = new Solution();
		TreeNode head = s.constructMaxTree(arr);
		boolean isAMaxTree = getIsAMaxTree(head);
		System.out.println(isAMaxTree);
	}
	private static boolean getIsAMaxTree(TreeNode head) {
		if (head == null) {
			return true;
		}
		LinkedList<TreeNode> q = new LinkedList<>();
		q.add(head);
		boolean isTrue = true;
		HashSet<Long> set = new HashSet<>();
		while (! q.isEmpty()) {
			TreeNode rootNow = q.poll();
			long leftVal = Long.MIN_VALUE;
			long rightVal = Long.MIN_VALUE;
			if (rootNow.left != null) {
				leftVal = rootNow.left.val;
				q.add(rootNow.left);
			}
			if (rootNow.right != null) {
				rightVal = rootNow.right.val;
				q.add(rootNow.right);
			}
			long rootNowVal = (long) rootNow.val;
			if (leftVal >= rootNowVal) {
				isTrue = false;
				break;
			}
			if (rightVal >= rootNowVal) {
				isTrue = false;
				break;
			}
			if (set.contains(rootNowVal)) {
				isTrue = false;
				break;
			}
			set.add(rootNowVal);
		}
		return isTrue;
	}
	static class Solution {
		public TreeNode constructMaxTree(int[] arr) {
			TreeNode[] nArr = new TreeNode[arr.length];
			HashSet<Integer> set = new HashSet<Integer>(arr.length * 3 / 2);
			int nArrLength = 0;
			for (int i = 0; i != arr.length; i ++) {
				if (! set.contains(arr[i])) {
					nArr[nArrLength] = new TreeNode(arr[i]);
					set.add(arr[i]);
					nArrLength ++;
				}
			}
			Stack<TreeNode> stack = new Stack<>();
			HashMap<TreeNode, TreeNode> lBigMap = new HashMap<TreeNode, TreeNode>();
			HashMap<TreeNode, TreeNode> rBigMap = new HashMap<TreeNode, TreeNode>();
			for (int i = 0; i != nArrLength; i ++) {
				TreeNode curNode = nArr[i];
				while(! stack.isEmpty() && stack.peek().val < curNode.val) {
					popStackSetMap(stack, lBigMap);
				}
				stack.push(curNode);
			}
			while(! stack.isEmpty()) {
				popStackSetMap(stack, lBigMap);
			}
			for (int i = nArrLength - 1; i != -1; i --) {
				TreeNode curNode = nArr[i];
				while(! stack.isEmpty() && stack.peek().val < curNode.val) {
					popStackSetMap(stack, rBigMap);
				}
				stack.push(curNode);
			}
			while(! stack.isEmpty()) {
				popStackSetMap(stack, rBigMap);
			}
			TreeNode head = null;
			for (int i = 0; i != nArrLength; i ++) {
				TreeNode curNode = nArr[i];
				TreeNode left = lBigMap.get(curNode);
				TreeNode right = rBigMap.get(curNode);
				if (left == null && right == null) {
					head = curNode;
				} else if (left == null) {
					setChilds(right, curNode);
				} else if (right == null) {
					setChilds(left, curNode);
				} else {
					setChilds((left.val < right.val) ? left : right, curNode);
				}
			}
			return head;
		}
		private void setChilds(TreeNode parent, TreeNode curNode) {
			if (parent.left == null) {
				parent.left = curNode;
			} else {
				parent.right = curNode;
			}
		}
		private void popStackSetMap(Stack<TreeNode> stack, HashMap<TreeNode, TreeNode> map) {
			TreeNode popNode = stack.pop();
			map.put(popNode, stack.isEmpty() ? null : stack.peek());
		}
	}
}