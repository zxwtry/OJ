package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
 * @details     Solution4: AC 60ms 18.45% 
 * @details     Solution5: 会WA，不知道为什么
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
	static class Solution4 {
	    public List<Integer> countSmaller(int[] nums) {
	        LinkedList<Integer> ans = new LinkedList<Integer>();
	        List<Integer> sorted = new ArrayList<Integer>();
	        for (int i = nums.length - 1; i >= 0; i--) {
	            int index = findIndex(sorted, nums[i]);
	            ans.addFirst(index);
	            sorted.add(index, nums[i]);
	        }
	        return ans;
	    }
	    private int findIndex(List<Integer> sorted, int target) {
	        if (sorted.size() == 0) return 0;
	        int start = 0;
	        int end = sorted.size() - 1;
	        if (sorted.get(end) < target) return end + 1;
	        if (sorted.get(start) >= target) return 0;
	        while (start + 1 < end) {
	            int mid = start + (end - start) / 2;
	            if (sorted.get(mid) < target) {
	                start = mid + 1;
	            } else {
	                end = mid;
	            }
	        }
	        if (sorted.get(start) >= target) return start;
	        return end;
	    }
	}
	static class Solution5 {
        public List<Integer> countSmaller(int[] nums) {
            LinkedList<Integer> ans = new LinkedList<Integer>();
            List<Integer> sorted = new ArrayList<Integer>();
            Comparator<Integer> myComparator = new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    long cut = (long)o1 - o2;
                    if (cut > 0) return 1;
                    return cut == 0 ? 0 : -1;
                }
            };
            for (int i = nums.length - 1; i >= 0; i--) {
                int anotherIndex = Collections.binarySearch(sorted, nums[i], myComparator);
                while (anotherIndex > 0 && sorted.get(anotherIndex - 1) == sorted.get(anotherIndex))
                    anotherIndex --;
                anotherIndex = anotherIndex < 0 ? -(anotherIndex + 1) : anotherIndex;
                ans.addFirst(anotherIndex);
                sorted.add(anotherIndex, nums[i]);
            }
            return ans;
        }
    }
}
