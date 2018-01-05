package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * 	Given two integers n and k, return all possible combinations 
 * 	of k numbers out of 1 ... n.

	For example,
	If n = 4 and k = 2, a solution is:
	
	[
	  [2,4],
	  [3,4],
	  [2,3],
	  [1,2],
	  [1,3],
	  [1,4],
	]
 */

public class P077_Combinations {
	public static void main(String[] args) {
		List<List<Integer>> ans = new Solution().combine(5, 4);
		Iterator<List<Integer>> it = ans.iterator();
		while (it.hasNext()) {
			tools.Utils.B_打印List_Integer_OneLine(it.next());
		}
	}
	/*
	 * 	挺简单的一道题，却AC得非常虚。。。虽然一次AC
	 * 	诶，自己还是得多练回溯
	 * 	39 ms
	 */
	static class Solution {
	    public List<List<Integer>> combine(int n, int k) {
	    	List<List<Integer>> ans = new LinkedList<List<Integer>>();
	    	int[] ks = new int[k];
	    	search(ks, 0, ans, n, k, 0);
	    	return ans;
	    }
	    void search(int[] ks, int ki, List<List<Integer>> ans, int n, int k, int ni) {
	    	if (ki == k) {
	    		List<Integer> temp = new ArrayList<Integer>(k);
	    		for (int i = 0; i != k; i ++)
	    			temp.add(ks[i]);
	    		ans.add(temp);
	    		return;
	    	}
	    	for (int i = ni; i != n; i ++) {
	    		if (ki != 0 && ks[ki - 1] >= i + 1)
	    			continue;
	    		ks[ki] = i + 1;
	    		ni ++;
	    		search(ks, ki + 1, ans, n, k, ni);
	    		ni --;
	    	}
	    }
	}
}
