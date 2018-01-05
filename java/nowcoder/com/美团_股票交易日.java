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
    //[235,55,628,455,414,547,454],7
    public static void main(String[] args) {
        int[] p = {10,22,5,75,65,80};
        int n = p.length;
        System.out.println(new Stock().maxProfit(p, n));
    }
    static public class Stock {
        public int maxProfit(int[] p, int n) {
            int[] f = new int[n];
            int min = p[0];
            for (int i = 1; i < n; i ++) {
                f[i] = Math.max(p[i] - min, f[i - 1]);
                min = Math.min(min, p[i]);
            }
            int[] b = new int[n];
            int max = p[n - 1];
            for (int i = n - 2; i > -1; i --) {
                b[i] = Math.max(max - p[i], b[i + 1]);
                max = Math.max(max, p[i]);
            }
            int ans = 0;
            for (int i = 1; i < n; i ++) {
                ans = Math.max(ans, f[i - 1] + b[i]);
            }
            return ans;
        }
    }
}
