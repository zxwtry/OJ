package leetcode;

import java.util.ArrayList;

/*
 * 	Given an integer n, generate all structurally unique BST's 
 * 	(binary search trees) that store values 1...n.

	For example,
	Given n = 3, your program should return all 5 unique BST's shown below.
	
	   1         3     3      2      1
	    \       /     /      / \      \
	     3     2     1      1   3      2
	    /     /       \                 \
	   2     1         2                 3
 */

import java.util.LinkedList;
import java.util.List;

import tools.TreeNode辅助.TreeNode;

public class P095_UniqueBinarySearchTreesII {
	public static void main(String[] args) {
		List<TreeNode> ans = new Solution().generateTrees(3);
		for (int i = 0; i < ans.size(); i ++) {
			TreeNode tree = ans.get(i);
			System.out.println(tree.val);
		}
	}
	/*
	 * 	AC
	 * 	3 ms
	 */
	static class Solution {
	    public List<TreeNode> generateTrees(int n) {
	        if (n <= 0) {
	        	return new LinkedList<TreeNode>();
	        }
	        return generateTrees(1, n);
 	    }
	    /*
	     * 	保证arr是排序的
	     */
	    List<TreeNode> generateTrees(int sti, int eni) {
	    	List<TreeNode> this_tree = new ArrayList<TreeNode>();
	    	if (sti > eni) {
	    		this_tree.add(null);
	    		return this_tree;
	    	}
	    	if (sti + 0 == eni) {
	    		TreeNode t0 = new TreeNode(sti);
	    		this_tree.add(t0);
	    		return this_tree;
	    	} else if (sti + 1 == eni) {
	    		// 第一种
	    		TreeNode tree_sti_1 = new TreeNode(sti);
	    		TreeNode tree_eni_1 = new TreeNode(eni);
	    		tree_sti_1.right = tree_eni_1;
	    		this_tree.add(tree_sti_1);
	    		// 第二种
	    		TreeNode tree_sti_2 = new TreeNode(sti);
	    		TreeNode tree_eni_2 = new TreeNode(eni);
	    		tree_eni_2.left = tree_sti_2;
	    		this_tree.add(tree_eni_2);
	    		return this_tree;
	    	}
	    	for (int tra = sti; tra <= eni; tra ++) {
	    		List<TreeNode> this_tree_1 = generateTrees(sti, tra - 1);
	    		List<TreeNode> this_tree_2 = generateTrees(tra + 1, eni);
	    		for (int i_1 = 0; i_1 < this_tree_1.size(); i_1 ++) {
	    			for (int i_2 = 0; i_2 < this_tree_2.size(); i_2 ++) {
	    				TreeNode tree_tra = new TreeNode(tra);
	    				tree_tra.left = this_tree_1.get(i_1);
	    				tree_tra.right = this_tree_2.get(i_2);
	    				this_tree.add(tree_tra);
	    			}
	    		}
	    	}
	    	return this_tree;
	    }
	}
}
