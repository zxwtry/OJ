package leetcode;

import java.util.Arrays;

/*
 * 	Suppose a sorted array is rotated at some pivot unknown to you beforehand.

	(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

	You are given a target value to search. 
	If found in the array return its index, otherwise return -1.

	You may assume no duplicate exists in the array.
 */

public class P033_SearchInRotatedSortedArray {
	public static void main(String[] args) {
		System.out.println(new Solution().search(new int[] {3, 1}, 1));
		System.out.println(new Solution2().search(new int[] {3, 1}, 1));
	}
	/*
	 * 	1 ms
	 * 	7.60%
	 * 	不知道怎么，WA好多次
	 */
	static class Solution {
	    public int search(int[] nums, int target) {
	    	if (nums == null || nums.length == 0)
	    		return -1;
	    	int broken = 1;
	    	while (broken != nums.length) {
	    		if (nums[broken - 1] > nums[broken])
	    			break;
	    		broken ++;
	    	}
	    	if (broken == nums.length)
	    		return getIndex(nums, 0, broken - 1, target);
	    	int ans = getIndex(nums, 0, broken - 1, target);
	    	if (ans != -1)
	    		return ans;
	    	return getIndex(nums, broken, nums.length - 1, target);
	    }
	    private int getIndex(int[] nums, int sti, int eni, int val) {
	    	if ((nums[sti] - val) * (nums[eni] - val) > 0)
	    		return -1;
	    	while (sti <= eni) {
	    		int mid = (sti + eni) >> 1;
	    		if (nums[mid] > val)
	    			eni = mid - 1;
	    		else if (nums[mid] < val)
	    			sti = mid + 1;
	    		else
	    			return mid;
	    	}
	    	return -1;
	    }
	}
	
	static class Solution2 {
	    public int search(int[] nums, int target) {
	        int numsLength = nums == null ? 0 : nums.length;
	        return search(nums, 0, numsLength - 1, target);
	    }

        private int search(int[] nums, int i, int j, int target) {
            if (i > j) return -1;
            if (nums[i] == target) {
                return i;
            }
            if (nums[j] == target) {
                return j;
            }
            if (i + 1 < j && nums[i + 1] < nums[j - 1]) {
                int ans = Arrays.binarySearch(nums, i + 1, j, target);
                return ans > -1 ? ans : -1;
            }
            int m = i + (j - i) / 2;
            if (nums[m] == target) {
                return m;
            }
            int left = search(nums, i + 1, m - 1, target);
            if (left != -1) {
                return left;
            }
            return search(nums, m + 1, j - 1, target);
        }
        
	}
}
