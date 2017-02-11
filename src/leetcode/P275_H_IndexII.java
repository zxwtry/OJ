package leetcode;

import javax.xml.stream.events.StartDocument;

import com.sun.xml.internal.bind.v2.model.core.LeafInfo;

/**
 * 	Follow up for H-Index: What if the citations array is sorted in 
 * 	ascending order? Could you optimize your algorithm?
 * 	
 * 	Hint:
 * 	
 * 	Expected runtime complexity is in O(log n) and the input is sorted.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P275_H_IndexII.java
 * @type        P275_H_IndexII
 * @date        2016年12月14日 下午10:20:20
 * @details     
 */
public class P275_H_IndexII {
	public static void main(String[] args) {
		int[] citations = new int[] {10, 8, 5, 4, 3};
		Solution solution = new Solution();
		System.out.println(solution.hIndex(citations));
	}
	static class Solution {
	    public int hIndex(int[] citations) {
	    	if (citations == null || citations.length < 1)
	    		return 0;
	        int leftIndex = 0, rightIndex = citations.length - 1;
	        int midIndex = 0;
	        while (leftIndex <= rightIndex) {
	        	midIndex = (leftIndex + rightIndex) / 2;
	        	int midValue = getValue(citations, midIndex);
	        	int midPlusValue = getValue(citations, midIndex + 1);
	        	if (midValue == midPlusValue) {
	        		return midValue;
	        	} else if (midValue < midPlusValue) {
	        		leftIndex = midIndex;
	        	} else {
	        		rightIndex = midIndex;
	        	}
	        }
	        return 0;
	    }
	    private int getValue(int[] citations, int index) {
	    	return Math.min(index + 1, citations[index]);
	    }
	}
}
