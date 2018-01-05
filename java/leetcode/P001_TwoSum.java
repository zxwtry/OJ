package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P001_TwoSum.java
 * @date        2017年6月2日 下午2:43:44
 * @details     Solution1: AC  9ms 64.67%
 * @details     Solution2: AC 11ms 49.51%
 */
public class P001_TwoSum {
	static class Solution1 {
	    public int[] twoSum(int[] nums, int target) {
	        int numsLen = nums == null ? 0 : nums.length;
	        HashMap<Integer, Integer> valueIndexMap = new HashMap<>(numsLen << 2);
	        for (int i = 0; i < numsLen; i ++) {
	            Integer val = valueIndexMap.get(target - nums[i]);
	            if (val != null) {
	                return new int[] {i, val};
	            } else {
	                valueIndexMap.put(nums[i], i);
	            }
	        }
	        return null;
	    }
	}
	static class Solution2 {
		public int[] twoSum(final int[] nums, int target) {
		    int len = nums == null ? 0 : nums.length;
		    Integer[] index = new Integer[len];
		    for (int i = 0; i < len; i ++) index[i] = i;
		    Arrays.sort(index, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (nums[o1] > nums[o2]) return 1;
                    return nums[o1] < nums[o2] ? -1 : 0;
                }
            });
		    int sti = 0, eni = len - 1, sum = 0;
		    while (sti < eni) {
		        sum = nums[index[sti]] + nums[index[eni]];
		        if (sum == target) return new int[] {index[sti], index[eni]};
		        if (sum < target) sti ++;
		        else eni --;
		    }
		    return null;
		}
	}
}