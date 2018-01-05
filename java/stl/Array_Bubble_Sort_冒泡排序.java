package stl;

/**
 * 	根据en.wikipedia.org/wiki/Bubble_sort中代码实现。
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     stl
 * @file        Array_Bubble_Sort_冒泡排序.java
 * @type        Array_Bubble_Sort_冒泡排序
 * @date        2017年2月9日 下午8:30:14
 * @details     
 */
public class Array_Bubble_Sort_冒泡排序 {
	static void bubbleSort1(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		boolean isSwapped = true;
		while (isSwapped) {
			isSwapped = false;
			for (int index = 1; index < arr.length; index ++) {
				if (arr[index - 1] > arr[index]) {
					swap(arr, index - 1, index);
					isSwapped = true;
				}
			}
		}
	}
	static void bubbleSort2(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		boolean isSwapped = true;
		int arrLen = arr.length;
		while (isSwapped) {
			isSwapped = false;
			for (int index = 1; index < arrLen; index ++) {
				if (arr[index - 1] > arr[index]) {
					swap(arr, index - 1, index);
					isSwapped = true;
				}
			}
			arrLen --;
		}
	}
	static void bubbleSort3(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		int n = arr.length;
		int newN = 0;
		while (n != 0) {
			newN = 0;
			for (int index = 1; index < n; index ++) {
				if (arr[index - 1] > arr[index]) {
					swap(arr, index - 1, index);
					newN = index;
				}
			}
			n = newN;
		}
	}
	static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
}
