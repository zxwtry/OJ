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
 * @details     Solution2:  AC  13ms   41.32%
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
}
