package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        小米_风口的猪中国牛市.java
 * @date        2017年7月5日 下午9:58:58
 * @details     
 */
public class 小米_风口的猪中国牛市 {
    static public class Solution {
        /**
         * 计算你能获得的最大收益
         * 
         * @param prices Prices[i]即第i天的股价
         * @return 整型
         */
        public int calculateMax(int[] p) {
            int pl = p == null ? 0 : p.length;
            if (pl == 0) return 0;
            int[] fMin = new int[pl + 1];
            int[] bMax = new int[pl + 1];
            int t = Integer.MAX_VALUE;
            for (int i = 0; i < pl; i ++) {
                t = Math.min(t, p[i]);
                if (i != 0) {
                    fMin[i] = Math.max(p[i] - t, fMin[i - 1]);
                }
            }
            t = Integer.MIN_VALUE;
            for (int i = pl - 1; i > -1; i --) {
                t = Math.max(t, p[i]);
                if (i != pl - 1) {
                    bMax[i] = Math.max(t - p[i], bMax[i + 1]);
                }
            }
            int ans = 0;
            for (int i = 0; i < pl; i ++) {
                ans = Math.max(ans, fMin[i] + bMax[i]);
            }
            return ans;
        }
    }
}
