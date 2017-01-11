package leetcode;

/**
 * 	Given an array nums, write a function to move all 0's to the 
 * 	end of it while maintaining the relative order of the non-zero elements.

	For example, given nums = [0, 1, 0, 3, 12], after calling your 
	function, nums should be [1, 3, 12, 0, 0].
	
	Note:
	You must do this in-place without making a copy of the array.
	Minimize the total number of operations.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P283_MoveZeroes.java
 * @type        P283_MoveZeroes
 * @date        2016年12月14日 下午10:25:13
 * @details     Solution: 1ms 18.90%
 */
public class P283_MoveZeroes {
	static class Solution1 {
		public void moveZeroes(int[] num) {
			if (num == null || num.length < 2) return;
			merge(num, 0, (num.length-1) / 2, num.length - 1);
		}
		private void merge(int[] num, int i, int j, int k) {
			if (j - i < 2) {
				if (num[i] == 0)
					swap(num, i, j);
			} else {
				merge(num, i, (i + j) / 2, j);
			}
			if (k - j - 1 < 2) {
				if (num[j + 1] == 0)
					swap(num, j + 1, k);
			} else {
				merge(num, j + 1, (k + j + 1) / 2, k);
			}
			int ji = j;
			while (ji >= i && num[ji] == 0) ji --;
			if (ji != j) {
				ji ++;
				int ki = j+1;
				while (ki <= k && num[ji] == 0 && num[ki] != 0) {
					swap(num, ki, ji);
					ji ++;
					ki ++;
				}
			}
		}
		void swap(int[] nums, int i, int j) {
			int t = nums[i];
			nums[i] = nums[j];
			nums[j] = t;
		}
	}
}
