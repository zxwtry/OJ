package nowcoder.com;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        去哪儿_寻找Coder.java
 * @date        2017年7月7日 下午8:52:30
 * @details     
 */
public class 去哪儿_寻找Coder {
    static public class Coder {
        static final char[] lc = "coder".toCharArray();
        static final char[] uc = "CODER".toCharArray();
        public String[] findCoder(String[] A, int n) {
            ArrayList<T> al  = new ArrayList<>(n);
            for (int i = 0; i < A.length; i ++) {
                int c = calc(A[i]);
                if (c != 0) {
                    al.add(new T(c, i));
                }
            }
            Collections.sort(al);
            String[] ans = new String[al.size()];
            for (int i = 0; i < al.size(); i ++) {
                ans[i] = A[al.get(i).index];
            }
            return ans;
        }
        int calc(String s) {
            int cnt = 0;
            int sn = s.length();
            if (sn < lc.length) return cnt;
            for (int i = 0; i <= sn - lc.length; i ++) {
                boolean alltrue = true;
                for (int k = 0; k < lc.length; k ++) {
                    char c = s.charAt(i + k);
                    alltrue &= (c == lc[k] || c == uc[k]);
                }
                if (alltrue) cnt ++;
            }
            return cnt;
        }
        static class T implements Comparable<T>{
            public T(int cnt, int index) {
                this.cnt = cnt;
                this.index = index;
            }
            int cnt;
            int index;
            @Override
            public int compareTo(T o) {
                if (cnt != o.cnt) {
                    return Integer.compare(o.cnt, this.cnt);
                }
                return Integer.compare(this.index, o.index);
            }
        }
    }
}
