package nowcoder.zuo;
/*
 * 	好神奇的一段代码
 * 	非常NB的一种思想
 * 	大致思想是：
 * 		从左向右遍历
 * 		维持另外一个en数组，用于保存dp信息
 * 		en[j]:
 * 			1,	现在找到的最长上升子序列的长度是J+1
 * 			2,	en[j]的值的意义是：在所有上升子序列中，且长度是J+1的中，结尾最小是en[j]
 *		更新的时候：
 *			对en数组进行二分搜索：
 *				首先，en数组肯定是递增的
 *				其次，我们每次找的都是 >= arr[]中，最小的那个
 *					如果找到，将en数组的那个数，更新成为arr[]
 *					如果没有找到，en数组在后面增加一位就是arr[]
 *		最后，找到en数组的长度就是最长上升子序列的长度
 *		另外，en数组就是一个最长上升子序列
*/

public class C08最长上升子序列 {
	// 这个例子中上升子序列是不能相同的。
	
	static int[] arr;
	public static void main(String[] args) {
		arr = new int[]{1, 9, 2, 8, 3, 7, 4, 6, 5};
		solve();
	}
	static void solve() {
		int len = arr.length;
		int[] dp = new int[len];
		int[] en = new int[len];
		en[0] = arr[0];
		dp[0] = 1;
		int right = 0, l = 0, r = 0, m = 0;
		for (int i = 0; i < len; i ++) {
			l = 0;
			r = right;
			while (l <= r) {
				m = (l + r) >> 1;
				if (arr[i] > en[m])
					l = m + 1;
				else
					r = m - 1;
			} 
			right = Math.max(right, l);
			en[l] = arr[i];
			dp[i] = l + 1;
		}
		for (int val : dp)
			System.out.printf("%d ", val);
		System.out.println();
	}
	static void solve2() {
		int len = arr.length;
		int ens[] = new int[len], enI = 0, l, m, r;
		boolean hasEqual = false;
		ens[0] = arr[0];
		for (int i = 1; i < len; i ++) {
			if (arr[i] > ens[enI]) {
				ens[++enI] = arr[i];
			} else {
				l = 0; r = enI; hasEqual = false;
				while (l < r) {
					m = (l+r) >>> 1;
					if (ens[m] < arr[i]) {
						l = m + 1;
					} else if (ens[m] > arr[i]) {
						r = m - 1;
					} else {
						hasEqual = true;
						break;
					}
				}
				if (! hasEqual) {
					
				}
			}
		}
	}
}
