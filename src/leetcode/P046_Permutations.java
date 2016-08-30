package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * 	Given a collection of distinct numbers, 
 * 	return all possible permutations.
	For example,
	[1,2,3] have the following permutations:
	[
	  [1,2,3],
	  [1,3,2],
	  [2,1,3],
	  [2,3,1],
	  [3,1,2],
	  [3,2,1]
	]
 */

public class P046_Permutations {
	public static void main(String[] args) {
		System.out.println(new Solution().permute(new int[] {1}));
	}
	/*
	 * 	全排列
	 * 	3 ms
	 * 	70.59% 
	 */
	static class Solution {
	    List<List<Integer>> ans = new LinkedList<List<Integer>>();
		public List<List<Integer>> permute(int[] nums) {
			if (nums == null || nums.length == 0)
				return ans;
	        Arrays.sort(nums);
	        search(nums, 0);
	    	return ans;
	    }
		private void search(int[] nums, int index) {
			if (index == nums.length) {
				List<Integer> answer = new LinkedList<Integer>();
				for (int i = 0; i != nums.length; i ++)
					answer.add(nums[i]);
				ans.add(answer);
			} else {
				for (int i = index; i < nums.length; i ++) {
					swap(nums, index, i);
					search(nums, index + 1);
					swap(nums, index, i);
				}
			}
		}
		private void swap(int[] nums, int i, int j) {
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
		}
	}
}
