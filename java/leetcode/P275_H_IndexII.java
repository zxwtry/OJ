package leetcode;

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
 * @details     StandardSolution: TLE
 * @details     Solution1:  AC  24ms  17.79%
 * @details     Solution2:  AC  13ms  41.32%
 * @details     Solution3:  AC  11ms  76.40%
 */
public class P275_H_IndexII {
	static class StandardSolution {
		public int hIndex(int[] citations) {
			if (citations == null || citations.length < 1)
	    		return 0;
			int maxHIndex = Integer.MIN_VALUE;
			for (int index = 0; index < citations.length; index ++) {
				maxHIndex = Math.max(maxHIndex, getValue(citations, index));
			}
			return maxHIndex;
		}
		private int getValue(int[] citations, int index) {
	    	return Math.min(citations.length - index, citations[index]);
	    }
	}
	static class Solution1 {
		public int hIndex(int[] citations) {
	    	if (citations == null || citations.length < 1)
	    		return 0;
	        return getMaxValue(citations, 0, citations.length - 1);
	    }
	    private int getMaxValue(int[] citations, int leftIndex, int rightIndex) {
	    	if (leftIndex == rightIndex) {
	    		return getValue(citations, leftIndex);
	    	}
	        int midIndex = 0;
	        if (upTowardsRight(citations, leftIndex, leftIndex + 1) < 0) return getValue(citations, leftIndex);
	        if (upTowardsLeft(citations, rightIndex - 1, rightIndex) < 0) return getValue(citations, rightIndex);
	        while (leftIndex <= rightIndex) {
	        	midIndex = (leftIndex + rightIndex) / 2;
	        	int upTR = midIndex + 1 < citations.length ? upTowardsRight(citations, midIndex, midIndex + 1) : 0;
	        	if (upTR == 0) {
	        		return Math.max(getMaxValue(citations, leftIndex, midIndex)
	        				, getMaxValue(citations, midIndex + 1, rightIndex));
	        	}
	        	int upTL = midIndex > 0 ? 0 : upTowardsLeft(citations, midIndex - 1, midIndex);
	        	if (upTL == 0) {
	        		return Math.max(getMaxValue(citations, leftIndex, midIndex - 1)
	        				, getMaxValue(citations, midIndex, rightIndex));
	        	}
	        	int upValue = 10 * upTR + upTL;
	        	switch (upValue) {
				case 0:
				case -11:
				case -1:
				case -10:
					return getValue(citations, midIndex);
				case 1:
				case -9:
					rightIndex = midIndex - 1;
					break;
				case 10:
				case 9:
					leftIndex = midIndex + 1;
					break;
				case 11:
				default:
					break;
				}
	        }
	        return 0;
	    }
	    private int upTowardsRight(int[] citations, int smallIndex, int largeIndex) {
	    	if (getValue(citations, largeIndex) > getValue(citations, smallIndex)) {
	    		return 1;
	    	} else if (getValue(citations, largeIndex) == getValue(citations, smallIndex)) {
	    		return 0;
	    	}
	    	return -1;
	    }
	    private int upTowardsLeft(int[] citations, int smallIndex, int largeIndex) {
	    	if (getValue(citations, largeIndex) < getValue(citations, smallIndex)) {
	    		return 1;
	    	} else if (getValue(citations, largeIndex) < getValue(citations, smallIndex)) {
	    		return 0;
	    	}
	    	return -1;
	    }
	    private int getValue(int[] citations, int index) {
	    	return Math.min(citations.length - index, citations[index]);
	    }
	}
	static class Solution2 {
	    public int hIndex(int[] citations) {
	        int len = citations.length;
	        int first = 0;
	        int mid;
	        int count = len;
	        int step;
	        while (count > 0) {
	            step = count / 2;
	            mid = first + step;
	            if (citations[mid] < len - mid) {
	                first = mid + 1;
	                count -= (step + 1);
	            }
	            else {
	                count = step;
	            }
	        }
	        return len - first;
	    }
	}
	static class Solution3 {
		public int hIndex(int[] citations) {
			int startIndex = 0, endIndex = citations.length - 1, middleIndex = 0;
			while (startIndex <= endIndex) {
				middleIndex = (startIndex + endIndex) / 2;
				if (citations[middleIndex] < citations.length - middleIndex) {
					startIndex = middleIndex + 1;
				} else {
					endIndex = middleIndex;
				}
				if (startIndex == middleIndex && middleIndex == endIndex)
					break;
			}
			return citations.length - startIndex;
		}
	}
}
