package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class P015_3SumEquals0 {
	static class Solution {
	    public List<List<Integer>> threeSum(int[] nums) {
	        List<List<Integer>> ans = new LinkedList<List<Integer>>();
	        Arrays.sort(nums);
	        int i = 0, j = 0, k = 0;
	        for (i = 0; i != nums.length; i ++) {
	        	if (i != 0 && nums[i] == nums[i - 1])
	        		continue;
	        	j = i + 1; k = nums.length - 1;
	        	while (j < k) {
	        		int sum = nums[i] + nums[j] + nums[k];
        			if (sum == 0) {
        				ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
        				do {
        					j ++;
        				} while (j != nums.length && j != 0 && nums[j] == nums[j-1]);
        				do {
        					k --;
        				} while (k != -1 && k != nums.length - 1 && nums[k] == nums[k + 1]);
        			} else if (sum < 0) {
        				do {
        					j ++;
        				} while (j != nums.length && j != 0 && nums[j] == nums[j-1]);
        			} else {
        				do {
        					k --;
        				} while (k != -1 && k != nums.length - 1 && nums[k] == nums[k + 1]);
        			}
	        	}
	        }
	        return ans;
	    }
	}
}
