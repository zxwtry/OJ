package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        美团_最大差值.java
 * @date        2017年7月7日 下午9:11:47
 * @details     
 */
public class 美团_最大差值 {
    static public class LongestDistance {
        public int getDis(int[] a, int n) {
            int al = a == null ? 0 : a.length;
            int max = 0;
            int small = a[0];
            for (int i = 1; i < al; i ++) {
                max = Math.max(a[i] - small, max);
                small = Math.min(small, a[i]);
            }
            return max;
        }
    }
}
