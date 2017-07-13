package nowcoder.com;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        华为_删数.java
 * @date        2017年7月12日 下午9:53:20
 * @details     
 */
public class 华为_删数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            sovle(sc);
        }
        sc.close();
    }

    private static void sovle(Scanner sc) {
        int n = sc.nextInt();
        T[] ts = new T[n];
        T next = null;
        for (int i = n - 1; i > -1; i --) {
            ts[i] = new T(i, next);
            next = ts[i];
        }
        ts[n - 1].next = ts[0];
        T head = ts[0];
        while (head.next != head) {
            head = head.next;
            head.next = head.next.next;
            head = head.next;
        }
        System.out.println( head.val );
    }
    static class T {
        public T(int val, T next) {
            this.next = next;
            this.val = val;
        }
        public T(int val) {
            this(val, null);
        }
        int val;
        T next;
    }
}
