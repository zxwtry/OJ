package leetcode;

import java.util.List;

/**
 * 	Given a list of points that form a polygon when joined sequentially, 
 * 	find if this polygon is convex (Convex polygon definition).

	Note:
	
	There are at least 3 and at most 10,000 points.
	Coordinates are in the range -10,000 to 10,000.
	You may assume the polygon formed by given points is always a simple polygon 
	(Simple polygon definition). In other words, we ensure that exactly
	 two edges intersect at each vertex, and that edges otherwise don't intersect each other.
	Example 1:
	
	[[0,0],[0,1],[1,1],[1,0]]
	
	Answer: True
	
	Explanation:
	Example 2:
	
	[[0,0],[0,10],[10,10],[10,0],[5,5]]
	
	Answer: False
	
	Explanation:
 */


/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P469_ConvexPolygon.java
 * @type        P469_ConvexPolygon
 * @date        2016年12月4日 上午11:31:24
 * @details     
 */
public class P469_ConvexPolygon {
	public static void main(String[] args) {
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     leetcode
	 * @file        P469_ConvexPolygon.java
	 * @type        Solution
	 * @date        2016年12月5日 下午3:52:21
	 * @details     艰难AC
	 * @details     323 ms
	 */
	static class Solution {
		int[] xs = null, ys = null;
		int cut = 0;		//区分输入和凸包 
		int n = 0;			//输入长度
		int top = 0;		//凸包栈顶
		int line = 0;		//共线有多少
	    public boolean isConvex(List<List<Integer>> p) {
	    	cut = p.size()+5;
	    	n = p.size();
	    	xs = new int[cut*2+1];
	    	ys = new int[cut*2+1];
	    	int ymin = 0;
	    	for (int i = 0; i < p.size(); i ++) {
	    		xs[i] = p.get(i).get(0);
	    		ys[i] = p.get(i).get(1);
	    		if (ys[i] < ys[ymin] || (ys[i] == ys[ymin] && xs[i] < xs[ymin])) ymin = i;
	    	}
	    	swap(xs , 0, ymin);
	    	swap(ys , 0, ymin);
	    	qsort(1, n-1);
	    	xs[n] = xs[0];
	    	ys[n] = ys[0];
	    	convexHull();
	    	System.out.println(top + "..." + line + "..." + n);
	    	return top + line == n;
	    }
	    private void convexHull() {
	    	int i = 0;
	    	for (; i < 3; i ++) {
	    		xs[cut+i] = xs[i];
	    		ys[cut+i] = ys[i];
	    	}
	    	if (n >= 3 && multiply(cut, cut+1, cut+2) == 0) line++;
	    	top = 2;
	    	for (; i <= n; i ++) {
	    		int m = 0;
	    		while (true) {
	    			m = multiply(cut+top-1, cut+top, i);
	    			if (m > 0) break;
	    			if (m == 0 && i != n) line ++;
	    			top --;
	    		}
	    		top ++;
	    		xs[top+cut] = xs[i];
	    		ys[top+cut] = ys[i];
	    	}
		}
		void swap(int[] arr, int i, int j) {
	    	int t = arr[i];
	    	arr[i] = arr[j];
	    	arr[j] = t;
	    }
	    
	    int distSquare(int i, int j) {
	    	return (xs[i] - xs[j]) * (xs[i] - xs[j]) + (ys[i] - ys[j]) * (ys[i] - ys[j]);
	    }
	    
	    int multiply(int i, int j, int k) {
	    	return (xs[j] - xs[i]) * (ys[k] - ys[i]) - (ys[j] - ys[i]) * (xs[k] - xs[i]);
	    }
	    
	    int cmp(int i, int j) {
	    	int m = multiply(0, i, j);
	    	if (m < 0) return 1;
	    	else if (m == 0 && distSquare(0, i) < distSquare(0, j)) return 1;
	    	else return -1;
	    }
	    
	    void qsort(int i, int j) {
	    	if (i < j) {
	    		int p = pa(i, j);
	    		qsort(i, p - 1);
	    		qsort(p + 1, j);
	    	}
	    }
	    
	    private int pa(int i, int j) {
	    	xs[2*cut] = xs[i];
	    	ys[2*cut] = ys[i];
	    	while (i < j) {
	    		while (i < j && cmp(2*cut, j) <= 0) j--;
	    		xs[i] = xs[j]; ys[i] = ys[j];
	    		while (i < j && cmp(i, 2*cut) <= 0) i ++;
	    		xs[j] = xs[i]; ys[j] = ys[i];
	    	}
	    	xs[i] = xs[2*cut];
	    	ys[i] = ys[2*cut];
			return i;
		}
	}
	
}
