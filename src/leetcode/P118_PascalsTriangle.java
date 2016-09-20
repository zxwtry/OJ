package leetcode;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * 	Given numRows, generate the first numRows of Pascal's triangle.

	For example, given numRows = 5,
	Return
	
	[
	     [1],
	    [1,1],
	   [1,2,1],
	  [1,3,3,1],
	 [1,4,6,4,1]
	]	
 */

import java.util.LinkedList;
import java.util.List;

public class P118_PascalsTriangle {
	public static void main(String[] args) {
		List<List<Integer>> ans = null;
		Solution s = new Solution();
		ans = s.generate(3);
		Iterator<List<Integer>> it = ans.iterator();
		while (it.hasNext()) {
			tools.Utils.B_打印List_Integer_OneLine(it.next());
		}
	}
	static class Solution {
		List<List<Integer>> ans = new LinkedList<>();
	    public List<List<Integer>> generate(int numRows) {
	    	if (numRows <= 0) {
	    		return ans;
	    	}
	    	List<Integer> pre_list = new ArrayList<Integer>(1);
	    	pre_list.add(1);
	    	ans.add(pre_list);
	    	for (int row = 1; row < numRows; row ++) {
	    		List<Integer> this_list = new ArrayList<Integer>(row);
	    		this_list.add(1);
	    		for (int i = 1; i < row; i ++) {
	    			this_list.add(pre_list.get(i - 1) + pre_list.get(i));
	    		}
	    		this_list.add(1);
	    		ans.add(this_list);
	    		pre_list = this_list;
	    	}
	        return ans;
	    }
	}
}
