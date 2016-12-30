package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 * 	Given n points on a 2D plane, find the maximum number of 
 * 	points that lie on the same straight line.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P149_MaxPointsOnALine.java
 * @type        P149_MaxPointsOnALine
 * @date        2016年12月30日 上午10:26:23
 * @details     Solution1: AC 128ms 3.48%
 */
public class P149_MaxPointsOnALine {
	static class Solution1 {
	    public int maxPoints(Point[] points) {
	    	if (points == null || points.length < 2) return points == null ? 0 : points.length;
	    	HashMap<Key, HashSet<Point>> map = new HashMap<Key, HashSet<Point>>();
	    	int ans = 0;
	    	for (int i = 0; i < points.length; i ++) {
	    		for (int j = 0; j < i; j ++) {
	    			if (points[i].x == points[j].x && points[i].y == points[j].y) {
	    				for (Entry<Key, HashSet<Point>> e : map.entrySet()) {
	    					if (e.getValue().contains(points[i]) || 
	    							e.getValue().contains(points[j])) {
	    						e.getValue().add(points[i]);
	    						e.getValue().add(points[j]);
	    						ans = Math.max(ans, e.getValue().size());
	    					}
	    				}
	    				continue;	
	    			}
	    			Key key = getKey(points[i], points[j]);
    				HashSet<Point> v = map.get(key);
	    			v = v == null ? new HashSet<Point>() : v;
	    			v.add(points[i]); v.add(points[j]);
	    			ans = Math.max(ans, v.size());
	    			map.put(key, v);
	    		}
	    	}
	        return map.isEmpty() ? points.length : ans;
	    }
	    public Key getKey(Point p1, Point p2) {
	    	int a = p1.y - p2.y;
	    	int b = p2.x - p1.x;
	    	int c = p1.x * p2.y - p2.x * p1.y;
	    	int m = getGCD(getGCD(Math.abs(a), Math.abs(b)), getGCD(Math.abs(a), Math.abs(c)));
	    	if (a / m < 0) m = -m;
	    	a /= m;
	    	b /= m;
	    	c /= m;
	    	return new Key(a, b, c);
	    }
	    public int getGCD(int a, int b) {
	    	if (a<b) return getGCD(b, a);
	    	if (b == 0) return a;
	    	int k = 0;
	    	while (true) {
	    		k = a%b;
	    		if (k == 0) break;
	    		a = b;
	    		b = k;
	    	}
	    	return b;
	    }
	    static class Key {
			int a, b, c;
			public Key(int a, int b, int c) {
				this.a = a;
				this.b = b;
				this.c = c;
			}
			@Override
			public boolean equals(Object obj) {
				if (! (obj instanceof Key)) return false;
				Key k = (Key) obj;
				return this.a == k.a && this.b == k.b && this.c == k.c;
			}
			@Override
			public int hashCode() {
				return (int)(((long)a*10000 + (long)b*100 + c) % 100000007);
			}
		}
	}
	static class Point {
		int x;
		int y;
		Point() { x = 0; y = 0; }
		Point(int a, int b) { x = a; y = b; }
	}
}
