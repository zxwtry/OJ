package tools;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author      zxwtry
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
		allPermutation_internal(arr, 0, ans, new int[] {0});
		return ans;
	}
	
	/**
	 * @method      allPermutation_internal
	 * @parameter   
	 * @return      void
	 * @details     附属于allPermutation_arrNoDulication_returnNotSorted(int[] arr)
	 */
	private static void allPermutation_internal(int[] arr, int arrIndex, int[][] ans, int[] ansIndex) {
		if (arrIndex == arr.length) {
			ans[ansIndex[0] ++] = arr.clone();
		} else {
			for (int index = arrIndex; index < arr.length; index ++) {
				swap(arr, index, arrIndex);
				allPermutation_internal(arr, arrIndex + 1, ans, ansIndex);
				swap(arr, index, arrIndex);
			}
		}
	}
	
	/**
	 * @method      allPermutation_arrHasDulication_returnSorted
	 * @parameter   
	 * @return      List<int[]>
	 * @details     输入arr未排序
	 * @details     arr可以含有重复
	 */
	public static List<int[]> allPermutation_arrHasDulication_returnSorted(int[] arr) {
		List<int[]> ans = new LinkedList<int[]>();
		if (arr == null || arr.length < 1)	return ans;
		Arrays.sort(arr);
		allPermutation_internal2(arr, ans, 0);
		return ans;
	}

	/**
	 * @method      allPermutation_internal2
	 * @parameter   
	 * @return      void
	 * @details     只被allPermutation_arrHasDulication_returnSorted调用
	 */
	private static void allPermutation_internal2(int[] arr, List<int[]> ans, int arrIndex) {
		if (arrIndex == arr.length) {
			ans.add(arr.clone());
		} else {
			for (int index = arrIndex; index < arr.length; index ++) {
				if (! allPermutation_internal2_isDuplicated(arr, arrIndex, index)) {
					swap(arr, arrIndex, index);
					allPermutation_internal2(arr, ans, arrIndex + 1);
					swap(arr, arrIndex, index);
				}
			}
		}
	}

	/**
	 * @method      allPermutation_internal2_isDuplicated
	 * @parameter   
	 * @return      boolean
	 * @details     只被allPermutation_internal2调用
	 */
	private static boolean allPermutation_internal2_isDuplicated(int[] arr, int arrIndex, int index) {
		for (int i = arrIndex; i < index; i ++) {
			if (arr[i] == arr[index])
				return true;
		}
		return false;
	}

	/**
	 * @method     nextPermutation
	 * @parameter   
	 * @return     	boolean
	 * @details    对于arr中相同数字的处理方式和 allPermutation_arrNoDulication_returnNotSorted不同
	 * @details    以3, 3, 4为例，nextPermutation迭代所有会是：3,4,3和4,3,3
	 * @details    以3, 3, 4为例，allPermutation_arrNoDulication_returnNotSorted迭代所有会是：
	 * @details    		3	4	3
	 * @details    		4	3	3
	 * @details    		4	3	3
	 * @details    		3	4	3
	 * @details    		3	3	4
	 * @details    		3	3	4
	 */
	public static boolean nextPermutation(int[] arr) {
		if (arr == null || arr.length < 2)	return false;
		int i2 = arr.length - 2;
		for (; i2 > -1 && arr[i2] >= arr[i2 + 1]; i2 --) {}
		if (i2 == -1)	return false;
		swap(arr, i2, nextPermutation_getLittleBigger(arr, i2));
		reverse(arr, i2 + 1, arr.length - 1);
		return true;
	}

	/**
	 * @method      nextPermutation_getLittleBigger
	 * @parameter   
	 * @return      int
	 * @details     附属于nextPermutation
	 */
	private static int nextPermutation_getLittleBigger(int[] arr, int i2) {
		int sti = i2 + 1, eni = arr.length - 1, mid = 0;
		while (sti < eni) {
			mid = (sti + eni + 1) / 2;
			if (arr[mid] > arr[i2]) {
				sti = mid;
			} else {
				eni = mid - 1; 
			}
		}
		return sti;
	}

	/**
	 * @method      prePermutation
	 * @parameter   
	 * @return      boolean
	 * @details     
	 */
	public static boolean prePermutation(int[] arr) {
		if (arr == null || arr.length < 2) 	return false;
		int i2 = arr.length - 2;
		for (; i2 > -1 && arr[i2] <= arr[i2 + 1]; i2 --) {}
		if (i2 == -1)	return false;
		swap(arr, i2, prePermutation_getLittleSmaller(arr, i2));
		reverse(arr, i2 + 1, arr.length - 1);
		return true;
	}
	
	/**
	 * @method      prePermutation_getLittleSmaller
	 * @parameter   
	 * @return      int
	 * @details     附属于prePermutation
	 */
	private static int prePermutation_getLittleSmaller(int[] arr, int i2) {
		int sti = i2 + 1, eni = arr.length - 1, mid = 0;
		while (sti < eni) {
			mid = (sti + eni + 1) / 2;
			if (arr[mid] >= arr[i2]) {
				eni = mid - 1;
			} else {
				sti = mid;
			}
		}
		return sti;
	}

	/**
	 * @method      swap
	 * @parameter   
	 * @return      void
	 * @details     外部保证不会发生数组越界，空指针
	 * @details     通用
	 */
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	/**
	 * @method      reverse
	 * @parameter   
	 * @return      void
	 * @details     [i, j] 反转
	 * @details     外部保证不会发生数组越界，空指针
	 * @details     通用
	 */
	private static void reverse(int[] arr, int i, int j) {
		while (i < j)	swap(arr, i ++, j --);
	}
	
}
