package leetcode;

import java.util.HashMap;

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
 * @details     Solution:  AC  36ms 45.82%
 * @details     Solution2: AC  47ms 28.50%
 */
public class P149_MaxPointsOnALine {
	static class Solution {
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
					map.put(pair, nv);
				}
				ans = Math.max(ans, max + same);
			}
			return ans;
		}
		static class Pair {
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
            public boolean equals(Object obj) {
                if (! (obj instanceof Pair)) return false;
                Pair p = (Pair) obj;
                return this.a == p.a && this.b == p.b;
            }
            @Override
            public int hashCode() {
                return this.a * 31 + this.b;
            }
		}
	}
	static class Point {
		int x;
		int y;
		Point() { x = 0; y = 0; }
		Point(int a, int b) { x = a; y = b; }
	}
    static class Solution2 {
        public int maxPoints(Point[] p) {
            if (p == null || p.length < 2) return p == null ? 0 : p.length;
            HashMap<Double, Integer> map = new HashMap<Double, Integer>();
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
                    double pair = calc(p[j].x - p[i].x, p[j].y - p[i].y);
                    Integer v = map.get(pair);
                    int nv = v == null ? 2 : v + 1;
                    max = Math.max(max, nv);
                    map.put(pair, nv);
                }
                ans = Math.max(ans, max + same);
            }
            return ans;
        }
        private double calc(int a, int b) {
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
            double ans = 0.0;
            ans += a;
            ans = ans * 200000000;
            ans += b;
            return ans;
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
    }
}
