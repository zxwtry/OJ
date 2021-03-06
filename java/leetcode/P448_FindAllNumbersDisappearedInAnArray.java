package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 *  Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), 
 *  some elements appear twice and others appear once.
 *  
 *  Find all the elements of [1, n] inclusive that do not appear in this array.
 *  
 *  Could you do it without extra space and in O(n) runtime? 
 *  You may assume the returned list does not count as extra space.
 *  
 *  Example:
 *  
 *  Input:
 *  [4,3,2,7,8,2,3,1]
 *  
 *  Output:
 *  [5,6]
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P448_FindAllNumbersDisappearedInAnArray.java
 * @type        P448_FindAllNumbersDisappearedInAnArray
 * @date        2017年3月9日 下午9:52:44
 * @details     Solution1: AC 17ms 65.56%
 * @details     Solution2: AC 19ms 41.07%
 */
public class P448_FindAllNumbersDisappearedInAnArray {
    static class Solution1 {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> answer = new LinkedList<>();
            boolean[] ie = new boolean[nums.length];
            for (int num : nums)
                ie[num - 1] = true;
            for (int i = 0; i < ie.length; i ++)
                if (! ie[i])
                    answer.add(i + 1);
            return answer;
        }
    }
    static class Solution2 {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> answer = new LinkedList<>();
            for (int index = 0; index < nums.length; index ++) {
                if (nums[index] == index + 1) continue;
                while (nums[nums[index] - 1] !=  nums[index]) {
                    swap(nums, index, nums[index] - 1);
                }
            }
            for (int index = 0; index < nums.length; index ++) {
                if (nums[index] != index + 1)
                    answer.add(index + 1);
            }
            return answer;
        }
        private void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
}
