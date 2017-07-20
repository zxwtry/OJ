package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        其它_字符串的旋转.java
 * @date        2017年7月20日 下午10:12:52
 * @details     
 */
public class 其它_字符串的旋转 {
    public static void main(String[] args) {
        
    }
    static public class StringRotation {
        public String rotateString(String A, int n, int p) {
            if (n < 2) return A;
            char[] cs = A.toCharArray();
            rotate(cs, 0, p);
            rotate(cs, p + 1, n - 1);
            rotate(cs, 0, n - 1);
            return new String(cs);
        }
        void rotate(char[] cs, int i, int j) {
            while (i < j) {
                swap(cs, i ++, j --);
            }
        }
        void swap(char[] cs, int i, int j) {
            char c = cs[i];
            cs[i] = cs[j];
            cs[j] = c;
        }
    }
}
