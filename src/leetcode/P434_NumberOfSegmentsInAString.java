package leetcode;

/*
 * 	Count the number of segments in a string, 
 * 	where a segment is defined to be a contiguous sequence of non-space characters.

	For example,
	
	Input: "Hello, my name is John"
	
	Output: 5
 */

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P434_NumberOfSegmentsInAString.java
 * @type        P434_NumberOfSegmentsInAString
 * @date        2016年12月4日 上午11:23:53
 * @details     
 */
public class P434_NumberOfSegmentsInAString {
	public static void main(String[] args) {
	}
	
	static class Solution {
	    public int countSegments(String s) {
	        int count = 0;
	        int pre = -1;
	        for (int i = 0; i < s.length(); i ++) {
	        	if (s.charAt(i) == ' ') {
	        		if (pre != -1 && pre == i - 1) {
	        			count ++;
	        		}
	        	} else {
	        		pre = i;
	        	}
	        }
	        if (s.length() != 0 && pre == s.length() - 1) count ++;
	    }
	}
}
