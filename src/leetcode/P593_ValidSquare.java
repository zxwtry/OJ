package leetcode;

import java.util.HashSet;
import java.util.Iterator;

/**
Given the coordinates of four points in 2D space, return whether the four points could construct a square.

The coordinate (x,y) of a point is represented by an integer array with two integers.

Example:
Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: True
Note:

All the input integers are in the range [-10000, 10000].
A valid square has four equal sides with positive length and four equal angles (90-degree angles).
Input points have no order.
 */


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P593_ValidSquare.java
 * @type        P593_ValidSquare
 * @date        2017年5月21日 上午10:21:26
 * @details     
 */
public class P593_ValidSquare {
    public static void main(String[] args) {
        int[] p1 = {1134,-2539};
        int[] p2 = {492,-1255};
        int[] p3 = {-792,-1897};
        int[] p4 = {-150,-3181};
        System.out.println(new Solution().validSquare(p1, p2, p3, p4));
    }
    static public class Solution {
        public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
            HashSet<Long> set = new HashSet<>();
            int[][] p = new int[] [] {p1, p2, p3, p4};
            for (int i = 0; i < 4; i ++) {
                for (int j = i+1; j < 4; j ++) {
                    set.add(calc(p[i], p[j]));
                }
            }
            if (set.size() != 2) return false;
            Iterator<Long> it = set.iterator();
            long v1 = it.next();
            long v2 = it.next();
            long min = Math.min(v1, v2);
            long max = Math.max(v1, v2);
            return min + min == max;
        }
        long calc(int[] p1, int[] p2) {
            return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
        }
    }
}
