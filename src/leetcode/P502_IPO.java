package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 	Suppose LeetCode will start its IPO soon. In order to sell a good price of 
 *  its shares to Venture Capital, LeetCode would like to work on some projects 
 *  to increase its capital before the IPO. Since it has limited resources, 
 *  it can only finish at most k distinct projects before the IPO. 
 *  Help LeetCode design the best way to maximize its total capital after 
 *  finishing at most k distinct projects.
 *  
 *  You are given several projects. For each project i, it has a pure profit Pi 
 *  and a minimum capital of Ci is needed to start the corresponding project. 
 *  Initially, you have W capital. When you finish a project, you will obtain 
 *  its pure profit and the profit will be added to your total capital.
 *  
 *  To sum up, pick a list of at most k distinct projects from given projects 
 *  to maximize your final capital, and output your final maximized capital.
 *  
 *  Example 1:
 *  Input: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].
 *  
 *  Output: 4
 *  
 *  Explanation: Since your initial capital is 0, you can only start the project indexed 0.
 *               After finishing it you will obtain profit 1 and your capital becomes 1.
 *               With capital 1, you can either start the project indexed 1 or the project indexed 2.
 *               Since you can choose at most 2 projects, you need to finish the project indexed
 *                2 to get the maximum capital.
 *               Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
 *  Note:
 *  You may assume all numbers in the input are non-negative integers.
 *  The length of Profits array and Capital array will not exceed 50,000.
 *  The answer is guaranteed to fit in a 32-bit signed integer.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P502_IPO.java
 * @type        P502_IPO
 * @date        2017年2月8日 下午11:25:12
 * @details     Solution1: TLE
 * @details     Solution2: AC 160ms 12.34%
 * @details     Solution3: AC  75ms 61.25%
 */
public class P502_IPO {
	static class Solution1 {
	    int maxC = 0;
	    int mk = 0, mw = 0;
	    int[] mp = null, mc = null;
	    boolean[] iv = null;
	    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
	        maxC = Math.max(maxC, W);
	        mk = k;
	        mw = W;
	        mp = Profits;
	        mc = Capital;
	        iv = new boolean[mp.length];
	        find(k, W);
	        return maxC;
	    }
        private void find(int k, int w) {
            if (k == 0) return;
            for (int i = 0; i < iv.length; i ++) {
                if (! iv[i] && w >= mc[i]) {
                    iv[i] = true;
                    maxC = Math.max(maxC, w + mp[i]);
                    find(k - 1, w + mp[i]);
                    iv[i] = false;
                }
            }
        }
	}
	static class Solution2 {
	    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
	        PriorityQueue<int[]> pqCap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
	        PriorityQueue<int[]> pqPro  = new PriorityQueue<>((a, b) -> (b[1] - a[1]));
	        for (int i = 0; i < Profits.length; i++) {
	            pqCap.add(new int[] {Capital[i], Profits[i]});
	        }
	        for (int i = 0; i < k; i++) {
	            while (!pqCap.isEmpty() && pqCap.peek()[0] <= W) {
	                pqPro.add(pqCap.poll());
	            }
	            if (pqPro.isEmpty()) break;
	            W += pqPro.poll()[1];
	        }
	        return W;
	    }
	}
	static class Solution3 {
	    public int findMaximizedCapital(int k, int W, int[] pro, int[] cap) {
	        PriorityQueue<int[]> c = new PriorityQueue<int[]>(new Comparator<int[]>() {
                @Override
                public int compare(int[] arr1, int[] arr2) {
                    if (arr1[0] < arr2[0]) return -1;
                    else if (arr1[0] > arr2[0]) return 1;
                    return 0;
                }
            });
	        PriorityQueue<int[]> p = new PriorityQueue<int[]>(new Comparator<int[]>() {
                @Override
                public int compare(int[] arr1, int[] arr2) {
                    if (arr1[1] < arr2[1]) return 1;
                    else if (arr1[1] > arr2[1]) return -1;
                    return 0;
                }
            });
	        for (int i = 0; i < pro.length; i ++)
	            c.add(new int[] {cap[i], pro[i]});
	        for (int i = 0; i < k; i ++) {
	            while (! c.isEmpty() && c.peek()[0] <= W)
	                p.add(c.poll());
	            if (p.isEmpty()) break;
	            W += p.poll()[1];
	        }
	        return W;
	    }
	}
}
