package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * 	Given a set of candidate numbers (C) and a target number (T), 
 * 	find all unique combinations in C where the candidate numbers sums to T.

	The same repeated number may be chosen from C unlimited number of times.

	Note:
	All numbers (including target) will be positive integers.
	The solution set must not contain duplicate combinations.
	For example, given candidate set [2, 3, 6, 7] and target 7, 
	A solution set is: 
	[
  		[7],
  		[2, 2, 3]
	]
 */

public class P039_Combination_Sum {
	public static void main(String[] args) {
		System.out.println(new Solution1().combinationSum(new int[] {1}, 7));
	}
	static class Solution1 {
		boolean[] isAccess = null;
		int[] isAnswer = null;
		List<List<Integer>> ans = null;
	    public List<List<Integer>> combinationSum(int[] candidates, int target) {
	    	if (candidates == null || candidates.length == 0)
	    		return null;
	    	Arrays.sort(candidates);
	    	isAccess = new boolean[candidates.length];
	    	isAnswer = new int[candidates.length];
	    	isAccess[0] = true;
	    	for (int i = 1; i != candidates.length; i ++)
	    		isAccess[i] = candidates[i] == candidates[i - 1] ? false : true;
	        ans = new LinkedList<List<Integer>>();
	        searchAllAns(candidates, 0, target);
	        return ans;
	    }
		private void searchAllAns(int[] candidates, int index, int target) {
			if (index == candidates.length || target < 0)
				return;
			if (target == 0) {
				List<Integer> answer = new LinkedList<Integer>();
				for (int i = 0; i != isAnswer.length; i ++)
					for (int j = 0; j != isAnswer[i]; j ++)
						answer.add(candidates[i]);
				if (answer.size() != 0)
					ans.add(answer);
			}
			for (int i = index; i != candidates.length; i ++) {
				if (! isAccess[index])
					continue;
				isAnswer[i] ++;
				searchAllAns(candidates, i, target - candidates[i]);
				isAnswer[i] --;
			}
		}
	}
}
