package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        时间空间效率的平衡_数组中的逆序对.java
 * @date        2017年7月1日 下午2:22:26
 * @details     剑指Offer
 */
public class 时间空间效率的平衡_数组中的逆序对 {
    static public class Solution {
        int cnt = 0;
        int mod = 1000000007;
        int[] b;
        public int InversePairs(int [] a) {
            int al = a == null ? 0 : a.length;
            b = new int[al];
            m(a, 0, al - 1);
            return cnt;
        }
        void m(int[] a, int i, int j) {
            if (j - i == 1) {
                if (a[i] > a[j]) {
                    cnt = (cnt + 1) % mod;
                    swap(a, i, j);
                }
                return;
            }
            if (j <= i) return;
            int m = (i + j) / 2;
            m(a, i, m);
            m(a, m+1, j);
            int p = i, q = m+1, r = i;
            while (p <= m && q <= j) {
                if (a[p] <= a[q]) {
                    b[r ++] = a[p ++];
                } else {
                    cnt = (cnt + m - p + 1) % mod;
                    b[r ++] = a[q ++];
                }
            }
            while (p <= m) {
                b[r ++] = a[p ++];
            }
            while (q <= j) {
                b[r ++] = a[q ++];
            }
            System.arraycopy(b, i, a, i, j - i + 1);
        }
        void swap(int[] a, int i, int j) {
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }
}
