package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        未知_二进制反转.java
 * @date        2017年7月31日 下午9:28:54
 * @details     
 */
public class 未知_二进制反转 {
    public static void main(String[] args) {
        int v = 0x123857;
        System.out.printf("%01X\n", reverseBit(v));
    }
    static int reverseBit(int v) {
        int b = 32;
        int a = 0;
        while (v != 0) {
            a = (a << 1) + (v & 1);
            v >>= 1;
            b --;
        }
        return a << b;
    }
}
