package leetcode;

import java.util.Stack;

/**
 * 	One way to serialize a binary tree is to use pre-order traversal. 
 * 	When we encounter a non-null node, we record the node's value. 
 * 	If it is a null node, we record using a sentinel value such as #.
 *  	
 *       _9_
 *      /   \
 *     3     2
 *    / \   / \
 *   4   1  #  6
 *  / \ / \   / \
 *  # # # #   # #
 *  For example, the above binary tree can be serialized to the string 
 *  "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.
 *  
 *  Given a string of comma separated values, verify whether it is a 
 *  correct preorder traversal serialization of a binary tree. 
 *  Find an algorithm without reconstructing the tree.
 *  
 *  Each comma separated value in the string must be either an 
 *  integer or a character '#' representing null pointer.
 *  
 *  You may assume that the input format is always valid, for example 
 *  it could never contain two consecutive commas such as "1,,3".
 *  
 *  Example 1:
 *  "9,3,4,#,#,1,#,#,2,#,6,#,#"
 *  Return true
 *  
 *  Example 2:
 *  "1,#"
 *  Return false
 *  
 *  Example 3:
 *  "9,#,#,1"
 *  Return false
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P331_VerifyPreorderSerializationOfABinaryTree.java
 * @type        P331_VerifyPreorderSerializationOfABinaryTree
 * @date        2017年1月10日 下午10:05:22
 * @details     Solution1: AC  6ms 98.69% *
 * @details     Solution2: AC 11ms 60.26% *
 * @details     Solution3: AC 10ms 77.05% *
 * @details     Solution4: AC 20ms 20.43% *
 */
public class P331_VerifyPreorderSerializationOfABinaryTree {
	static class Solution1 {
	    public boolean isValidSerialization(String preorder) {
	        int diff = 1;
	        char c = '0';
	        int newIndex = 0;
	        for (int index = 0; index < preorder.length(); index ++) {
	            c = preorder.charAt(index);
	            if (c == ',') continue;
	            if (-- diff < 0) return false;
	            if (c != '#') {
	                newIndex = index;
	                while(newIndex + 1 < preorder.length() && isANum(preorder.charAt(newIndex + 1)))
	                    newIndex ++;
	                index = newIndex;
	                diff += 2;
	            }
	        }
	        return diff == 0;
	    }
	    private boolean isANum(char c) {
	        return c >= '0' && c <= '9';
	    }
	}
	static class Solution2 {
	    public boolean isValidSerialization(String preorder) {
	        String[] nodes = preorder.split(",");
	        int diff = 1;
	        for (String node: nodes) {
	            if (--diff < 0) return false;
	            if (!node.equals("#")) diff += 2;
	        }
	        return diff == 0;
	    }
	}
	static class Solution3 {
	    public boolean isValidSerialization(String preorder) {
	        String[] strs = preorder.split(",");
	        int degree = -1;         // root has no indegree, for compensate init with -1
	        for (String str: strs) {
	            degree++;             // all nodes have 1 indegree (root compensated)
	            if (degree > 0) {     // total degree should never exceeds 0
	                return false;
	            }      
	            if (!str.equals("#")) {// only non-leaf node has 2 outdegree
	                degree -= 2;
	            }  
	        }
	        return degree == 0;
	    }
	}
}
