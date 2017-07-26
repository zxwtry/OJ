package template;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_Tree_并查集.java
 * @date        2017年7月25日 下午9:20:52
 * @details     AC http://hihocoder.com/problemset/problem/1066
 */
public class RECITE_Tree_并查集 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }
    
    static HashMap<String, String> map = new HashMap<>();

    private static void solve(Scanner sc) {
        map.clear();
        int n = sc.nextInt();
        for (int i = 0; i < n; i ++) {
            String a = sc.next();
            String b = sc.next();
            String c = sc.next();
            if (a.charAt(0) == '0') {
                //合并
                b = findRoot(b);
                c = findRoot(c);
                map.put(b, c);
            } else {
                //判断
                b = findRoot(b);
                c = findRoot(c);
                System.out.println(b.equals(c) ? "yes" : "no");
            }
        }
    }
    
    private static String findRoot(String s) {
        String v = map.get(s);
        if (v == null || v.equals(s)) {
            map.put(s, s);
            return s;
        }
        v = findRoot(v);
        map.put(s, v);
        return v;
    }
    
}
