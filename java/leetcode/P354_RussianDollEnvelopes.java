package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**	
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). 
 * One envelope can fit into another if and only if both the width and height of one envelope 
 * is greater than the width and height of the other envelope.
 * 
 *	What is the maximum number of envelopes can you Russian doll? (put one inside other)
 *	
 *	Example:
 *	Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian 
 *	doll is 3 ([2,3] => [5,4] => [6,7]).
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P354_RussianDollEnvelopes.java
 * @type        P354_RussianDollEnvelopes
 * @date        2017年2月4日 下午7:33:23
 * @details     Solution1: AC 309ms 46.29%
 * @details     Solution2: AC  23ms 75.62% 
 */
public class P354_RussianDollEnvelopes {
	static class Solution1 {
	    public int maxEnvelopes(int[][] envelopes) {
	        if (envelopes == null || envelopes.length == 0) return 0;
	        Arrays.sort(envelopes, new Comparator<int[]>() {
                @Override
                public int compare(int[] val1, int[] val2) {
                    long cut = (long)val1[0] - val2[0];
                    if (cut != 0) return cut > 0 ? 1 : -1;
                    cut = (long)val1[1] - val2[1];
                    if (cut != 0) return cut > 0 ? 1 : -1;
                    return 0;
                }
            });
	        int max = 0;
	        int[] dp = new int[envelopes.length];
	        int maxNow = 0;
	        for (int i = 0; i < dp.length; i ++) {
	            maxNow = 0;
	            for (int j = i - 1; j > -1; j --) {
	                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
	                    maxNow = Math.max(maxNow, dp[j]);
	                }
	            }
	            max = Math.max(max, maxNow + 1);
	            dp[i] = maxNow + 1;
	        }
	        return max;
	    }
	}
	static class Solution2 {
	    public int maxEnvelopes(int[][] envelopes) {
	        if(envelopes == null || envelopes.length == 0 
	           || envelopes[0] == null || envelopes[0].length != 2)
	            return 0;
	        Arrays.sort(envelopes, new Comparator<int[]>(){
	            public int compare(int[] val1, int[] val2){
	                long cut = (long)val1[0] - val2[0];
                    if (cut != 0) return cut > 0 ? 1 : -1;
                    cut = (long)val1[1] - val2[1];
                    if (cut != 0) return cut > 0 ? -1 : 1;
                    return 0;
	           } 
	        });
	        int dp[] = new int[envelopes.length];
	        int len = 0;
	        for(int[] envelope : envelopes){
	            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
	            index = index < 0 ? -(index + 1) : index;
	            dp[index] = envelope[1];
	            len += (index == len ? 1 : 0);
	        }
	        return len;
	    }
	}
}
