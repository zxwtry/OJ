package leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 *  Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 *  
 *  Example 1:
 *  Input: [0,1]
 *  Output: 2
 *  Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 *  Example 2:
 *  Input: [0,1,0]
 *  Output: 2
 *  Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 *  Note: The length of the given binary array will not exceed 50,000.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P525_ContiguousArray.java
 * @type        P525_ContiguousArray
 * @date        2017年2月19日 上午11:17:38
 * @details     Solution1: TLE
 * @details     Solution2: TLE
 * @details     Solution3: AC 143ms  6.93%
 * @details     Solution4: AC 101ms 37.30%
 * @details     Solution5: AC  87ms 58.10%
 * @details     Solution6: AC  50ms 99.05%
 */
public class P525_ContiguousArray {
    static class Solution1 {
        int maxL = 0;
        public int findMaxLength(int[] nums) {
            int cnt0 = 0, cnt1 = 0;
            for (int num : nums)
                if (num == 0) cnt0 ++;
                else cnt1 ++;
            find(nums, 0, nums.length - 1, cnt0, cnt1);
            return maxL;
        }
        private void find(int[] nums, int i, int j, int cnt0, int cnt1) {
            if (Math.min(cnt0, cnt1) * 2 < maxL) return;
            if (cnt0 == cnt1) {
                maxL = cnt0 << 1;
                return;
            }
            if (nums[i] != nums[j]) {
                if (cnt0 < cnt1) {
                    //找到1的去除
                    if (nums[i] == 1) {
                        find(nums, i + 1, j, cnt0, cnt1 - 1);
                    } else {
                        find(nums, i, j - 1, cnt0, cnt1 - 1);
                    }
                } else {
                    //找到0的去除
                    if (nums[i] == 0) {
                        find(nums, i + 1, j, cnt0 - 1, cnt1);
                    } else {
                        find(nums, i, j - 1, cnt0 - 1, cnt1);
                    }
                }
            } else {
                if (nums[i] == 0) {
                    find(nums, i + 1, j, cnt0 - 1, cnt1);
                    find(nums, i, j - 1, cnt0 - 1, cnt1);
                } else {
                    find(nums, i + 1, j, cnt0, cnt1 - 1);
                    find(nums, i, j - 1, cnt0, cnt1 - 1);
                }
            }
        }
    }
    static class Solution2 {
        public int findMaxLength(int[] nums) {
            int n = nums.length;
            int[] cnt0 = new int[nums.length + 1];
            int cnt = 0;
            int maxL = 0;
            for (int i = 0; i < n; i ++) {
                cnt += nums[i] == 0 ? 1 : 0;
                cnt0[i + 1] = cnt;
                if ((i + 1) == cnt << 1) {
                    maxL = i + 1;
                }
            }
            for (int i = 1; i < n - maxL; i ++) {
                for (int j = n - 1; j > i + maxL; j --) {
                    if (((j - i) & 1) == 0) continue;
                    if ((j - i + 1) <= maxL) break;
                    if (cnt0[j + 1] - cnt0[i] == (j - i + 1) / 2)
                        maxL = Math.max(maxL, (j - i + 1));
                }
            }
            return maxL;
        }
    }
    static class Solution3 {
        public int findMaxLength(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) nums[i] = -1;
            }
            HashMap<Integer, Integer> sumToIndex = new HashMap<Integer, Integer>();
            sumToIndex.put(0, -1);
            int sum = 0, max = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (sumToIndex.containsKey(sum)) {
                    max = Math.max(max, i - sumToIndex.get(sum));
                } else {
                    sumToIndex.put(sum, i);
                }
            }
            return max;
        }
    }
    static class Solution4 {
        public int findMaxLength(int[] nums) {
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            int sum = 0, max = 0;
            int n = nums.length;
            for (int i = 0; i < n; i ++) {
                sum += nums[i] == 0 ? -1 : 1;
                if (map.containsKey(sum)) {
                    max = Math.max(max, i - map.get(sum));
                } else {
                    map.put(sum, i);
                }
            }
            return max;
        }
    }
    static class Solution5 {
        public int findMaxLength(int[] nums) {
            int n = nums.length;
            HashMap<Integer, Integer> map = new HashMap<>(n);
            map.put(0, -1);
            int sum = 0, max = 0;
            for (int i = 0; i < n; i ++) {
                sum += nums[i] == 0 ? -1 : 1;
                if (map.containsKey(sum)) {
                    max = Math.max(max, i - map.get(sum));
                } else {
                    map.put(sum, i);
                }
            }
            return max;
        }
    }
    static class Solution6 {
        public int findMaxLength(int[] nums) {
            int n = nums.length;
            int[] cnt = new int[2 * n + 1];
            Arrays.fill(cnt, Integer.MAX_VALUE);
            cnt[n] = -1;
            int sum = 0, max = 0;
            for (int i = 0; i < n; i ++) {
                sum += nums[i] == 0 ? -1 : 1;
                if (cnt[n + sum] != Integer.MAX_VALUE) {
                    max = Math.max(max, i - cnt[n + sum]);
                } else {
                    cnt[n + sum] = i;
                }
            }
            return max;
        }
    }
}
