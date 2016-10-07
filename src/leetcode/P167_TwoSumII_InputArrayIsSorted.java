package leetcode;

import java.util.Arrays;

/*
 * 	Given an array of integers that is already sorted in ascending order,
 *  find two numbers such that they add up to a specific target number.

	The function twoSum should return indices of the two numbers 
	such that they add up to the target, where index1 must 
	be less than index2. Please note that your returned answers 
	(both index1 and index2) are not zero-based.
	
	You may assume that each input would have exactly one solution.
	
	Input: numbers={2, 7, 11, 15}, target=9
	Output: index1=1, index2=2	
	
	就是给一个排序数组和一个指定和
	要求从排序数组中找到两个数，两个数的和等于指定
	返回两个数的下标，注意下标各加1
 */

public class P167_TwoSumII_InputArrayIsSorted {
	static int[] numsInput = null;
	static int target = 0, min = -10000, max = 10000;
	public static void main(String[] args) {
//		TestTwoSumEntry();
		numsInput = new int[] {2, 7, 11, 15};
		target = 9;
		Solution s = new Solution();
		int[] ans = s.twoSum(numsInput, target);
		tools.Utils.printArray(ans, 100);
	}
	static void TestTwoSumEntry() {
		int count = 0;
		for (int i = 0; i < 1000; i ++) {
			generateArrayAndTarget(10000);
			Solution s = new Solution();
			int[] ans = s.twoSum(numsInput, target);
			if (numsInput[ans[0] - 1] + numsInput[ans[1] - 1] != target) {
				count ++;
			}
		}
		System.out.println(count);
	}
	static void generateArrayAndTarget(int lenOfNums) {
		int add1 = generateInteger(min, max);
		int add2 = generateInteger(min, max);
		target = add1 + add2;
		numsInput = new int[lenOfNums];
		for (int i = 2; i < lenOfNums; i ++) {
			numsInput[i] = generateInteger(min, max);
		}
		Arrays.sort(numsInput);
	}
	static int generateInteger(int min, int max) {
		long range = (long)max - min + 1;
		return min + (int) (Math.random() * range);
	}
	/*
	 * 	1 ms
	 * 	37.22%
	 */
	static class Solution {
	    public int[] twoSum(int[] numbers, int target) {
	    	int[] ans = new int[2];
	    	int smallIndex = 0, bigIndex = numbers.length - 1;
	    	int calcTarget = numbers[smallIndex] + numbers[bigIndex];
	    	while (calcTarget != target) {
	    		if (calcTarget < target) {
	    			smallIndex ++;
	    		} else {
	    			bigIndex --;
	    		}
	    		calcTarget = numbers[smallIndex] + numbers[bigIndex];
	    	}
	    	ans[0] = smallIndex + 1;
	    	ans[1] = bigIndex + 1;
	        return ans;
	    }
	}
}
