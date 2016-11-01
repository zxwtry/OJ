package nowcoder.zuo;

import java.util.HashMap;

import tools.TreeNode辅助.TreeNode;

public class Book035_在二叉树中找到累加和为指定值的最长路径长度 {
	public static void main(String[] args) {
		
	}
	/*
	 * 	时间：O(N)
	 * 	空间：O(h)
	 */
	static class Solution {
		public int getMaxLength(TreeNode head, int sum) {
			HashMap<Integer, Integer> sumMap = new HashMap<>();
			sumMap.put(0, 0);
			return preOrder(head, sum , 0, 1, 0, sumMap);
		}
		private int preOrder(TreeNode head, int sum, int preSum, int level, int maxLen, HashMap<Integer, Integer> sumMap) {
			if (head == null) {
				return maxLen;
			}
			int curSum = preSum + head.val;
			if (! sumMap.containsKey(curSum)) {
				sumMap.put(curSum, level);
			}
			if (sumMap.containsKey(curSum - sum)) {
				maxLen = Math.max(level - sumMap.get(curSum - sum), maxLen);
			}
			maxLen = preOrder(head.left, curSum, preSum, level + 1, maxLen, sumMap);
			maxLen = preOrder(head.right, curSum, preSum, level + 1, maxLen, sumMap);
			if (level == sumMap.get(curSum)) {
				sumMap.remove(curSum);
			}
			return 0;
		}
	}
}
