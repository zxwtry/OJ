package leetcode;

import java.util.LinkedList;
import java.util.List;

/*
 * 	Given a collection of numbers that might contain duplicates, 
 * 	return all possible unique permutations.

	For example,
	[1,1,2] have the following unique permutations:
	[
	  [1,1,2],
	  [1,2,1],
	  [2,1,1]
	]
 */

public class P047_PermutationsII {
	/*
	 * 	没有重复的规则是：
	 * 		第i个数与第j个数交换时，要求[i,j)中没有与第j个数相等的数
	 * 	6 ms
	 * 	44.78%
	 */
	static class Solution2 {
		List<List<Integer>> ans = new LinkedList<List<Integer>>();
	    public List<List<Integer>> permuteUnique(int[] nums) {
	        if (nums == null || nums.length < 1)
	        	return ans;
	        java.util.Arrays.sort(nums);
	        searchAllpermutations(nums, 0);
	        return ans;
	    }
		private void searchAllpermutations(int[] nums, int index) {
			if (index == nums.length) {
				List<Integer> answer = new LinkedList<Integer>();
				for (int i = 0; i != nums.length; i ++)
					answer.add(nums[i]);
				ans.add(answer);
			}
			for (int i = index; i != nums.length; i ++) {
				if (! isDuplicated(nums, i, index)) {
					swap(nums, i, index);
					searchAllpermutations(nums, index + 1);
					swap(nums, i, index);
				}
			}
		}
		private boolean isDuplicated(int[] nums, int i, int index) {
			for (int j = index; j < i; j ++)
				if (nums[j] == nums[i])
					return true;
			return false;
		}
		private void swap(int[] nums, int i, int j) {
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
		}
	}
}
