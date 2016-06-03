package stl;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 	这个模拟C++ stl 里面的lower_bound，upper_bound函数
 * 	使用的是二分法来进行，时间复杂度O(log(N))
 * 	返回的都是索引
 * 	函数介绍：
 * 		lower_bound:
 * 			arr[index] <= val 且 Math.abs(arr[index]-val)最小的
 * 			返回index
 * 		upper_bound:
 * 			arr[index] >= val 且 Math.abs(arr[index]-val)最小的
 * 			返回index
 */

public class Array_Binary_Search {
	public static void main(String[] args) {
		MyComparator myComparator1 = new MyComparator(true);
		MyComparator myComparator2 = new MyComparator(false);
		int[] arr = {1, 3, 3, 5, 5, 5, 7, 7, 9, 9};
//		bubbleSort(arr, 0, arr.length-1, myComparator1);
//		printArray(arr);
//		bubbleSort(arr, 0, arr.length-1, myComparator2);
//		printArray(arr);
//		quickSort(arr, 0, arr.length-1, myComparator1);
//		printArray(arr);
//		quickSort(arr, 0, arr.length-1, myComparator2);
//		printArray(arr);
		printArray(arr);
		bubbleSortStandard(arr, 0, arr.length-1, myComparator1);
		printArray(arr);
		int val = 3;
		System.out.println(lowerBound(arr, 0, arr.length-1, val));
		bubbleSortStandard(arr, 0, arr.length-1, myComparator2);
		printArray(arr);
		System.out.println(lowerBound(arr, 0, arr.length-1, val));
	}
	static void printArray(int[] arr) {
		int ind = 0;
		System.out.printf("index:\t");
		while (ind < arr.length-1)
			System.out.printf("%d ", ind++);
		System.out.println(ind);
		ind = 0;
		System.out.printf("val  :\t");
		while (ind < arr.length-1) {
			System.out.printf("%d ", arr[ind++]);
		}
		System.out.println(arr[ind]);
	}
	/*
	 * 	冒泡排序：
	 * 		标准冒泡排序进行优化好像有两种方式，
	 * 		不过下面的这份代码并不是标准冒泡排序
	 */
	static void bubbleSort(int[] arr, int sta, int end,
			Comparator<Integer> comparator) {
		for (int ini = sta; ini <= end; ini ++) {
			for (int inj = ini+1; inj <= end; inj ++)
				if (comparator.compare(arr[ini], arr[inj]) > 0) {
					swap(arr, ini, inj);
				}
		}
	}
	/*
	 * 	标准冒泡排序：
	 * 		
	 */
	static void bubbleSortStandard(int[] arr, int sta, int end, Comparator<Integer> comparator) {
		if (arr == null || arr.length == 0 || sta < 0 ||
				sta >= end || end >= arr.length)	return;
		boolean isSwap = true;
		for (int ini = sta; ini < end && isSwap; ini ++) {
			isSwap = false;
			for (int inj = end; inj > ini; inj --) {
				if (comparator.compare(arr[inj-1], arr[inj]) > 0) {
					isSwap = true;
					arr[inj]   = arr[inj-1] ^ arr[inj];
					arr[inj-1] = arr[inj-1] ^ arr[inj];
					arr[inj]   = arr[inj-1] ^ arr[inj];
				}
			}
		}
	}
	static void quickSort(int[] arr, int sta, int end, Comparator<Integer> comparator) {
		if (sta >= end)
			return;
		int part = partition(arr, sta, end, comparator);
		quickSort(arr, sta, part-1, comparator);
		quickSort(arr, part+1, end, comparator);
	}
	/*
	 * 	上课的时候，无聊写了一个快排
	 * 	注意快排进行比较的时候，如果忽略等于的情况
	 * 	可能在数据出现相同的时候就会进入死循环
	 */
	static int partition(int[] arr, int sta, int end, Comparator<Integer> comparator) {
		int pivot = arr[sta];
		while (sta < end) {
			while (comparator.compare(arr[end], pivot) >= 0 && sta < end)	end --;
			arr[sta] = arr[end];
			while (comparator.compare(arr[sta], pivot) <= 0 && sta < end)	sta ++;
			arr[end] = arr[sta];
		}
		arr[sta] = pivot;
		return sta;
	}
	static void swap(int[] arr, int ini, int inj) {
		arr[ini] = arr[ini] ^ arr[inj];
		arr[inj] = arr[ini] ^ arr[inj];
		arr[ini] = arr[ini] ^ arr[inj];
	}
	/*
	 * 	使用二分进行搜索的一个前提条件是，数组有序
	 */
	public static int lowerBound(int[] arr, int sta, int end, int val) {
		if (arr == null || arr.length == 0)
			return -1;
		if (val > Math.max(arr[0], arr[arr.length-1]) || val < Math.min(arr[0], arr[arr.length-1]))
			return -2;
		boolean isUP = arr[0] - arr[arr.length-1] < 0;
		MyComparator myComparator = new MyComparator(isUP);
		int mid = 0;
		int com = 0;
		while (end > sta) {
			mid = (end + sta) >> 1;
			com = myComparator.compare(arr[mid], val);
			if (com > 0)
				end = mid-1;
			else if (com < 0)
				sta = mid+1;
			else
				return mid;
		}
		if (sta == end)
			return end;
		else
			return sta;
	}
	static class MyComparator implements Comparator<Integer> {
		boolean isUP;
		public MyComparator(boolean isUP) {
			this.isUP = isUP;
		}
		@Override
		public int compare(Integer o1, Integer o2) {
			if (isUP)
				return o1 - o2;
			else
				return o2 - o1;
		}
	}
}
