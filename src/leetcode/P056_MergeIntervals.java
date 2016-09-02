package leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/*
 * 	Given a collection of intervals, merge all overlapping intervals.

	For example,
	Given [1,3],[2,6],[8,10],[15,18],
	return [1,6],[8,10],[15,18].
 */

public class P056_MergeIntervals {
	public static void main(String[] args) {
		List<Interval> list = new LinkedList<Interval>();
		list.add(new Interval(5, 5));
		list.add(new Interval(2, 3));
		list.add(new Interval(2, 2));
		list.add(new Interval(3, 4));
		list.add(new Interval(3, 4));
		list = generate(new int[][]{{362,367},{314,315},{133,138},{434,443},{202,203},
			{144,145},{229,235},{205,212},{314,323},{128,129},{413,414},{342,345},
			{43,49},{333,342},{173,178},{386,391},{131,133},{157,163},{187,190},
			{186,186},{17,19},{63,69},{70,79},{386,391},{98,102},{236,239},{195,195},
			{338,338},{169,170},{151,153},{409,416},{377,377},{90,96},{156,165},
			{182,186},{371,372},{228,233},{297,306},{56,61},{184,190},{401,403},
			{221,228},{203,212},{39,43},{83,84},{66,68},{80,83},{32,32},{182,182},
			{300,306},{235,238},{267,272},{458,464},{114,120},{452,452},{372,375},
			{275,280},{302,302},{5,9},{54,62},{237,237},{432,439},{415,421},{340,347},
			{356,358},{165,168},{15,17},{259,265},{201,204},{192,197},{376,383},
			{210,211},{362,367},{481,488},{59,64},{307,315},{155,164},{465,467},
			{55,60},{20,24},{297,304},{207,210},{322,328},{139,142},{192,195},
			{28,36},{100,108},{71,76},{103,105},{34,38},{439,441},{162,168},
			{433,433},{368,369},{137,137},{105,112},{278,280},{452,452},
			{131,132},{475,480},{126,129},{95,104},{93,99},{394,403},{70,78}});
		List<Interval> ans = new Solution().merge(list);
		Iterator<Interval> it = ans.iterator();
		while (it.hasNext()) {
			Interval temp = it.next();
			System.out.println(temp.start + "..." + temp.end);
		}
	}
	/*
	 * 	16 ms
	 * 	39.87%
	 */
	static class Solution {
		List<Interval> ans = new LinkedList<Interval>();
	    public List<Interval> merge(List<Interval> intervals) {
	    	if (intervals == null || intervals.size() == 0)
	    		return ans;
	    	HashMap<Integer, Interval> map = new HashMap<Integer, Interval>();
	    	Iterator<Interval> it = intervals.iterator();
	    	while (it.hasNext()) {
	    		Interval temp = it.next();
	    		if (! (map.containsKey(temp.start) && map.get(temp.start).end >= temp.end))
	    			map.put(temp.start, temp);
	    	}
	    	int end_pre = 0;
	    	Interval last = null;
	    	TreeSet<Integer> set = new TreeSet<Integer>(map.keySet());
	    	for (int start : set) {
	    		Interval cur = map.get(start);
	    		if (last == null) {
 	    			last = cur;
 	    			end_pre = last.end;
	    		} else {
	    			if (cur.start <= end_pre || cur.start == last.start) {
	    				end_pre = Math.max(end_pre, cur.end);
	    			} else {
	    				last.end = end_pre;
	    				ans.add(last);
	    				last = cur;
	    				end_pre = Math.max(end_pre, cur.end);
	    			}
	    		}
	    	}
	    	last.end = end_pre;
	    	ans.add(last);
	        return ans;
	    }
	}
	/*
	 * 	14 ms
	 * 	74.70% 
	 */
	static class Solution2 {
		List<Interval> ans = new LinkedList<Interval>();
	    public List<Interval> merge(List<Interval> intervals) {
	    	if (intervals == null || intervals.size() == 0)
	    		return ans;
	    	HashMap<Integer, Interval> map = new HashMap<Integer, Interval>();
	    	Iterator<Interval> it = intervals.iterator();
	    	while (it.hasNext()) {
	    		Interval temp = it.next();
	    		if (! (map.containsKey(temp.start) && map.get(temp.start).end >= temp.end))
	    			map.put(temp.start, temp);
	    	}
	    	int end_pre = 0;
	    	Interval last = null;
	    	TreeSet<Integer> set = new TreeSet<Integer>(map.keySet());
	    	for (int start : set) {
	    		Interval cur = map.get(start);
	    		if (last == null) {
 	    			last = cur;
 	    			end_pre = last.end;
	    		} else {
	    			if (cur.start <= end_pre) {
	    				end_pre = Math.max(end_pre, cur.end);
	    			} else {
	    				last.end = end_pre;
	    				ans.add(last);
	    				last = cur;
	    				end_pre = Math.max(end_pre, cur.end);
	    			}
	    		}
	    	}
	    	last.end = end_pre;
	    	ans.add(last);
	        return ans;
	    }
	}
	/*
	 * 	16 ms
	 * 	39.87%
	 */
	static class Solution3 {
		List<Interval> ans = new LinkedList<Interval>();
	    public List<Interval> merge(List<Interval> intervals) {
	    	if (intervals == null || intervals.size() == 0)
	    		return ans;
	    	HashMap<Integer, Interval> map = new HashMap<Integer, Interval>();
	    	Iterator<Interval> it = intervals.iterator();
	    	TreeSet<Integer> set = new TreeSet<Integer>();
	    	while (it.hasNext()) {
	    		Interval temp = it.next();
	    		if (! (map.containsKey(temp.start) && map.get(temp.start).end >= temp.end)) {
	    			map.put(temp.start, temp);
	    			set.add(temp.start);
	    		}
	    	}
	    	int end_pre = 0;
	    	Interval last = null;
	    	for (int start : set) {
	    		Interval cur = map.get(start);
	    		if (last == null) {
 	    			last = cur;
 	    			end_pre = last.end;
	    		} else {
	    			if (cur.start <= end_pre) {
	    				end_pre = Math.max(end_pre, cur.end);
	    			} else {
	    				last.end = end_pre;
	    				ans.add(last);
	    				last = cur;
	    				end_pre = Math.max(end_pre, cur.end);
	    			}
	    		}
	    	}
	    	last.end = end_pre;
	    	ans.add(last);
	        return ans;
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
		for (int i = 0; i != input.length; i ++) {
			ans.add(new Interval(input[i][0], input[i][1]));
		}
		return ans;
	}
}
