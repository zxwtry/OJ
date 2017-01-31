package leetcode;

/**
 * 	Given a non-empty array of integers, return the third maximum number in this array. 
 * 	If it does not exist, return the maximum number. The time complexity must be in O(n).
 *	
 *	Example 1:
 *	Input: [3, 2, 1]
 *	
 *	Output: 1
 *	
 *	Explanation: The third maximum is 1.
 *	Example 2:
 *	Input: [1, 2]
 *	
 *	Output: 2
 *	
 *	Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
 *	Example 3:
 *	Input: [2, 2, 3, 1]
 *	
 *	Output: 1
 *	
 *	Explanation: Note that the third maximum here means the third maximum distinct number.
 *	Both numbers with value 2 are both considered as second maximum.	
 */

public class P414_ThirdMaximumNumber {
	public static void main(String[] args) {
		int[] nums = tools.Random随机生成器.A_生成一个随机数据(10, 0, 100);
		tools.Utils.printArray(nums, 100);
		Solution s = new Solution();
		System.out.println(s.thirdMax(nums));
	}
	/*
	 * 	Ac
	 * 	6 ms
	 * 	33.93%
	 */
	static class Solution {
		final int K = 3;
	    public int thirdMax(int[] nums) {
	        int[] rank = new int[K];
	        int rankReachIndex = -1;
	        for (int val : nums) {
	        	int i = 0;
	        	boolean isHasSame = false;
	        	for (; ! isHasSame && i <= rankReachIndex; i ++) {
	        		if (val == rank[i]) {
	        			isHasSame = true;
	        		} else if (val > rank[i]) {
	        			break;
	        		}
	        	}
	        	if (! isHasSame) {
		        	if (i == rankReachIndex + 1) {
		        		if (rankReachIndex < K - 1) {
		        			rank[++ rankReachIndex] = val;
		        		}
		        	} else {
		        		int save = 0;
		        		if (rankReachIndex < K - 1) {
		        			save = rank[rankReachIndex];
		        		}
		        		for (int index = rankReachIndex - 1; index >= i; index --) {
		        			rank[index + 1] = rank[index];
		        		}
		        		if (rankReachIndex < K - 1) {
		        			rank[++ rankReachIndex] = save;
		        		}
		        		rank[i] = val;
		        	}
	        	}
//	        	System.out.println("目前的val是" + val);
//	        	tools.Utils.printArray(rank, K);
	        }
	        if (rankReachIndex == K - 1) {
	        	return rank[K - 1];
	        } else {
	        	return rank[0];
	        }
	    }
	}
}
