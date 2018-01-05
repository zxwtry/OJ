package nowcoder.com;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        其它_二叉树的序列化.java
 * @date        2017年7月21日 下午10:18:43
 * @details     
 */
public class 其它_二叉树的序列化 {
    static public class TreeToSequence {
        public String toSequence(TreeNode root) {
            return in(root);
        }
        private String in(TreeNode root) {
            if (root == null) return "";
            return in(root.left) + root.val + in(root.right);
        }
        
    }
}
