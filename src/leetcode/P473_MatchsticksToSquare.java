package leetcode;

import java.util.Arrays;

/**
 * 	Remember the story of Little Match Girl? By now, you know exactly what 
 * 	matchsticks the little match girl has, please find out a way you can 
 * 	make one square by using up all those matchsticks. You should not break 
 * 	any stick, but you can link them up, and each matchstick must be used
 * 	exactly one time.
 *	
 *	Your input will be several matchsticks the girl has, represented with 
 *	their stick length. Your output will either be true or false, to represent 
 *	whether you can save this little girl or not.
 *	
 *	Example 1:
 *	Input: [1,1,2,2,2]
 *	Output: true
 *	
 *	Explanation: You can form a square with length 2, one side of the square 
 *	came two sticks with length 1.
 *	Example 2:
 *	Input: [3,3,3,3,4]
 *	Output: false
 *	
 *	Explanation: You cannot find a way to form a square with all the matchsticks 
 *	and you cannot save this little girl.
 *	Note:
 *	The length sum of the given matchsticks is in the range of 0 to 10^9.
 *	The length of the given matchstick array will not exceed 15.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P473_MatchsticksToSquare.java
 * @type        P473_MatchsticksToSquare
 * @date        2016年12月18日 上午11:09:15
 * @details     Solution1: AC 171ms 37.23%
 */
public class P473_MatchsticksToSquare {
	static class Solution1 {
		int lay = 0;
		boolean[] iv = null;
		boolean isS = false;
		int sum = 0;
	    public boolean makesquare(int[] nums) {
	        sum = 0;
	        int max = 0;
	        for (int v : nums) {
	        	sum += v; 
	        	max = Math.max(max, v);
	        }
	        if (sum % 4 != 0) return false;
	        sum = sum / 4;
	        if (max > sum) return false;
	        Arrays.sort(nums);
	        iv = new boolean[nums.length];
	        search(nums, sum, 0);
	        return isS;
	    }
		private void search(int[] n, int need, int l) {
			if (need == 0) {
				l ++;
				if (l == 4) {
					isS = true;
					return;
				}
				need = sum;
			}
			int i = n.length - 1;
			for (; i > -1; i --) {
				
				if (i != n.length - 1 && n[i] == n[i+1] && !iv[i] && !iv[i+1])
					continue;
				if (! iv[i] && n[i] <= need && ! isS) {
					iv[i] = true;
					search(n, need - n[i], l);
					iv[i] = false;
				}
			}
		}
	}
}
