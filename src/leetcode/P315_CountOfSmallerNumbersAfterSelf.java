package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
 * @details     Solution2: TLE
 * @details     Solution3: AC 12ms 60.82%
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
	static class Solution2 {
	    public List<Integer> countSmaller(int[] numArray) {
	        List<Integer> answerList = new ArrayList<Integer>(numArray.length);
	        if (numArray == null || numArray.length < 1) return answerList;
	        answerList.add(0);
	        int smallerCount = 0;
	        for (int numArrayIndex = numArray.length - 2; numArrayIndex > -1; numArrayIndex --) {
	            smallerCount = 0;
	            for (int numArrayLeftIndex = numArrayIndex + 1; numArrayLeftIndex < numArray.length; numArrayLeftIndex ++) {
	                if (numArray[numArrayLeftIndex] < numArray[numArrayIndex]) {
	                    smallerCount ++;
	                } else if (numArray[numArrayLeftIndex] == numArray[numArrayIndex]) {
	                    if (smallerCount == 0) {
    	                    answerList.add(0, answerList.get(numArrayLeftIndex - numArrayIndex - 1));
    	                    break;
	                    }
	                }
	            }
	            if (answerList.size() < numArray.length - numArrayIndex)
	                answerList.add(0, smallerCount);
	        }
	        return answerList;
	    }
	}
	static class Solution3 {
	    class NewTreeNode {
	        public NewTreeNode left, right;
	        public int val, sum, dup = 1;
	        public NewTreeNode(int value) {
	            this.val = value;
	        }
	    }
	    public List<Integer> countSmaller(int[] numArray) {
	        LinkedList<Integer> answerList = new LinkedList<Integer>();
	        NewTreeNode rootNode = null;
	        for (int numArrayIndex = numArray.length - 1; numArrayIndex > -1; numArrayIndex --) {
	            rootNode = countSmallerInternal(numArray[numArrayIndex], rootNode, 0, answerList);
	        }
	        return answerList;
	    }
        private NewTreeNode countSmallerInternal(int num, NewTreeNode rootNode,  int preSum, LinkedList<Integer> answerList) {
            if (rootNode == null) {
                rootNode = new NewTreeNode(num);
                answerList.addFirst(preSum);
            } else if (num == rootNode.val) {
                rootNode.dup ++;
                answerList.addFirst(preSum + rootNode.sum);
            } else if (num > rootNode.val) {
                rootNode.right = countSmallerInternal(num, rootNode.right, preSum + rootNode.sum + rootNode.dup, answerList);
            } else if (num < rootNode.val) {
                rootNode.sum ++;
                rootNode.left = countSmallerInternal(num, rootNode.left, preSum, answerList);
            }
            return rootNode;
        }
	}
}
