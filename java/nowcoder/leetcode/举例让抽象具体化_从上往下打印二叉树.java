package nowcoder.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        举例让抽象具体化_从上往下打印二叉树.java
 * @date        2017年6月30日 下午3:39:42
 * @details     剑指Offer
 */
public class 举例让抽象具体化_从上往下打印二叉树 {
    public class Solution {
        public ArrayList<Integer> PrintFromTopToBottom(TreeNode n) {
            LinkedList<TreeNode> ll = new LinkedList<TreeNode>();
            ArrayList<Integer> ans = new ArrayList<Integer>();
            if (n != null) ll.addLast(n);
            while (ll.size() != 0) {
                int nn = ll.size();
                for (int i = 0 ; i < nn; i ++)
                {
                    TreeNode now = ll.pollFirst();
                    if (now.left != null) ll.addLast(now.left);
                    if (now.right != null) ll.addLast(now.right);
                    ans.add(now.val);
                }
            } 
            return ans;
        }
    }
}
