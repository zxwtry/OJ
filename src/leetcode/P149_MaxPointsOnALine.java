package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import leetcode.P149_MaxPointsOnALine.Solution2.Pair;

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
 * @details     Solution2: AC  30ms 54.35%
 */
public class P149_MaxPointsOnALine {
    public static void main(String[] args) {
//        [[0,0],[94911151,94911150],[94911152,94911151]]
        Point p0 = new Point(0, 0);
        Point p1 = new Point(94911151, 94911150);
//        Point p2 = new Point(94911152, 94911151);
        Point p2 = new Point(-94911150, -94911151);
        
        Pair pair = new Pair(p1.x - p0.x, p1.y - p0.y);
//        System.out.println(pair.oneline(p2.x - p0.x, p2.y - p0.y));
        
        
        
        Point[] points = new Point[]{p0, p1, p2};
        System.out.println(new Solution1().maxPoints(points));
        System.out.println(new Solution2().maxPoints(points));
    }
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
	static class Solution2 {
		public int maxPoints(Point[] p) {
			if (p == null || p.length < 2) return p == null ? 0 : p.length;
			HashMap<Pair, Integer> map = new HashMap<Pair, Integer>();
			int ans = 0;
			for (int i = 0; i < p.length; i ++) {
				map.clear();
				int same = 0;
				int max = 1;
				for (int j = 0; j < p.length; j ++) {
					if (i == j) continue;
					if (p[i].x == p[j].x && p[i].y == p[j].y) {
						same ++;
						continue;
					}
					Pair pair = new Pair(p[j].x - p[i].x, p[j].y - p[i].y);
					Integer v = map.get(pair);
					int nv = v == null ? 2 : v + 1;
					max = Math.max(max, nv);
					if (v == null) map.put(pair, nv);
				}
				ans = Math.max(ans, max + same);
			}
			return ans;
		}
		static class Pair implements Comparable<Pair>{
		    int a, b;
		    
		    public Pair(int a, int b) {
		        int c = gcd(Math.abs(a), Math.abs(b));
		        a = a / c;
		        b = b / c;
		        if (a < 0 && b < 0) {
		            a = -a;
		            b = -b;
		        } else if (((a >>> 31 ) ^ (b >>> 31)) == 1) {
		            a = -Math.abs(a);
		            b = Math.abs(b);
		        }
		        this.a = a;
		        this.b = b;
            }
		    
		    public boolean oneline(long dx, long dy) {
		        return dx * a == dy * a;
		    }
		    
		    private int gcd(int a, int b) {
		        if (a < b) return gcd(b, a);
		        int t = 0;
		        while (b != 0) {
		            t = a % b;
		            a = b;
		            b = t;
		        }
		        return a;
		    }
		    
            @Override
            public int compareTo(Pair o) {
                if (o.a > this.a) return 1;
                else if (o.a < this.a) return -1;
                if (o.b > this.b) return 1;
                else if (o.b < this.b) return -1;
                return 0;
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
