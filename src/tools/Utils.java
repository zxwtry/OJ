package tools;

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
}