package leetcode;

/*
 * 	A peak element is an element that is greater than its neighbors.
	
	Given an input array where num[i] ≠ num[i+1], find a peak element
	 and return its index.
	
	The array may contain multiple peaks, in that case return the index 
	to any one of the peaks is fine.
	
	You may imagine that num[-1] = num[n] = -∞.
	
	For example, in array [1, 2, 3, 1], 3 is a peak element and 
	your function should return the index number 2.
 */

public class P162_FindPeakElement {
	public static void main(String[] args) {
		int[] arr = tools.Random随机生成器.A_生成一个不重复随机数据(100, -9000, 9000);
		System.out.println(tools.Utils.LEETCODE_int_array_序列化_(arr));
		arr = new int[] {1,2,3,1};
		Solution2 s = new Solution2();
		System.out.println(s.findPeakElement(arr));
		testTLE();
	}
	static void testTLE() {
		int count = 0;
		for (int i = 0; i < 10000; i ++) {
			int len = (int) ( Math.random() * 10000 );
			if (len == 0) {
				continue;
			}
			int[] arr = tools.Random随机生成器.A_生成一个不重复随机数据(len, - 5 * len, 5 * len);
//			System.out.println("arr的长度是：" + arr.length);
			Solution2 s = new Solution2();
			int ans = s.findPeakElement(arr);
//			System.out.println(ans);
			boolean isTrue = true;
			if (ans == 0) {
				if (arr.length != 1) {
					isTrue &= arr[1] < arr[0];
				}
			} else if (ans == arr.length - 1) {
				isTrue &= arr[arr.length - 1] > arr[arr.length - 2];
			} else {
				isTrue &= arr[ans - 1] < arr[ans] && arr[ans + 1] < arr[ans];
			}
			if (! isTrue) {
				count ++;
			}
		}
		System.out.println(count);
	}
	/*
	 * 	彻彻底底想错了
	 */
	static class Solution {
	    public int findPeakElement(int[] nums) {
	    	if (nums == null || nums.length == 0) {
	    		return 0;
	    	}
	    	if (nums.length == 1) {
	    		return nums[0];
	    	}
	    	int maxPeakIndex = Integer.MIN_VALUE;
	    	int maxPeak = Integer.MIN_VALUE;
	    	if (nums[0] > nums[1]) {
	    		maxPeakIndex = 0;
	    		maxPeak = nums[0];
	    	}
	    	if (nums[nums.length - 1] > nums[nums.length - 2]) {
	    		if (nums[nums.length - 1] > maxPeak) {
	    			maxPeak = nums[nums.length - 1];
	    			maxPeakIndex = nums.length - 1;
	    		}
	    	}
	    	for (int i = 1; i < nums.length - 1; i ++) {
	    		if (nums[i] > maxPeak && nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
	    			maxPeakIndex = i;
	    			maxPeak = nums[i];
	    		}
	    	}
	    	return maxPeakIndex;
	    }
	}
	/*
	 * 	0 ms 34.74%
	 */
	static class Solution2 {
	    public int findPeakElement(int[] nums) {
	    	if (nums == null || nums.length == 0) {
	    		return 0;
	    	}
	    	if (nums.length == 1) {
	    		return 0;
	    	}
	    	int sti = 1, eni = nums.length - 2;
	    	if (nums[sti - 1] > nums[sti]) {
	    		return sti - 1;
	    	}
	    	if (nums[eni + 1] > nums[eni]) {
	    		return eni + 1;
	    	}
	    	while (sti < eni) {
	    		int mid = (sti + eni) / 2;
	    		if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
	    			return mid;
	    		}
	    		if (mid == sti) {
	    			return eni;
	    		}
	    		if (nums[mid] < nums[mid - 1]) {
	    			eni = mid;
	    		} else {
	    			sti = mid;
	    		}
	    	}
	    	return sti;
	    }
	}
}
