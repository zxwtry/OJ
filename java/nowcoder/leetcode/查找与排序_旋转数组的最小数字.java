package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        查找与排序_旋转数组的最小数字.java
 * @date        2017年6月29日 下午9:45:34
 * @details     剑指Offer
 */
public class 查找与排序_旋转数组的最小数字 {
    static public class Solution {
        public int minNumberInRotateArray(int [] a) {
            int al = a == null ? 0 : a.length;
            if (al == 0) return 0;
            if (a[0] >= a[al - 1]) {
                return f(a, 0, al - 1);        
            } else return a[0];
        }
        int f(int[] a, int i, int j) {
            if (a[i] < a[j]) return a[i];
            if (i == j) return a[i];
            if (i > j) return Math.min(a[i], a[j]);
            int m = (i + j) / 2;
            if (a[m] > a[i]) {
                return f(a, m + 1, j);
            } else if (a[m] < a[j]) {
                return f(a, i, m );
            } else {
                return Math.min(f(a, i, m), f(a, m + 1, j));
            }
        }
    }
}
