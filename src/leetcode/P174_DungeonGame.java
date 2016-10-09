package leetcode;

/*
 * 	The demons had captured the princess (P) and imprisoned her 
 * in the bottom-right corner of a dungeon. The dungeon consists 
 * of M x N rooms laid out in a 2D grid. Our valiant knight (K) 
 * was initially positioned in the top-left room and must fight 
 * his way through the dungeon to rescue the princess.

	The knight has an initial health point represented by 
	a positive integer. If at any point his health point 
	drops to 0 or below, he dies immediately.
	
	Some of the rooms are guarded by demons, so the knight 
	loses health (negative integers) upon entering these rooms; 
	other rooms are either empty (0's) or contain magic orbs that
	 increase the knight's health (positive integers).
	
	In order to reach the princess as quickly as possible, 
	the knight decides to move only rightward or downward in each step.
	
	
	Write a function to determine the knight's minimum initial health 
	so that he is able to rescue the princess.
	
	For example, given the dungeon below, the initial health of 
	the knight must be at least 7 if he follows the optimal 
	path RIGHT-> RIGHT -> DOWN -> DOWN.
	
	-2 (K)	-3	3
	-5	-10	1
	10	30	-5 (P)
	
	Notes:
	
	The knight's health has no upper bound.
	Any room can contain threats or power-ups, even the first room
	 the knight enters and the bottom-right room where the princess 
	 is imprisoned.
 */

public class P174_DungeonGame {
	public static void main(String[] args) {
//		int[][] d = new int[][] {
//			{-2, -3, 3},
//			{-5, -10, 1},
//			{10, 30, -5}
//		};
//		StandardSolution s = new StandardSolution();
//		System.out.println(s.calculateMinimumHP(d));
		
		
		int len = 2, max = 100, min = -200;
		int[][] arr = new int[len][len];
		for (int i = 0; i < len; i ++) {
			arr[i] = tools.Random随机生成器.A_生成一个随机数据(len, min, max);
		}
		String st = tools.Utils.LEETCODE_int_二位数组_序列化_(arr);
		System.out.println(st);
		
		
		
//		int[][] d = generateSquare(4);
//		String s = tools.Utils.LEETCODE_int_二位数组_序列化_(d);
//		System.out.println(s);
		
	}
	static int[][] generateSquare(int len) {
		int[][] ans = new int[len][len];
		for (int i = 0; i < len; i ++) {
			ans[i] = tools.Random随机生成器.A_生成一个随机数据(len, - 5 * len, len);
		}
		return ans;
	}
	
	static class Solution {
	    public int calculateMinimumHP(int[][] d) {
	    	int[][] min = new int[d.length][d[0].length];
	    	int[][] dp = new int[d.length][d[0].length];
	    	min[0][0] = d[0][0];
	    	dp[0][0] = d[0][0];
	    	for (int i = 1; i < d.length; i ++) {
	    		if (d[i][0] >= 0) {
	    			min[i][0] = min[i - 1][0];
	    		} else {
	    			min[i][0] = min[i - 1][0] + d[i][0];
	    		}
	    		dp[i][0] = dp[i - 1][0] + d[i][0];
	    	}
	    	for (int j = 1; j < d[0].length; j ++) {
	    		if (d[0][j] >= 0) {
	    			min[0][j] = min[0][j - 1];
	    		} else {
	    			min[0][j] = min[0][j - 1] + d[0][j];
	    		}
	    		dp[0][j] = dp[0][j - 1] + d[0][j];
	    	}
	    	for (int i = 1; i < d.length; i ++) {
	    		for (int j = 1; j < d[i].length; j ++) {
	    			if (d[i][j] >= 0) {
	    				min[i][j] = Math.max(min[i - 1][j], min[i][j - 1]);
	    			} else {
	    				min[i][j] = Math.max(min[i - 1][j], min[i][j - 1]) + d[i][j];
	    			}
	    			
//	    			dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + d[i][j];
	    			dp[i][j] = min[i - 1][j] < min[i][j - 1] ? dp[i][j - 1] : dp[i - 1][j] + d[i][j];
	    		}
	    	}
//	    	tools.Utils.A_打印二维数组(min);
//	    	System.out.println("===================");
//	    	tools.Utils.A_打印二维数组(dp);
	    	if (dp[d.length - 1][d[0].length - 1] <= 0) {
	    		return  - dp[d.length - 1][d[0].length - 1] + 1;
	    	} else {
	    		return 0;
	    	}
	    }
	}
	/*
	 * 	4 ms
	 * 	20.86% 
	 */
	static class StandardSolution {
		/*
		 * 	定义一个int[][] h，表达的意思是：
		 * 		对于h[i][j]的值，对应的物理含义是：
		 * 			从[i][j]往下往右看，其所包含的全部数组，构成一个新的二维数组
		 * 			要从[i][j]到最后一个点，所必需的生命值
		 */
		public int calculateMinimumHP(int[][] d) {
			if (d == null || d.length == 0 || d[0].length == 0) {
				return 0;
			}
			int row = d.length, col = d[0].length;
			int[][] h = new int[row][col];
			h[row - 1][col - 1] = thisPositionMinHp(d, row - 1, col - 1);
			for (int i = row - 2; i > -1; i --) {
				h[i][col - 1] = thisPositionMinHP(d, i, col - 1, h[i + 1][col - 1]);
			}
			for (int j = col - 2; j > -1; j --) {
				h[row - 1][j] = thisPositionMinHP(d, row - 1, j, h[row - 1][j + 1]);
			}
			for (int i = row - 2; i > -1; i --) {
				for (int j = col - 2; j > -1; j --) {
					h[i][j] = Math.min(thisPositionMinHP(d, i, j, h[i + 1][j]), thisPositionMinHP(d, i, j, h[i][j + 1]));
				}
			}
			
//			System.out.println("++++++++++++++++++++++++++");
//			tools.Utils.A_打印二维数组(h);
//			System.out.println("++++++++++++++++++++++++++");
//			System.out.println(h[0][0]);
			return h[0][0];
		}
		/*
		 * 	这里的假设是，用户带着返回值的HP来，能够保证用户在来之前和来之后，都不会挂掉。
		 * 	而且，这个HP值是最小HP值
		 */
		private int thisPositionMinHp(int[][] d, int i, int j) {
			return d[i][j] < 0 ? - d[i][j] + 1 : 1;
		}
		private int thisPositionMinHP(int[][] d, int i, int j, int minHP) {
			int originHP = minHP - d[i][j];
			return originHP < 1 ? 1 : originHP;
//			if (minHP + d[i][j] > 0) {
//				return minHP;
//			} else {
//				return - d[i][j] - minHP + 1;
//			}
//			return d[i][j] < 0 ?  - d[i][j] + minHP : minHP;
		}
	}
}
