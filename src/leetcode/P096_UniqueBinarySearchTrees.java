package leetcode;

public class P096_UniqueBinarySearchTrees {
	public static void main(String[] args) {
		System.out.println(new Solution().numTrees(3));
	}
	/*
	 * 	TLE
	 */
	static class Solution {
	    public int numTrees(int n) {
	        return generateTrees(1, n);
	    }
	    int generateTrees(int sti, int eni) {
	    	if (sti > eni) {
	    		return 1;
	    	}
	    	if (sti + 0 == eni) {
	    		return 1;
	    	} else if (sti + 1 == eni) {
	    		return 2;
	    	}
	    	int this_count = 0;
	    	for (int tra = sti; tra <= eni; tra ++) {
	    		this_count += generateTrees(sti, tra - 1) * generateTrees(tra + 1, eni);
	    	}
	    	return this_count;
	    }
	}
}
