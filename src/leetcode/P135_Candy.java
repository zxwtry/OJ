package leetcode;

import javax.xml.bind.helpers.AbstractMarshallerImpl;

import tools.TreeLinkNode辅助.TreeLinkNode;

/*
 * 	There are N children standing in a line. Each child is assigned a rating value.

	You are giving candies to these children subjected to the following requirements:
	
	Each child must have at least one candy.
	Children with a higher rating get more candies than their neighbors.
	What is the minimum candies you must give?
 */

public class P135_Candy {
	public static void main(String[] args) {
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(100, 0, 900);
		System.out.println(tools.Utils.LEETCODE_int_array_序列化_(arr));
	}
	/*
	 * 	有这么简单的Hard题吗？
	 * 	6 ms
	 * 	9.28%
	 */
	static class Solution {
	    public int candy(int[] ratings) {
	    	if (ratings == null || ratings.length == 0) {
	    		return 0;
	    	}
	    	int[] forward = new int[ratings.length];
	    	for (int i = 0; i < forward.length; i ++) {
	    		if (i == 0 || ratings[i] <= ratings[i - 1]) {
	    			forward[i] = 1;
	    		} else {
	    			forward[i] = forward[i - 1] + 1;
	    		}
	    	}
	    	int[] backward = new int[ratings.length];
	    	for (int i = backward.length - 1; i > -1; i --) {
	    		if (i == backward.length - 1 || ratings[i] <= ratings[i + 1]) {
	    			backward[i] = 1;
	    		} else {
	    			backward[i] = backward[i + 1] + 1; 
	    		}
	    	}
	    	int sum = 0;
	    	for (int i = 0; i < backward.length; i ++) {
	    		sum += Math.max(forward[i], backward[i]);
	    	}
	        return sum;
	    }
	}
}