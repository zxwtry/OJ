package leetcode;

import java.util.Arrays;
import java.util.List;

/**
 *  You are given an integer array nums and you have to return 
 *  a new counts array. The counts array has the property where counts[i] 
 *  is the number of smaller elements to the right of nums[i].
 * 	
 * 	Example:
 * 	
 * 	Given nums = [5, 2, 6, 1]
 * 	
 * 	To the right of 5 there are 2 smaller elements (2 and 1).
 * 	To the right of 2 there is only 1 smaller element (1).
 * 	To the right of 6 there is 1 smaller element (1).
 * 	To the right of 1 there is 0 smaller element.
 * 	Return the array [2, 1, 1, 0].
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P315_CountOfSmallerNumbersAfterSelf.java
 * @type        P315_CountOfSmallerNumbersAfterSelf
 * @date        2016年12月29日 下午10:25:51
 * @details     Solution1: AC 15ms 47.89%
 */
public class P315_CountOfSmallerNumbersAfterSelf {
	static class Solution1 {
	    class Node {
	        Node left, right;
	        int val, sum, dup = 1;
	        public Node(int v, int s) {
	            val = v;
	            sum = s;
	        }
	    }
	    public List<Integer> countSmaller(int[] nums) {
	        Integer[] ans = new Integer[nums.length];
	        Node root = null;
	        for (int i = nums.length - 1; i >= 0; i--) {
	            root = insert(nums[i], root, ans, i, 0);
	        }
	        return Arrays.asList(ans);
	    }
	    private Node insert(int num, Node node, Integer[] ans, int i, int preSum) {
	        if (node == null) {
	            node = new Node(num, 0);
	            ans[i] = preSum;
	        } else if (node.val == num) {
	            node.dup++;
	            ans[i] = preSum + node.sum;
	        } else if (node.val > num) {
	            node.sum++;
	            node.left = insert(num, node.left, ans, i, preSum);
	        } else {
	            node.right = insert(num, node.right, ans, i, preSum + node.dup + node.sum);
	        }
	        return node;
	    }
	}
}
