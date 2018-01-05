package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        美团_直方图内最大矩形.java
 * @date        2017年7月7日 下午9:53:57
 * @details     
 */
public class 美团_直方图内最大矩形 {
    static public class MaxInnerRec {
        public int countArea(int[] a, int n) {
            int al = a == null ? 0 : a.length;
            if (al == 0) return 0;
            int[] b = new int[al];
            b[0] = -1;
            int ans = a[0];
            for (int i = 1; i < al; i ++) {
                int j = i - 1;
                if (a[j] < a[i]) {
                    b[i] = i - 1;
                } else {
                    while (j > -1 && a[j] >= a[i]) {
                        if (j == b[j]) {
                            j --;
                        } else {
                            j = b[j];
                        }
                    }
                    b[i] = j ;
                }
            }
            int[] c = new int[al];
            c[al - 1] = al;
            for (int i = al - 2; i > -1; i --) {
                int j = i + 1;
                if (a[j] < a[i]) {
                    c[i] = i + 1;
                } else {
                    while (j < al && a[j] >= a[i]) {
                        if (j == c[j]) {
                            j ++;
                        } else {
                            j = c[j];
                        }
                    }
                    c[i] = j ;
                }
            }
            for (int i = 0; i < al; i ++) {
                ans = Math.max(ans, a[i] * (c[i] - b[i] - 1));
            }
            return ans;
        }
    }
}
