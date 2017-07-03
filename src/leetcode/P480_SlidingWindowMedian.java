package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 	Median is the middle value in an ordered integer list. If the size of the list is even, 
 * 	there is no middle value. So the median is the mean of the two middle value.
 *	
 *	Examples: 
 *	[2,3,4] , the median is 3
 *	
 *	[2,3], the median is (2 + 3) / 2 = 2.5
 *	
 *	Given an array nums, there is a sliding window of size k which is moving from 
 *	the very left of the array to the very right. You can only see the k numbers in the window. 
 *	Each time the sliding window moves right by one position. 
 *	Your job is to output the median array for each window in the original array.
 *	
 *	For example,
 *	Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *	
 *	Window position                Median
 *	---------------               -----
 *	[1  3  -1] -3  5  3  6  7       1
 *	 1 [3  -1  -3] 5  3  6  7       -1
 *	 1  3 [-1  -3  5] 3  6  7       -1
 *	 1  3  -1 [-3  5  3] 6  7       3
 *	 1  3  -1  -3 [5  3  6] 7       5
 *	 1  3  -1  -3  5 [3  6  7]      6
 *	Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 *	
 *	Note: 
 *	You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P480_SlidingWindowMedian.java
 * @type        P480_SlidingWindowMedian
 * @date        2017年1月8日 下午5:26:44
 * @details     Solution1: WA
 * @details     Solution2: AC 74ms 60.19%
 * @details     Solution3: TLE
 */
public class P480_SlidingWindowMedian {
    static class Solution1 {
        public double[] medianSlidingWindow(int[] nums, int k) {
            if (nums == null || k < 1 || nums.length < k)
                return new double[0];
            if (k == 1) {
                double[] answer = new double[nums.length];
                for (int index = 0; index < nums.length; index ++) {
                    answer[index] = nums[index];
                }
                return answer;
            }
            double[] answer = new double[nums.length - k + 1];
            int[] nis = new int[nums.length];
            int[] arr = new int[k];
            int i = 0;
            for (; i < k; i ++) {
                nis[i] = i;
                arr[i] = nums[i];
            }
            int answerIndex = 0;
            bubbleSort(arr, nis);
            setAnswer(answer, answerIndex ++, arr, k);
            int removeNumsIndex = 0;
            System.out.print("arr is \t");
            tools.Utils.printArray(arr, arr.length, 5);
            System.out.print("nis is \t");
            tools.Utils.printArray(nis, nis.length, 5);
            for (; i < nums.length; i ++) {
                nis[i] = nis[removeNumsIndex];
                arr[nis[removeNumsIndex]] = nums[i];
                adjust(nis, arr, nis[removeNumsIndex]);
                setAnswer(answer, answerIndex, arr, k);
                answerIndex ++;
                removeNumsIndex ++;
                System.out.print("arr is \t");
                tools.Utils.printArray(arr, arr.length, 5);
                System.out.print("nis is \t");
                tools.Utils.printArray(nis, nis.length, 5);
            }
            return answer;
        }
        private void adjust(int[] nis, int[] arr, int i) {
            while (i >= 0 && i < arr.length) {
                if (arr[i] >= accessArr(arr, i - 1) && arr[i] <= accessArr(arr, i + 1))
                    return;
                if (arr[i] < accessArr(arr, i - 1)) {
                    swap(arr, i - 1, i);
                    swap(nis, i - 1, i);
//                    swap(nis, nis[i - 1], nis[i]);
                    i --;
                }
                if (arr[i] > accessArr(arr, i + 1)) {
                    swap(arr, i + 1, i);
                    swap(nis, i + 1, i);
//                    swap(nis, nis[i + 1], nis[i]);
                    i ++;
                }
            }
        }
        private int accessArr(int[] arr, int index) {
            if (index < 0) return Integer.MIN_VALUE;
            else if (index >= arr.length) return Integer.MAX_VALUE;
            return arr[index];
        }
        private void bubbleSort(int[] arr, int[] nis) {
            for (int i = 0; i < arr.length; i ++) {
                for (int j = 0; j < i; j ++) {
                    if (arr[j] > arr[i]) {
                        swap(arr, i, j);
                        swap(nis, i, j);
//                        swap(nis, nis[i], nis[j]);
                    }
                }
            }
        }
        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        private void setAnswer(double[] answer, int index, int[] arr, int k) {
            if ((k & 1) == 1) {
                answer[index] = arr[k >> 1];
            } else {
                answer[index] = (arr[k >> 1] + arr[(k >> 1) - 1]) / 2.0;
            }
        }
    }
    
    static class Solution2 {
        static class T implements Comparable<T> {
            int val;
            public T(int val) {
                this.val = val;
            }
            @Override
            public int compareTo(T o) {
                return Integer.compare(o.val, this.val);
            }
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(
//            new Comparator<Integer>() {
//                public int compare(Integer i1, Integer i2) {
//                    return i2.compareTo(i1);
//                }
//            }
//        );
        PriorityQueue<T> maxHeap = new PriorityQueue<>();
        public double[] medianSlidingWindow(int[] nums, int k) {
            int n = nums.length - k + 1;
            if (n <= 0) return new double[0];
            double[] result = new double[n];
            for (int i = 0; i <= nums.length; i++) {
                if (i >= k) {
                    result[i - k] = getMedian();
                    remove(nums[i - k]);
                }
                if (i < nums.length) {
                    add(nums[i]);
                }
            }
            return result;
        }
        private void add(int num) {
            if (num < getMedian()) {
                maxHeap.add(new T(num));
            }
            else {
                minHeap.add(num);
            }
            if (maxHeap.size() > minHeap.size()) {
                    minHeap.add(maxHeap.poll().val);
            }
            if (minHeap.size() - maxHeap.size() > 1) {
                maxHeap.add(new T(minHeap.poll()));
            }
        }
        private void remove(int num) {
            if (num < getMedian()) {
                maxHeap.remove(num);
            }
            else {
                minHeap.remove(num);
            }
            if (maxHeap.size() > minHeap.size()) {
                    minHeap.add(maxHeap.poll().val);
            }
            if (minHeap.size() - maxHeap.size() > 1) {
                maxHeap.add(new T(minHeap.poll()));
            }
        }
        private double getMedian() {
            if (maxHeap.isEmpty() && minHeap.isEmpty()) return 0;
            if (maxHeap.size() == minHeap.size()) {
                return ((double)maxHeap.peek().val + (double)minHeap.peek()) / 2.0;
            }
            else {
                    return (double)minHeap.peek();
            }
        }
    }
    static class Solution3 {
        public double[] medianSlidingWindow(int[] nums, int k) {
            double[] answer = new double[nums.length - k + 1];
            int[] arr = new int[k];
            for (int index = 0; index < answer.length; index ++) {
                System.arraycopy(nums, index, arr, 0, k);
                Arrays.sort(arr);
                answer[index] = ((k & 1) == 1) ? arr[k / 2] : ((long)arr[k / 2] + arr[k / 2 - 1]) / 2.0;
            }
            return answer;
        }
    }
}
