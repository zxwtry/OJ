package leetcode;

import java.util.LinkedList;

import tools.TreeNode辅助.TreeNode;

/**
 * 	You need to find the largest value in each row of a binary tree.
 *  Example:
 *  Input: 
 *           1
 *          / \
 *         3   2
 *        / \   \  
 *       5   3   9 
 *  Output: [1, 3, 9]
 */


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P515_FindLargestElementInEachRow.java
 * @type        P515_FindLargestElementInEachRow
 * @date        2017年2月13日 上午9:16:20
 * @details     Solution: AC 13 ms
 */
public class P515_FindLargestElementInEachRow {
	static class Solution {
	    public int[] findValueMostElement(TreeNode root) {
	    	if (root == null) return new int[0];
	    	LinkedList<TreeNode> layer = new LinkedList<TreeNode>();
	        TreeNode rootNow = null;
	        layer.add(root);
	        int layCount = 1;
	        int layCountPre = 0;
	        int maxValueRow = Integer.MIN_VALUE;
	        LinkedList<Integer> maxRow = new LinkedList<Integer>();
	        while(layer.size() != 0) {
	        	rootNow = layer.pollFirst();
	        	if (rootNow == null) {
	        		maxRow.add(maxValueRow);
	        		maxValueRow = Integer.MIN_VALUE;
	        		layCount ++;
	        	} else {
	        		if (layCount == layCountPre + 1) {
	        			layCountPre = layCount;
	        			layer.add(null);
	        		}
	        		maxValueRow = Math.max(maxValueRow, rootNow.val);
		        	if (rootNow.left != null)
		        		layer.addLast(rootNow.left);
		        	if (rootNow.right != null)
		        		layer.addLast(rootNow.right);
	        	}
	        }
	        int[] ansInt = new int[maxRow.size()];
	        int ansIntIndex = 0;
	        for (int v : maxRow) {
	        	ansInt[ansIntIndex ++] = v;
	        }
	        return ansInt;
	    }
	}
}
