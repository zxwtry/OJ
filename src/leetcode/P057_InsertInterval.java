package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * 	Given a set of non-overlapping intervals, 
 * 	insert a new interval into the intervals (merge if necessary).

	You may assume that the intervals were initially sorted according
	 to their start times.
	[
	Example 1:
	Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
	
	Example 2:
	Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as 
	[1,2],[3,10],[12,16].
	
	This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
	
	input help:
		{{1,2},{3,5},{6,7},{8,10},{12,16}}
 */

public class P057_InsertInterval {
	public static void main(String[] args) {
//		List<Integer> zxw = new ArrayList<Integer>();
//		zxw.add(0);
//		zxw.add(1);
//		zxw.add(2);
//		zxw.add(3);
//		zxw.add(4);
//		zxw.add(4, 1999);
//		tools.Utils.B_打印List_Integer(zxw);

//		for (int i = -2; i < 20; i ++) {
			List<Interval> input = generate(new int[][] {
//				{1,4}, {10,14},{19,25},{29,34}
				{1,4}
			});
			Interval newInterval = new Interval(2, 100);
			new Solution().insert(input, newInterval);
			print(input);
//			System.out.println(i + "..." + new Solution().getIndexBEEnd(input, newInterval));
//			new Solution().getIndex(input, newInterval);
//		}
	}
	
	static class Solution2 {
        public List<Interval> insert(List<Interval> is, Interval ni) {
            if (ni == null) {
                return is;
            }
            int in = is == null ? 0 : is.size();
            if (in == 0) {
                return Arrays.asList(ni);
            }
            int li = Collections.binarySearch(is, ni, new Comparator<Interval>() {
                @Override
                public int compare(Interval a, Interval b) {
                    
                    return 0;
                }
            });
        }
	}
	
	
	static class Solution {
	    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
	    	if (intervals == null) {
	    		intervals = new LinkedList<Interval>();
	    		intervals.add(newInterval);
	    		return intervals;
	    	}
	    	if (intervals.size() == 0) {
	    		intervals.add(newInterval);
	    		return intervals;
	    	}
	    	int sti = getIndexGEStart(intervals, newInterval);
	    	int eni = getIndexBEEnd(intervals, newInterval);
	    	if (sti == -1) {
	    		intervals.add(0, new Interval(newInterval.start, Math.min(intervals.get(0).start, newInterval.end)));
	    		sti ++;
	    		eni ++;
	    	}
	    	if (eni == intervals.size()) {
	    		intervals.add(eni, new Interval(Math.max(intervals.get(eni - 1).end, newInterval.start), newInterval.end));
	    	}
	    	Interval interval_sti = intervals.get(sti);
    		Interval interval_eni = intervals.get(eni);
	    	if (sti == eni) {
	    		return intervals;
	    	} else if (sti > eni) {
	    		
	    	} else {
	    		if (newInterval.start > interval_sti.end) {
	    			if (newInterval.end < interval_eni.start) {
	    				for (int i = eni - 1; i > sti; i --)
		    				intervals.remove(i);
		    			intervals.add(sti + 1, newInterval);
	    			} else {
		    			intervals.get(eni).start = newInterval.start;
		    			for (int i = eni - 1; i > sti; i --)
		    				intervals.remove(i);
	    			}
	    		} else {
	    			if (newInterval.end < interval_eni.start) {
	    				interval_sti.end = newInterval.end;
	    				for (int i = eni - 1; i > sti; i --)
	    					intervals.remove(i);
	    			} else {
	    				interval_sti.end = interval_eni.end;
	    				for (int i = eni; i > sti; i --)
	    					intervals.remove(i);
	    			}
	    		}
	    	}
	    	return intervals;
	    }
	    /*
	     *   newInterval.start	>=  intervals.get(ans).start
	     */
	    int getIndexGEStart(List<Interval> intervals, Interval newInterval) {
	    	int sti = 0, eni = intervals.size() - 1;
	    	if (newInterval.start >= intervals.get(eni).start)
	    		return eni;
	    	while (sti < eni) {
	    		int mid = (sti + eni) >> 1;
	    		int temp = intervals.get(mid).start - newInterval.start;
	    		if (temp > 0) {
	    			eni = mid;
	    		} else if (temp == 0) {
	    			return mid;
	    		} else {
	    			sti = mid + 1;
	    		}
	    	}
	    	return sti - 1;
	    }
	    /*
	     *   newInterval.end	<=  intervals.get(ans).end
	     */
	    int getIndexBEEnd(List<Interval> intervals, Interval newInterval) {
	    	int sti = 0, eni = intervals.size() - 1;
	    	if (intervals.get(sti).end >= newInterval.end)
	    		return sti;
	    	while (sti < eni) {
	    		int mid = ((sti + eni) >> 1) + 1;
	    		int temp = intervals.get(mid).end - newInterval.end;
	    		if (temp > 0 ) {
	    			eni = mid - 1;
	    		} else if (temp == 0) {
	    			return mid;
	    		} else {
	    			sti = mid;
	    		}
	    	}
	    	return sti + 1;
	    }
	}
	static class Interval {
		int start;
		int end;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }
	}
	static List<Interval> generate(int[][] input) {
		List<Interval> ans = new LinkedList<Interval>();
		for (int i = 0; i != input.length; i ++)
			ans.add(new Interval(input[i][0], input[i][1]));
		return ans;
	}
	static void print(List<Interval> ans) {
		Iterator<Interval> it = ans.iterator();
		while (it.hasNext()) {
			Interval temp = it.next();
			System.out.println(temp.start + "..." + temp.end);
		}
	}
}
