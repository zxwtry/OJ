package nowcoder.com;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        华为170706.java
 * @date        2017年7月6日 下午8:45:32
 * @details     
 */
public class 华为170706 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<String, LinkedList<String>> m = new HashMap<>();
        for (int i = 0; i < n; i ++) {
            String a = sc.next();
            String b = sc.next();
            LinkedList<String> ll = m.get(a);
            if (ll == null) {
                ll = new LinkedList<String>();
                m.put(a, ll);
            }
            ll.add(b);
        }
        String a = sc.next(), b = sc.next();
        HashSet<String> v = new HashSet<>();
        LinkedList<String> q = new LinkedList<>();
        q.clear();
        v.clear();
        q.add(a);
        v.add(a);
        boolean findb = false;
        while (q.size() != 0) {
            int size = q.size();
            for (int i = 0; i < size; i ++) {
                String now = q.poll();
                v.add(now);
                if (now.equals(b)) {
                    findb = true;
                    break;
                }
                LinkedList<String> ll = m.get(now);
                if (ll != null) {
                    for (String next : ll) {
                        if (! v.contains(next)) {
                            q.add(next);
                        }
                    }
                }
            }
            if (findb) break;
        }
        q.clear();
        v.clear();
        q.add(b);
        v.add(b);
        boolean finda = false;
        while (q.size() != 0) {
            int size = q.size();
            for (int i = 0; i < size; i ++) {
                String now = q.poll();
                v.add(now);
                if (now.equals(a)) {
                    finda = true;
                    break;
                }
                LinkedList<String> ll = m.get(now);
                if (ll != null) {
                    for (String next : ll) {
                        if (! v.contains(next)) {
                            q.add(next);
                        }
                    }
                }
            }
            if (finda) break;
        }
        System.out.println((finda && findb) ? "T" : "F");
        sc.close();
    }
}
