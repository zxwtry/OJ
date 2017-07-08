package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        京东_小东分苹果.java
 * @date        2017年7月8日 下午10:28:36
 * @details     
 */
public class 京东_小东分苹果 {
    public static void main(String[] args) {
        System.out.println(new Apples().getInitial(3));
    }
    static public class Apples {
        public int getInitial(int n) {
            int[] arr = new int[n + 1];
            for (int v = 1; ; v ++) {
                arr[1] = v;
                boolean find = true;
                for (int i = 2; i <= n; i ++) {
                    if (arr[i - 1] % (n - 1) != 0) {
                        find = false;
                        break;
                    }
                    arr[i] = 1 + (arr[i - 1] / (n - 1)) * n;
                }
                if (find) break;
            }
            return arr[n];
        }
    }
}
