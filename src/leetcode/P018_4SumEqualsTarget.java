package leetcode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class P018_4SumEqualsTarget {
	public static void main(String[] args) {
//		System.out.println(new Solution1().threeSum(new int[] {-4, -1, -1, 0, 1, 2}, -1, 0));
//		System.out.println(new Solution1().threeSum(new int[] {-2, 0, 0, 2, 2}, -1, 0));
		System.out.println(new Solution1().fourSum(new int[] {1, 0, -1, 0, -2, 2}, 0));
		System.out.println(new Solution1().fourSum(new int[] {-2, 2, 2, 2, 2, 2}, 4));
		System.out.println(new Solution1().fourSum(new int[] {2, 2, 2, 2, 2, 2, 4, 0}, 8));
	}
	/*
	 * 	106 ms
	 * 	6.18%
	 */
	static class Solution1 {
	    public List<List<Integer>> fourSum(int[] nums, int target) {
	        List<List<Integer>> ans = new LinkedList<List<Integer>>();
	        if (nums == null || nums.length < 4)
	        	return ans;
	        Arrays.sort(nums);
	        for (int i = 0; i != nums.length; i ++) {
	        	if (i != 0 && nums[i] == nums[i - 1])
	        		continue;
	        	List<List<Integer>> temp = threeSum(nums, i, target - nums[i]);
	        	Iterator<List<Integer>> it = temp.iterator();
	        	while (it.hasNext()) {
	        		List<Integer> list = it.next();
	        		list.add(nums[i]);
	        		ans.add(list);
	        	}
	        }
	        return ans;
	    }
	    List<List<Integer>> threeSum(int[] nums, int bomb, int target) {
	    	List<List<Integer>> ans = new LinkedList<List<Integer>>();
	    	int i = 0, j = 0, k = 0;
	    	for (i = bomb + 1; i != nums.length; i ++) {
	    		if (i != 0 && nums[i] == nums[i - 1] && (i != bomb + 1))	continue;
	    		j = i + 1;  k = nums.length - 1;
	    		while (j < k) {
	    			int sum = nums[i] + nums[j] + nums[k];
	    			if (sum == target) {
	    				List<Integer> list = new LinkedList<Integer>();
	    				list.add(nums[i]);   list.add(nums[j]);   list.add(nums[k]);
	    				ans.add(list);
	    				do {
	    					j ++;
	    				} while (j != nums.length && nums[j] == nums[j - 1]);
	    				do {
	    					k --;
	    				} while (k != -1 && nums[k] == nums[k + 1]);
	    			} else if (sum > target) {
	    				do {
	    					k --;
	    				} while (k != -1 && nums[k] == nums[k + 1]);
	    			} else {
	    				do {
	    					j ++;
	    				} while (j != nums.length && nums[j] == nums[j - 1]);
	    			}
	    		}
	    	}
	    	return ans;
	    }
	}
	/*
	 * 	87 ms
	 * 	11.81%
	 */
	static class Solution2 {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
	    public List<List<Integer>> fourSum(int[] nums, int target) {
	        if (nums == null || nums.length < 4)
	        	return ans;
	        Arrays.sort(nums);
	        for (int i = 0; i != nums.length; i ++) {
	        	if (i != 0 && nums[i] == nums[i - 1])
	        		continue;
	        	threeSum(nums, i, target - nums[i]);
	        }
	        return ans;
	    }
	    void threeSum(int[] nums, int bomb, int target) {
	    	int i = 0, j = 0, k = 0;
	    	for (i = bomb + 1; i != nums.length; i ++) {
	    		if (i != 0 && nums[i] == nums[i - 1] && (i != bomb + 1))	continue;
	    		j = i + 1;  k = nums.length - 1;
	    		while (j < k) {
	    			int sum = nums[i] + nums[j] + nums[k];
	    			if (sum == target) {
	    				List<Integer> list = new LinkedList<Integer>();
	    				list.add(nums[i]);   list.add(nums[j]);
	    				list.add(nums[k]);   list.add(nums[bomb]);
	    				ans.add(list);
	    				do {
	    					j ++;
	    				} while (j != nums.length && nums[j] == nums[j - 1]);
	    				do {
	    					k --;
	    				} while (k != -1 && nums[k] == nums[k + 1]);
	    			} else if (sum > target) {
	    				do {
	    					k --;
	    				} while (k != -1 && nums[k] == nums[k + 1]);
	    			} else {
	    				do {
	    					j ++;
	    				} while (j != nums.length && nums[j] == nums[j - 1]);
	    			}
	    		}
	    	}
	    }
	}
}
