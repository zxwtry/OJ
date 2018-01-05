package template;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_String_Trie图.java
 * @date        2017年7月23日 下午8:22:03
 * @details     使用nowcoder 去哪儿-文本嗅探 测试
 */
public class RECITE_String_Trie图 {
    static final char START = 'a';
    static final int LEN = 26;
    public static void main(String[] args) {
        String[] keys = {"abcde", "abcdf"};
        String[] ss = {"abababababababababcdabcdf"};
        int n = ss.length;
        int m = keys.length;
        tools.Utils.printArray(new KeywordDetect().containKeyword(ss, n, keys, m), n, 5);
    }
    static public class KeywordDetect {
        static final int LEN = 26;
        static final char START = 'a';
        public int[] containKeyword(String[] A, int n, String[] keys, int m) {
            if (n == 0) return new int[] {-1};
            if (m == 0) {
                int[] ans = new int[n];
                Arrays.fill(ans, -1);
                return ans;
            }
            return solve(keys, A);
        }
        static int[] solve(String[] keys, String[] ss) {
            TN root = init(keys);
            TN rootNow = null;
            LinkedList<Integer> ll = new LinkedList<>();
            for (int si = 0; si < ss.length; si ++) {
                String s = ss[si];
                int sn = s == null ? 0 : s.length();
                rootNow = root;
                for (int i = 0; i < sn; i ++) {
                    int nextIndex = s.charAt(i) - START;
                    rootNow = rootNow.nexts[nextIndex];
                    if (rootNow.isEndOfAWord) {
                        ll.add(si);
                        break;
                    }
                }
            }
            if (ll.size() == 0) return new int[] {-1};
            int[] ans = new int[ll.size()];
            int ai = 0;
            for (int v : ll) ans[ai ++] = v;
            return ans;
        }
        static TN init(String[] keys) {
            TN root = new TN();
            for (String key : keys) insert(key, root);
            root.suffix = root;
            Queue<TN> q = new LinkedList<TN>();
            for (int i = 0; i < LEN; i ++) {
                if (root.nexts[i] == null) {
                    root.nexts[i] = root;
                } else {
                    root.nexts[i].suffix = root;
                    q.add(root.nexts[i]);
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
            return root;
        }
        static void insert(String a, TN root) {
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
