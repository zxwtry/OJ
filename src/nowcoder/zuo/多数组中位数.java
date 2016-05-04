package nowcoder.zuo;

/*
 * 	给定两个有序数组arr1和arr2，两个数组长度都为N，求两个数组中所有数的上中位数。
 * 	例如：
 * 	arr1 = {1,2,3,4};
 * 	arr2 = {3,4,5,6};
 * 	一共8个数则上中位数是第4个数，所以返回3。
 * 	
 * 	arr1 = {0,1,2};
 * 	arr2 = {3,4,5};
 * 	一共6个数则上中位数是第3个数，所以返回2。
 * 	
 * 	要求：时间复杂度O(logN)
 */


public class 多数组中位数 {
	public static void main(String[] args) {
//		int[] arr1 = new int[]{1,2,3,3,4,9,10,11,12,13,14,15};
//		int[] arr2 = new int[]{4,15,15,25,31,36,37,43,44,47,48,50};
		int[] arr1 = {0,7,17,20};
		int[] arr2 = {4,29,30,38};
		System.out.println(getUpMedian(arr1, arr2));
	}
    public static int getUpMedian(int[] arr1, int[] arr2) {
    	int offset = 0;
    	if (arr1 == null && arr2 == null) 	return -1;
    	if (arr1.length == 0)	return 0;
    	if (arr1.length == 1)	return Math.min(arr1[0], arr2[0]);
    	int mid = (arr1.length - 1) >> 1;
    	if (arr1[mid] == arr2[mid]) 	return arr1[mid];
    	int[] big = arr1[mid] > arr2[mid] ? arr1 : arr2;
    	int[] small = arr1[mid] < arr2[mid] ? arr1 : arr2;
    	if ((arr1.length & 0x1) == 0 && (mid+1 < arr1.length && 
    			big[mid] <= small[mid+1])) 	return big[mid];
    	if ((arr1.length & 0x1) == 0 && (mid+1 < arr1.length && 
    			big[mid] > small[mid+1])) 	offset ++;
    	if ((arr1.length & 0x1) == 1 && (mid-1 >= 0 && 
    			small[mid] > big[mid-1]))	return small[mid];
    	if (mid == 0)	return small[1];
    	mid += offset;
    	int[] newArr1 = new int[mid];
    	int[] newArr2 = new int[mid];
    	System.arraycopy(big, 0, newArr1, 0, mid);
    	System.arraycopy(small, arr1.length-mid, newArr2, 0, mid);
    	return getUpMedian(newArr1, newArr2);
    }
}
