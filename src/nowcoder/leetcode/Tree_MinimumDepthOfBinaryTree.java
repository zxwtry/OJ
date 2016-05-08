package nowcoder.leetcode;

/*
 * 	Given a binary tree, find its minimum depth.
 * 	The minimum depth is the number of nodes along 
 * 	the shortest path from the root node down to 
 * 	the nearest leaf node.
 */

public class Tree_MinimumDepthOfBinaryTree {
	static int depth = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		TreeNode node1 = new TreeNode();
		TreeNode node2 = new TreeNode();
		TreeNode node3 = new TreeNode();
		TreeNode node4 = new TreeNode();
		TreeNode node5 = new TreeNode();
		TreeNode node6 = new TreeNode();
		TreeNode node7 = new TreeNode();
		TreeNode node8 = new TreeNode();
		TreeNode node9 = new TreeNode();
		TreeNode node10 = new TreeNode();
		node1.left = node2;
		node2.left = node3;
		node3.left = node4;
		node4.left = node5;
		node5.left = node6;
		node6.left = node7;
		node7.left = node8;
		node8.left = node9;
		
		node1.right = node10;
		
		System.out.println(run(node1));
		
	}
	
    public static int run(TreeNode root) {
    	dfs(root , 1);
    	return depth;
    }
    
    private static void dfs(TreeNode root, int i) {
    	if (root.left == null && root.right == null) {
    		depth = depth > i ? i : depth;
    		return;
    	}
    	if (null != root.left)
    		dfs(root.left, i + 1);
    	if (null != root.right)
    		dfs(root.right, i + 1);
	}

	static class TreeNode {
    	TreeNode left, right;
    }
}
