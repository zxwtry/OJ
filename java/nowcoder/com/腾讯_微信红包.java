package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        腾讯_微信红包.java
 * @date        2017年7月15日 下午10:13:14
 * @details     
 */
public class 腾讯_微信红包 {
    static public class Gift {
        public int getValue(int[] g, int n) {
            if (n == 0) return 0;
            int v = g[0];
            int c = 1;
            for (int i = 1; i < g.length; i ++) {
                if (g[i] == v) {
                    c ++;
                } else {
                    c --;
                    if (c == 0) {
                        c = 1;
                        v = g[i];
                    }
                }
            }
            c = 0;
            for (int i = 0; i < g.length; i ++) {
                if (g[i] == v) {
                    c ++;
                }
            }
            return c >= (g.length + 1) / 2 ? v : 0;
        }
    }
}
