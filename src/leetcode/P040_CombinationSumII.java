package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/*
 * 	Given a collection of candidate numbers (C) and a target number (T), 
 * 	find all unique combinations in C where the candidate numbers sums to T.

	Each number in C may only be used once in the combination.

	Note:
	All numbers (including target) will be positive integers.
	The solution set must not contain duplicate combinations.
	For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
	A solution set is: 
	[
	  [1, 7],
	  [1, 2, 5],
	  [2, 6],
	  [1, 1, 6]
	]
 */

public class P040_CombinationSumII {
	public static void main(String[] args) {
	    int t = 7;
		System.out.println(new Solution2().combinationSum2(new int[] {10, 1, 2, 7, 6, 1, 5}, t));
		System.out.println(new Solution3().combinationSum2(new int[] {10, 1, 2, 7, 6, 1, 5}, t));
//		System.out.println(new Solution2().combinationSum2(new int[] {2, 5, 2, 1, 2}, 5));
//		System.out.println(new Solution3().combinationSum2(new int[] {2, 5, 2, 1, 2}, 5));
//		System.out.println(new Solution2().combinationSum2(new int[] {3,1,3,5,1,1}, t));
//		System.out.println(new Solution3().combinationSum2(new int[] {3,1,3,5,1,1}, t));
	}
	static class Solution3 {
	    public List<List<Integer>> combinationSum2(int[] cs, int t) {
	        List<List<Integer>> ans = new LinkedList<>();
	        int cn = cs == null ? 0 : cs.length;
	        if (cn < 1) {
	            return ans;
	        }
	        Arrays.sort(cs);
	        int[] s = new int[getSLength(cs, 0, cn - 1, t)];
	        search(ans, cs, 0, s, 0, t, false);
	        return ans;
	    }
	    private void search(List<List<Integer>> ans, int[] cs, int ci, int[] s,
                int si, int t, boolean lu) {
	        if (t == 0) {
	            ArrayList<Integer> oneAns = new ArrayList<>(si);
	            for (int i = 0; i < si; i ++) {
	                oneAns.add(s[i]);
	            }
	            ans.add(oneAns);
	            return;
	        }
	        if (ci == cs.length) {
	            return;
	        }
	        if (ci != 0 && cs[ci - 1] == cs[ci] && ! lu) {
	            search(ans, cs, ci + 1, s, si, t, false);
	            return;
	        }
	        s[si] = cs[ci];
	        //选用
	        search(ans, cs, ci + 1, s, si + 1, t - cs[ci], true);
	        //不用
	        search(ans, cs, ci + 1, s, si, t, false);
        }
        int getSLength(int[] cs, int ci, int cj, int t) {
	        int min = cs[ci];
	        for (int i = ci + 1; i <= cj; i ++) {
	            min = Math.min(min, cs[i]);
	        }
	        return t / min + 1;
	    }
	}
	/*
	 * 	想复杂了
	 */
	static class Solution1 {
		int[] isAccess = null;
		boolean[] isAnswer = null;
		List<List<Integer>> ans = null;
	    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
	    	if (candidates == null || candidates.length == 0)
	    		return null;
	    	Arrays.sort(candidates);
	    	isAccess = new int[candidates.length];
	    	isAnswer = new boolean[candidates.length];
	    	isAccess[0] = 0;
	    	for (int i = 1; i != candidates.length; i ++)
	    		isAccess[i] = candidates[i] == candidates[i - 1] ? isAccess[i - 1] : i;
	        ans = new LinkedList<List<Integer>>();
	        searchAllAns(candidates, 0, target);
	        addSpecialAns(candidates, target);
	        return ans;
	    }
		private void addSpecialAns(int[] candidates, int target) {
			for (int i = 0; i != candidates.length; i ++) {
				if (i == isAccess[i])
					continue;
				int num = i - isAccess[i] + 1;
				for (int j = 0; j != num; j ++)
					isAnswer[isAccess[i] + j] = true;
				searchAllAns(candidates, 0, target - num * candidates[i]);
				for (int j = 1; j != num; j ++)
					isAnswer[isAccess[i] + j] = true;
			}
		}
		private void searchAllAns(int[] candidates, int index, int target) {
			if (index == candidates.length || target < 0)
				return;
			if (target == 0) {
				List<Integer> answer = new LinkedList<Integer>();
				for (int i = 0; i != isAnswer.length; i ++)
					if (isAnswer[i])
						answer.add(candidates[i]);
				if (answer.size() != 0)
					ans.add(answer);
			}
			for (int i = index; i != candidates.length; i ++) {
				if (isAnswer[i] || isAccess[i] != i)
					continue;
				isAnswer[i] = true;
				searchAllAns(candidates, i, target - candidates[i]);
				isAnswer[i] = false;
			}
		}
	}
	/*
	 * 	15 ms
	 * 	26.62%
	 */
	static class Solution2 {
		List<List<Integer>> ans = null;
		boolean[] isAnswer = null;
		int[] index1 = null, index2 = null;
		public List<List<Integer>> combinationSum2(int[] candidates, int target) {
			if (candidates == null || candidates.length == 0)
	    		return ans;
			Arrays.sort(candidates);
			ans = new LinkedList<List<Integer>>();
			isAnswer = new boolean[candidates.length];
			index1 = new int[candidates.length];
			index1[0] = 0;
			for (int i = 1; i != candidates.length; i ++)
				index1[i] = candidates[i] == candidates[i - 1] ? index1[i - 1] : i;
			index2 = new int[candidates.length];
			index2[index2.length - 1] = index2.length - 1;
			for (int i = index2.length - 2; i != -1; i --)
				index2[i] = candidates[i] == candidates[i + 1] ? index2[i + 1] : i;
			searchAllAns(candidates, -1, target);
			return ans;
		}
		private void searchAllAns(int[] candidates, int index, int target) {
			if (target == 0) {
				List<Integer> answer = new LinkedList<Integer>();
				for (int i = 0; i != candidates.length; i ++)
					if (isAnswer[i])
						answer.add(candidates[i]);
				if (answer.size() != 0)
					ans.add(answer);
			}
			if (index == candidates.length || target < 0)
				return;
			for (int i = index + 1; i != candidates.length; i ++) {
				if (isAnswer[i])
					continue;
				if (index1[i] == i && index2[i] == i) {
					isAnswer[i] = true;
					searchAllAns(candidates, i, target - candidates[i]);
					isAnswer[i] = false;
				} else {
					int num = index2[i] - index1[i] + 1;
					i = index2[i];
					for (int j = 1; j <= num; j ++) {
						for (int k = 0; k != j; k ++)
							isAnswer[k + index1[i]] = true;
						searchAllAns(candidates, i, target - j * candidates[i]);
						for (int k = 0; k != j; k ++)
							isAnswer[k + index1[i]] = false;
					}
				}
			}
		}
	}
}