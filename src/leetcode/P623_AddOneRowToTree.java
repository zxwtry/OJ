package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;

import tools.TreeNode辅助.TreeNode;

/**

Given the root of a binary tree, then value v and depth d, you need to add 
a row of nodes with value v at the given depth d. The root node is at depth 1.

The adding rule is: given a positive integer depth d, for each NOT null 
tree nodes N in depth d-1, create two tree nodes with value v as N's left subtree root 
and right subtree root. And N's original left subtree should be the left subtree 
of the new left subtree root, its original right subtree should be the right subtree 
of the new right subtree root. If depth d is 1 that means there is no depth d-1 at all,
 then create a tree node with value v as the new root of the whole original tree, 
 and the original tree is the new root's left subtree.

Example 1:
Input: 
A binary tree as following:
       4
     /   \
    2     6
   / \   / 
  3   1 5   

v = 1

d = 2

Output: 
       4
      / \
     1   1
    /     \
   2       6
  / \     / 
 3   1   5   

Example 2:
Input: 
A binary tree as following:
      4
     /   
    2    
   / \   
  3   1    

v = 1

d = 3

Output: 
      4
     /   
    2
   / \    
  1   1
 /     \  
3       1
Note:
The given d is in range [1, maximum depth of the given tree + 1].
The given binary tree has at least one tree node.

 */


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P623_AddOneRowToTree.java
 * @date        2017年6月18日 上午9:56:57
 * @details     AC
 */
public class P623_AddOneRowToTree {
    static public class Solution {
        public TreeNode addOneRow(TreeNode root, int v, int d) {
            if (d == 1) {
                TreeNode newRoot = new TreeNode(v);
                newRoot.left = root;
                return newRoot;
            }
            int layerCount = 2;
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            ArrayList<TreeNode> vTreeNodeList = new ArrayList<>();
            while (! queue.isEmpty()) {
                int queueSize = queue.size();
                for (int queueIndex = 0; queueIndex < queueSize; queueIndex ++) {
                    TreeNode nodeNow = queue.poll();
                    if (layerCount == d) {
                        queue.add(nodeNow.left);
                        queue.add(nodeNow.right);
                        nodeNow.left = new TreeNode(v);
                        vTreeNodeList.add(nodeNow.left);
                        nodeNow.right = new TreeNode(v);
                        vTreeNodeList.add(nodeNow.right);
                    } else {
                        if (nodeNow.left != null) {
                            queue.add(nodeNow.left);
                        }
                        if (nodeNow.right != null) {
                            queue.add(nodeNow.right);
                        }
                    }
                }
                if (layerCount == d) {
                    break;
                }
                layerCount ++;
            }
            for (int vTreeNodeIndex = 0, vTreeNodeLength = vTreeNodeList.size(); vTreeNodeIndex < 
                    vTreeNodeLength; vTreeNodeIndex ++) {
                TreeNode nodeNow = queue.poll();
                if (vTreeNodeIndex % 2 == 0) {
                    vTreeNodeList.get(vTreeNodeIndex).left = nodeNow;
                } else {
                    vTreeNodeList.get(vTreeNodeIndex).right = nodeNow;
                }
            }
            return root;
        }
    }
}
