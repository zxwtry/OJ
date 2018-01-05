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
	
	static class Solution {
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
                    int v = b.start;
                    if (a.start <= v && a.end >= v) {
                        return 0;
                    } else if (a.start > v) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });
            int ri = Collections.binarySearch(is, ni, new Comparator<Interval>() {
                @Override
                public int compare(Interval a, Interval b) {
                    int v = b.end;
                    if (a.start <= v && a.end >= v) {
                        return 0;
                    } else if (a.start > v) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });
            if (li > -1 && ri > -1) {
                //两个都是在内部
                if (li == ri) {
                    return is;
                }
                is.get(li).end = is.get(ri).end;
                //删除[li + 1, ri]
                for (int k = li + 1; k <= ri; k ++) {
                    is.remove(li + 1);
                }
                return is;
            }
            if (li > -1 && ri < 0) {
                //li在内部，ri在外部
                int rv = - (ri + 1);
                rv --;
                is.get(li).end = ni.end;
                //删除[li + 1, rv]
                for (int k = li + 1; k <= rv; k ++) {
                    is.remove(li + 1);
                }
                return is;
            }
            if (li < 0 && ri > -1) {
                //li在外部，ri在内部
                int lv = -(li + 1);
                is.get(ri).start = ni.start;
                //删除[lv, ri - 1]
                for (int k = lv; k <= ri - 1; k ++) {
                    is.remove(lv);
                }
                return is;
            }
            if (li < 0 && ri < 0) {
                //li在外部，ri在外部
                int lv = - (li + 1);
                if (li == ri) {
                    is.add(lv, ni);
                    return is;
                }
                is.get(lv).start = ni.start;
                is.get(lv).end = ni.end;
                int rv = - (ri + 1);
                rv --;
                //删除[lv + 1, rv]
                for (int k = lv + 1; k <= rv; k ++) {
                    is.remove(lv + 1);
                }
                return is;
            }
            return null;
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
