package nowcoder.leetcode;

import java.util.Arrays;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        知识迁移能力_数字在排序数组中出现的次数.java
 * @date        2017年7月1日 下午9:38:38
 * @details     剑指Offer
 */
public class 知识迁移能力_数字在排序数组中出现的次数 {
    static public class Solution {
        public int GetNumberOfK(int [] a , int k) {
            int i = Arrays.binarySearch(a, k);
             if (i < 0) return 0;
             int s = i, t = i, len = a.length;
             while ((s - 1 > -1) && a[s - 1] == k) s --;
             while ((t + 1 < len) && a[t + 1] == k) t ++;
             return t - s + 1;
         }
     }
}
