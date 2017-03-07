package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 	Given a set of intervals, for each of the interval i, 
 * 	check if there exists an interval j whose start point is 
 * 	bigger than or equal to the end point of the interval i, 
 * 	which can be called that j is on the "right" of i.
 *	
 *	For any interval i, you need to store the minimum 
 *	interval j's index, which means that the interval 
 *	j has the minimum start point to build the "right" 
 *	relationship for interval i. If the interval j doesn't exist, 
 *	store -1 for the interval i. Finally, you need output 
 *	the stored value of each interval as an array.
 *	
 *	Note:
 *	You may assume the interval's end point is always bigger than its start point.
 *	You may assume none of these intervals have the same start point.
 *	Example 1:
 *	Input: [ [1,2] ]
 *	
 *	Output: [-1]
 *	
 *	Explanation: There is only one interval in the collection, so it outputs -1.
 *	Example 2:
 *	Input: [ [3,4], [2,3], [1,2] ]
 *	
 *	Output: [-1, 0, 1]
 *	
 *	Explanation: There is no satisfied "right" interval for [3,4].
 *	For [2,3], the interval [3,4] has minimum-"right" start point;
 *	For [1,2], the interval [2,3] has minimum-"right" start point.
 *	Example 3:
 *	Input: [ [1,4], [2,3], [3,4] ]
 *	
 *	Output: [-1, 2, -1]
 *	
 *	Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
 *	For [2,3], the interval [3,4] has minimum-"right" start point.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P436_FindRightInterval.java
 * @type        P436_FindRightInterval
 * @date        2017年3月7日 上午10:13:26
 * @details     Solution1: AC 28ms 91.59%
 */
public class P436_FindRightInterval {
	static class Solution1 {
	    public int[] findRightInterval(Interval[] intervals) {
	        Integer[] indices = new Integer[intervals.length];
	        for (int index = 0; index < indices.length; index ++)
	            indices[index] = index;
	        Arrays.sort(indices, new Comparator<Integer>() {
                @Override
                public int compare(Integer it1, Integer it2) {
                    return intervals[indices[it1]].start - intervals[indices[it2]].start;
                }
            });
	        int[] answer = new int[intervals.length];
	        for (int index = 0; index < answer.length; index ++) {
	            int binIndex = binarySearch(intervals, indices, intervals[index].end);
	            binIndex = binIndex >= answer.length ? -1 : binIndex;
	            answer[index] = binIndex;
	        }
	        return answer;
	    }
        private int binarySearch(Interval[] intervals, Integer[] indices, int start) {
            if (start > intervals[indices[indices.length - 1]].start) return indices.length;
            int startIndex = 0, endIndex = indices.length - 1;
            int middleIndex = 0;
            while (startIndex < endIndex) {
                middleIndex = startIndex + (endIndex - startIndex) / 2 ;
                if (intervals[indices[middleIndex]].start > start) {
                    endIndex = middleIndex;
                } else if (intervals[indices[middleIndex]].start == start) {
                    return indices[middleIndex];
                } else {
                    startIndex = middleIndex + 1;
                }
            }
            return indices[startIndex];
        }
	}
	static class Interval {
		int start;
		int end;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }
	}
}
