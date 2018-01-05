package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import tools.TreeNode辅助.TreeNode;

/**
 * 	Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
	
	According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined
	 between two nodes v and w as the lowest node in T that has both v and w as descendants 
	 (where we allow a node to be a descendant of itself).”
	
	        _______3______
	       /              \
	    ___5__          ___1__
	   /      \        /      \
	   6      _2       0       8
	         /  \
	         7   4
	For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example 
	is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to 
	the LCA definition.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P236_LowestCommonAncestorofaBinaryTree.java
 * @type        P236_LowestCommonAncestorofaBinaryTree
 * @date        2016年12月10日 下午10:21:43
 * @details     Solution1: AC 21ms 11.25% 
 * @details     Solution2: AC 19ms 13.92%
 */
public class P236_LowestCommonAncestorofaBinaryTree {
	static class Solution1 {
	    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	    	if (root == null || p == null || q == null) return null;
	    	if (p == q) return p;
	    	LinkedList<TreeNode> qN = new LinkedList<TreeNode>();
	    	LinkedList<Integer> qI = new LinkedList<Integer>();
	    	HashMap<TreeNode, TreeNode> parentMap = new HashMap<TreeNode, TreeNode>();
	    	qN.add(root);
	    	qI.add(1);
	    	int pLN = p == root ? 1 : -1;
	    	int qLN = q == root ? 1 : -1;
	    	TreeNode rootNow = null;
	    	int layerNow = 0;
	    	while (! qN.isEmpty()) {
	    		rootNow = qN.poll();
	    		layerNow = qI.poll();
	    		if (rootNow.left != null) {
	    			parentMap.put(rootNow.left, rootNow);
	    			qN.add(rootNow.left);
	    			qI.add(layerNow + 1);
	    			if (rootNow.left == p) pLN = layerNow + 1;
	    			if (rootNow.left == q) qLN = layerNow + 1;
	    		}
	    		if (rootNow.right != null) {
	    			parentMap.put(rootNow.right, rootNow);
	    			qN.add(rootNow.right);
	    			qI.add(layerNow + 1);
	    			if (rootNow.right == p) pLN = layerNow + 1;
	    			if (rootNow.right == q) qLN = layerNow + 1;
	    		}
	    		if (pLN != -1 && qLN != -1) break;
	    	}
	    	if (pLN > qLN) {
	    		TreeNode pG = p;
	    		for (int i = pLN - qLN; i > 0; i --)
	    			pG = parentMap.get(pG);
	    		p = pG;
	    	} else if (pLN < qLN) {
	    		TreeNode qG = q;
	    		for (int i = qLN - pLN; i > 0; i --)
	    			qG = parentMap.get(qG);
	    		q = qG;
	    	}
	    	while (p != q) {
	    		p = parentMap.get(p);
	    		q = parentMap.get(q);
	    	}
	    	return p;
	    }
	}
	static class Solution2 {
	    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	        if(root==null || p==null || q==null) return null;
	        List<TreeNode> pathp = new ArrayList<>();
	        List<TreeNode> pathq = new ArrayList<>();
	        pathp.add(root);
	        pathq.add(root);
	        getPath(root, p, pathp);
	        getPath(root, q, pathq);
	        TreeNode lca = null;
	        for(int i=0; i<pathp.size() && i<pathq.size(); i++) {
	            if(pathp.get(i) == pathq.get(i)) lca = pathp.get(i);
	            else break;
	        }
	        return lca;  
	    }
	    private boolean getPath(TreeNode root, TreeNode n, List<TreeNode> path) {
	        if(root==n) {
	            return true;  
	        }
	        if(root.left!=null) {
	            path.add(root.left);
	            if(getPath(root.left, n, path)) return true;
	            path.remove(path.size()-1);
	        }
	        if(root.right!=null) {
	            path.add(root.right);
	            if(getPath(root.right, n, path)) return true;
	            path.remove(path.size()-1);
	        }
	        return false;
	    }
	}
}
