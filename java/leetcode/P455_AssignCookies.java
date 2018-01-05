package leetcode;

import java.util.Arrays;

/*
 * 	Assume you are an awesome parent and want to give your children some cookies. 
 * 	But, you should give each child at most one cookie. 
 * 	Each child i has a greed factor gi, which is the minimum size of a cookie 
 * 	that the child will be content with; and each cookie j has a size sj. 
 * 	If sj >= gi, we can assign the cookie j to the child i, 
 * 	and the child i will be content. Your goal is to maximize the number of 
 * 	your content children and output the maximum number.

	Note:
	You may assume the greed factor is always positive. 
	You cannot assign more than one cookie to one child.

	Example 1:
	Input: [1,2,3], [1,1]
	
	Output: 1
	
	Explanation: You have 3 children and 2 cookies. 
	The greed factors of 3 children are 1, 2, 3. 
	And even though you have 2 cookies, since their size is both 1, 
	you could only make the child whose greed factor is 1 content.
	You need to output 1.
	Example 2:
	Input: [1,2], [1,2,3]
	
	Output: 2
	
	Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. 
	You have 3 cookies and their sizes are big enough to gratify all of the children, 
	You need to output 2.
	
	
	每人至少一个
	每个人　i 的贪婪指数  	gi  (>= gi才会满意)
	每块糖   j 　的大小		cj
 * 
 */

public class P455_AssignCookies {
	public static void main(String[] args) {
		debugSolution();
	}
	static void debugSolution() {
		int[] g = new int[] {1, 2, 3, 4};
		int[] s = new int[] {3, 2, 1, 3};
		Solution solution = new Solution();
		System.out.println(solution.findContentChildren(g, s));
	}
	/*
	 * 	AC
	 * 	19 ms
	 */
	static class Solution {
	    public int findContentChildren(int[] g, int[] s) {
	        int count = 0;
	        Arrays.sort(g);
	        Arrays.sort(s);
	        int gi = g.length - 1, si = s.length - 1;
	        while (gi > -1 && si > -1) {
	        	while (gi > -1 && g[gi] > s[si]) {
	        		gi --;
	        	}
	        	if (gi > -1) {
	        		gi --;
	        		si --;
	        		count ++;
	        	}
	        }
	        return count;
	    }
	}
}
