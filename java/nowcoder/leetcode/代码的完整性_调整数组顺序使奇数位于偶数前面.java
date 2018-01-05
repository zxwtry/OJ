package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        代码的完整性_调整数组顺序使奇数位于偶数前面.java
 * @date        2017年6月30日 上午9:43:12
 * @details     剑指Offer 
 */
public class 代码的完整性_调整数组顺序使奇数位于偶数前面 {
    static public class Solution {
        public void reOrderArray(int [] a) {
            int al = a == null ? 0 : a.length;
            if (al == 0) return;
            int i = 0, j = al - 1;
            int[] p = new int[al];    
            System.arraycopy(a, 0, p, 0, al);
            int k = al - 1;
            while (j > -1) {
                if (p[j] % 2 == 0) {
                    a[k --] = p[j];
                }
                j --;
            }   
            k = 0;
            while (i < al) {
                if (p[i] % 2== 1) {
                    a[k ++] = p[i];
                }
                i ++;
            }
        }
    }
}
