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
	public static void main(String[] args) {
//		System.out.println(new Solution().permuteUnique(new int[] {3, 2, 1}));
//		System.out.println(new Solution().permuteUnique(new int[] {3, 2, 1}));
//		System.out.println(new Solution().permuteUnique(new int[] {5, 4,3, 2, 2, 2, 1, 0, -1, -2}));
//		System.out.println(new Solution().permuteUnique(new int[] {3, 3, 2, 2, 1}));
//		System.out.println(new Solution2().permuteUnique(new int[] {1, 2, 3, 4}));
		System.out.println(new Solution2().permuteUnique(new int[] {3, 3, 5}));
	}
	/*
	 * 	先用HashSet试试
	 * 	32 ms
	 * 	10.27%
	 * 	第一次提交没有AC，将<<i修改成<<i*(i + 1)就AC了
	 * 	说明这种方法是对应现在的leetcode测试数据可行。。。
	 * 	况且效率真的不高
	 */
	static class Solution {
		List<List<Integer>> ans = new LinkedList<List<Integer>>();
		java.util.HashSet<Long> set = new java.util.HashSet<Long>();
		public List<List<Integer>> permuteUnique(int[] nums) {
			if (nums == null || nums.length < 1)
				return ans;
			searchAllPermutation(nums, 0);
			return ans;
		}
		private void searchAllPermutation(int[] nums, int index) {
			if (index == nums.length) {
				long l = generateLong(nums);
				if (set.contains(l))
					return;
				else
					set.add(l);
				List<Integer> answer = new LinkedList<Integer>();
				for (int i = 0; i != nums.length; i ++)
					answer.add(nums[i]);
				ans.add(answer);
			}
			for (int i = index; i != nums.length; i ++) {
				swap(nums, index, i);
				searchAllPermutation(nums, index + 1);
				swap(nums, index, i);
			}
		}
		private void swap(int[] nums, int i, int j) {
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
		}
		long generateLong(int[] nums) {
			long l = 0l;
			for (int i = 0; i != nums.length; i ++)
				l += (nums[i] << (i * (i + 1)));
			return l;
		}
	}
	/*
	 * 	使用HashSet是不行的。
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
