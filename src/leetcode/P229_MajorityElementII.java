package leetcode;

import java.util.LinkedList;
import java.util.List;

/*
 * 	Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * 	The algorithm should run in linear time and in O(1) space.

	Hint:
	
	How many majority elements could it possibly have?	
 */

public class P229_MajorityElementII {
	public static void main(String[] args) {
		Solution1 s = new Solution1();
		System.out.println(s.majorityElement(new int[] {3,2,3}));
	}
	/*
	 * 	3 ms
	 * 	62.30%
	 */
	static class Solution1 {
	    public List<Integer> majorityElement(int[] nums) {
	    	List<Integer> ans = new LinkedList<>();
	    	if (null == nums || nums.length == 0) {
	    		return ans;
	    	}
	    	if (nums.length < 3) {
	    		ans.add(nums[0]);
	    		if (nums.length == 2 && nums[1] != nums[0]) {
	    			ans.add(nums[1]);
	    		}
	    		return ans;
	    	}
	    	int v1 = 0, v2 = 0, t1 = 0, t2 = 0;
	    	for (int val : nums) {
	    		if (v1 == val) {
	    			t1 ++;
	    		} else if (v2 == val) {
	    			t2 ++;
	    		} else if (t1 <= 0) {
	    			v1 = val;
	    			t1 = 1;
	    		} else if (t2 <= 0) {
	    			v2 = val;
	    			t2 = 1;
	    		} else {
	    			t1 --;
	    			t2 --;
	    		}
	    	}
	    	t1 = 0;
	    	t2 = 0;
	    	for (int val : nums) {
	    		if (val == v1) {
	    			t1 ++;
	    		} else if (val == v2) {
	    			t2 ++;
	    		}
	    	}
	    	if (t1 > nums.length / 3) {
	    		ans.add(v1);
	    	}
	    	if (v1 != v2 && t2 > nums.length / 3) {
	    		ans.add(v2);
	    	}
	    	return ans;
	    }
	}
	
	/*
	 * 	5 ms
	 * 	31.52%
	 */
	static class Solution2 {
		final int K = 3;
	    public List<Integer> majorityElement(int[] nums) {
	    	List<Integer> ans = new LinkedList<>();
	    	if (null == nums || nums.length == 0) {
	    		return ans;
	    	}
	    	if (nums.length < 3) {
	    		ans.add(nums[0]);
	    		if (nums.length == 2 && nums[1] != nums[0]) {
	    			ans.add(nums[1]);
	    		}
	    		return ans;
	    	}
	    	int[] value = new int[K - 1];
	    	int[] times = new int[K - 1];
	    	for (int val : nums) {
	    		boolean isFound = false;
	    		for (int i = 0; i < K - 1; i ++) {
	    			if (val == value[i]) {
	    				times[i] ++;
	    				isFound = true;
	    			}
	    		}
	    		if (! isFound) {
	    			for (int i = 0; i < K - 1; i ++) {
	    				if (times[i] <= 0) {
	    					value[i] = val;
	    					times[i] = 1;
	    					isFound = true;
	    					break;
	    				}
	    			}
	    		}
	    		if (! isFound) {
	    			for (int i = 0; i < K - 1; i ++) {
	    				times[i] --;
	    			}
	    		}
	    	}
	    	for (int i = 0; i < K - 1; i ++) {
	    		times[i] = 0;
	    	}
	    	for (int val : nums) {
	    		for (int i = 0; i < K - 1; i ++) {
	    			if (value[i] == val) {
	    				times[i] ++;
	    			}
	    		}
	    	}
	    	boolean isHas0 = false;
	    	for (int i = 0; i < K - 1; i ++) {
	    		if (times[i] > nums.length / K) {
	    			if (value[i] == 0) {
	    				if (! isHas0) {
	    					isHas0 = true;
	    				} else {
	    					continue;
	    				}
	    			}
	    			ans.add(value[i]);
	    		}
	    	}
	    	return ans;
	    }
	}
}
