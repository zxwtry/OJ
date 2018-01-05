package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        去哪儿_二分查找.java
 * @date        2017年7月7日 下午8:39:28
 * @details     
 */
public class 去哪儿_二分查找 {
    static public class BinarySearch {
        public int getPos(int[] A, int n, int v) {
            int l = 0, r = n - 1;
            while (l < r) {
                int m = (l + r) / 2;
                if (A[m] > v) {
                    r = m - 1;
                } else if (A[m] < v) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            if (l < n && A[l] == v) return l;
            return -1;
        }
    }
}
