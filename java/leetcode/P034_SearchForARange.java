package leetcode;

/*
 * 	Given a sorted array of integers, find the starting and 
 * 	ending position of a given target value.

	Your algorithm's runtime complexity must be in the order of O(log n).

	If the target is not found in the array, return [-1, -1].

	For example,
	Given [5, 7, 7, 8, 8, 10] and target value 8,
	return [3, 4].
 */

public class P034_SearchForARange {
	public static void main(String[] args) {
		new Solution().searchRange(new int[] {4, 8, 15, 16, 17, 18}, 8);
	}
	/*
	 * 	1 ms
	 * 	7.00%
	 */
	static class Solution {
		public int[] searchRange(int[] nums, int target) {
			boolean isHas = false;
			for (int i = 0; !isHas && i != nums.length; i ++)
				isHas |= nums[i] == target;
			if (isHas)
				return new int[] {getIndexFirst(nums, 0, nums.length - 1, target),
							getIndexLast(nums, 0, nums.length - 1, target)};
			else 
				return new int[] {-1, -1};
		}
		/*
		 * 	第一个
		 */
		private int getIndexFirst(int[] nums, int sti, int eni, int target) {
			while (sti < eni) {
				int mid = (sti + eni) >> 1;
				if (nums[mid] >= target)
					eni = mid;
				else
					sti = mid + 1;
			}
			return sti;
		}
		private int getIndexLast(int[] nums, int sti, int eni, int target) {
			while (sti < eni) {
				int mid = sti + eni;
				mid = (mid & 0x1) == 1 ? (mid >> 1) + 1 : mid >> 1;
				if (nums[mid] <= target)
					sti = mid;
				else
					eni = mid - 1;
			}
			return sti;
		}
	}
}