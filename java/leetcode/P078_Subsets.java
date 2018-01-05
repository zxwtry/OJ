package leetcode;

/*
 * 	Given a set of distinct integers, nums, return all possible subsets.

	Note: The solution set must not contain duplicate subsets.
	
	For example,
	If nums = [1,2,3], a solution is:
	
	[
	  [3],
	  [1],
	  [2],
	  [1,2,3],
	  [1,3],
	  [2,3],
	  [1,2],
	  []
	]
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class P078_Subsets {
	public static void main(String[] args) {
		List<List<Integer>> ans = new Solution().subsets(new int[] {3, 2, 1, 0});
		Iterator<List<Integer>> it = ans.iterator();
		while (it.hasNext()) {
			tools.Utils.B_打印List_Integer_OneLine(it.next());
		}
	}
	/*
	 * 	比上题逻辑要好得多
	 * 	AC	
	 * 	2 ms
	 */
	static class Solution {
		List<List<Integer>> ans = new LinkedList<List<Integer>>();
		int[] ks = null;
	    public List<List<Integer>> subsets(int[] nums) {
	    	ks = new int[nums.length];
	    	for (int list_size = 0; list_size <= nums.length; list_size ++)
	    		search(nums, list_size, 0, -1);
	        return ans;
	    }
		private void search(int[] nums, int list_size, int li, int maxi) {
			if (li == list_size) {
				List<Integer> temp = new ArrayList<Integer>(list_size);
				for (int i = 0; i != list_size; i ++)
					temp.add(ks[i]);
				ans.add(temp);
				return;
			}
			for (int i = maxi + 1; i < nums.length; i ++) {
				ks[li ++] = nums[i];
				search(nums, list_size, li, i);
				li --;
			}
		}
	}
}
