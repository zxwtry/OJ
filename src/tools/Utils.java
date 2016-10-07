package tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
	public static void printArray(long[] arr, int n) {
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
	public static void A_打印二维boolean数组(boolean[][] arr) {
		if (arr == null || arr.length < 1)
			return;
		for (int i = 0; i != arr.length; i ++) {
			for (int j = 0; j != arr[i].length; j ++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}
	public static void A_打印二维char数组(char[][] arr) {
		if (arr == null || arr.length < 1)
			return;
		for (int i = 0; i != arr.length; i ++) {
			for (int j = 0; j != arr[i].length; j ++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}
	public static void B_打印List_String(List<String> list) {
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
	public static void B_打印List_String_OneLine(List<String> list) {
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + "\t");
		}
		System.out.println();
	}
	public static void B_打印List_Integer(List<Integer> list) {
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
	public static void B_打印List_Integer_OneLine(List<Integer> list) {
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
	}
	public static void B_打印List_Object(List<Object> list) {
		Iterator<Object> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
	public static void B_打印List_List_Integer__(List<List<Integer>> list) {
		Iterator<List<Integer>> it = list.iterator();
		while (it.hasNext()) {
			B_打印List_Integer_OneLine(it.next());
		}
	}
	// 打印区结束
	// 生成区开始
	public static List<List<Integer>> C_生成List_List_Integer__从二维数组(int[][] num) {
		if (num == null) {
			return new LinkedList<List<Integer>>();
		}
		List<List<Integer>> ans = new ArrayList<>(num.length);
		for (int i = 0; i < num.length; i ++) {
			List<Integer> this_list = new ArrayList<Integer>(Math.max(num[i].length, 1));
			for (int j = 0; j < num[i].length; j ++) {
				this_list.add(num[i][j]);
			}
			ans.add(this_list);
		}
		return ans;
	}
	public static List<Integer> C_生成List_Integer_从一维数组(int[] num) {
		if (num == null) {
			return new ArrayList<>();
		}
		List<Integer> ans = new ArrayList<>(num.length);
		for (int i = 0; i < num.length; i ++) {
			ans.add(num[i]);
		}
		return ans;
	}
	// 生成区结束
	public static String LEETCODE_int_array_序列化_(int[] arr) {
		StringBuilder st = new StringBuilder(arr.length * 3 + 5);
		st.append("[");
		for (int i = 0; i < arr.length; i ++) {
			st.append(arr[i]);
			if (i != arr.length - 1)
				st.append(", ");
		}
		st.append("]");
		return st.toString();
	}
	public static int[] LEETCODE_int_array_反序列化_(String s) {
		String sub = s.substring(1, s.length() - 1);
		String[] parts = sub.split(",");
		if (parts.length == 1 && parts[0].trim().equals("")) {
			return new int[0];
		}
		int[] ans = new int[parts.length];
		for (int i = 0; i < parts.length; i ++) {
			ans[i] = Integer.parseInt(parts[i].trim());
		}
		return ans;
	}
	public static String LEETCODE_int_二位数组_序列化_(int[][] arr) {
		StringBuilder st = new StringBuilder();
		st.append('[');
		for (int i = 0; i < arr.length; i ++) {
			st.append(LEETCODE_int_array_序列化_(arr[i]));
			if (i != arr.length - 1) {
				st.append(',');
			}
		}
		st.append(']');
		return st.toString();
	}
}