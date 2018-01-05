package leetcode;

import java.util.ArrayList;
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
	    int v = 19;
		System.out.println(new Solution1().combinationSum(new int[] {10, 1, 2, 7, 6, 1, 5, 1, 1, 1, 1}, v));
		System.out.println(new Solution2().combinationSum(new int[] {10, 1, 2, 7, 6, 1, 5, 1, 1, 1, 1}, v));
	}
	
	static class Solution2 {
	    public List<List<Integer>> combinationSum(int[] candidates, int target) {
	        int cn = candidates == null ? 0 : candidates.length;
	        if (cn == 0) {
	            return new ArrayList<>(0);
	        }
	        Arrays.sort(candidates);
	        
	        int selectedLength = getSelectedLength(candidates, 0, cn - 1, target);
	        int[] selected = new int[selectedLength];
	        //去除重复方法：如果candidates[i- 1] == candidates[i]
	        //             那么，i就不用选了
	        
	        List<List<Integer>> ans = new LinkedList<>();
	        search(ans, candidates, 0, selected, 0, target);
	        return ans;
	    }

        private void search(List<List<Integer>> ans, int[] candidates, 
                int candidateIndex, int[] selected, int selectedIndex, int target) {
            if (target == 0) {
                ArrayList<Integer> oneAns = new ArrayList<>(selectedIndex);
                for (int i = 0; i < selectedIndex; i ++) {
                    oneAns.add(selected[i]);
                }
                ans.add(oneAns);
                return;
            }
            if (candidateIndex == candidates.length) {
                return;
            }
            if (target < candidates[candidateIndex]) {
                return;
            }
            if (candidateIndex != 0 && candidates[candidateIndex - 1] == candidates[candidateIndex]) {
                search(ans, candidates, candidateIndex + 1, selected, selectedIndex, target);
                return;
            }
            selected[selectedIndex] = candidates[candidateIndex];
            //已经选择当前   通过target退出
            search(ans, candidates, candidateIndex, selected, selectedIndex + 1, target - candidates[candidateIndex]);
            //不选择当前       通过candidateIndex退出
            search(ans, candidates, candidateIndex + 1, selected, selectedIndex, target);
        }

        int getSelectedLength(int[] arr, int i, int j, int target) {
	        int min = getMin(arr, i, j);
	        return target / min + 1;
	    }
	    
	    int getMin(int[] arr, int i, int j) {
	        int min = arr[i];
	        for (int k = i + 1; k <= j; k ++) {
	            min = Math.min(min, arr[k]);
	        }
	        return min;
	    }
	    
	}
	/*
	 * 	6 ms
	 * 	62.68%
	 */
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
				if (! isAccess[i])
					continue;
				isAnswer[i] ++;
				searchAllAns(candidates, i, target - candidates[i]);
				isAnswer[i] --;
			}
		}
	}
}
