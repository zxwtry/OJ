package leetcode;

import java.util.HashSet;

public class P202_HappyNumber {
	public static void main(String[] args) {
		
	}
	/*
	 * 	5 ms
	 * 	38.66%
	 */
	static class Solution {
	    public boolean isHappy(int n) {
	    	HashSet<Integer> set = new HashSet<>();
	    	while (! set.contains(n)) {
	    		if (n == 1) {
	    			return true;
	    		}
	    		set.add(n);
	    		int[] bits = getAllBits(n);
	    		int nNew = 0;
	    		for (int val : bits) {
	    			nNew += val * val;
	    		}
	    		n = nNew;
	    	}
	        return false;
	    }
	    /*
	     * 	n肯定不可能是0
	     */
	    int[] getAllBits(int n) {
	    	int len = (int)Math.log10(n) + 1;
	    	int[] ans = new int[len];
	    	for (int i = len - 1; i > -1; i --) {
	    		ans[i] = n % 10;
	    		n = n / 10;
	    	}
	    	return ans;
	    }
	}
}
