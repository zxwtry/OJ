package leetcode;

import java.util.Arrays;

/**

Given an array consists of non-negative integers, your task is to count 
the number of triplets chosen from the array that can make triangles 
if we take them as side lengths of a triangle.

Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P611_ValidTriangleNumber.java
 * @date        2017年6月13日 下午9:57:10
 * @details     
 */
public class P611_ValidTriangleNumber {
    public static void main(String[] args) throws Exception {
        int[] arr = {8, 1, 7, 3, 5};
        System.out.println(new Solution().triangleNumber(arr));
    }
    static public class Solution {
        public int triangleNumber(int[] nums) {
            int nl = nums == null ? 0 : nums.length;
            if (nl < 3) return 0;
            Arrays.sort(nums);
            int i = 0, j = 0;
            int cnt = 0;
            for (i = 0; i < nl-2; i ++) {
                for (j = i+1; j < nl-1; j ++) {
                    int startIndex = j+1;
                    int endIndex = Arrays.binarySearch(nums, nums[i] + nums[j]);
                    if (endIndex < 0) {
                        endIndex = (- endIndex) - 1;
                    } else {
//                        endIndex --;
                    }
                    if (endIndex - startIndex > 0) {
                        System.out.println();
                    }
                    cnt += Math.max(endIndex - startIndex, 0);
                }
            }
            return cnt;
        }
    }
}
