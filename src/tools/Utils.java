package tools;

import tools.Models.Node;

public class Utils {
	// 开始swap区
	public static <T> void swap(T[] arr, int i, int j) {
		int arr_end = 0;
		if (arr == null || (arr_end=arr.length-1) < 1
				|| i < 0 || j < 0 || i > arr_end || j > arr_end)
			return;
		T tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	public static void swap(char[] arr, int i, int j) {
		int arr_end = 0;
		if (arr == null || (arr_end=arr.length-1) < 1
				|| i < 0 || j < 0 || i > arr_end || j > arr_end)
			return;
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	public static void swap(int[] arr, int i, int j) {
		int arr_end = 0;
		if (arr == null || (arr_end=arr.length-1) < 1
				|| i < 0 || j < 0 || i > arr_end || j > arr_end)
			return;
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	// swap区结束
	// reverse区开始
	public static void reverse(char[] arr, int i, int j) {
		int arrEnd = 0;
		if (arr == null || (arrEnd = arr.length-1) < 1 || i < 0
				|| j < 0 || i > arrEnd || j > arrEnd || i >= j)
			return;
		while (i < j) {
			char tmp = arr[i];
			arr[i++] = arr[j];
			arr[j--] = tmp;
		}
	}
	public static void reverse(int[] arr, int i, int j) {
		int arrEnd = 0;
		if (arr == null || (arrEnd = arr.length-1) < 1 || i < 0
				|| j < 0 || i > arrEnd || j > arrEnd || i >= j)
			return;
		while (i < j) {
			int tmp = arr[i];
			arr[i++] = arr[j];
			arr[j--] = tmp;
		}
	}
	public static<T> void reverse(T[] arr, int i, int j) {
		int arrEnd = 0;
		if (arr == null || (arrEnd = arr.length-1) < 1 || i < 0
				|| j < 0 || i > arrEnd || j > arrEnd || i >= j)
			return;
		while (i < j) {
			T tmp = arr[i];
			arr[i++] = arr[j];
			arr[j--] = tmp;
		}
	}
	// reverse区结束
	// 打印区开始
	public static void printNode(Node head) {
		Node tmp = head;
		while (tmp != null) {
			System.out.printf("%d\t", tmp.data);
			tmp = tmp.next;
		}
		System.out.println();
	}
	public static void printArray(char[] arr, int n) {
		int arrEnd = 0;
		if (arr == null || (arrEnd = arr.length-1) < 0)
			return;
		for (int i = 0; i <= arrEnd; i ++) {
			if (i % n == n - 1 || i == arrEnd)
				System.out.println(arr[i]);
			else
				System.out.printf("%c\t", arr[i]);
		}
	}
	public static void printArray(int[] arr, int n) {
		int arrEnd = 0;
		if (arr == null || (arrEnd = arr.length-1) < 0)
			return;
		for (int i = 0; i <= arrEnd; i ++) {
			if (i % n == n - 1 || i == arrEnd)
				System.out.println(arr[i]);
			else
				System.out.printf("%d\t", arr[i]);
		}
	}
	public static void printNoBlank(char[] arr, int n) {
		int arrEnd = 0;
		if (arr == null || (arrEnd = arr.length-1) < 0)
			return;
		for (int i = 0; i <= arrEnd; i ++) {
			if (i % n == n - 1 || i == arrEnd)
				System.out.println(arr[i]);
			else
				System.out.printf("%c", arr[i]);
		}
	}
	public static void printNoBlank(int[] arr, int n) {
		int arrEnd = 0;
		if (arr == null || (arrEnd = arr.length-1) < 0)
			return;
		for (int i = 0; i <= arrEnd; i ++) {
			if (i % n == n - 1 || i == arrEnd)
				System.out.println(arr[i]);
			else
				System.out.printf("%d", arr[i]);
		}
	}
	public static void A_打印二维数组(int[][] arr) {
		if (arr == null || arr.length < 1)
			return;
		for (int i = 0; i != arr.length; i ++) {
			for (int j = 0; j != arr[i].length; j ++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}
	// 打印区结束
}