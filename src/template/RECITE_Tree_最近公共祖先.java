package template;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_Tree_最近公共祖先.java
 * @date        2017年7月26日 上午10:16:46
 * @details     TLE http://hihocoder.com/problemset/problem/1067
 */
public class RECITE_Tree_最近公共祖先 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }
    
    static enum Color {
        WHITE, GREY, BLACK
    }
    
    static HashMap<String, HashMap<String, LinkedList<Integer>>> qm = new HashMap<>(1000000, 1);
    static HashMap<String, TN> nm = new HashMap<>(1000000, 1);
    
    static void solve(Scanner sc) {
        qm.clear();
        nm.clear();
        int n = sc.nextInt();
        TN root = null;
        for (int i = 0; i < n; i ++) {
            String a = sc.next();
            String b = sc.next();
            TN na = nm.get(a);
            TN nb = nm.get(b);
            if (na == null) {
                na = new TN(a);
                nm.put(a, na);
                qm.put(a, new HashMap<String, LinkedList<Integer>>());
                if (root == null) root = na;
            }
            if (nb == null) {
                nb = new TN(b);
                nm.put(b, nb);
                qm.put(b, new HashMap<String, LinkedList<Integer>>());
            }
            na.children.add(nb);
        }
        int m = sc.nextInt();
        String[] ans = new String[m];
        for (int i = 0; i < m; i ++) {
            String a = sc.next();
            String b = sc.next();
            HashMap<String, LinkedList<Integer>> qa = qm.get(a);
            LinkedList<Integer> lb = qa.get(b);
            if (lb == null) {
                lb = new LinkedList<>();
                qa.put(b, lb);
            }
            lb.add(i);
            HashMap<String, LinkedList<Integer>> qb = qm.get(b);
            LinkedList<Integer> la = qb.get(a);
            if (la == null) {
                la = new LinkedList<>();
                qb.put(a, la);
            }
            la.add(i);
        }
        dfs(root, null, ans);
        for (String s : ans)
            System.out.println(s);
    }
    
    private static void dfs(TN root, TN parent, String[] ans) {
        root.color = Color.GREY;
        HashMap<String, LinkedList<Integer>> qmm = qm.get(root.name);
        for (String mate : qmm.keySet()) {
            TN mateTN = nm.get(mate);
            String ansOne = null;
            if (mateTN.color == Color.BLACK) {
                ansOne = mateTN.ds.findSet().name;
            } else if (mateTN.color == Color.GREY) {
                ansOne = mateTN.name;
            }
            for (int ansIndex : qmm.get(mateTN.name)) {
                ans[ansIndex] = ansOne;
            }
        }
        for (TN child : nm.get(root.name).children) {
            if (child.color == Color.WHITE) {
                dfs(child, root, ans);
            }
        }
        if (parent != null) {
            root.ds.union(parent.ds);
        }
        root.color = Color.BLACK;
    }

    static class TN {
        LinkedList<TN> children = new LinkedList<>();
        DisjointSet ds;
        String name;
        Color color = Color.WHITE;
        public TN(String name) {
            this.name = name;
            ds = new DisjointSet(name);
        }
    }
    
    static class DisjointSet {
        DisjointSet parent;
        String name;
        public DisjointSet(String name) {
            this.parent = this;
            this.name = name;
        }
        public void union(DisjointSet a) {
            this.findSet().parent = a.findSet();
        }
        public DisjointSet findSet() {
            if (this != parent) {
                parent = parent.findSet();
            }
            return parent;
        }
    }
}