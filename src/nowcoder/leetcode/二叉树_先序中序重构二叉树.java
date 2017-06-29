package nowcoder.leetcode;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        二叉树_先序中序重构二叉树.java
 * @date        2017年6月29日 下午9:28:04
 * @details     剑指offer
 */
public class 二叉树_先序中序重构二叉树 {
    public class Solution {
        public TreeNode reConstructBinaryTree(int [] p,int [] i) {
            int pl = p == null ? 0 : p.length;
            int il = i == null ? 0 : i.length;
            if (pl == 0 || il == 0 || pl != il) return null;
            return con(p, 0, pl-1, i, 0, il-1);
        }
        TreeNode con(int[] p, int pi, int pj, int[] i, int ii, int ij) {
            if (pi > pj) return null;
            int nv = p[pi];
            TreeNode n = new TreeNode(nv);
            int is = 0;
            for (int ik = ii; ik <= ij; ik ++) {
                if (i[ik] == nv) {
                    is = ik;
                    break;
                }
            }
            int ik = is;
            n.left = con(p, pi+1, pi + 1 + ik - 1 - ii, i, ii, ik - 1);
            n.right = con(p, pi + 1 + ik - 1 - ii + 1, pj, i, ik+1, ij);
            return n;
        }
    }
}
