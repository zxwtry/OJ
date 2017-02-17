package leetcode;

import java.util.Arrays;

/**
 * 	There are n bulbs that are initially off. You first turn on all the bulbs.
 *  Then, you turn off every second bulb. On the third round, you toggle every third bulb 
 *  (turning on if it's off or turning off if it's on). For the ith round, you toggle every
 *   i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after
 *    n rounds.
 *
 *	Example:
 *	
 *	Given n = 3. 
 *	
 *	At first, the three bulbs are [off, off, off].
 *	After first round, the three bulbs are [on, on, on].
 *	After second round, the three bulbs are [on, off, on].
 *	After third round, the three bulbs are [on, off, off]. 
 *	
 *	So you should return 1, because there is only one bulb is on.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P319_BulbSwitcher.java
 * @type        P319_BulbSwitcher
 * @date        2017年1月9日 下午9:05:02
 * @details     Solution1: AC 0ms 18.03%
 * @details     StandardSolution: TLE
 */
public class P319_BulbSwitcher {
	static class StandardSolution {
	    public int bulbSwitch(int n) {
	        if (n == 1) return 1;
            if (n == 2) return 2;
            if (n == 3) return 1;
	        boolean[] isOn = new boolean[n + 1];
	        Arrays.fill(isOn, true);
	        for (int index = 2; index <= n; index ++) {
	            int times = index;
	            while (times <= n) {
	                isOn[times] = ! isOn[times];
	                times += index;
	            }
	        }
	        int countOfOn = 0;
	        for (boolean on : isOn) {
	            countOfOn += on ? 1 : 0;
	        }
	        return countOfOn - 1;
	    }
	}
	static class Solution2 {
	    public int bulbSwitch(int n) {
	        return (int)Math.sqrt(n);
	    }
	}
}
