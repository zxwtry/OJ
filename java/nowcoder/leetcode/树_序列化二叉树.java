package nowcoder.leetcode;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        树_序列化二叉树.java
 * @date        2017年7月4日 下午8:47:49
 * @details     剑指Offer
 */
public class 树_序列化二叉树 {
    
    static public class Solution {
        char UU = '#';
        char UN = '$';
        char NU = '^';
        char NN = '*';
        
        String Serialize(TreeNode root) {
            if (root == null) return "*";
            StringBuilder st = new StringBuilder();
            ser(root, st);
            return st.toString();
        }
        void ser(TreeNode n, StringBuilder st) {
            boolean l = n.left == null;
            boolean r = n.right == null;
            if (l == false && r == false) st.append(UU);
            if (l == false && r == true) st.append(UN);
            if (l == true && r == false) st.append(NU);
            if (l == true && r == true) st.append(NN);
            String vv = String.valueOf(n.val);
            st.append(vlen(vv.length()));
            st.append(vv);
            if (! l) {
                ser(n.left, st);
            }
            if (! r) {
                ser(n.right, st);
            }
        }
        int i = 0;
        TreeNode Deserialize(String str) {
            int len = str == null ? 0 : str.length();
            if (len == 1 || len == 0) return null;
            i = 0;
            return der(str, len);
        }
        TreeNode der(String s, int len) {
            if (i >= len) return null;
            char sign = s.charAt(i ++);
            boolean l = false, r = false;
            if (sign == UU) {
                l = false;
                r = false;
            } else if (sign == UN) {
                l = false;
                r = true;
            } else if (sign == NU) {
                l = true;
                r = false;
            } else {
                l = true;
                r = true;
            }
            int vl = len(s.charAt(i ++));
            int val = Integer.parseInt(s.substring(i, i + vl));
            i += vl;
            TreeNode now = new TreeNode(val);
            if (! l) {
                now.left = der(s, len);
            } 
            if (! r) {
                now.right = der(s, len);
            }
            return now;
        }
        int len(char c) {
            if (c >= '0' && c <= '9') {
                return c - '0';
            }
            return 10 + c - 'a';
        }
        char vlen(int l) {
            if (l >= 0 && l <= 9) {
                return (char)(l + '0');
            }
            return (char)(l - 10 + 'a');
        }
    }
}
