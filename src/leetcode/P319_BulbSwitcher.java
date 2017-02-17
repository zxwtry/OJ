package leetcode;

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
 * @details     
 */
public class P319_BulbSwitcher {
	static class Solution1 {
	    public int bulbSwitch(int n) {
	        if (n == 1) return 1;
	        if (n == 2) return 2;
	        if (n == 3) return 1;
	        int countOfOn = 0;
	        boolean isOn = false;
	        for (int nIndex = 2; nIndex < n; nIndex ++) {
	            isOn = false;
	            for (int sqrtIndex = 3; sqrtIndex * sqrtIndex < n; sqrtIndex ++) {
	                if (nIndex % sqrtIndex == 0)
	                    isOn = ! isOn;
	            }
	            countOfOn += isOn ? 1 : 0;
	        }
	        return countOfOn;
	    }
	}
}
