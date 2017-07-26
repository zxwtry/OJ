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
public class RECITE_Tree_并查集2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }
    
    static HashMap<String, DisjointSet> map = new HashMap<>();

    private static void solve(Scanner sc) {
        map.clear();
        int n = sc.nextInt();
        for (int i = 0; i < n; i ++) {
            String a = sc.next();
            String b = sc.next();
            String c = sc.next();
            if (a.charAt(0) == '0') {
                //合并
                DisjointSet bs = map.get(b);
                DisjointSet cs = map.get(c);
                if (bs == null) {
                    bs = new DisjointSet(b);
                    map.put(b, bs);
                }
                if (cs == null) {
                    cs = new DisjointSet(c);
                    map.put(c, cs);
                }
                bs.union(cs);
            } else {
                //判断
                DisjointSet bs = map.get(b);
                DisjointSet cs = map.get(c);
                if (bs == null || cs == null) {
                    System.out.println("no");
                } else {
                    System.out.println(bs.findSet() == cs.findSet() ? "yes" : "no");
                }
            }
        }
    }
    
    static class DisjointSet {
        DisjointSet parent;
        String name;
        public DisjointSet(String name) {
            this.parent = this;
            this.name = name;
        }
        public void union(DisjointSet anotherSet) {
            this.findSet().parent = anotherSet.findSet();
        }
        public DisjointSet findSet() {
            if (this != parent) {
                parent = parent.findSet();
            }
            return parent;
        }
    }
    
}