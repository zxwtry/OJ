package tools;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     stl
 * @file        PermutationAndCombination排列组合.java
 * @type        PermutationAndCombination排列组合
 * @date        2016年11月22日 上午9:54:48
 * @details     
 */
public class PermutationAndCombination排列组合 {
	
	/**
	 * @method      all_permutation
	 * @parameter   
	 * @return      int[][]
	 * @details     假设arr中没有重复的元素
	 * @details     返回的，非排序好的。
	 */
	public static int[][] allPermutation_arrNoDulication_returnNotSorted(int[] arr) {
		if (arr == null || arr.length < 1) 	return new int[0][0];
		int ansLen = 1;
		for (int i = arr.length; i > 1; i --)	ansLen *= i;
		int [][] ans = new int[ansLen][arr.length];
		allPermutation_internal(arr, arr.length, ans, new int[] {0});
		return ans;
	}
	
	/**
	 * @method      allPermutation_internal
	 * @parameter   
	 * @return      void
	 * @details     附属于allPermutation_arrNoDulication_returnNotSorted(int[] arr)
	 */
	private static void allPermutation_internal(int[] arr, int n, int[][] ans, int[] ansIndex) {
		if (n == 1) {
			ans[ansIndex[0] ++] = arr.clone();
		} else {
			for (int i = 0; i < n; i ++) {
				swap(arr, i, n - 1);
				allPermutation_internal(arr, n - 1, ans, ansIndex);
				swap(arr, i, n - 1);
			}
		}
	}

	public static void next_permutation(int[] arr) {
		int nixu = -1;
		for (int i = 0; nixu == -1 && i < arr.length - 1; i ++)
			if (arr[i] > arr[i + 1]) nixu = i;
		if (nixu == -1) {
			swap(arr, arr.length - 1, arr.length - 2);
			return;
		}
		
	}
	
	/**
	 * @method      swap
	 * @parameter   
	 * @return      void
	 * @details     外部保证不会发生数组越界，空指针
	 */
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
}
