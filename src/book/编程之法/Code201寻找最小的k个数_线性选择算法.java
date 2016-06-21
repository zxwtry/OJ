package book.编程之法;

/*
 * 	有问题，想不清楚
 * 	实际使用采用堆方法
 */

public class Code201寻找最小的k个数_线性选择算法 {
	public static void main(String[] args) {
		int[] arr = {1, 9, 2, 8, 3, 7, 4, 6, 5, 5, 4, 3, 2, 1};
		quickSelect(arr, 5, 0, arr.length-1);
		tools.Utils.printArray(arr, 10);
	}
	static void quickSelect(int[] arr, int k, int l, int r) {
		int i = 0, j = 0, p = 0;
		if (l <= r) {
			i = l; j = r; p = arr[l];
			while (true) {
				while (++ i <= r && arr[i] <= p) {}
				while (-- j >= l && arr[j] <= p) {}
				if (i < j)	tools.Utils.swap(arr, i, j);
				else	break;
			}
			tools.Utils.swap(arr, i, r-1);
			if (k <= i)
				quickSelect(arr, k, l, i-1);
			else if (k > i + 1)
				quickSelect(arr, k, i+1, r);
		} else {
			insertSort(arr, l, r - l + 1);
		}
	}
	static void insertSort(int[] arr, int l, int r) {
		for (int i = l+1; i <= r; i ++) {
			int j = l-1, tmp = arr[i];
			while (++j < i && arr[j] <= arr[i]) {}
			for (int k = i-1; k >= j; k --) {
				arr[k+1] = arr[k];
			}
			arr[j] = tmp;
		}
	}
}
