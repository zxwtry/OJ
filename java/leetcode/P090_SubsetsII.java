package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * 	Given a collection of integers that might contain duplicates, nums, 
 * 	return all possible subsets.

	Note: The solution set must not contain duplicate subsets.
	
	For example,
	If nums = [1,2,2], a solution is:
	
	[
	  [2],
	  [1],
	  [1,2,2],
	  [2,2],
	  [1,2],
	  []
	]
 */

public class P090_SubsetsII {
	public static void main(String[] args) {
		int[] nums = null;
		nums = new int[] {1, 1, 2, 2, 3, 3};
		List<List<Integer>> ans = new Solution().subsetsWithDup(nums);
		Iterator<List<Integer>> it = ans.iterator();
		while (it.hasNext()) {
			tools.Utils.B_打印List_Integer_OneLine(it.next());
		}
	}
	/*
	 * 	AC
	 * 	无论何时，都需要耐心分析边界问题。
	 * 	3 ms
	 */
	static class Solution {
		List<List<Integer>> ans = new LinkedList<>();
		int[] first_same, last_same, record, nums;
	    public List<List<Integer>> subsetsWithDup(int[] nums) {
	    	ans.add(new LinkedList<Integer>());
	    	if (nums == null || nums.length == 0) {
	    		return ans;
	    	}
	    	Arrays.sort(nums);
	    	this.nums = nums;
	    	first_same = new int[nums.length];
	    	last_same = new int[nums.length];
	    	record = new int[nums.length];
	    	for (int i = 0; i != nums.length; i ++) {
	    		if (0 == i || nums[i - 1] != nums[i]) {
	    			first_same[i] = i;
	    		} else {
	    			first_same[i] = first_same[i - 1];
	    		}
	    	}
	    	for (int i = nums.length - 1; i > - 1; i --) {
	    		if (i == nums.length - 1 || nums[i] != nums[i + 1]) {
	    			last_same[i] = i;
	    		} else {
	    			last_same[i] = last_same[i + 1];
	    		}
	    	}
//	    	tools.Utils.printArray(first_same, 10);
//	    	tools.Utils.printArray(last_same, 10);
	    	for (int len = 1; len <= nums.length; len ++) {
	    		search(len, 0, 0);
	    	}
	        return ans;
	    }
		private void search(int len, int len_now, int sti) {
			if (len_now == len) {
				List<Integer> temp = new ArrayList<Integer>(len);
				for (int i = 0; i != len; i ++) {
					temp.add(record[i]);
				}
				ans.add(temp);
				return;
			}
			for (int i = sti; i < nums.length; i ++) {
				if (first_same[i] == last_same[i]) {
					record[len_now] = nums[i];
					search(len, len_now + 1, i + 1);
				} else {
					int count_same = last_same[i] - first_same[i];
					for (int same_now = 0; same_now <= count_same; same_now ++) {
						if (len_now + same_now >= len) {
							break;
						}
//						if (same_now != 0) {
						record[len_now + same_now] = nums[i];
//						}
						search(len, len_now + same_now + 1, last_same[i] + 1);
					}
					i += count_same;
				}
			}
		}
	}
}