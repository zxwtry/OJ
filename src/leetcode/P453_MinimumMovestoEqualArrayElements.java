package leetcode;

/*
 * 	Given a non-empty integer array of size n, find the minimum number of 
 * 	moves required to make all array elements equal, 
 * 	where a move is incrementing n - 1 elements by 1.

	Example:
	
	Input:
	[1,2,3]
	
	Output:
	3
	
	Explanation:
	Only three moves are needed (remember each move increments two elements):
	
	[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 */

public class P453_MinimumMovestoEqualArrayElements {
	public static void main(String[] args) {
		debugSolution();
	}
	static void debugSolution() {
		int[] nums = new int[] {1, 2, 3};
		Solution s = new Solution();
		System.out.println(s.minMoves(nums));
	}
	/*
	 * 	AC
	 * 	13 ms
	 * 	52.55%
	 */
	static class Solution {
	    public int minMoves(int[] nums) {
	    	if (nums == null || nums.length < 2) {
	    		return 0;
	    	}
	    	int min = Integer.MAX_VALUE;
	    	long sum = 0;
	    	for (int val : nums) {
	    		min = Math.min(min, val);
	    		sum += val;
	    	}
	    	sum -= nums.length * min;
	        return (int) sum;
	    }
	}
}
