package leetcode;

import java.util.HashMap;
import java.util.HashSet;

import tools.TreeNode辅助.TreeNode;

/**
 * 	Given a binary search tree (BST) with duplicates, find all the mode(s) 
 * (the most frequently occurred element) in the given BST.
 *  
 *  Assume a BST is defined as follows:
 *  
 *  The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 *  The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 *  Both the left and right subtrees must also be binary search trees.
 *  For example:
 *  Given BST [1,null,2,2],
 *     1
 *      \
 *       2
 *      /
 *     2
 *  return [2].
 *  
 *  Note: If a tree has more than one mode, you can return them in any order.
 *  
 *  Follow up: Could you do that without using any extra space? 
 *  (Assume that the implicit stack space incurred due to recursion does not count).
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P501_FindModeinBinarySearchTree.java
 * @type        P501_FindModeinBinarySearchTree
 * @date        2017年2月8日 下午11:24:26
 * @details     Solution1: AC 7ms 65.12%
 */
public class P501_FindModeinBinarySearchTree {
	static class Solution1 {
	    int pre = Integer.MIN_VALUE;
	    int count = 0;
	    int max = 0;
	    boolean compareOne = false;
	    HashMap<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>();
	    public int[] findMode(TreeNode root) {
	        if (root == null) return new int[0];
	        if (root.val == pre) pre = pre + 1;
	        middle(root);
	        if (max <= count) {
                HashSet<Integer> set = map.get(count);
                if (set == null) {
                    set = new HashSet<Integer>();
                }
                set.add(pre);
                map.put(count, set);
                max = count;
            }
	        int[] answer = new int[map.get(max).size()];
	        int answerIndex = 0;
	        for (Integer i : map.get(max)) {
	            answer[answerIndex ++] = i;
	        }
	        return answer;
	    }
	    private void middle(TreeNode root) {
	        if (root == null) return;
	        middle(root.left);
	        if (! compareOne) {
	            pre = root.val;
	            count = 1;
	            compareOne = true;
	        } else {
    	        if (pre == root.val) {
    	            count ++;
    	        } else {
    	            if (max <= count) {
    	                HashSet<Integer> set = map.get(count);
    	                if (set == null) {
    	                    set = new HashSet<Integer>();
    	                }
    	                set.add(pre);
    	                map.put(count, set);
    	                max = count;
    	            }
                    pre = root.val;
    	            count = 1;
    	        }
	        }
	        middle(root.right);
	    }
	}
}
