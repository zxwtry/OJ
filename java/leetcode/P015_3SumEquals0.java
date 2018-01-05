package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class P015_3SumEquals0 {
	static class Solution {
	    public List<List<Integer>> threeSum(int[] ns) {
	        int nn = ns == null ? 0 : ns.length;
	        List<List<Integer>> ans = new LinkedList<List<Integer>>();
	        if (nn < 3) return ans; 
	        Arrays.sort(ns);
	        int i = 0, j = 0, k = 0;
	        for (i = 0; i != nn; i ++) {
	        	if (i != 0 && ns[i] == ns[i - 1])
	        		continue;
	        	j = i + 1; k = nn - 1;
	        	while (j < k) {
	        		int sum = ns[i] + ns[j] + ns[k];
        			if (sum == 0) {
        				ans.add(Arrays.asList(ns[i], ns[j], ns[k]));
        				do {
        					j ++;
        				} while (j != nn && ns[j] == ns[j-1]);
        				do {
        					k --;
        				} while (k != -1 && ns[k] == ns[k + 1]);
        			} else if (sum < 0) {
        				do {
        					j ++;
        				} while (j != nn && ns[j] == ns[j-1]);
        			} else {
        				do {
        					k --;
        				} while (k != -1 && ns[k] == ns[k + 1]);
        			}
	        	}
	        }
	        return ans;
	    }
	}
}
