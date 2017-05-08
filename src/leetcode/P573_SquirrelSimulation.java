package leetcode;

/**
    There's a tree, a squirrel, and several nuts. Positions are represented 
    by the cells in a 2D grid. Your goal is to find the minimal distance for the squirrel 
    to collect all the nuts and put them under the tree one by one. 
    The squirrel can only take at most one nut at one time and can move in four directions - 
    up, down, left and right, to the adjacent cell. The distance is represented by the number of moves.
    
    Example 1:
    Input: 
    Height : 5
    Width : 7
    Tree position : [2,2]
    Squirrel : [4,4]
    Nuts : [[3,0], [2,5]]
    Output: 12
    Explanation:
    
    Note:
    All given positions won't overlap.
    The squirrel can take at most one nut at one time.
    The given positions of nuts have no order.
    Height and width are positive integers. 3 <= height * width <= 10,000.
    The given positions contain at least one nut, only one tree and one squirrel.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P573_SquirrelSimulation.java
 * @type        P573_SquirrelSimulation
 * @date        2017年5月7日 上午10:14:44
 * @details     Solution: AC 12ms 33.33%
 */
public class P573_SquirrelSimulation {
    static public class Solution {
        public int minDistance(int h, int w, int[] t, int[] s, int[][] n) {
            int cut = Integer.MAX_VALUE;
            int sum = 0;
            for (int i = n.length - 1; i > -1; i --) {
                int iv = calc(n[i], t), ov = calc(n[i], s);
                sum += (iv << 1);
                cut = Math.min(cut, ov - iv);
            }
            return sum + cut;
        }
        private int calc(int[] p, int[] t) {
            return Math.abs(p[0] - t[0]) + Math.abs(p[1] - t[1]);
        }
    }
}
