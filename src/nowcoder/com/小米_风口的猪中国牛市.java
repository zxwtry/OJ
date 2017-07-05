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
    public static void main(String[] args) {
        int[] p = {5,15,56,26,62,65,57,69};
        System.out.println(new Solution().calculateMax(p));
    }
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
            int[] arr = {0, 0};
            int pv = p[0];
            for (int i = 1; i <= pl; i ++) {
                if (i == pl || p[i - 1] > p[i]) {
                    add(arr, p[i - 1] - pv);
                    pv = i == pl ? pv : p[i];
                }
            }
            System.out.println(arr[0] + "..." + arr[1]);
            return arr[0] + arr[1];
        }
        void add(int[] arr, int v) {
            if (arr[0] == 0) {
                arr[0] = v;
                return;
            }
            if (arr[1] == 0) {
                arr[1] = v;
                return;
            }
            int i = arr[0] < arr[1] ? 0 : 1;
            if (arr[i] < v) {
                arr[i] = v;
            }
        }
    }
}
