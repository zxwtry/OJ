package nowcoder.zuo;

import java.util.Arrays;

/**
 * 	给一个数组arr，返回这个数组中最小的第k个数
 * 	例如：arr={1, 7, 2, 6, 3, 5, 4}; k=3; 返回3
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book092_找到无序数组中最小的k个数.java
 * @type        Book092_找到无序数组中最小的k个数
 * @date        2016年12月24日 下午4:11:10
 * @details     Solution1:使用长度为k的大堆，时间O(N*logk)
 */
public class Book092_找到无序数组中最小的k个数 {
	static class Solution1 {
		public int minK(int[] arr, int k) {
			if (arr == null || k >= arr.length) return 0;
			int[] heap = new int[k];
			System.arraycopy(arr, 0, heap, 0, k);
			for (int i = (k-2)/2; i > -1; i --) {
				heapDn(heap, i, k);
			}
			for (int i = k; i < arr.length; i ++)
				if (arr[i] < heap[0]){
					heap[0] = arr[i];
					heapDn(heap, 0, k);
				}
			return heap[0];
		}
		private void heapDn(int[] heap, int i, int k) {
			int c = 2 * i + 1;
			while (c < k) {
				if (c+1 < k && heap[c+1] > heap[c]) c ++;
				if (heap[c] > heap[i]) {
					swap(heap, i, c);
				} else break;
				i = c;
				c = 2 * i + 1;
			}
		}
		private void swap(int[] a, int i, int j) {
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
	}
	/**
	 * 	经典算法： BFPRT算法
	 * 	解决的问题：在O(N)时间内，从无序数组中找到第k小的数。
	 * 	假设BFPRT算法的函数是：int select(int[] arr, int k);
	 * 	该函数的公式为在arr中找到第k小的数，然后返回该数。
	 * 	select(arr, k)的过程如下：
	 * 		1,	将arr中的n个元素划分成n/5组，每组5个元素，如果最后的组不够5个元素，
	 * 			那么最后剩下的元素为一组(n%5个元素)
	 * 		2,	对于每个组进行插入排序，只针对每个组最多5个元素之间的组内排序，组和组之间
	 * 			并不排序。排序后找到每个组的中位数，如果组的元素个数为偶数，这里规定找下中位数。
	 * 		3,	步骤2中一共会找到n/5个中位数，让这些中位数组成一个新的数组，记为mArr。
	 * 			递归调用select(mArr, mArr.length/2)，意义是找到mArr这个数组中的中位数，
	 * 			即mArr中的第(mArr.length/2)小的数。
	 * 		4,	假设步骤3中递归调用select(mArr, mArr.length/2)后，返回的数为x。
	 * 			根据这个x划分整个arr数组(partition过程)。划分完成，返回x在arr中的位置为i。
	 * 		5,	如果i==k，说明x为整个数组中的第k小的数，直接返回
	 * 			如果i<k，说明x处在第k小的数的左边，应该在x的右边寻找第k小的数，
	 * 					所以递归调用select函数，在右半边寻找k-i小的数
	 * 			如果i>k，说明x处在第k小的数的右边，应该在x的左边寻找第k小的数，
	 * 					所以递归调用select函数，在左半边寻找k小的数
	 */
	static class Solution2 {
		public int[] getMinKNumsByBFPRT(int[] arr, int k) {
			if (k < 1 || k > arr.length) return arr;
			int minKth = getMinKthByBFPRT(arr, k);
			int[] res = new int[k];
			int index = 0;
			for (int i = 0; i != arr.length; i ++) {
				if (arr[i] < minKth) res[index ++] = arr[i];
			}
			for (; index != res.length; index ++)
				res[index] = minKth;
			return res;
		}
		private int getMinKthByBFPRT(int[] arr, int k) {
			int[] copyArr = copyArray(arr);
			return select(copyArr, 0, copyArr.length - 1, k - 1);
		}
		private int select(int[] copyArr, int begin, int end, int i) {
			if (begin == end) return copyArr[begin];
			int pivot = medianOfMedians(copyArr, begin, end);
			int[] pivotRange = partition(copyArr, begin, end, pivot);
			if (i >= pivotRange[0] && i <= pivotRange[1])
				return copyArr[i];
			else if (i < pivotRange[0])
				return select(copyArr, begin, pivotRange[0] - 1, i);
			else
				return select(copyArr, pivotRange[1] + 1, end, i);
		}
		private int[] partition(int[] copyArr, int begin, int end, int pivot) {
			int small = begin - 1;
			int cur = begin;
			int big = end + 1;
			while (cur != big) {
				if (copyArr[cur] < pivot)
					swap(copyArr, ++small, cur ++);
				else if (copyArr[cur] > pivot)
					swap(copyArr, cur, -- big);
				else
					cur ++;
			}
			int[] range = new int[2];
			range[0] = small + 1;
			range[1] = big - 1;
			return range;
		}
		private int medianOfMedians(int[] copyArr, int begin, int end) {
			int num = end - begin + 1;
			int offset = num % 5 == 0 ? 0 : 1;
			int[] mArr = new int[num / 5 + offset];
			for (int i = 0; i < mArr.length; i ++) {
				int beginI = begin + i * 5;
				int endI = beginI + 4;
				mArr[i] = getMedian(copyArr, beginI, Math.min(end, endI));
			}
			return select(mArr, 0, mArr.length - 1, mArr.length / 2);
		}
		private int getMedian(int[] copyArr, int begin, int end) {
			insertionSort(copyArr, begin, end);
			int sum = end + begin;
			int mid = (sum / 2) + sum % 2;
			return copyArr[mid];
		}
		private void insertionSort(int[] copyArr, int begin, int end) {
			for (int i = begin + 1; i != end + 1; i ++) {
				for (int j = i; j != begin; j --) {
					if (copyArr[j - 1] > copyArr[j]) {
						swap(copyArr, j - 1, j);
					} else break;
				}
			}
		}
		private void swap(int[] a, int i, int j) {
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
		private int[] copyArray(int[] arr) {
			int[] res = new int[arr.length];
			for (int i = 0; i != res.length; i ++)
				res[i] = arr[i];
			return res;
		}
	}
}
