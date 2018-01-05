package nowcoder.com;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        去哪儿_文本嗅探.java
 * @date        2017年7月14日 下午10:06:21
 * @details     
 */
public class 去哪儿_文本嗅探 {
    //["nowcoder","hello","now"],3,["coder",now],2
    public static void main(String[] args) {
        String[] A = {"nowcoder","hello","now"};
        int n = A.length;
        String[] keys = {"coder","now"};
        int m = keys.length;
        int[] ans = new KeywordDetect().containKeyword(A, n, keys, m);
        tools.Utils.printArray(ans, ans.length, 5);
    }
    static public class KeywordDetect {
        public int[] containKeyword(String[] A, int n, String[] keys, int m) {
            TN root = new TN();
            for (String key : keys) {
                insert(key, root);
            }
            root.suffix = root;
            Queue<TN> q = new LinkedList<>();
            for (int i = 0; i < 26; i ++) {
                if (null == root.nexts[i]) {
                    root.nexts[i] = root;
                } else {
                    root.nexts[i].suffix = root;
                    q.add(root.nexts[i]);
                }
            }
            TN rootNow = null;
            while (! q.isEmpty()) {
                rootNow = q.poll();
                for (int i = 0; i < 26; i ++) {
                    if (null == rootNow.nexts[i]) {
                        rootNow.nexts[i] = rootNow.suffix.nexts[i];
                    } else {
                        rootNow.nexts[i].suffix = rootNow.suffix.nexts[i];
                        q.add(rootNow.nexts[i]);
                    }
                }
            }
            LinkedList<Integer> ll = new LinkedList<>();
            for (int i = 0; i < n;i ++) {
                String s = A[i];
                int sn = s.length();
                boolean isFind = false;
                rootNow = root;
                for (int k = 0; k < sn && ! isFind; k ++) {
                    rootNow = rootNow.nexts[s.charAt(k) - 'a'];
                    isFind |= rootNow.isEndOfAWord;
                }
                if (isFind) {
                    ll.add(i);
                }
            }

            if (ll.size() == 0) {
                return new int[]{-1};
            }
            int[] ans = new int[ll.size()];
            int ansi = 0;
            for (Integer llv : ll) {
                ans[ansi ++] = llv;
            }
            return ans;
        }
        static void insert(String key, TN root) {
            TN rootNow = root;
            int keyLength = key.length();
            for (int i = 0; i < keyLength; i ++) {
                int nextsIndex = key.charAt(i) - 'a';
                if (null != rootNow.nexts[nextsIndex]) {
                    rootNow = rootNow.nexts[nextsIndex];
                } else {
                    TN tn = new TN();
                    rootNow.nexts[nextsIndex] = tn;
                    rootNow = tn;
                }
            }
            rootNow.isEndOfAWord = true;
        }
        static class TN {
            boolean isEndOfAWord = false;
            TN[] nexts = new TN[26];
            TN suffix = null;
        }
    }
}
