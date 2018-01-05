package leetcode;

import tools.TreeNode辅助.TreeNode;

/**
 *  Given a binary search tree (BST), find the lowest common ancestor (LCA) 
 *  of two given nodes in the BST.

	According to the definition of LCA on Wikipedia: “The lowest common ancestor
	 is defined between two nodes v and w as the lowest node in T that has both v
	  and w as descendants (where we allow a node to be a descendant of itself).”
	
	        _______6______
	       /              \
	    ___2__          ___8__
	   /      \        /      \
	   0      _4       7       9
	         /  \
	         3   5
	For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6.
	 Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant
	  of itself according to the LCA definition.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P235_LowestCommonAncestorOfABinarySearchTree.java
 * @type        P235_LowestCommonAncestorOfABinarySearchTree
 * @date        2016年12月9日 下午10:15:09
 * @details     Solution1: AC 8ms 54.82%
 * @details     Solution2: AC 8ms 54.82%
 */
public class P235_LowestCommonAncestorOfABinarySearchTree {
	static class Solution1 {
	    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	        if (calc(root, p, q) <= 0) return root;
	        else
	        	return root.val > p.val ? lowestCommonAncestor(root.left, p, q)
	        			: lowestCommonAncestor(root.right, p, q);
	    }
	    private int calc(TreeNode p1, TreeNode p2, TreeNode p3) {
	    	return (p1.val - p2.val) * (p1.val - p3.val);
	    }
	}
	static class Solution2 {
	    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	    	while (true) {
	    		if (calc(root, p, q) <= 0) return root;
	    		root = root.val > p.val ? root.left : root.right;
	    		if (root == null) break;
	    	}
	    	return null;
	    }
	    private int calc(TreeNode p1, TreeNode p2, TreeNode p3) {
	    	return (p1.val - p2.val) * (p1.val - p3.val);
	    }
	}
}
