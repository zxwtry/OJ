package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *  Given scores of N athletes, find their relative ranks and the people with 
 *  the top three highest scores, who will be awarded medals: "Gold Medal", 
 *  "Silver Medal" and "Bronze Medal".
 *  
 *  Example 1:
 *  Input: [5, 4, 3, 2, 1]
 *  Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 *  Explanation: The first three athletes got the top three highest scores, 
 *  so they got "Gold Medal", "Silver Medal" and "Bronze Medal". 
 *  For the left two athletes, you just need to output their relative ranks 
 *  according to their scores.
 *  Note:
 *  N is a positive integer and won't exceed 10,000.
 *  All the scores of athletes are guaranteed to be unique.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P506_RelativeRanks.java
 * @type        P506_RelativeRanks
 * @date        2017年3月10日 下午7:50:53
 * @details     Solution1: AC  26ms 51.87%
 * @details     Solution2: AC 124ms  8.57%
 * @details     Solution3: AC  13ms 95.59%
 */
public class P506_RelativeRanks {
    static class Solution1 {
        public String[] findRelativeRanks(int[] nums) {
            ArrayList<Integer> list = new ArrayList<Integer>(nums.length);
            for (int i = 0; i < nums.length; i ++)
                list.add(i);
            Collections.sort(list, new Comparator<Integer>() {
                @Override
                public int compare(Integer int1, Integer int2) {
                    if (nums[int1] > nums[int2]) {
                        return -1;
                    } else if (nums[int1] == nums[int2]) {
                        return 0;
                    }
                    return 1;
                }
            });
            String[] answer = new String[nums.length];
            String[] rec = {"Gold Medal", "Silver Medal", "Bronze Medal"};
            int i = 0;
            for (; i < nums.length && i < 3; i ++) {
                answer[list.get(i)] = rec[i];
            }
            for (; i < nums.length; i ++) {
                answer[list.get(i)] = (i + 1) + "";
            }
            return answer;
        }
    }
    static class Solution2 {
        public String[] findRelativeRanks(int[] nums) {
            int[] rank = new int[nums.length];
            for (int i = 0; i < nums.length; i ++)
                rank[i] = i;
            for (int i = 0; i < nums.length; i ++) {
                for (int j = i + 1; j < nums.length; j ++) {
                    if (nums[rank[i]] < nums[rank[j]]) {
                        int t = rank[i];
                        rank[i] = rank[j];
                        rank[j] = t;
                    }
                }
            }
            String[] answer = new String[rank.length];
            String[] rec = {"Gold Medal", "Silver Medal", "Bronze Medal"};
            int i = 0;
            for (; i < rank.length && i < 3; i ++) {
                answer[rank[i]] = rec[i];
            }
            for (; i < rank.length; i ++) {
                answer[rank[i]] = (i + 1) + "";
            }
            return answer;
        }
    }
    static class Solution3 {
        public String[] findRelativeRanks(int[] nums) {
            int[] rank = new int[nums.length];
            for (int i = 0; i < nums.length; i ++)
                rank[i] = i;
            qsort(nums, rank, 0, nums.length - 1);
            String[] answer = new String[rank.length];
            String[] rec = {"Gold Medal", "Silver Medal", "Bronze Medal"};
            int i = 0;
            for (; i < rank.length && i < 3; i ++) {
                answer[rank[i]] = rec[i];
            }
            for (; i < rank.length; i ++) {
                answer[rank[i]] = (i + 1) + "";
            }
            return answer;
        }
        static void qsort(int[] nums, int[] rank, int sti, int eni) {
            if (sti < eni) {
                int p = partition(nums, rank, sti, eni);
                qsort(nums, rank, sti, p - 1);
                qsort(nums, rank, p + 1, eni);
            }
        }
        private static int partition(int[] nums, int[] rank, int sti, int eni) {
            int save = rank[sti];
            while (sti < eni) {
                while (sti < eni && nums[save] >= nums[rank[eni]]) eni --;
                rank[sti] = rank[eni];
                while (sti < eni && nums[save] <= nums[rank[sti]]) sti ++;
                rank[eni] = rank[sti];
            }
            rank[sti] = save;
            return sti;
        }
    }
}
