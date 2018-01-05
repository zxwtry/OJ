package leetcode;

import java.util.LinkedList;

import tools.TreeNode辅助.TreeNode;

/**
 * 	Serialization is the process of converting a data structure or object into
 *  a sequence of bits so that it can be stored in a file or memory buffer, or
 *  transmitted across a network connection link to be reconstructed later in
 *  the same or another computer environment.
 *  Design an algorithm to serialize and deserialize a binary tree. There is no
 *   restriction on how your serialization/deserialization algorithm should work.
 *  You just need to ensure that a binary tree can be serialized to a string and
 *   this string can be deserialized to the original tree structure.
 *  
 *  For example, you may serialize the following tree
 *  
 *      1
 *     / \
 *    2   3
 *       / \
 *      4   5
 *  as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes
 *   a binary tree. You do not necessarily need to follow this format, so 
 *   please be creative and come up with different approaches yourself.
 *  Note: Do not use class member/global/static variables to store states.
 *   Your serialize and deserialize algorithms should be stateless.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P297_SerializeAndDeserializeBinaryTree.java
 * @type        P297_SerializeAndDeserializeBinaryTree
 * @date        2016年12月24日 下午10:28:25
 * @details     Codc1: AC 37ms 16.95%
 */
public class P297_SerializeAndDeserializeBinaryTree {
	/**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */
	static class Codec1 {
	    // Encodes a tree to a single string.
	    public String serialize(TreeNode root) {
	    	if (root == null) return "";
	        StringBuilder st = new StringBuilder();
	        LinkedList<TreeNode> nodeQueue = new LinkedList<TreeNode>();
	        nodeQueue.add(root);
	        TreeNode rootNow = null;
	        int nodeIndex = 0;
	        st.append("0,"+root.val+"#");
	        while (! nodeQueue.isEmpty()) {
	        	rootNow = nodeQueue.pollFirst();
	        	if (rootNow.left != null) {
	        		st.append(-(nodeIndex + 1) + "," + rootNow.left.val + "#");
	        		nodeQueue.addLast(rootNow.left);
	        	}
	        	if (rootNow.right != null) {
	        		st.append((nodeIndex + 1) + "," + rootNow.right.val + "#");
	        		nodeQueue.addLast(rootNow.right);
	        	}
	        	nodeIndex ++;
	        }
	        st.deleteCharAt(st.length() - 1);
	        //System.out.println(nodeIndex+ "..." +st.toString().split("#").length);
	        return st.toString();
	    }
	    // Decodes your encoded data to tree.
	    public TreeNode deserialize(String data) {
	    	if (data == null || data.length() < 1) return null;
	    	String[] dataSplits = data.split("#");
	    	TreeNode[] nodes = new TreeNode[dataSplits.length];
	    	for (int index = 0; index < dataSplits.length; index ++) {
	    		String[] intSplits = dataSplits[index].split(",");
	    		int parentIndex = Integer.parseInt(intSplits[0]);
	    		int nodeValue = Integer.parseInt(intSplits[1]);
	    		nodes[index] = new TreeNode(nodeValue);
	    		if (parentIndex > 0) {
	    			nodes[parentIndex - 1].right = nodes[index];
	    		} else if (parentIndex < 0) {
	    			nodes[-parentIndex - 1].left = nodes[index];
	    		}
	    	}
	        return nodes[0];
	    }
	}
	// Your Codec object will be instantiated and called as such:
	// Codec codec = new Codec();
	// codec.deserialize(codec.serialize(root));
}
