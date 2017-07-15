package nowcoder.com;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        去哪儿_血型遗传检测.java
 * @date        2017年7月15日 上午9:18:40
 * @details     
 */
public class 去哪儿_血型遗传检测 {
    public static void main(String[] args) {
        String father = "B";
        String mother = "A";
        System.out.println(Arrays.asList(new ChkBloodType().chkBlood(father, mother)));
    }
    static public class ChkBloodType {
        final String[] all = {"O", "A", "B", "AB"};
        final int[] key = {
            0 + 10 * 0,
            0 + 10 * 1,
            1 + 10 * 1,
            1 + 10 * 2,
            1 + 10 * 3,
            0 + 10 * 2,
            2 + 10 * 2,
            2 + 10 * 3,
            0 + 10 * 3,
            3 + 10 * 3,
        };
        final int[][] list = {
            {0},
            {1, 0},
            {1, 0},
            {1, 3, 2, 0},
            {1, 3, 2},
            {2, 0},
            {2, 0},
            {1, 3, 2},
            {1, 2},
            {1, 3, 2},
        };
        public String[] chkBlood(String father, String mother) {
            // write code here
            HashMap<Integer, int[]> m = new HashMap<>();
            for (int i = 0; i < list.length; i ++) {
                m.put(key[i], list[i]);
            }
            int fi = 0;
            int mi = 0;
            for (; fi < all.length; fi ++) {
                if (father.equals(all[fi])) break;
            }
            for (; mi < all.length; mi ++) {
                if (mother.equals(all[mi])) break;
            }
            int kk = Math.min(fi, mi) + 10 * Math.max(fi, mi);
            int[] arr = m.get(kk);
            String[] ans = new String[arr.length];
            for (int i = 0; i < arr.length; i ++) {
                ans[i] = all[arr[i]];
            }
            return ans;
        }
    }
}
