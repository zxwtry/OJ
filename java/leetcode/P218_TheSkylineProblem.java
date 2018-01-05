package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 	A city's skyline is the outer contour of the silhouette formed by
 *  all the buildings in that city when viewed from a distance. Now
 *  suppose you are given the locations and height of all the buildings
 *  as shown on a cityscape photo (Figure A), write a program to output
 *  the skyline formed by these buildings collectively (Figure B).
 *  
 *	The geometric information of each building is represented by a triplet 
 *	of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the 
 *	left and right edge of the ith building, respectively, and Hi is its 
 *	height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, 
 *	and Ri - Li > 0. You may assume all buildings are perfect rectangles 
 *	grounded on an absolutely flat surface at height 0.
 *	
 *	For instance, the dimensions of all buildings in Figure A are recorded 
 *	as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 *	
 *	The output is a list of "key points" (red dots in Figure B) in the 
 *	format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines 
 *	a skyline. A key point is the left endpoint of a horizontal line segment. 
 *	Note that the last key point, where the rightmost building ends, 
 *	is merely used to mark the termination of the skyline, and always 
 *	has zero height. Also, the ground in between any two adjacent buildings 
 *	should be considered part of the skyline contour.
 *	
 *	For instance, the skyline in Figure B should be represented as:
 *	[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 *	
 *	Notes:
 *	
 *	The number of buildings in any input list is guaranteed to be in 
 *	the range [0, 10000].
 *	The input list is already sorted in ascending order by the left 
 *	x position Li.
 *	The output list must be sorted by the x position.
 *	There must be no consecutive horizontal lines of equal height 
 *	in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 
 *	is not acceptable; the three lines of height 5 should be merged into one in the 
 *	final output as such: [...[2 3], [4 5], [12 7], ...]
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P218_TheSkylineProblem.java
 * @type        P218_TheSkylineProblem
 * @date        2016年12月29日 下午9:47:43
 * @details     Solution1: AC 319ms 37.79% 
 */
public class P218_TheSkylineProblem {
	static class Solution1 {
	    public List<int[]> getSkyline(int[][] buildings) {
	    	List<int[]> ans = new LinkedList<int[]>();
	    	if (buildings == null || buildings.length < 1)
	    		return ans;
	    	List<int[]> verticalLines = new ArrayList<int[]>(buildings.length * 2);
	    	for (int[] building : buildings) {
	    		verticalLines.add(new int[] {building[0], building[2]});
	    		verticalLines.add(new int[] {building[1], -building[2]});
	    	}
	    	Collections.sort(verticalLines, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[0] < o2[0]) return -1;
					else if (o1[0] == o2[0]) return o2[1] - o1[1];
					return 1;
				}
			});
	    	PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(verticalLines.size(), 
	    			new Comparator<Integer>() {
						@Override
						public int compare(Integer o1, Integer o2) {
							return o2 - o1;
						}
			});
	    	int pre = 0, cur = 0;
	    	for (int[] vl : verticalLines) {
	    		if (vl[1] > 0) {
	    			maxHeap.add(vl[1]);
	    			cur = maxHeap.peek();
	    		} else {
	    			maxHeap.remove(-vl[1]);
	    			cur = maxHeap.peek() == null ? 0 : maxHeap.peek();
	    		}
	    		if (cur != pre) {
	    			ans.add(new int[] {vl[0], cur});
	    			pre = cur;
	    		}
	    	}
	    	return ans;
	    }
	}
	static class Solution {
	    //大顶推比较器
	    public class MaxCom implements Comparator<Integer> {
	        public int compare(Integer a, Integer b){
	            return b - a ; // 大的在堆的顶端
	        }
	    } 
	    //数组比较器
	    public class ArrayCom implements Comparator<int[]> {
	        public int compare(int[] a, int[] b) {
	            if(a[0] != b[0]) return a[0] - b[0];  //先按左边界进行排序
	            return b[1] - a[1];  // 相等 则高的在前面
	        }
	    }
	    public List<int[]> getSkyline(int[][] buildings) {  
	        List<int[]> res = new ArrayList<int[]>();  
	        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new MaxCom());
	        List<int[]> ver = new ArrayList<int[]>();  // 记录每一个竖线
	        for(int i = 0 ; i < buildings.length ; i++){
	            int[] temp = buildings[i]; 
	            ver.add(new int[]{temp[0], temp[2]});  // 左边界竖线
	            ver.add(new int[]{temp[1], -temp[2]});  // 右边界竖线 为了区分 存入负值
	        }
	        Collections.sort(ver, new ArrayCom());
	        int cur = 0, pre = 0;
	        for(int i = 0 ; i < ver.size() ; i++){
	            int[] temp = ver.get(i);
	            if(temp[1] > 0) {  // 左边界
	                maxHeap.offer(temp[1]);  //高度入队
	                cur = maxHeap.peek(); // 当前最高的
	            }else { // 右边界
	                maxHeap.remove(-temp[1]);  // 将对应的高度从堆中删除 这里就是右边存负值的方便之处
	                cur = (maxHeap.peek() == null ? 0 : maxHeap.peek()); // 如果右边界是最后一个则高度为0，否则更新当前最高
	            }
	            if(cur != pre) {  // 与上一个最高的不相等
	                res.add(new int[]{temp[0], cur});
	                pre = cur;  // 保存当前高度为下一次的前面高度
	            }
	        }
	        return res;       
	    }
	}
}
