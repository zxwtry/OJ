package leetcode;

import java.util.LinkedList;
import java.util.List;

/*
 * 	Find all possible combinations of k numbers that add up to a number n, 
 * 	given that only numbers from 1 to 9 can be used and each combination 
 * 	should be a unique set of numbers.

	Example 1:
	
	Input: k = 3, n = 7
	
	Output:
	
	[[1,2,4]]
	
	Example 2:
	
	Input: k = 3, n = 9
	
	Output:
	
	[[1,2,6], [1,3,5], [2,3,4]]
 */

public class P216_CombinationSumIII {
	public static void main(String[] args) {
		int k = 3;
		int n = 7;
		Solution s = new Solution();
		List<List<Integer>> ans = s.combinationSum3(k, n);
		System.out.println(ans);
	}
	/*
	 * 	1 ms
	 * 	53.00%
	 */
	static class Solution {
		final int maxN = 45;
		final int minN = 1;
		final int arrayValueMin = 1;
		final int arrayValueMax = 9;
		List<List<Integer>> ans = new LinkedList<>();
		int[] array = null;
	    public List<List<Integer>> combinationSum3(int k, int n) {
	    	if (n > maxN || n < minN) {
	    		return ans;
	    	}
	    	array = new int[k];
	    	search(0, 0, k, n);
	        return ans;
	    }
		private void search(int index, int sum, int k, int n) {
			if (sum > n) {
				return;
			}
			if (index == k) {
				if (sum == n) {
					List<Integer> list = new LinkedList<>();
					for (int arrayValue : array) {
						list.add(arrayValue);
					}
					ans.add(list);
				}
				return;
			}
			int indexValue = index == 0 ? arrayValueMin : array[index - 1] + 1;
			if (indexValue > arrayValueMax) {
				return;
			}
			for (; indexValue <= arrayValueMax; indexValue ++) {
				array[index] = indexValue;
				search(index + 1, sum + indexValue, k, n);
			}
		}
	}
}
