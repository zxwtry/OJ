package template;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_Tree_线段数.java
 * @date        2017年7月25日 下午5:00:37
 * @details     http://hihocoder.com/problemset/problem/1077?sid=1136890
 * @details     solve: TLE   solve2: AC   如果使用Java碰到无法优化的TLE，可以使用BufferedReader代替Scanner
 */
public class RECITE_Tree_线段数 {
    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            solve(sc);
//        }
//        sc.close();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            if (! solve2(br) ) {
                break;
            }
        }
    }
 
    static boolean solve2(BufferedReader br) throws Exception {
        String nL = br.readLine();
        if (nL == null) return false;
        int n = 0;
        for (int i = 0; i < nL.length(); i ++) {
            n = n * 10 + nL.charAt(i) - '0';
        }
        int[] ws = new int[n + 1];
        String oL = br.readLine();
        int wi = 0;
        int v = 0;
        for (int i = 0; i < oL.length(); i ++) {
            char c = oL.charAt(i);
            if (c == ' ') {
                ws[wi ++] = v;
                v = 0;
            } else {
                v = v * 10 + c - '0';
            }
        }
        ws[wi ++] = v;
        SN root = init(ws, n, 0, n - 1);
        int m = 0;
        String mL = br.readLine();
        for (int i = 0; i < mL.length(); i ++) {
            m = m * 10 + mL.charAt(i) - '0';
        }
        int[] vs = new int[5];
        int vi  = 0;
        for (int i = 0; i < m; i ++) {
            String vL = br.readLine();
            v = 0;
            vi = 0;
            for (int k = 0; k < vL.length(); k ++) {
                char c = vL.charAt(k);
                if (c == ' ') {
                    vs[vi ++] = v;
                    v = 0;
                } else {
                    v = v * 10 + c - '0';
                }
            }
            if (vi == 2)
            vs[vi ++] = v;
            int a = vs[0], b = vs[1], c = vs[2];
            if (a == 0) {
                System.out.println(query(root, b - 1, c - 1));
            } else {
                update(root, b - 1, c);
            }
        }
        return true;
    }

    static void solve(Scanner sc) {
        int n = sc.nextInt();
        int[] ws = new int[n];
        for (int i = 0; i < n; i ++) {
            ws[i] = sc.nextInt();
        }
        SN root = init(ws, n, 0, n - 1);
        int m = sc.nextInt();
        for (int i = 0; i < m; i ++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            if (a == 0) {
                System.out.println(query(root, b - 1, c - 1));
            } else {
                update(root, b - 1, c);
            }
        }
    }
    
    private static void update(SN root, int index, int value) {
        LinkedList<SN> ll = new LinkedList<>();
        findIndex(root, index, ll);
        SN rootNow = ll.pollLast();
        rootNow.value = value;
        while (ll.size() != 0) {
            rootNow = ll.pollLast();
            int leftValue = rootNow.left == null ? Integer.MAX_VALUE : rootNow.left.value;
            int rightValue = rootNow.right == null ? Integer.MAX_VALUE : rootNow.right.value;
            rootNow.value = Math.min(leftValue, rightValue);
        }
    }
    
    private static void findIndex(SN root, int index, LinkedList<SN> ll) {
        if (root == null) return;
        ll.add(root);
        if (root.low == index && root.high == index) return;
        int m = root.low + (root.high - root.low) / 2;
        if (index <= m) findIndex(root.left, index, ll);
        else findIndex(root.right, index, ll);
    }

    private static int query(SN root, int i, int j) {
        int ans = Integer.MAX_VALUE;
        if (i > j || root == null) return ans;
        if (i == root.low && j == root.high) return root.value;
        int m = root.low + (root.high - root.low) / 2;
        if (i <= m && j <= m) {
            return query(root.left, i, j);
        }
        if (i > m && j > m) {
            return query(root.right, i, j);
        }
        return Math.min(query(root, i, m), query(root, m + 1, j));
    }
    
    private static SN init(int[] ws, int n, int i, int j) {
        if (n < 1) return null;
        if (i > j) return null;
        SN rootNow = new SN(i, j);
        if (i == j) {
            rootNow.value = ws[i];
            return rootNow;
        }
        int m = i + (j - i) / 2;
        rootNow.left = init(ws, n, i, m);
        rootNow.right = init(ws, n, m + 1, j);
        rootNow.value = Integer.MAX_VALUE;
        if (rootNow.left != null) {
            rootNow.value = Math.min(rootNow.value, rootNow.left.value);
        }
        if (rootNow.right != null) {
            rootNow.value = Math.min(rootNow.value, rootNow.right.value);
        }
        return rootNow;
    }

    static class SN {
        int low, high;
        int value;
        public SN(int left, int right) {
            this.low = left;
            this.high = right;
        }
        SN left, right;
        @Override
        public String toString() {
            return String.format("low:%d  high:%d  value:%d", low, high, value);
        }
    }
}
