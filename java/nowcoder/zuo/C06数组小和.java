package nowcoder.zuo;

/*
 * 	数组小和的定义如下：
 * 		例如，数组s={1, 3, 5, 2, 4, 6}
 * 			      下标: 0, 1, 2, 3, 4, 5
 * 		在s[0]的左边小于等于s[0]的数的和为0
 * 		在s[1]的左边小于等于s[1]的数的和为1
 * 		在s[2]的左边小于等于s[2]的数的和为1+3=4
 * 		在s[3]的左边小于等于s[3]的数的和为1
 * 		在s[4]的左边小于等于s[4]的数的和为1+3+2=6
 * 		在s[5]的左边小于等于s[5]的数的和为1+3+5+2+4=15
 * 		所以s的小和=0+1+4+1+6+15=27
 */

public class C06数组小和 {
	public static void main(String[] args) {
		int[] arr1 = {1,3,5,7,9};
		int[] arr2 = {10000};
		printArray(mergeTwoArr(arr1, arr2));
	}
	static void mergeInArr(int[] arr, int sta, int end) {
		if (sta < 0 || sta >= end || end > arr.length)	return;
		if (sta+1 == end && arr[sta] > arr[end]) {
			arr[sta] = arr[sta]^arr[end];
			arr[end] = arr[sta]^arr[end];
			arr[sta] = arr[sta]^arr[end];
			return;
		}
		int mid = (sta+end)>>1;
		mergeInArr(arr, sta, mid);
		mergeInArr(arr, mid+1, end);
		
	}
	static int[] mergeTwoArr(int[] arr1, int[] arr2) {
		if (arr1 == null || arr1.length == 0)	return arr2;
		if (arr2 == null || arr2.length == 0)	return arr1;
		int[] arr = new int[arr1.length+arr2.length];
		int in1 = 0, in2 = 0, in = 0;
		while (in1 < arr1.length && in2 < arr2.length)
			if (arr1[in1] <= arr2[in2])
				arr[in++] = arr1[in1++];
			else
				arr[in++] = arr2[in2++];
		if (in1 == arr1.length)
			System.arraycopy(arr2, in2, arr, in, arr2.length-in2);
		else
			System.arraycopy(arr1, in1, arr, in, arr1.length-in1);
		return arr;
	}
	static void printArray(int[] arr) {
		for (int in = 0; in < arr.length-1; in ++)
			System.out.printf("%d ", arr[in]);
		System.out.println(arr[arr.length-1]);
	}
}
