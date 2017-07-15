package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        美团_股票交易日.java
 * @date        2017年7月15日 下午10:34:08
 * @details     
 */
public class 美团_股票交易日 {
    static public class Stock {
        public int maxProfit(int[] p, int n) {
            int[] f = new int[n];
            int min = f[0];
            for (int i = 1; i < n; i ++) {
                f[i] = p[i] - min;
                min = Math.min(min, p[i]);
            }
            int[] b = new int[n];
            int max = f[n - 1];
            for (int i = n - 2; i > -1; i --) {
                b[i] = max - p[i];
                max = Math.max(max, p[i]);
            }
            int 
        }
    }
}
