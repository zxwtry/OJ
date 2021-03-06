package leetcode;

import java.util.Arrays;

/**
 * 	Define S = [s,n] as the string S which consists of n connected strings s.
 *  For example, ["abc", 3] ="abcabcabc".
 *	
 *	On the other hand, we define that string s1 can be obtained from string s2 
 *	if we can remove some characters from s2 such that it becomes s1. 
 *	For example, “abc” can be obtained from “abdbec” based on our definition, 
 *	but it can not be obtained from “acbbe”.
 *	
 *	You are given two non-empty strings s1 and s2 (each at most 100 characters long)
 *	and two integers 0 ≤ n1 ≤ 106 and 1 ≤ n2 ≤ 106. Now consider the strings S1 and S2,
 *	where S1=[s1,n1] and S2=[s2,n2]. Find the maximum integer M such that [S2,M] 
 *	can be obtained from S1.
 *	
 *	Example:
 *	
 *	Input:
 *	s1="acb", n1=4
 *	s2="ab", n2=2
 *	
 *	Return:
 *	2
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P466_CountTheRepetitions.java
 * @type        P466_CountTheRepetitions
 * @date        2016年12月4日 下午8:31:07
 * @details     Solution1: AC 8ms 83.39%
 */
public class P466_CountTheRepetitions {
	static class Solution1 {
	    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
	        int[] rapport = new int[102], rest = new int[102];
	        Arrays.fill(rapport, -1);
	        Arrays.fill(rest, -1);
	        int b = -1, posRest = 0, rap = 0, last = -1;
	        rapport[0] = rest[0] = 0;
	        for (int i = 1; i <= s2.length() + 1; i ++) {
	            int j = 0;
	            for (j = 0; j < s1.length(); j ++) {
	                if (s2.charAt(posRest) == s1.charAt(j)) {
	                    posRest ++;
	                    if (posRest == s2.length()) {
	                        rap ++;
	                        posRest = 0;
	                    }
	                }
	            }
	            for (int k = 0; k < i; k ++)
	                if (posRest == rest[k]) {
	                    b = k;
	                    last = i;
	                    break;
	                }
	            rapport[i] = rap;
	            rest[i] = posRest;
	            if (b >= 0) break;
	        }
	        int interval = last - b;
	        if (b >= n1) return rapport[n1] / n2;
	        return ((n1-b)/interval*(rapport[last]-rapport[b])+rapport[(n1-b)%interval+b])/n2;
	    }
	    
	}
	
}
