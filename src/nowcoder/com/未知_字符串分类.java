package nowcoder.com;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        未知_字符串分类.java
 * @type        未知_字符串分类
 * @date        2017年3月24日 下午1:45:44
 * @details     AC
 */
public class 未知_字符串分类 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String s = null;
        HashSet<A> set = new HashSet<A>();
        for (int i = 0; i < n; i ++) {
            s = scanner.next();
            A a = new A();
            for (int j = 0; j < s.length(); j ++)
                a.m[s.charAt(j)] ++;
            set.add(a);
        }
        System.out.println(set.size());
        scanner.close();
    }
    static class A {
        int[] m = new int[128];
        @Override
        public boolean equals(Object obj) {
            if (! (obj instanceof A)) return false;
            A a = (A)obj;
            boolean t = true;
            for (int i = 0; t & i < m.length; i ++)
                t &= m[i] == a.m[i];
            return t;
        }
        @Override
        public int hashCode() {
            int hash = 0;
            for (int v : m)
                hash = (hash * 31 + v) % 1000007;
            return hash;
        }
    }
}
