package book.编程之法;

/*
 * 	=====建立一个k小堆        (这种想法是错误的)
 * 	=====n-k个数加入到堆中
 * 	正确的想法是：
 * 		首先应该建立大堆，为什么呢?
 * 		每次添加元素都是和最大的进行比较，不是和最小的。
 * 		添加完元素之后，只需要进行一次向下调整又能够找到最大的元素
 * 		
 */

public class Code201寻找最小的k个数_用k堆 {
	public static void main(String[] args) {
		int[] arr1 = {1, 9, 2, 8, 3, 7, 4, 6, 5, 3, 4};
		int[] arr2 = {1, 9, 2, 8};
		solve(arr1, arr2);
		for (int i = 0; i < arr2.length; i ++) {
			System.out.printf("%d ", arr2[i]);
		}
		System.out.println();
	}
	static void solve(int[] arr1, int[] arr2) {
		buildHeap(arr2);
		for (int i = arr2.length; i < arr1.length; i ++) {
			add(arr2, arr1[i]);
		}
	}
	static void buildHeap(int[] arr) {
		int mr = 0;
		if (arr == null || (mr = arr.length-1) < 1)
			return;
		int sta = (mr-1) >> 1;
		for (int i = sta; i >= 0; i --) {
			down(arr, i, mr, sta);
		}
	}
	static void add(int[] arr, int val) {
		if (val >= arr[0])	return;
		arr[0] = val;
		down(arr, 0, arr.length-1, (arr.length-1) >> 1);
	}
	static void down(int[] arr, int j, int mr, int sta) {
		while (j <= sta)  {
			int lc = (j << 1) + 1;
			if (lc < mr && arr[lc] < arr[lc+1])
				lc ++;
			if (arr[lc] > arr[j])
				tools.Utils.swap(arr, j, lc);
			j = lc;
		}
	}
}
