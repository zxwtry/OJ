package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        时间效率_数组中出现次数超过一半的数字.java
 * @date        2017年7月1日 上午9:43:22
 * @details     剑指Offer
 */
public class 时间效率_数组中出现次数超过一半的数字 {
    static public class Solution {
        public int MoreThanHalfNum_Solution(int [] a) {
            int al = a == null ? 0 : a.length;
            if (al == 0) return 0;
            int v = a[0];
            int t = 1;
            for (int i = 1; i < al; i ++) {
                if (a[i] == v) {
                    t ++;
                } else if (t > 1) {
                    t --;
                } else {
                    v = a[i];
                    t = 1;
                }
            }
            t = 0;
            for (int k : a) {
                if (k == v) t ++;
            }
            return t > (al / 2) ? v : 0;
        }
    }
}
