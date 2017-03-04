package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import tools.TreeNode辅助.TreeNode;

/**
 * 	The thief has found himself a new place for his thievery again. There is only 
 *  one entrance to this area, called the "root." Besides the root, each house has 
 *  one and only one parent house. After a tour, the smart thief realized that 
 *  "all houses in this place forms a binary tree". It will automatically contact
 *  the police if two directly-linked houses were broken into on the same night.
 * 	
 * 	Determine the maximum amount of money the thief can rob tonight without 
 *  alerting the police.
 * 	
 * 	Example 1:
 * 	     3
 * 	    / \
 * 	   2   3
 * 	    \   \ 
 * 	     3   1
 * 	Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * 	Example 2:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \ 
 * 	 1   3   1
 * 	Maximum amount of money the thief can rob = 4 + 5 = 9.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P337_HouseRobberIII.java
 * @type        P337_HouseRobberIII
 * @date        2017年2月2日 下午9:12:11
 * @details     Solution1: WA [3,2,3,null,3,null,1] *
 * @details     Solution2: TLE *
 * @details     Solution3: AC 11ms 26.93% *
 * @details     Solution3: AC  3ms 45.04% *
 */
public class P337_HouseRobberIII {
	static class Solution1 {
	    public int rob(TreeNode root) {
	        if (root == null) return 0;
	        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
	        TreeNode rootNow = null;
	        queue.addFirst(root);
	        queue.addFirst(null);
	        int index = 0;
	        int[] sum = new int[] {0, 0};
	        while (true) {
	            rootNow = queue.pollLast();
	            if (rootNow == null) {
	                if (queue.isEmpty()) break;
	                index = index == 0 ? 1 : 0;
	                queue.addFirst(null);
	            } else {
	                sum[index] += rootNow.val;
	                if (rootNow.left != null)
	                    queue.addFirst(rootNow.left);
	                if (rootNow.right != null)
	                    queue.addFirst(rootNow.right);
	            }
	        }
	        return Math.max(sum[0], sum[1]);
	    }
	}
}
