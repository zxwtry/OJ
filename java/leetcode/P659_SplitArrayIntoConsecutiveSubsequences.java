package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P659_SplitArrayIntoConsecutiveSubsequences.java
 * @date        2017年8月13日 上午10:03:31
 * @details     
 */
public class P659_SplitArrayIntoConsecutiveSubsequences {
    static public class Solution {
        public boolean isPossible(int[] nums) {
            int nn = nums == null ? 0 : nums.length;
            if (nn < 1) return false;
            int[] val = new int[nn];
            int[] cnt = new int[nn];
            int cntIndex = 0;
            val[cntIndex] = nums[0];
            cnt[cntIndex] = 1;
            cntIndex ++;
            for (int i = 1; i < nn; i ++) {
                int v = nums[i];
                if (val[cntIndex - 1] == v) {
                    cnt[cntIndex - 1] ++;
                } else {
                    val[cntIndex] = v;
                    cnt[cntIndex] = 1;
                    cntIndex ++;
                }
            }
            return solve(cnt, val, cntIndex);
        }
        boolean solve(int[] cnt, int[] val, int cntLength) {
            if (cntLength < 3) return false;
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < cnt[0]; i ++) {
                q.add(0);
            }
            for (int i = 1; i < cntLength; i ++) {
                if (val[i] - val[i - 1] != 1) {
                    //必须断开
                    if (q.isEmpty()) {
                        return false;
                    }
                    if (i - 1 - q.peek() + 1 < 3) {
                        return false;
                    }
                    q.poll();
                    q.add(i);
                } else {
                    //尽量连上
                    //断开
                    int cntCut = cnt[i] - cnt[i - 1];
                    for (int t = 0; t < - cntCut; t ++) {
                        if (q.isEmpty()) {
                            return false;
                        }
                        if (i - 1 - q.peek() + 1 < 3) {
                            return false;
                        }
                        q.poll();
                    }
                    //新增
                    for (int t = 0; t < cntCut; t ++) {
                        q.add(i);
                    }
                }
            }
            while (! q.isEmpty()) {
                if (cntLength - 1 - q.poll() + 1 < 3) {
                    return false;
                }
            }
            return true;
        }
    }
}
