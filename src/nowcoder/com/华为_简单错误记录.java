package nowcoder.com;

import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        华为_简单错误记录.java
 * @date        2017年7月7日 下午7:06:00
 * @details     
 */
public class 华为_简单错误记录 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<T, T> m = new HashMap<>();
        int sign = 0;
        while (sc.hasNext()) {
            String s = sc.next();
            int line = sc.nextInt();
            int sl = s.length();
            int li = s.lastIndexOf('\\');
            if (sl - li - 1 > 16) {
                li = sl - 17;
            }
            s = s.substring(li + 1);
            T t = new T(s, line);
            T v = m.get(t);
            if (v == null) {
                t.sign = sign ++;
                m.put(t, t);
            } else {
                v.cnt ++;
            }
        }
        int i = 0;
        TreeSet<T> set = new TreeSet<>(m.values());
        for (T t : set) {
            i ++;
            System.out.println(t.s + " " + t.line + " " + t.cnt);
            if (i == 8) break;
        }
        sc.close();
    }
    static class T implements Comparable<T> {
        String s;
        int sign;
        int cnt = 1;
        int line;
        public T(String s, int line) {
            this.s = s;
            this.line = line;
        }
        @Override
        public int compareTo(T o) {
            if (this.cnt != o.cnt) {
                return Integer.compare(o.cnt, this.cnt);
            }
            return Integer.compare(this.sign, o.sign);
        }
        @Override
        public int hashCode() {
            return (this.s.hashCode() << 4) + line;
        }
        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (! (obj instanceof T)) return false;
            T t = (T)obj;
            return this.s.equals(t.s) && this.line == t.line;
        }
    }
}
