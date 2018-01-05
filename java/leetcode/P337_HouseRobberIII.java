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
 * @details     Solution4: AC  3ms 45.04% *
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
	static class Solution2 {
	    public int rob(TreeNode root) {
	        if (root == null) return 0;
	        int val = 0;
	        if (root.left != null) {
	            val += rob(root.left.left) + rob(root.left.right);
	        }
	        if (root.right != null) {
	            val += rob(root.right.left) + rob(root.right.right);
	        }
	        return Math.max(val + root.val, rob(root.left) + rob(root.right));
	    }
	}
	static class Solution3 {
	    public int rob(TreeNode root) {
	        return robSub(root, new HashMap<TreeNode, Integer>());
	    }
	    private int robSub(TreeNode root, Map<TreeNode, Integer> map) {
	        if (root == null) return 0;
	        if (map.containsKey(root)) return map.get(root);
	        int val = 0;
	        if (root.left != null) {
	            val += robSub(root.left.left, map) + robSub(root.left.right, map);
	        }
	        
	        if (root.right != null) {
	            val += robSub(root.right.left, map) + robSub(root.right.right, map);
	        }
	        val = Math.max(val + root.val, robSub(root.left, map) + robSub(root.right, map));
	        map.put(root, val);
	        return val;
	    }
	}
	static class Solution4 {
	    public int rob(TreeNode root) {
	        int[] res = robSub(root);
	        return Math.max(res[0], res[1]);
	    }

	    private int[] robSub(TreeNode root) {
	        if (root == null) return new int[2];
	        
	        int[] left = robSub(root.left);
	        int[] right = robSub(root.right);
	        int[] res = new int[2];

	        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
	        res[1] = root.val + left[0] + right[0];
	        
	        return res;
	    }
	}
}
