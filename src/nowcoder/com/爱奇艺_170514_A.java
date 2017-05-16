package nowcoder.com;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;


public class 爱奇艺_170514_A {
    public static void main(String[] args) {
        solve();
    }
    static void solve2() {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();   // money
        ArrayList<Integer> pl = new ArrayList<>();
        ArrayList<Integer> vl = new ArrayList<>();
        HashMap<Integer, Integer> m = new HashMap<>();
//        TreeSet<Integer> pset = new TreeSet<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return Integer.compare(o2, o1);
//            }
//        });
        TreeSet<Integer> pset = new TreeSet<>();
        int ans = 0;
        while (in.hasNextInt()) {
            int p = in.nextInt();
            int v = in.nextInt();
            pl.add(p);
            vl.add(v);
            ans = Math.max(ans, v);
//            pset.add(p);
        }
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < pl.size(); i ++) {
//            Integer p = pset.lower(e)
            int ruler = s+1;
            while (true) {
                Integer v = pset.lower(s);
                if (v == null) break;
              int nv = v + pl.get(i);;
              if (nv <= s) {
                  al.add(nv);
                  Integer kk = m.get(nv);
                  if (kk == null) kk = 0;
                  Integer kk2 = m.get(v);
                  if (kk2 == null) kk2 = 0;
                  int vv = Math.max(kk, kk2 + vl.get(i));
                  m.put(nv, vv);
                  ans = Math.max(ans, vv);
                  pset.add(nv);
              }
            }
//            for (Integer v : pset) {
//                int nv = v + pl.get(i);;
//                if (nv <= s) {
//                    al.add(nv);
//                    Integer kk = m.get(nv);
//                    if (kk == null) kk = 0;
//                    Integer kk2 = m.get(v);
//                    if (kk2 == null) kk2 = 0;
//                    int vv = Math.max(kk, kk2 + vl.get(i));
//                    m.put(nv, vv);
//                    ans = Math.max(ans, vv);
//                }
//            }
            pset.add(pl.get(i));
            m.put(pl.get(i), vl.get(i));
            pset.addAll(al);
            al.clear();
        }
        System.out.println(ans);
    }
    static void solve() {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();   // money
        ArrayList<Integer> pl = new ArrayList<>();
        ArrayList<Integer> vl = new ArrayList<>();
        int ans = 0;
        int[] m = new int[s + 1];
        int pm = Integer.MAX_VALUE;
        while (in.hasNextInt()) {
            int p = in.nextInt();
            int v = in.nextInt();
            pl.add(p);
            vl.add(v);
            pm = Math.min(pm, p);
        }
        pm = Math.max(pm, 1);
        for (int i = pl.size() - 1; i > -1; i --) {
            for (int j = s; j >= 0; j -= pm) {
                int k = j - pl.get(i);
                if (k < 0) break;
                m[j] = Math.max(m[j], m[k] + vl.get(i));
            }
        }
        System.out.println(m[s]);
    }
}
