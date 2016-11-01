package nowcoder.zuo;

import tools.TreeNode辅助.TreeNode;

public class Book036_找到二叉树中的最大搜索二叉树 {
	public static void main(String[] args) {
		
	}
	//O(N) O(h)
	static class Solution {
		public TreeNode biggestSubBST(TreeNode head) {
			int[] record = new int[3];
			return posOrder(head, record);
		}
		private TreeNode posOrder(TreeNode head, int[] record) {
			if (head == null) {
				record[0] = 0;
				record[1] = Integer.MAX_VALUE;
				record[2] = Integer.MIN_VALUE;
				return null;
			}
			int val = head.val;
			TreeNode left = head.left;
			TreeNode right = head.right;
			TreeNode lBST = posOrder(left, record);
			int lSize = record[0];
			int lMin = record[1];
			int lMax = record[2];
			TreeNode rBST = posOrder(right, record);
			int rSize = record[0];
			int rMin = record[1];
			int rMax = record[2];
			record[1] = Math.min(lMax, val);
			record[2] = Math.max(rMax, val);
			if (left == lBST && right == rBST && lMax < val && val < rMin) {
				record[0] = lSize + rSize + 1;
				return head;
			}
			record[0] = Math.max(lSize, rSize);
			return lSize > rSize ? lBST : rBST;
		}
	}
}
