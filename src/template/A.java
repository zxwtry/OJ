package template;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class A {
    public static void main(String[] args) {
        //["gcrc","yeaugthm","acdgoigr"],3,["yu"],1
        String[] A = {"gcrc","yeaugthm","acdgoigr"};
        int n = A.length;
        String[] keys = {"yu"};
        int m = keys.length;
        tools.Utils.printArray(new KeywordDetect().containKeyword(A, n, keys, m), 100, 5);
    }
    static public class KeywordDetect {
        static final int LEN = 26;
        static final char START = 'a';
        public int[] containKeyword(String[] A, int n, String[] keys, int m) {
            if (n == 0) return new int[] {-1};
            int[] ans = new int[n];
            if (m == 0) {
                Arrays.fill(ans, -1);
                return ans;
            }
            TN root = new TN();
            for (String key : keys) insert(key, root);
            root.suffix = root;
            Queue<TN> q = new LinkedList<>();
            for (int i = 0; i < LEN; i ++) {
                if (root.nexts[i] == null) {
                    root.nexts[i] = root;
                } else {
                    root.nexts[i].suffix = root;
                }
            }
            TN rootNow = null;
            while (! q.isEmpty()) {
                rootNow = q.poll();
                for (int i = 0; i < LEN; i ++) {
                    if (rootNow.nexts[i] == null) {
                        rootNow.nexts[i] = rootNow.suffix.nexts[i];
                    } else {
                        rootNow.nexts[i].suffix = rootNow.suffix.nexts[i];
                        q.add(rootNow.nexts[i]);
                    }
                }
            }
            int cnt = 0;
            for (int i = 0; i < n; i ++) {
                String s = A[i];
                int sn = s == null ? 0 : s.length();
                rootNow = root;
                boolean find = false;
                for (int j = 0; j < sn; j ++) {
                    rootNow = rootNow.nexts[s.charAt(j) - START];
                    System.out.println(i + "..." + j);
                    if (rootNow.isEndOfAWord) {
                        find = true;
                        break;
                    }
                }
                ans[i] = find ? i : -1;
                cnt += find ? 1 : 0;
            }
            if (cnt == n) return ans;
            if (cnt == 0) return new int[] {-1};
            int[] newA = new int[cnt];
            int ni = 0;
            for (int i = 0; i < n; i ++) {
                if (ans[i] != -1) {
                    newA[ni ++] = ans[i];
                }
            }
            return newA;
        }
        void insert(String a, TN root) {
            TN rootNow = root;
            int an = a == null ? 0 : a.length();
            for (int i = 0; i < an; i ++) {
                int nextIndex = a.charAt(i) - START;
                if (rootNow.nexts[nextIndex] == null) {
                    rootNow.nexts[nextIndex] = new TN();
                }
                rootNow = rootNow.nexts[nextIndex];
            }
            rootNow.isEndOfAWord = true;
        }
        static class TN {
            boolean isEndOfAWord;
            TN[] nexts = new TN[LEN];
            TN suffix;
        }
    }
}
