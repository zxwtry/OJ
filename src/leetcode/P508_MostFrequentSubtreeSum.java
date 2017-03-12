package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import tools.TreeNode辅助.TreeNode;

/**
 *  Given the root of a tree, you are asked to find the most frequent subtree sum. 
 *  The subtree sum of a node is defined as the sum of all the node values formed 
 *  by the subtree rooted at that node (including the node itself). So what is the most 
 *  frequent subtree sum value? If there is a tie, return all the values with the highest 
 *  frequency in any order.
 *  
 *  Examples 1
 *  Input:
 *  
 *    5
 *   /  \
 *  2   -3
 *  return [2, -3, 4], since all the values happen only once, return all of them in any order.
 *  Examples 2
 *  Input:
 *  
 *    5
 *   /  \
 *  2   -5
 *  return [2], since 2 happens twice, however -5 only occur once.
 *  Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P508_MostFrequentSubtreeSum.java
 * @type        P508_MostFrequentSubtreeSum
 * @date        2017年3月11日 下午9:34:33
 * @details     Solution1: AC 17ms 84.77% 
 * @details     Solution2: AC 29ms 26.15%
 * @details     Solution3: AC 17ms 84.77%
 */
public class P508_MostFrequentSubtreeSum {
    static class Solution1 {
        Map<Integer, Integer> sumToCount;
        int maxCount;
        public int[] findFrequentTreeSum(TreeNode root) {
            maxCount = 0;
            sumToCount = new HashMap<Integer, Integer>();
            postOrder(root);
            List<Integer> res = new ArrayList<>();
            for (int key : sumToCount.keySet()) {
                if (sumToCount.get(key) == maxCount) {
                    res.add(key);
                }
            }
            int[] result = new int[res.size()];
            for (int i = 0; i < res.size(); i++) {
                result[i] = res.get(i);
            }
            return result;
        }
        private int postOrder(TreeNode root) {
            if (root == null) return 0;
            int left = postOrder(root.left);
            int right = postOrder(root.right);
            int sum = left + right + root.val;
            int count = sumToCount.getOrDefault(sum, 0) + 1;
            sumToCount.put(sum, count);
            maxCount = Math.max(maxCount, count);
            return sum;
        }
    }
    static class Solution2 {
        public int[] findFrequentTreeSum(TreeNode root) {
            HashMap<Integer, HashSet<Integer>> countSet = new HashMap<Integer, HashSet<Integer>>();
            HashMap<Integer, Integer> sumCount = new HashMap<Integer, Integer>(); 
            int[] maxCount = new int[] {Integer.MIN_VALUE};
            search(root, countSet, maxCount, sumCount);
            int[] answer = new int[countSet.get(maxCount[0]).size()];
            int answerIndex = 0;
            for (int val : countSet.get(maxCount[0]))
                answer[answerIndex ++] = val;
            return answer;
        }
        private int search(TreeNode root, HashMap<Integer, HashSet<Integer>> countSet, int[] maxCount, HashMap<Integer, Integer> sumCount) {
            if (root == null) return 0;
            int val = root.val + search(root.left, countSet, maxCount, sumCount) + search(root.right, countSet, maxCount, sumCount);
            int count = sumCount.getOrDefault(val, 0) + 1;
            sumCount.put(val, count);
            if (count != 1) countSet.get(count - 1).remove(val);
            HashSet<Integer> set = countSet.getOrDefault(count, new HashSet<Integer>());
            set.add(val);
            countSet.put(count, set);
            maxCount[0] = Math.max(maxCount[0], count);
            return val;
        }
    }
    static class Solution3 {
        public int[] findFrequentTreeSum(TreeNode root) {
            int[] countCount = new int[]{Integer.MIN_VALUE, 0};
            HashMap<Integer, Integer> sumCount = new HashMap<>();
            find(root, countCount, sumCount);
            int[] answer = new int[countCount[1]];
            int answerIndex = 0;
            for (Map.Entry<Integer, Integer> entry : sumCount.entrySet())
                if (entry.getValue() == countCount[0])
                    answer[answerIndex ++] = entry.getKey();
            return answer;
        }
        private int find(TreeNode root, int[] countCount, HashMap<Integer, Integer> sumCount) {
            if (root == null) return 0;
            int sum = root.val + find(root.left, countCount, sumCount) + find(root.right, countCount, sumCount);
            int count = sumCount.getOrDefault(sum, 0) + 1;
            sumCount.put(sum, count);
            if (countCount[0] == count) countCount[1] ++;
            else if (countCount[0] < count) {
                countCount[0] = count;
                countCount[1] = 1;
            }
            return sum;
        }
    }
}
