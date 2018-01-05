package leetcode;

/*
 * 	Write an efficient algorithm that searches for a value 
 * 	in an m x n matrix. This matrix has the following properties:

	Integers in each row are sorted from left to right.
	The first integer of each row is greater than the last integer of
	 the previous row.
	For example,
	
	Consider the following matrix:
	
	[
	  [1,   3,  5,  7],
	  [10, 11, 16, 20],
	  [23, 30, 34, 50]
	]
	Given target = 3, return true.
 */

public class P074_SearchA2DMatrix {
	public static void main(String[] args) {
		System.out.println(new Solution().searchMatrix(new int[][] {
			{1,   3,  5,  7},
			{10, 11, 16, 20},
			{23, 30, 34, 50},
		}, 50));
	}
	/*
	 * 	一次AC
	 * 	1 ms
	 * 	 还是需要注意二分时候的判断条件
	 */
	static class Solution {
	    public boolean searchMatrix(int[][] matrix, int target) {
	    	int cow = matrix.length, col = matrix[0].length;
	    	int sti = 0, eni = cow * col - 1;
	    	while (sti <= eni) {
	    		int mid = ( sti + eni ) >> 1;
	    		int coi = mid / col, coj = mid - coi * col;
	    		if (matrix[coi][coj] == target)
	    			return true;
	    		else if (matrix[coi][coj] > target)
	    			eni = mid - 1;
	    		else
	    			sti = mid + 1;
	    	}
	        return false;
	    }
	}
}
