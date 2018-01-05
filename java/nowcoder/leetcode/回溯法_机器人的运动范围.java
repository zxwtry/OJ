package nowcoder.leetcode;

import java.util.LinkedList;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        回溯法_机器人的运动范围.java
 * @date        2017年7月4日 下午10:17:15
 * @details     剑指Offer
 */
public class 回溯法_机器人的运动范围 {
    static public class Solution {
        public int movingCount(int h, int rs, int cs) {
            LinkedList<Integer> rl = new LinkedList<>();
            LinkedList<Integer> cl = new LinkedList<>();
            rl.add(0);
            cl.add(0);
            boolean[][] v = new boolean[rs][cs];
            int cnt = 0;
            while (rl.size() != 0) {
                int ri = rl.poll();
                int ci = cl.poll();
                if (! v[ri][ci]) {
                    String sr = String.valueOf(ri);
                    String sl = String.valueOf(ci);
                    int sum = 0;
                    for (int i = sr.length() - 1; i > -1; i --) {
                        sum += sr.charAt(i) - '0';
                    }
                    for (int i = sl.length() - 1; i > -1; i --) {
                        sum += sl.charAt(i) - '0';
                    }
                    if (sum <= h) {
                        v[ri][ci] = true;
                        cnt ++;
                        if (ri - 1 > -1 && ! v[ri - 1][ci]) {
                            rl.add(ri - 1);
                            cl.add(ci);
                        }
                        if (ri + 1 < rs && ! v[ri + 1][ci]) {
                            rl.add(ri + 1);
                            cl.add(ci);
                        }
                        if (ci - 1 > -1 && ! v[ri][ci - 1]) {
                            rl.add(ri);
                            cl.add(ci - 1);
                        }
                        if (ci + 1 < cs && ! v[ri][ci + 1]) {
                            rl.add(ri);
                            cl.add(ci + 1);
                        }
                    }
                }
            }
            return cnt;
        }
    }
}
