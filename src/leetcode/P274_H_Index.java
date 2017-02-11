package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * 	Given an array of citations (each citation is a non-negative integer)
 *  of a researcher, write a function to compute the researcher's h-index.

	According to the definition of h-index on Wikipedia: "A scientist has
	index h if h of his/her N papers have at least h citations each, and
	the other N − h papers have no more than h citations each."
	
	For example, given citations = [3, 0, 6, 1, 5], which means the researcher 
	has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations 
	respectively. Since the researcher has 3 papers with at least 3 citations 
	each and the remaining two with no more than 3 citations each, his h-index is 3.
	
	Note: If there are several possible values for h, the maximum one is 
	taken as the h-index.
	
	Hint:
	
	An easy approach is to sort the array first.
	What are the possible values of h-index?
	A faster approach is to use extra space.
	
	两个步骤：
		1,	从大到小排序
		2,	h-index = max min (f(i), i)
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P274_H_Index.java
 * @type        P274_H_Index
 * @date        2016年12月14日 下午10:18:38
 * @details     Solution1: AC 4ms 6.79%
 */
public class P274_H_Index {
	static class Solution1 {
	    public int hIndex(int[] citations) {
	    	if (citations == null) return 0;
	    	Arrays.sort(citations);
	    	reverse(citations);
	    	int hIndex = 0;
	    	for (int index = 0; index < citations.length; index ++) {
	    		hIndex = Math.max(hIndex, Math.min(citations[index], index + 1));
	    	}
	    	return hIndex;
	    }
	    private void reverse(int[] arr) {
	    	if (arr == null) return;
	    	int startIndex = 0, endIndex = arr.length - 1;
	    	int temp = 0;
	    	while (startIndex < endIndex) {
	    		temp = arr[startIndex];
	    		arr[startIndex] = arr[endIndex];
	    		arr[endIndex] = temp;
	    		startIndex ++;
	    		endIndex --;
	    	}
	    }
	}
}
