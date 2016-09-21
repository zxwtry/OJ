package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
 * 	Given an index k, return the kth row of the Pascal's triangle.

	For example, given k = 3,
	Return [1,3,3,1].
	
	Note:
	Could you optimize your algorithm to use only O(k) extra space?
 */

public class P119_PascalsTriangleII {
	public static void main(String[] args) {
		Solution s = new Solution();
		for (int i = -1; i < 11; i ++) {
			List<Integer> ans = s.getRow(i);
			tools.Utils.B_打印List_Integer_OneLine(ans);
		}
	}
	static class Solution {
	    public List<Integer> getRow(int rowIndex) {
	    	rowIndex ++;
	    	List<Integer> ans = new ArrayList<>(rowIndex > 0 ? rowIndex : 1);
	    	if (rowIndex <= 1) {
	    		ans.add(1);
	    		return ans;
	    	}
	    	for (int i = 0; i < rowIndex; i ++) {
	    		ans.add(0);
	    	}
	    	ans.set(rowIndex - 1, 1);
	    	ans.set(rowIndex - 2, 1);
	    	int index = rowIndex - 3;
	    	while (index != - 1) {
	    		for (int jndex = index + 1; jndex < rowIndex - 1; jndex ++) {
	    			ans.set(jndex, ans.get(jndex) + ans.get(jndex + 1));
	    		}
	    		ans.set(index, 1);
	    		index --;
	    	}
	    	return ans;
	    }
	}
}
