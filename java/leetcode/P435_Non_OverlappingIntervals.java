package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 	Given a collection of intervals, find the minimum number of intervals
 *  you need to remove to make the rest of the intervals non-overlapping.
 *	
 *	Note:
 *	You may assume the interval's end point is always bigger than its start point.
 *	Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 *	Example 1:
 *	Input: [ [1,2], [2,3], [3,4], [1,3] ]
 *	
 *	Output: 1
 *	
 *	Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 *	Example 2:
 *	Input: [ [1,2], [1,2], [1,2] ]
 *	
 *	Output: 2
 *	
 *	Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 *	Example 3:
 *	Input: [ [1,2], [2,3] ]
 *	
 *	Output: 0
 *	
 *	Explanation: You don't need to remove any of the intervals since 
 *	they're already non-overlapping.
 */


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P435_Non_OverlappingIntervals.java
 * @type        P435_Non_OverlappingIntervals
 * @date        2017年3月6日 下午10:11:27
 * @details     Solution1: AC 8ms 83.78%
 * @details     Solution2: AC 8ms 83.78%
 */
public class P435_Non_OverlappingIntervals {
	static class Solution1 {
	    public int eraseOverlapIntervals(Interval[] intervals) {
	        if (intervals.length == 0)  return 0;
	        Arrays.sort(intervals, new myComparator());
	        int end = intervals[0].end;
	        int count = 1;
	        for (int i = 1; i < intervals.length; i++) {
	            if (intervals[i].start >= end) {
	                end = intervals[i].end;
	                count++;
	            }
	        }
	        return intervals.length - count;
	    }
	    class myComparator implements Comparator<Interval> {
	        public int compare(Interval a, Interval b) {
	            return a.end - b.end;
	        }
	    }
	}
	static class Solution2 {
	    public int eraseOverlapIntervals(Interval[] intervals) {
	        Arrays.sort(intervals, new Comparator<Interval>() {
                @Override
                public int compare(Interval it1, Interval it2) {
                    return it1.end - it2.end;
                }
            });
	        int end = Integer.MIN_VALUE, count = 0;
	        for (Interval interval : intervals)
	            if (interval.start >= end) {
	                end = interval.end;
	                count ++;
	            }
	        return intervals.length - count;
	    }
	}
	static class Interval {
		int start;
		int end;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }
	}
}
