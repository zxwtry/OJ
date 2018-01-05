package leetcode;

import java.util.LinkedList;
import java.util.List;

public class P022_GenerateParentheses {
    static class Solution3 {
        final char L = '(', R = ')';
        List<String> ans = new LinkedList<>();

        public List<String> generateParenthesis(int n) {
            char[] cs = new char[2 * n];
            for (int i = 0; i < n; i++) {
                cs[i] = L;
                cs[i + n] = R;
            }
            search(cs, n - 1, n, 2 * n - 2);
            return ans;
        }

        private void search(char[] cs, int i, int j, int k) {
            ans.add(new String(cs));
            if (j > k || i == -1)
                return;
            for (int v = j; v <= k; v++) {
                swap(cs, v, i);
                search(cs, i - 1, i, Math.min(2 * (i - 1), v - 1));
                swap(cs, v, i);
            }
        }

        void swap(char[] cs, int i, int j) {
            char c = cs[i];
            cs[i] = cs[j];
            cs[j] = c;
        }
    }
}
