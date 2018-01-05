package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        数组_数组中重复的数字.java
 * @date        2017年7月2日 下午7:40:47
 * @details     剑指Offer
 */
public class 数组_数组中重复的数字 {
    static public class Solution {
        public boolean duplicate(int a[],int n,int [] d) {
            if (n == 0) {
                return false;
            }
            for (int i = 0; i < n; i ++) {
                while (a[i] != i) {
                    if (a[i] == a[a[i]]) {
                        d[0] = a[i];
                        return true;
                    } else {
                        swap(a, a[i], i);
                    }
                }
            }
            return false;
        }
        void swap(int[] a, int i, int j) {
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }
}
