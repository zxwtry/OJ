package leetcode;

/*
 * 	Follow up for problem "Populating Next Right Pointers in Each Node".

	What if the given tree could be any binary tree? 
	Would your previous solution still work?
	
	Note:
	
	You may only use constant extra space.
	For example,
	Given the following binary tree,
	         1
	       /  \
	      2    3
	     / \    \
	    4   5    7
	After calling your function, the tree should look like:
	         1 -> NULL
	       /  \
	      2 -> 3 -> NULL
	     / \    \
	    4-> 5 -> 7 -> NULL
 */

import tools.TreeLinkNode辅助.TreeLinkNode;

/*
 * 	跟上题的区别就是：这里的树不一定是满二叉树，而且还是要空间O(1)
 */

public class P117_PopulatingNextRightPointersinEachNodeII {
	static int N = Integer.MIN_VALUE;
	public static void main(String[] args) {
		TreeLinkNode root = tools.TreeLinkNode辅助.A_生成满二叉树(new int[]{
			1,
			2, 4,
//			3, N, N, 5,
//			6, N, N, N, N, N, N, 7,
//			8, N, N, N, N, N, N, N, N, N, N, N, N, N, N, 9
		});
		Solution3 s = new Solution3();
		s.connect(root);
		tools.TreeLinkNode辅助.B_按层打印(root);
	}
	static class Solution {
		public void connect(TreeLinkNode root) {
			if (root == null) {
				return;
			}
			pre_order(root, null);
		}
		private void pre_order(TreeLinkNode root, TreeLinkNode parent_next) {
			if (root.next == null && parent_next != null) {
				root.next = parent_next.left;
			}
			
			if (root.left == null && root.right == null) {
				return;
			}
			if (root.left != null && root.right != null) {
				root.left.next = root.right;
			}
			if (root.left != null) {
				pre_order(root.left, root.next);
			}
			if (root.right != null) {
				pre_order(root.right, root.next);
			}
		}
	}
	static class Solution2 {
		TreeLinkNode global_pre = null, global_save = null;
		public void connect(TreeLinkNode root) {
			if (root == null) {
				return;
			}
			pre_order(null, root, null);
		}
		
		private void pre_order(TreeLinkNode pre, TreeLinkNode root, TreeLinkNode parent_next) {
			global_save = root;
			if (root.next == null && parent_next != null) {
				root.next = parent_next.left;
			}
			System.out.println("global_pre : "+(global_pre == null ? 0 : global_pre.val) 
					+ "\t\t pre : " + (pre == null ? 0 : pre.val) 
					+ "\t\t root : " + root.val);
			if (global_pre == null) {
				global_pre = root;
			}
			if (root.left == null && root.right == null) {
				global_pre = root;
				return;
			}
			if (root.left != null && root.right != null) {
				root.left.next = root.right;
			}
			global_pre = global_save;
			if (root.left != null) {
				pre_order(global_pre, root.left, root.next);
			}
			global_pre = global_save;
			if (root.right != null) {
				pre_order(global_pre, root.right, root.next);
			}
		}
	}
	/*
	 * 	AC
	 * 	1 ms
	 */
	static class Solution3 {
		public void connect(TreeLinkNode root) {
			if (root == null) {
				return;
			}
			TreeLinkNode pre_head = root; 		// previous level's head
			TreeLinkNode pre_current = null; 	// previous level's pointer
			TreeLinkNode head = null; 			// current level's head
			TreeLinkNode current = null; 		// current level's pointer
			while (pre_head != null) {
				pre_current = pre_head;
				while (pre_current != null) {
					if (pre_current.left != null) {
						if (head == null) {
							head = pre_current.left;
							current = pre_current.left;
						} else {
							current.next = pre_current.left;
							current = current.next;
						}
					}
					if (pre_current.right != null) {
						if (head == null) {
							head = pre_current.right;
							current = pre_current.right;
						} else {
							current.next = pre_current.right;
							current = current.next;
						}
					}
					pre_current = pre_current.next;
				}
				pre_head = head;
				head = null;
			}
		}
	}
}