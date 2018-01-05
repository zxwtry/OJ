package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**

Given m arrays, and each array is sorted in ascending order. Now you can pick up two 
integers from two different arrays (each array picks one) and calculate the distance. 
We define the distance between two integers a and b to be their absolute difference 
|a-b|. Your task is to find the maximum distance.

Example 1:
Input: 
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the first or third array 
and pick 5 in the second array.
Note:
Each given array will have at least 1 number. There will be at least two non-empty arrays.
The total number of the integers in all the m arrays will be in the range of [2, 10000].
The integers in the m arrays will be in the range of [-10000, 10000].

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P624_MaximumDistanceInArrays.java
 * @date        2017年6月18日 上午9:31:42
 * @details     AC
 */
public class P624_MaximumDistanceInArrays {
    static public class Solution {
        public int maxDistance(int[][] arrays) {
            if (arrays == null || arrays.length == 0) return 0;
            Arrays.sort(arrays, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
            int ans = 0;
            int minCut = arrays[0][0];
            for (int arraysIndex = 1; arraysIndex < arrays.length; arraysIndex ++) {
                ans = Math.max(arrays[arraysIndex][arrays[arraysIndex].length - 1] - minCut, ans);
            }
            int maxCut = arrays[0][arrays[0].length - 1];
            for (int arraysIndex = 1; arraysIndex < arrays.length; arraysIndex ++) {
                ans = Math.max(maxCut - arrays[arraysIndex][0], ans);
            }
            return ans;
        }
    }
}
