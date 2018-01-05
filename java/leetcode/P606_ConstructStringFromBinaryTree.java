package leetcode;

import tools.TreeNode辅助.TreeNode;

/**

You need to construct a string consists of parenthesis and integers 
from a binary tree with the preorder traversing way.

The null node needs to be represented by empty parenthesis pair "()". 
And you need to omit all the empty parenthesis pairs that don't affect 
the one-to-one mapping relationship between the string and the original binary tree.

Example 1:
Input: Binary tree: [1,2,3,4]
       1
     /   \
    2     3
   /    
  4     

Output: "1(2(4))(3)"

Explanation: Originallay it needs to be "1(2(4)())(3()())", 
but you need to omit all the unnecessary empty parenthesis pairs. 
And it will be "1(2(4))(3)".
Example 2:
Input: Binary tree: [1,2,3,null,4]
       1
     /   \
    2     3
     \  
      4 

Output: "1(2()(4))(3)"

Explanation: Almost the same as the first example, 
except we can't omit the first parenthesis pair to break 
the one-to-one mapping relationship between the input and the output.

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P606_ConstructStringFromBinaryTree.java
 * @date        2017年6月4日 上午10:22:35
 * @details     Solution: AC
 */
public class P606_ConstructStringFromBinaryTree {
    static public class Solution {
        StringBuilder st = new StringBuilder();
        TreeNode head = null;
        public String tree2str(TreeNode t) {
            if (t == null) return "";
            head = t;
            search(t);
            return st.toString();
        }
        void search(TreeNode t) {
            if (t != head) st.append('(');
            st.append(t.val);
            if (t.left == null || t.right == null) { 
                if (t.left == null && t.right == null) {
                    
                } else if (t.left == null) {
                    st.append("()");
                    search(t.right);
                } else {
                    search(t.left);
                }
            } else {
                search(t.left);
                search(t.right);
            }
            if (t != head) st.append(')');
        }
    }
}
