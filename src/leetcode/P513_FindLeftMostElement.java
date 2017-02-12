package leetcode;

import java.util.LinkedList;

import tools.TreeNode辅助.TreeNode;

/**
 *	Given a binary tree, find the left most element in the last row of the tree.
 *	
 *	Example 1:
 *	Input:
 *	
 *	    2
 *	   / \
 *	  1   3
 *	
 *	Output:
 *	1
 *	Example 2: 
 *	Input:
 *	
 *	        1
 *	       / \
 *	      2   3
 *	     /   / \
 *	    4   5   6
 *	       /
 *	      7
 *	
 *	Output:
 *	7
 *	Note: You may assume the tree is not NULL.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P513_FindLeftMostElement.java
 * @type        P513_FindLeftMostElement
 * @date        2017年2月12日 下午8:30:36
 * @details     Solution1: AC
 */
public class P513_FindLeftMostElement {
	static class Solution1 {
	    public int findLeftMostNode(TreeNode root) {
	        LinkedList<TreeNode> layer = new LinkedList<TreeNode>();
	        TreeNode rootNow = null;
	        layer.add(root);
	        int layCount = 1;
	        int layCountPre = 0;
	        int ans = 0;
	        while(layer.size() != 0) {
	        	rootNow = layer.pollFirst();
	        	if (rootNow == null) {
	        		layCount ++;
	        	} else {
	        		if (layCount == layCountPre + 1) {
	        			ans = rootNow.val;
	        			layCountPre = layCount;
	        			layer.add(null);
	        		}
		        	if (rootNow.left != null)
		        		layer.addLast(rootNow.left);
		        	if (rootNow.right != null)
		        		layer.addLast(rootNow.right);
	        	}
	        }
	        return ans;
	    }
	}
}
