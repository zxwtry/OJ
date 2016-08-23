package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class P022_GenerateParentheses {
	public static void main(String[] args) {
		System.out.println(new Solution().generateParenthesis(14).size());
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
	/*
	 * 	这种思想非常好，就是看left的最右一个
	 * 	能够在一定程度避免2^n的悲剧
	 * 	但是代码非常不好写，可以继续试试，20160823暂停。
	 */
	static class Solution2 {
		private final char left = '(', right = ')';
        List<String> ans = new LinkedList<String>();
        char[] c = null;
	    public List<String> generateParenthesis(int n) {
	    	if (n < 1)
	    		return ans;
	    	int len = n << 1;
	    	c = new char[len];
	    	generateArray(0, len - 1);
	    	
	    	return ans;
	    }
	    private void generateArray(int sti, int eni) {
	    	if (sti >= eni)
	    		return;
	    	c[sti] = left;   c[eni] = right;
	    	if (eni == sti + 1) {
	    		ans.add(new String(c));
	    		return;
	    	}
	    	int midEnd = ((sti + eni) >>> 1) + 1, i = 0;
	    	for (i = sti + 1; i < midEnd; i ++)
	    		c[i] = left;
	    	for (i = midEnd; i != eni; i ++)
	    		c[i] = right;
	    	ans.add(new String(c));
	    	c[eni - 1] = left;
	    	c[eni - 2] = right;
	    	if (sti + 1 < eni - 3) {
	    		generateArray(sti + 1, eni - 3);
	    	} else {
	    		ans.add(new String(c));
	    		return;
	    	}
	    	for (i = midEnd; i < eni - 1; i ++) {
	    		
	    	}
	    }
	}
}
