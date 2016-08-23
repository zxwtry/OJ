package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class P022_GenerateParentheses {
	public static void main(String[] args) {
		System.out.println(new Solution().generateParenthesis(2));
	}
	/*
	 * 	4ms
	 * 	17.90%
	 * 	很慢
	 */
	static class Solution {
		private final char left = '(', right = ')';
        List<String> ans = new LinkedList<String>();
        HashSet<String> set = new HashSet<String>();
        char[] c = null;
        int count_left = 1, l = 1, r = 1;
	    public List<String> generateParenthesis(int n) {
	    	if (n < 1)
	    		return ans;
	    	c = new char[n << 1];
	    	c[0] = left;  c[c.length - 1] = right;
	    	r = c.length - 2;
	    	dfs(1, n);
	        return ans;
	    }
	    private void dfs(int i, int n) {
	    	if (count_left < (i+1) / 2)
	    		return;
	    	if (count_left == n) {
	    		for (int j = 0; j != c.length; j ++)
	    			c[j] = c[j] == left ? left : right;
	    		String temp = new String(c);
	    		if (!set.contains(temp)) {
	    			ans.add(temp);
	    			set.add(temp);
	    		}
	    		
	    	}
	    	if (i == c.length - 1)
	    		return;
	    	c[i] = left;
	    	count_left ++;
	    	dfs(i + 1, n);
	    	count_left --;
	    	c[i] = right;
	    	c[i] = right;
	    	dfs(i + 1, n);
	    }
	}
}
