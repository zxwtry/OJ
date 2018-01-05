package leetcode;

/**

Suppose you have a long flowerbed in which some of the plots are planted and some are not. 
However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, 
where 0 means empty and 1 means not empty), and a number n, 
return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: True
Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: False
Note:
The input array won't violate no-adjacent-flowers rule.
The input array size is in the range of [1, 20000].
n is a non-negative integer which won't exceed the input array size.

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P605_CanPlaceFlowers.java
 * @date        2017年6月4日 上午9:59:35
 * @details     Solution: AC
 */
public class P605_CanPlaceFlowers {
    static public class Solution {
        public boolean canPlaceFlowers(int[] f, int n) {
            int fsti = 0, feni = f.length - 1;
            int cnt = 0;
            while (fsti <= feni && f[fsti] == 0) {
                cnt ++;
                fsti ++;
            }
            //all zero
            if (fsti == feni+1) return n <= (feni+2)/2;
            n -= cnt / 2;
            if (n < 1) return true;
            cnt = 0;
            while (fsti <= feni && f[feni] == 0) {
                cnt ++;
                feni --;
            }
            n -= cnt / 2;
            if (n < 1) return true;
            cnt = 0;
            for (int i = fsti+1; i <= feni; i ++) {
                if (f[i] == 1) {
                    n -= (cnt - 1) / 2;
                    if (n < 1) return true;
                    cnt = 0;
                } else {
                    cnt ++;
                }
            }
            return false;
        }
    }
}
