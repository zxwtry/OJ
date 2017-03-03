package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;


/**
 * 	Given a non-empty array of integers, return the k most frequent elements.
 *	
 *	For example,
 *	Given [1,1,1,2,2,3] and k = 2, return [1,2].
 *	
 *	Note: 
 *	You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 *	Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 *	
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P347_TopKFrequentElements.java
 * @type        P347_TopKFrequentElements
 * @date        2017年2月3日 下午11:39:29
 * @details     Solution1: AC 46ms 24.16% *
 * @details     Solution2: AC 26ms 85.78% *
 * @details     Solution3: AC 33ms 55.62% *
 */
public class P347_TopKFrequentElements {
	static class Solution1 {
	    public List<Integer> topKFrequent(int[] nums, int k) {
	        HashMap<Integer, Integer> map = new HashMap<>();
	        for (int n : nums )
	            map.put(n, map.getOrDefault(n, 0) + 1);
	        //使用堆完成前K个查找
	        int[] maxK = new int[k];
	        int maxKIndex = 0;
	        for (Entry<Integer, Integer> entry : map.entrySet()) {
	            if (maxKIndex < k) {
	                maxK[maxKIndex ++] = entry.getKey();
	                if (maxKIndex == k) {
	                    for (int endIndex = (k - 1) / 2; endIndex >= 0; endIndex --) {
	                        heapDn(map, maxK, maxKIndex, endIndex);
	                    }
	                }
	            } else {
	                if (entry.getValue() > map.get(maxK[0])) {
	                    maxK[0] = entry.getKey();
	                    heapDn(map, maxK, k, 0);
	                }
	            }
	        }
	        List<Integer> list = new ArrayList<>(k);
	        for (int m : maxK)
	            list.add(m);
	        return list;
	    }
	    private void heapDn(HashMap<Integer, Integer> map, int[] maxK, int k, int index) {
	        int c = 2 * index + 1;
	        while (c < k) {
	            if (c + 1 < k && map.get(maxK[c + 1]) < map.get(maxK[c])) c ++;
	            if (map.get(maxK[c]) < map.get(maxK[index])) {
	                swap(maxK, index, c);
	                index = c;
	                c = 2 * index + 1;
	            } else break;
	        }
	    }
	    private void swap(int[] arr, int i, int j) {
	        int temp = arr[i];
	        arr[i] = arr[j];
	        arr[j] = temp;
	    }
	}
}
