package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * 	Given an integer array, your task is to find all the different possible increasing subsequences 
 * 	of the given array, and the length of an increasing subsequence should be at least 2 .
 *	
 *	Example:
 *	Input: [4, 6, 7, 7]
 *	Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 *	Note:
 *	The length of the given array will not exceed 15.
 *	The range of integer in the given array is [-100,100].
 *	The given array may contain duplicates, and two equal integers should also be considered as a 
 *	special case of increasing sequence.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P491_IncreasingSubsequences.java
 * @type        P491_IncreasingSubsequences
 * @date        2017年2月6日 下午11:29:12
 * @details     Solution1: AC 98ms  8.00%
 * @details     Solution2: AC 60ms 40.13%
 */
public class P491_IncreasingSubsequences {
	static class Solution1 {
	    List<List<Integer>> answer = new LinkedList<List<Integer>>();
	    public List<List<Integer>> findSubsequences(int[] nums) {
	        if (nums == null || nums.length == 0)
	            return answer;
	        HashSet<List<Integer>> answerSet = new HashSet<List<Integer>>();
	        HashMap<Integer, TreeSet<Integer>> mapValueIndex = new HashMap<>();
	        for (int index = 0; index < nums.length; index ++) {
	            TreeSet<Integer> set = mapValueIndex.getOrDefault(nums[index], new TreeSet<Integer>());
	            set.add(index);
	            mapValueIndex.put(nums[index], set);
	        }
	        HashMap<Integer, LinkedList<LinkedList<Integer>>> map = new HashMap<>();
	        for (int index = 0; index < nums.length; index ++) {
	            TreeSet<Integer> indexSet = mapValueIndex.get(nums[index]);
	            LinkedList<LinkedList<Integer>> lll = new LinkedList<LinkedList<Integer>>();
	            if (indexSet.size() == 1 || indexSet.subSet(0, index).size() == 0) {
	                for (int j = 0; j < index; j ++) {
	                    if (mapValueIndex.get(nums[j]).subSet(0, j + 1).last() != j) continue;
	                    if (nums[j] < nums[index]) {
	                        LinkedList<Integer> llT = new LinkedList<>();
	                        llT.add(nums[j]);
	                        llT.add(nums[index]);
	                        lll.add(llT);
	                        for (LinkedList<Integer> ll : map.get(j)) {
	                            @SuppressWarnings("unchecked")
                                LinkedList<Integer> llC = (LinkedList<Integer>)ll.clone();
	                            llC.add(nums[index]);
	                            lll.add(llC);
	                        }
	                    }
	                }
	            } else {
	                SortedSet<Integer> indexSubSet =  indexSet.subSet(0, index);
	                int i = indexSubSet.last();
	                if (indexSubSet.size() == 1) {
    	                LinkedList<Integer> llT = new LinkedList<>();
    	                for (int t = 0; t < indexSubSet.size() + 1; t ++)
    	                    llT.add(nums[index]);
    	                lll.add(llT);
	                }
	                for (LinkedList<Integer> ll : map.get(i)) {
	                    @SuppressWarnings("unchecked")
                        LinkedList<Integer> llC = (LinkedList<Integer>) ll.clone();
	                    llC.add(nums[index]);
	                    lll.add(llC);
	                }
	                for (int v = i + 1; v < index; v ++) {
	                    if (mapValueIndex.get(nums[v]).subSet(0, v + 1).last() != v) continue;
	                    if (nums[v] < nums[index]) {
                            LinkedList<Integer> llG = new LinkedList<>();
                            llG.add(nums[v]);
                            llG.add(nums[index]);
                            lll.add(llG);
                            for (LinkedList<Integer> ll : map.get(v)) {
                                @SuppressWarnings("unchecked")
                                LinkedList<Integer> llC = (LinkedList<Integer>)ll.clone();
                                llC.add(nums[index]);
                                lll.add(llC);
                            }
                        }
	                }
	            }
	            map.put(index, lll);
	        }
	        for (Entry<Integer, LinkedList<LinkedList<Integer>>> e : map.entrySet()) {
	            answerSet.addAll(e.getValue());
	        }
	        return new LinkedList<>(answerSet);
	    }
	}
	static class Solution2 {
	    HashSet<List<Integer>> answerSet = new HashSet<List<Integer>>();
        ArrayList<Integer> list = new ArrayList<>();
	    public List<List<Integer>> findSubsequences(int[] nums) {
	        find(0, nums);
	        return new ArrayList<>(answerSet);
	    }
        private void find(int index, int[] nums) {
            if (list.size() > 1) answerSet.add(new ArrayList<Integer>(list));
            for (int i = index; i < nums.length; i ++) {
                if (list.size() == 0 || nums[i] >= list.get(list.size() - 1)) {
                    list.add(nums[i]);
                    find(i + 1, nums);
                    list.remove(list.size() - 1);
                }
            }
        }
	}
}
