package leetcode;

import java.util.ArrayList;
import tools.TreeNode辅助.TreeNode;

/*
 * 	Given a complete binary tree, count the number of nodes.
	
	Definition of a complete binary tree from Wikipedia:
	In a complete binary tree every level, except possibly the last, 
	is completely filled, and all nodes in the last level are as far left as possible.
	 It can have between 1 and 2h nodes inclusive at the last level h.
 */

public class P222_CountCompleteTreeNodes {
	public static void main(String[] args) {
		
	}
	static class Solution {
		int answer = 0;
	    public int countNodes(TreeNode root) {
	    	if (null == root) {
	    		return 0;
	    	}
	        int lengthOfleft = 0;
	        int lengthOfRight = 0;
	        TreeNode leftRoot = root;
	        TreeNode rightRoot = root;
	        ArrayList<TreeNode> leftEdge = new ArrayList<>();
	        ArrayList<TreeNode> rightEdge = new ArrayList<>();
	        boolean isLeftRootRightExist = true;
	        boolean isRightRootLeftExist = true;
 	        while (leftRoot != null) {
	        	leftEdge.add(leftRoot);
	        	isLeftRootRightExist = leftRoot.right != null;
	        	leftRoot = leftRoot.left;
	        	lengthOfleft ++;
	        }
 	        if (! isLeftRootRightExist) {
 	        	return 1 << lengthOfleft;
 	        }
	        while (rightRoot != null) {
	        	rightEdge.add(rightRoot);
	        	isRightRootLeftExist = rightRoot.left != null;
	        	rightRoot = rightRoot.right;
	        	lengthOfRight ++;
	        }
	        if (isRightRootLeftExist) {
	        	return (1 << lengthOfleft) - 2;
	        }
	        if (lengthOfleft == lengthOfRight) {
	        	return ( 1 << lengthOfleft ) - 1;
	        }
	        int baseCount = ( 1 << lengthOfRight) - 1;
	        if (rightDepth(rightEdge.get(1)) == lengthOfleft - 1) {
	        	//搜索右边
	        	searchRight(baseCount + (1 << (lengthOfleft - 2)), rightEdge, 1, rightEdge.size() - 1, lengthOfleft);
	        } else {
	        	//搜索左边
	        }
	        return answer;
	    }
	    private void searchRight(int baseCount, ArrayList<TreeNode> rightEdge, int startIndex, int endIndex, int lengthOfleft) {
	    	if (answer != 0) {
	    		return;
	    	}
	    	int midIndex = (startIndex + endIndex) / 2;
	    	int lengthOfLeftDepth = leftDepth(rightEdge.get(midIndex));
	    	if (lengthOfLeftDepth < 0) {
	    		answer = baseCount + (1 << (lengthOfleft - startIndex - 1)) - (1 << (lengthOfleft - midIndex - 1))  + 1;
	    		return;
	    	}
	    	if (midIndex + lengthOfLeftDepth == lengthOfleft) {
	    		searchRight(baseCount, rightEdge, midIndex + 1, endIndex, lengthOfLeftDepth);
	    	} else {
	    		searchRight(baseCount, rightEdge, startIndex, midIndex, lengthOfLeftDepth);
	    	}
	    	
		}
		public int leftDepth(TreeNode root) {
	    	TreeNode leftRoot = root;
	    	int depth = 0;
	    	boolean isRightExists = true;
	    	while (leftRoot != null) {
	    		isRightExists = leftRoot.right != null;
	    		leftRoot = leftRoot.left;
	    		depth ++;
	    	}
	    	if (! isRightExists) {
	    		return -depth;
	    	}
	    	return depth;
	    }
	    public int rightDepth(TreeNode root) {
	    	TreeNode rightRoot = root;
	    	int depth = 0;
	    	boolean isLeftExists = true;
	    	while (rightRoot != null) {
	    		isLeftExists = rightRoot.left != null;
	    		rightRoot = rightRoot.right;
	    		depth ++;
	    	}
	    	if (isLeftExists) {
	    		return -depth;
	    	}
	    	return depth;
	    }
	}
}
