package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *  Given an array of integers and an integer k, you need to find the number of 
 *  unique k-diff pairs in the array. Here a k-diff pair is defined as an integer 
 *  pair (i, j), where i and j are both numbers in the array and their absolute difference is k.
 *  
 *  Example 1:
 *  Input: [3, 1, 4, 1, 5], k = 2
 *  Output: 2
 *  Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 *  Although we have two 1s in the input, we should only return the number of unique pairs.
 *  Example 2:
 *  Input:[1, 2, 3, 4, 5], k = 1
 *  Output: 4
 *  Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 *  Example 3:
 *  Input: [1, 3, 1, 5, 4], k = 0
 *  Output: 1
 *  Explanation: There is one 0-diff pair in the array, (1, 1).
 *  Note:
 *  The pairs (i, j) and (j, i) count as the same pair.
 *  The length of the array won't exceed 10,000.
 *  All the integers in the given input belong to the range: [-1e7, 1e7].
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P532_K_diffPairsInAnArray.java
 * @type        P532_K_diffPairsInAnArray
 * @date        2017年3月5日 上午10:58:47
 * @details     Solution1: AC 26ms
 */
public class P532_K_diffPairsInAnArray {
    static class Solution1 {
        public int findPairs(int[] nums, int k) {
            if (nums == null || nums.length < 2) return 0;
            if (k == 0) {
                int answer = 0;
                HashMap<Integer, Integer> map = new HashMap<>(nums.length);
                for (int num : nums) {
                    Integer numVal = map.get(num);
                    if (numVal == null) {
                        numVal = 0;
                    }
                    numVal ++;
                    map.put(num, numVal);
                }
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    if (entry.getValue() >= 2) answer ++;
                }
                return answer;
            }
            HashSet<Integer> set = new HashSet<Integer>(nums.length);
            for (int num : nums)
                set.add(num);
            int pairs = 0;
            for (int val : set)
                if (set.contains(val - k))
                    pairs ++;
            return pairs;
        }
    }
}
