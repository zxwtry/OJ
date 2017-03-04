package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * 	Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the
 *  numbers seen so far as a list of disjoint intervals.
 *	
 *	For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the
 *  summary will be:
 *	
 *	[1, 1]
 *	[1, 1], [3, 3]
 *	[1, 1], [3, 3], [7, 7]
 *	[1, 3], [7, 7]
 *	[1, 3], [6, 7]
 *	Follow up:
 *	What if there are lots of merges and the number of disjoint intervals are small compared
 *  to the data stream's size?
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P352_DataStreamAsDisjointIntervals.java
 * @type        P352_DataStreamAsDisjointIntervals
 * @date        2017年2月4日 下午7:29:16
 * @details     SummaryRanges1: AC 279ms 10.88%
 * @details     SummaryRanges2: AC 226ms 27.41%
 * @details     SummaryRanges3: AC 173ms 74.27%
 */
public class P352_DataStreamAsDisjointIntervals {
    static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
        @Override
        public String toString() {
            return start + "..." + end;
        }
    }
	static class SummaryRanges1 {
	    TreeMap<Integer, Interval> map = null;
	    public SummaryRanges1() {
	        map = new TreeMap<Integer, Interval>();
	    }
	    public void addNum(int val) {
	        if (map.containsKey(val)) return;
	        Integer lowKey = map.lowerKey(val);
	        Integer highKey = map.higherKey(val);
	        if (lowKey != null && highKey != null && map.get(lowKey).end + 1 == val && val + 1 == highKey) {
	            //连上两片
	            map.get(lowKey).end = map.get(highKey).end;
	            map.remove(highKey);
	        } else if (lowKey != null && map.get(lowKey).end + 1 >= val) {
	            //连上左边
	            map.get(lowKey).end = Math.max(map.get(lowKey).end, val);
	            //map.get(lowKey).end = val;
	        } else if (highKey != null && val + 1 == highKey) {
	            //连上右边
	            Interval interval = map.get(highKey);
	            interval.start = val;
	            map.remove(highKey);
	            map.put(val, interval);
	        } else {
	            //直接加上
	            map.put(val, new Interval(val, val));
	        }
	    }
	    public List<Interval> getIntervals() {
	        return new ArrayList<>(map.values());
	    }
	}
	static class SummaryRanges2 {
	    TreeMap<Integer, Interval> tree;
	    public SummaryRanges2() {
	        tree = new TreeMap<>();
	    }
	    public void addNum(int val) {
	        if(tree.containsKey(val)) return;
	        Integer l = tree.lowerKey(val);
	        Integer h = tree.higherKey(val);
	        if(l != null && h != null && tree.get(l).end + 1 == val && h == val + 1) {
	            tree.get(l).end = tree.get(h).end;
	            tree.remove(h);
	        } else if(l != null && tree.get(l).end + 1 >= val) {
	            tree.get(l).end = Math.max(tree.get(l).end, val);
	        } else if(h != null && h == val + 1) {
	            tree.put(val, new Interval(val, tree.get(h).end));
	            tree.remove(h);
	        } else {
	            tree.put(val, new Interval(val, val));
	        }
	    }
	    public List<Interval> getIntervals() {
	        return new ArrayList<>(tree.values());
	    }
	}
	/**
	 * Your SummaryRanges object will be instantiated and called as such:
	 * SummaryRanges obj = new SummaryRanges();
	 * obj.addNum(val);
	 * List<Interval> param_2 = obj.getIntervals();
	 */
}
