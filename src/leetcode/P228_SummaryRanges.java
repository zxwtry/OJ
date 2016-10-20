package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * 	Given a sorted integer array without duplicates, return the summary of its ranges.

	For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */

public class P228_SummaryRanges {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.summaryRanges(new int[] {0,1,2,4,5,7}));
	}
	/*
	 * 	9 ms
	 * 	0.75%
	 */
	static class Solution {
		List<String> ans = new LinkedList<>();
	    public List<String> summaryRanges(int[] nums) {
	    	if (nums == null || nums.length == 0) {
	    		return ans;
	    	}
	    	int len = nums.length;
	    	ArrayList<Integer> frontIndex = new ArrayList<>(len);
	    	ArrayList<Integer> behindIndex = new ArrayList<>(len);
	    	int preRange = nums[0];
	    	int nowRange = 0;
	    	frontIndex.add(0);
	    	for (int index = 1; index < len; index ++) {
	    		nowRange = nums[index] - index;
	    		if (nowRange != preRange) {
	    			behindIndex.add(index - 1);
	    			frontIndex.add(index);
	    			preRange = nowRange;
	    		}
	    	}
	    	behindIndex.add(len - 1);
	    	int listLength = frontIndex.size();
	    	for (int i = 0; i < listLength; i ++) {
	    		int front = frontIndex.get(i);
	    		int behind = behindIndex.get(i);
	    		if (front == behind) {
	    			ans.add(String.valueOf(nums[front]));
	    		} else {
	    			ans.add(String.format("%d->%d", nums[front], nums[behind]));
	    		}
	    	}
	        return ans;
	    }
	}
}
