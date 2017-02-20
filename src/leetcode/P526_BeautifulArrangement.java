package leetcode;

import java.math.BigInteger;
import java.util.HashSet;

/**
 *  Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array 
 *  that is constructed by these N numbers successfully if one of the following is true for 
 *  the ith position (1 ≤ i ≤ N) in this array:
 *  
 *  The number at the ith position is divisible by i.
 *  i is divisible by the number at the ith position.
 *  Now given N, how many beautiful arrangements can you construct?
 *  
 *  Example 1:
 *  Input: 2
 *  Output: 2
 *  Explanation: 
 *  
 *  The first beautiful arrangement is [1, 2]:
 *  
 *  Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
 *  
 *  Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
 *  
 *  The second beautiful arrangement is [2, 1]:
 *  
 *  Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
 *  
 *  Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 *  Note:
 *  N is a positive integer and will not exceed 15.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P526_BeautifulArrangement.java
 * @type        P526_BeautifulArrangement
 * @date        2017年2月19日 上午10:40:42
 * @details     
 */
public class P526_BeautifulArrangement {
    public static void main(String[] args) {
        int N = 43;
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.countArrangement(N));
    }
    static class Solution1 {
        @SuppressWarnings("unchecked")
        public int countArrangement(int N) {
            HashSet<Integer>[] sets = new HashSet[N + 1];
            for (int setsIndex = 1; setsIndex < sets.length; setsIndex ++) {
                HashSet<Integer> set = new HashSet<Integer>();
                for (int smallIndex = 1; smallIndex <= setsIndex; smallIndex ++)
                    if (setsIndex % smallIndex == 0) {
                        set.add(smallIndex);
                    }
                for (int bigIndex = setsIndex + 1; bigIndex <= N; bigIndex ++)
                    if (bigIndex % setsIndex == 0) {
                        set.add(bigIndex);
                    }
                sets[setsIndex] = set;
                System.out.println(set);
            }
            System.out.println("====");
            HashSet<Integer> nowGet = new HashSet<Integer>();
            
            //return 0;
        }
    }
}
