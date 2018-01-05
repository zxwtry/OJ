package leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * 	Given a singly linked list where elements are sorted in ascending order,
 *  convert it to a height balanced BST.
 */

import tools.ListNode辅助.ListNode;
import tools.TreeNode辅助.TreeNode;

public class P109_ConvertSortedListtoBinarySearchTree {
	public static void main(String[] args) {
		ListNode head = tools.ListNode辅助.A_一维生成器(new int[] {1, 2, 3});
		TreeNode root = new Solution2().sortedListToBST(head);
		tools.TreeNode辅助.B_按层打印(root);
	}
	/*
	 * 	TLE了，对时间的要求很高哪。
	 */
	static class Solution {
	    public TreeNode sortedListToBST(ListNode head) {
	    	int len = 0;
	    	ListNode cur = head;
	    	while (cur != null) {
	    		len ++;
	    		cur = cur.next;
	    	}
	    	int[] nums = new int[len];
	    	len = 0;
	    	cur = head;
	    	while (cur != null) {
	    		nums[len] = cur.val;
	    		len ++;
	    		cur = cur.next;
	    	}
	        return sortedArrayToBST(nums, 0, len - 1);
	    }
		private TreeNode sortedArrayToBST(int[] nums, int i, int j) {
			if (i > j) {
				return null;
			}
			if (i == j) {
				return new TreeNode(nums[i]);
			}
			int root_index = (i + j) / 2;
			TreeNode root = new TreeNode(nums[root_index]);
			root.left = sortedArrayToBST(nums, i, root_index - 1);
			root.right = sortedArrayToBST(nums, root_index + 1, j);
			return root;
		}
	}
	/*
	 * 	还是TLE，是不是rehash的锅
	 * 	下一个方法采用ArrayList的吧
	 */
	static class Solution2 {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	    public TreeNode sortedListToBST(ListNode head) {
	    	int count = 0;
	    	while (head != null) {
	    		map.put(count ++, head.val);
	    		head = head.next;
	    	}
	    	return zxwtry_generate(0, count - 1);
	    }
		private TreeNode zxwtry_generate(int i, int j) {
			if (i > j) {
				return null;
			}
			if (i == j) {
				return new TreeNode(map.get(i));
			}
			int mid = (i + j) / 2;
			TreeNode root = new TreeNode(map.get(mid));
			root.left = zxwtry_generate(i, mid - 1);
			root.right = zxwtry_generate(mid + 1, j);
			return root;
		}
	}
	/*
	 * 	艰难地AC了
	 * 	3 ms
	 */
	static class Solution3 {
		ArrayList<Integer> arr = new ArrayList<>();
	    public TreeNode sortedListToBST(ListNode head) {
	    	while (head != null) {
	    		arr.add(head.val);
	    		head = head.next;
	    	}
	    	return sortedArrayToBST(0, arr.size() - 1);
	    }
	    private TreeNode sortedArrayToBST(int i, int j) {
			if (i > j) {
				return null;
			}
			if (i == j) {
				return new TreeNode(arr.get(i));
			}
			int root_index = (i + j) / 2;
			TreeNode root = new TreeNode(arr.get(root_index));
			root.left = sortedArrayToBST(i, root_index - 1);
			root.right = sortedArrayToBST(root_index + 1, j);
			return root;
		}
	}
}
