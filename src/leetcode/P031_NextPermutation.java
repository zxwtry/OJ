package leetcode;

import java.util.Arrays;

/*
 * 	Implement next permutation, which rearranges numbers 
 * 	into the lexicographically next greater permutation of numbers.

	If such arrangement is not possible, it must rearrange it 
	as the lowest possible order (ie, sorted in ascending order).

	The replacement must be in-place, do not allocate extra memory.

	Here are some examples. Inputs are in the left-hand column and 
	its corresponding outputs are in the right-hand column.
	1,2,3 → 1,3,2
	3,2,1 → 1,2,3
	1,1,5 → 1,5,1
	全排列中的下一个
 */

public class P031_NextPermutation {
	public static void main(String[] args) {
		// System.out.println(new Solution1().binaryIndex(new int[] {17, 15, 13,
		// 11, 9, 7, 5, 3, 1}, 0, 8, 6));
		// 0 1 2 3 4 5 6 7 8
//		 int[] nums = new int[] {2, 1};
		int[] nums = new int[] { 1, 2, 3, 4, 5 };
		new Solution2().nextPermutation(nums);
		tools.Utils.printArray(nums, 10);
	}

	/*
	 * 2 ms 11.25%
	 */
	static class Solution1 {
		public void nextPermutation(int[] nums) {
			int len = 0, index = 0, nixu = -1, i = 0, temp = 0;
			if (nums == null || (len = nums.length) < 2)
				return;
			if (nums[len - 1] > nums[len - 2]) {
				swap(nums, len - 1, len - 2);
				return;
			}
			for (index = len - 1; index != 0; index--) {
				if (nums[index - 1] < nums[index]) {
					nixu = index - 1;
					break;
				}
			}
			if (index == 0) {
				for (i = 0; i != len >>> 1; i++)
					swap(nums, i, len - 1 - i);
			} else {
				int swapIndex = binaryIndex(nums, nixu + 1, nums.length - 1, nums[nixu]);
				swap(nums, swapIndex, nixu);
				temp = nixu + nums.length + 1;
				i = (temp & 0x1) == 1 ? temp >> 1 : (temp >> 1) - 1;
				for (; i != nixu; i--)
					swap(nums, i, temp - 1 - i);
			}
		}

		private void swap(int[] nums, int i, int j) {
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
		}

		/*
		 * 保证 [sti, eni]上面arr是逆序的。 val < arr[sti] 找到第一个大于val的对应index
		 */
		int binaryIndex(int[] arr, int sti, int eni, int val) {
			while (sti < eni) {
				int temp = sti + eni, mid = (temp & 0x1) == 0 ? temp >> 1 : (temp >> 1) + 1;
				if (arr[mid] <= val) {
					eni = mid - 1;
				} else {
					sti = mid;
				}
			}
			return eni;
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     leetcode
	 * @file        P031_NextPermutation.java
	 * @type        Solution2
	 * @date        2016年11月22日 下午1:45:03
	 * @details     AC 94.65%
	 */
	static class Solution2 {
		public void nextPermutation(int[] arr) {
			if (arr == null || arr.length < 2)	return;
			int i2 = arr.length - 2;
			for (; i2 > -1 && arr[i2] >= arr[i2 + 1]; i2--) {}
			if (i2 == -1) {
				Arrays.sort(arr);
			} else {
				swap(arr, i2, nextPermutation_getLittleBigger(arr, i2));
				reverse(arr, i2 + 1, arr.length - 1);
			}
		}
		private int nextPermutation_getLittleBigger(int[] arr, int i2) {
			int sti = i2 + 1, eni = arr.length - 1, mid = 0;
			while (sti < eni) {
				mid = (sti + eni + 1) / 2;
				if (arr[mid] > arr[i2]) {
					sti = mid;
				} else {
					eni = mid - 1; 
				}
			}
			return sti;
		}
		private void swap(int[] arr, int i, int j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		private void reverse(int[] arr, int i, int j) {
			while (i < j)	swap(arr, i ++, j --);
		}
	}
}