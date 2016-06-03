package stl;

import java.util.Comparator;

import com.sun.java.swing.plaf.nimbus.AbstractRegionPainter;
import com.sun.media.jfxmedia.control.VideoDataBuffer;

import javafx.animation.ParallelTransition;

/*
 * 	这个模拟C++ stl 里面的lower_bound，upper_bound函数
 * 	使用的是二分法来进行，时间复杂度O(log(N))
 * 	返回的都是索引
 */

public class Array_Binary_Search {
	public static void main(String[] args) {
		MyComparator myComparator1 = new MyComparator(true);
		System.out.println(myComparator1.compare(1, 4));
		MyComparator myComparator2 = new MyComparator(false);
		System.out.println(myComparator2.compare(1, 4));
		int[] arr = {1, 9, 2, 8, 3, 7, 4, 6, 5};
//		bubbleSort(arr, 0, arr.length-1, myComparator1);
//		printArray(arr);
//		bubbleSort(arr, 0, arr.length-1, myComparator2);
//		printArray(arr);
		quickSort(arr, 0, arr.length-1, myComparator1);
		printArray(arr);
		quickSort(arr, 0, arr.length-1, myComparator2);
		printArray(arr);
	}
	static void printArray(int[] arr) {
		for (int val : arr)
			System.out.printf("%d ", val);
		System.out.println();
	}
	static void bubbleSort(int[] arr, int sta, int end,
			Comparator<Integer> comparator) {
		for (int ini = sta; ini <= end; ini ++) {
			for (int inj = ini+1; inj <= end; inj ++)
				if (comparator.compare(arr[ini], arr[inj]) > 0) {
					swap(arr, ini, inj);
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
	static int partition(int[] arr, int sta, int end, Comparator<Integer> comparator) {
		int pivot = arr[sta];
		while (sta < end) {
			while (comparator.compare(arr[end], pivot) > 0 && sta < end)	end --;
			arr[sta] = arr[end];
			while (comparator.compare(arr[sta], pivot) < 0 && sta < end)	sta ++;
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
	public static int lowerBound(int[] arr, int val, boolean isUP) {
		return 0;
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
