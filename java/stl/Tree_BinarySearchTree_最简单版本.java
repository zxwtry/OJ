package stl;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     stl
 * @file        Tree_BinarySearchTree_最简单版本.java
 * @type        Tree_BinarySearchTree_最简单版本
 * @date        2017年2月18日 下午7:35:03
 * @details     
 */
public class Tree_BinarySearchTree_最简单版本 {
    public static TreeNode A_从数组中生成BST(int[] numArr) {
        TreeNode rootNode = null;
        for (int num : numArr)
            rootNode = A_从数组中生成BST_insert(num, rootNode);
        return rootNode;
    }
    private static TreeNode A_从数组中生成BST_insert(int num, TreeNode rootNode) {
        if (rootNode == null) {
            rootNode = new TreeNode(num);
        } else if (num < rootNode.val) {
            rootNode.left = A_从数组中生成BST_insert(num, rootNode.left);
        } else {
            rootNode.right = A_从数组中生成BST_insert(num, rootNode.right);
        }
        return rootNode;
    }
}
