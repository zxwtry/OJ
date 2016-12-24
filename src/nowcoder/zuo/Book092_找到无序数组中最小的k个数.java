package nowcoder.zuo;

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
}
