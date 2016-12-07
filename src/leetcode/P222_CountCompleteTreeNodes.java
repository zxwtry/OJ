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
		testSolutio1();
	}
	static void testSolutio1() {
		int N = Integer.MIN_VALUE;
		TreeNode root = tools.TreeNode辅助.A_生成满二叉树(new int[] {
			0, 
			1, 2,
			3, 4, 5, 6,
//			7, 8, 9, 10, 11, 12, 13, 14
			7, 8, 9, 10, 11, 12, 13, N
		});
		Solution1 s = new Solution1();
		System.out.println(s.countNodes(root));
		syso
	}
	/*
	 *  是一个完全二叉树，可能是满二叉树
	 *  看意思，就是从二叉树斜着下去，进行二分
	 */
	static class Solution1 {
		public int countNodes(TreeNode root) {
			int ans = 0;
			ArrayList<TreeNode> leftEdge = new ArrayList<>();
			int lenOfLeft = 0;
			TreeNode left = root;
			while (left != null) {
				leftEdge.add(left);
				left = left.left;
				lenOfLeft ++;
			}
			int lenOfRight = calcRight(leftEdge, 0);
			if (lenOfLeft == lenOfRight) {
				return (1 << lenOfLeft) - 1;
			}
			int min = 0;
			int max = leftEdge.size() - 1;
			int sti = max;
			int eni = min;
			while (sti > eni && sti >= min && eni <= max) {
				int mid = (sti + eni) / 2;
				int lenOfMid = calcRight(leftEdge, mid);
				System.out.printf("sti : %d \t eni : %d \t mid : %d \r\n", sti, eni, mid);
				System.out.printf("lenOfLeft : %d \t lenOfRight : %d \t lenOfMid : %d \r\n", lenOfLeft, lenOfRight, lenOfMid);
				if (lenOfLeft == lenOfMid) {
					eni = mid - 1;
				} else {
					sti = mid;
				}
			}
			System.out.printf("sti : %d \t eni : %d \r\n", sti, eni);
			return ans;
		}
		private int calcRight(ArrayList<TreeNode> leftEdge, int i) {
			TreeNode right = leftEdge.get(i);
			int lenOfRight = i;
			while (right != null) {
				right = right.right;
				lenOfRight ++;
			}
			return lenOfRight;
		}
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
