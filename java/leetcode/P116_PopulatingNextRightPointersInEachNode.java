package leetcode;


import tools.TreeLinkNode辅助.TreeLinkNode;

/*
 * 	Given a binary tree

	    struct TreeLinkNode {
	      TreeLinkNode *left;
	      TreeLinkNode *right;
	      TreeLinkNode *next;
	    }
	Populate each next pointer to point to its next right node. 
	If there is no next right node, the next pointer should be set to NULL.
	
	Initially, all next pointers are set to NULL.
	
	Note:
	
	You may only use constant extra space.
	You may assume that it is a perfect binary tree (ie, all leaves
	 are at the same level, and every parent has two children).
	For example,
	Given the following perfect binary tree,
	         1
	       /  \
	      2    3
	     / \  / \
	    4  5  6  7
	After calling your function, the tree should look like:
	         1 -> NULL
	       /  \
	      2 -> 3 -> NULL
	     / \  / \
	    4->5->6->7 -> NULL
 */

public class P116_PopulatingNextRightPointersInEachNode {
	public static void main(String[] args) {
		TreeLinkNode tree = tools.TreeLinkNode辅助.A_生成满二叉树(new int[] {
				1,
				2, 3,
//				4, 5, 6, 7,
//				8,9,10,11,12,13,14,15
		});
		new Solution2().connect(tree);
		System.out.println(tree.val);
	}
	/*
	 * 	充分利用完全满二叉树这个特点
	 * 	AC
	 * 	0 ms
	 */
	static class Solution2 {
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
			if (root.left == null) {
				return;
			}
			root.left.next = root.right;
			pre_order(root.left, root.next);
			pre_order(root.right, root.next);
		}
	}
}
