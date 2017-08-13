package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P658_FindKClosestElements.java
 * @date        2017年8月13日 上午9:39:01
 * @details     
 */
public class P658_FindKClosestElements {
    static public class Solution {
        public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
            int an = arr == null ? 0 : arr.size();
            List<Integer> ans = new ArrayList<>(k);
            if (an < 1 || k < 1) return ans;
            int index = Collections.binarySearch(arr, x);
            if (index < 0) {
                int t = - (index + 1);
                if (t >= an) {
                    index = t - 1;
                } else {
                    int v = t - 1;
                    if (v > -1) {
                        index = arr.get(t) < arr.get(v) ? t : v;
                    } else {
                        index = t;
                    }
                }
            }
            int left = index, right = index;
            while (left > -1 || right < an) {
                if (right - left + 1 == k) {
                    break;
                }
                Integer leftValue = left - 1 > -1 ? arr.get(left - 1) : null;
                Integer rightValue = right + 1 < an ? arr.get(right + 1) : null;
                if (getCut(leftValue, x) <= getCut(rightValue, x)) {
                    left --;
                } else {
                    right ++;
                }
            }
            for (int i = left; i <= right; i ++) {
                ans.add(arr.get(i));
            }
            return ans;
        }
        
        long getCut(Integer a, Integer b) {
            if (a == null) return Long.MAX_VALUE;
            long v = a;
            return Math.abs(v - b);
        }
    }
}
