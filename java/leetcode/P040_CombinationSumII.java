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
	static class Solution {
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
	        //找完了
	        if (ci == cs.length) {
	            return;
	        }
	        //没法向下找
	        if (t < cs[ci]) {
	            return;
	        }
	        //去重
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
}