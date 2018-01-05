package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        去哪儿_字符串替换.java
 * @date        2017年7月14日 下午10:00:04
 * @details     
 */
public class 去哪儿_字符串替换 {
    //"A%sC%sE",7,['B','D','F']
    public static void main(String[] args) {
        String A = "A%sC%sE";
        int n = 7;
        int m = 0;
        char[] arg = "BDF".toCharArray();
        System.out.println(new StringFormat().formatString(A, n, arg, m));
    }
    static public class StringFormat {
        public String formatString(String A, int n, char[] arg, int m) {
            int an = arg.length;
            char[] ans = new char[n + an];
            int argi = 0;
            int ansi = 0;
            for (int i = 0; i < n; i ++) {
                char c = A.charAt(i);
                if (c == '%' && i != n-1 && A.charAt(i + 1) == 's') {
                    i ++;
                    ans[ansi ++] = arg[argi ++];
                } else {
                    ans[ansi ++] = c;
                }
            }
            while (argi < an) {
                ans[ansi ++] = arg[argi ++];
            }
            return new String(ans, 0, ansi);
        }
    }
}
