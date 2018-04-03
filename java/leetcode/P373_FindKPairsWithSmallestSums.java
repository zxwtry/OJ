package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P373_FindKPairsWithSmallestSums.java
 * @date        2018年4月3日 上午10:16:26
 * @details     
 */
public class P373_FindKPairsWithSmallestSums {
    public static void main(String[] args) {
        //[-13,22,35,56,76]
        //[-13,22,44,117,990,1000,1543,2015]
        //25
        int[] nums1 = {-13,22,35,56,76};
        int[] nums2 = {-13,22,44,117,990,1000,1543,2015};
        int k = 25;
        List<int[]> ans = new Solution2().kSmallestPairs(nums1, nums2, k);
        for (int[] one : ans) {
            System.out.println(one[0] + "  " + one[1]);
        }
    }
    static class Solution {
        public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {            
            int i1 = 0, i2 = 0;
            int n1 = nums1 == null ? 0 : nums1.length;
            int n2 = nums2 == null ? 0 : nums2.length;
            if (n1 * n2 <= k) {
                i1 = n1 - 1;
                i2 = n2 - 1;
            } else {
                while (i1 < n1 || i2 < n2) {
                    if ((i1 + 1) * (i2 + 1) >= k) {
                        break;
                    }
                    if (i1 + 1 < n1 && i2 + 1 < n2) {

                        if (nums1[i1] + nums2[i2 + 1] >= nums1[i1 + 1] + nums2[i2]) {
                            i1 ++;
                        } else {
                            i2 ++;
                        }
                        while (i1 + 1 < n1 && nums1[i1] + nums2[i2] >= nums1[i1 + 1] + nums2[0]) {
                            i1 ++;
                        }
                        while (i2 + 1 < n2 && nums1[0] + nums2[i2 + 1] <= nums1[i1] + nums2[i2]) {
                            i2 ++;
                        }
                    } else {
                        boolean added = false;
                        while (i1 + 1 < n1 && nums1[i1] + nums2[i2] >= nums1[i1 + 1] + nums2[0]) {
                            i1 ++;
                            added = true;
                        }
                        while (i2 + 1 < n2 && nums1[0] + nums2[i2 + 1] <= nums1[i1] + nums2[i2]) {
                            i2 ++;
                            added = true;
                        }
                        if (! added) {
                            if (i1 + 1 < n1) i1 ++;
                            if (i2 + 1 < n2) i2 ++;
                        }
                    }
                }
                while (i1 + 1 < n1 && nums1[i1] + nums2[i2] >= nums1[i1 + 1] + nums2[0]) {
                    i1 ++;
                }
                while (i2 + 1 < n2 && nums1[0] + nums2[i2 + 1] <= nums1[i1] + nums2[i2]) {
                    i2 ++;
                }
            }
            List<int[]> ans = new ArrayList<>();
            for (int k1 = 0; k1 <= i1; k1 ++) {
                for (int k2 = 0; k2 <= i2; k2 ++) {
                    ans.add(new int[] {nums1[k1], nums2[k2]});
                }
            }
            Collections.sort(ans, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    int v1 = o1[0] + o1[1];
                    int v2 = o2[0] + o2[1];
                    return v1 < v2 ? -1 : 1;
                }
            });
            while (ans.size() > k) {
                ans.remove(ans.size() - 1);
            }
            return ans;
        }
    }
    static class Solution2 {
        public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            int n1 = nums1 == null ? 0 : nums1.length;
            int n2 = nums2 == null ? 0 : nums2.length;
            int[] s1 = new int[n1];
            int ansSize = Math.min(n1 * n2, k);
            List<int[]> ans = new ArrayList<>(ansSize);
            for (int i = 0; i < ansSize; i ++) {
                int minIndex = 0;
                int minValue = Integer.MAX_VALUE;
                for (int i1 = 0; i1 < n1; i1 ++) {
                    if (s1[i1] < n2) {
                        int nowValue = nums1[i1] + nums2[s1[i1]];
                        if (nowValue < minValue) {
                            minValue = nowValue;
                            minIndex = i1;
                        }
                    }
                }
                ans.add(new int[] {nums1[minIndex], nums2[s1[minIndex]]});
                s1[minIndex] ++;
            }
            return ans;
        }
    }
        
}
