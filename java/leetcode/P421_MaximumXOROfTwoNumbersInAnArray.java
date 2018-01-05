package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 	Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
 *	
 *	Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 *	
 *	Could you do this in O(n) runtime?
 *	
 *	Example:
 *	
 *	Input: [3, 10, 5, 25, 2, 8]
 *	
 *	Output: 28
 *	
 *	Explanation: The maximum result is 5 ^ 25 = 28.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P421_MaximumXOROfTwoNumbersInAnArray.java
 * @type        P421_MaximumXOROfTwoNumbersInAnArray
 * @date        2017年3月6日 下午8:41:14
 * @details     Solution1: TLE
 * @details     Solution3: TLE
 * @details     Solution4: AC 122ms 16.18%
 */
public class P421_MaximumXOROfTwoNumbersInAnArray {
	static class Solution1 {
	    public int findMaximumXOR(int[] nums) {
	        if (nums == null || nums.length < 2) return 0;
	        int max = Integer.MIN_VALUE;
	        for (int i = 0; i < nums.length; i ++)
	            for (int j = 0; j < i; j ++)
	                max = Math.max(max, nums[i] ^ nums[j]);
	        return max;
	    }
	}
	static class Solution3 {
	    public int findMaximumXOR(int[] nums) {
	        int max = 0, mask = 0;
            Set<Integer> set = new HashSet<>();
	        for(int i = 31; i >= 0; i --){
	            mask = mask | (1 << i);
	            set.clear();
	            for(int num : nums){
	                set.add(num & mask);
	            }
	            int tmp = max | (1 << i);
	            for(int prefix : set){
	                if(set.contains(tmp ^ prefix)) {
	                    max = tmp;
	                    break;
	                }
	            }
	        }
	        return max;
	    }
	}
	static class Trie {
        Trie[] children;
        public Trie() {
            children = new Trie[2];
        }
    }
	static class Solution4 {
	    public int findMaximumXOR(int[] nums) {
    	    if(nums == null || nums.length == 0) {
                return 0;
            }
            Trie root = new Trie();
            for(int num: nums) {
                Trie curNode = root;
                for(int i = 31; i >= 0; i --) {
                    int curBit = (num >>> i) & 1;
                    if(curNode.children[curBit] == null) {
                        curNode.children[curBit] = new Trie();
                    }
                    curNode = curNode.children[curBit];
                }
            }
            int max = Integer.MIN_VALUE;
            for(int num: nums) {
                Trie curNode = root;
                int curSum = 0;
                for(int i = 31; i >= 0; i --) {
                    int curBit = (num >>> i) & 1;
                    if(curNode.children[curBit ^ 1] != null) {
                        curSum += (1 << i);
                        curNode = curNode.children[curBit ^ 1];
                    }else {
                        curNode = curNode.children[curBit];
                    }
                }
                max = Math.max(curSum, max);
            }
            return max;
        }
	}
}