package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        去哪儿_首个重复字符.java
 * @date        2017年7月7日 下午8:50:48
 * @details     
 */
public class 去哪儿_首个重复字符 {
    static public class FirstRepeat {
        public char findFirstRepeat(String A, int n) {
            boolean[] d = new boolean[128];
            for (int i = 0; i < n; i ++) {
                char c = A.charAt(i);
                if (d[c]) return c;
                d[c] = true;
            }
            return 'c';
        }
    }
}
