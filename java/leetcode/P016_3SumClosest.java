package leetcode;

import java.util.Arrays;

public class P016_3SumClosest {
	static class Solution {
	    public int threeSumClosest(int[] ns, int target) {
	        int nn = ns == null ? 0 : ns.length;
	        if (nn < 3) return 0;
	    	int ans = ns[0] + ns[1] + ns[2];
	    	int cut = Integer.MAX_VALUE;
	    	Arrays.sort(ns);
	    	for (int i = 0; i != nn; i ++) {
	    		int j = i + 1, k = nn - 1;
	    		while (j < k) {
	    			int sum = ns[i] + ns[j] + ns[k];
	    			int real = sum - target;
	    			int tmp = Math.abs(real);
	    			if (real == 0) {
	    				return target;
	    			} else if (tmp < cut) {
	    				ans = sum;
	    				cut = tmp;
	    				if (real < cut)
	    					j ++;
	    				else
	    					k --;
	    			} else if (tmp == cut) {
	    				if (real < cut)
	    					j ++;
	    				else
	    					k --;
	    			} else {
	    				if (real < cut)
	    					j ++;
	    				else
	    					k --;
	    			}
	    		}
	    	}
	    	return ans;
	    }
	}
}