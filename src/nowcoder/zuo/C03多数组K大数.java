package nowcoder.zuo;

import java.util.Arrays;

/*
 * 	给定两个有序数组arr1和arr2，在给定一个整数k，返回两个数组的所有数中第K小的数。
 * 	例如：
 * 	arr1 = {1,2,3,4,5};
 * 	arr2 = {3,4,5};
 * 	K = 1;
 * 	因为1为所有数中最小的，所以返回1；
 * 	arr1 = {1,2,3};
 * 	arr2 = {3,4,5,6};
 * 	K = 4;
 * 	因为3为所有数中第4小的数，所以返回3；
 * 	要求：如果arr1的长度为N，arr2的长度为M，时间复杂度请达到O(log(min{M,N}))
 */

public class C03多数组K大数 {
	public static void main(String[] args) {
		int[] arr1 = {0,2,4,6,7,8,9,9,10,12,17,18};
		int[] arr2 = {0,4,6,8,12,13,22,30,30,31,42,43,45};
		System.out.println("arr1.length : " + arr1.length );
		System.out.println("arr2.length : " + arr2.length );
		for (int index = 1; index <= arr1.length + arr2.length; index ++) {
			int kth = index;
			if (index == 14) {
				System.out.println();
			}
			System.out.println(index +"...."+findKthNum2(arr1, arr2, kth));
		}
	}
	
	public static  int getUpMedianHelp(int[] arr1,int arr1start,int arr1end, int[] arr2,int arr2start,int arr2end) {
		 
        if(arr1end-arr1start==0)
            return Math.min(arr1[arr1start],arr2[arr2start]);
     int offset = ((arr1end - arr1start + 1) & 1) ^ 1;
         if(arr1[(arr1start+arr1end)/2]==arr2[(arr2start+arr2end)/2])
        {
            return arr1[(arr1start+arr1end)/2];
        }
        else if(arr1[(arr1start+arr1end)/2]>arr2[(arr2start+arr2end)/2])
        {
            arr1end=(arr1start+arr1end)/2;
            arr2start=(arr2start+arr2end)/2+offset;
           return  getUpMedianHelp(arr1, arr1start, arr1end,  arr2, arr2start, arr2end);
        }
        else
        {
            arr2end=(arr2start+arr2end)/2;
            arr1start=(arr1start+arr1end)/2+offset;
            return  getUpMedianHelp(arr1, arr1start, arr1end,  arr2, arr2start, arr2end);
        }

}

	
	public static int findKthNum2(int[] arr1, int[] arr2, int kth) {
		 if(kth>arr1.length+arr2.length||kth<1)
	            return 0;
	        int[] longArr = arr1.length >= arr2.length ? arr1 : arr2;
	        int[] shortArr = arr1.length < arr2.length ? arr1 : arr2;
	        int lenL = longArr.length;
	        int lenS = shortArr.length;
	        if(kth<=lenS)
	        {
	            return getUpMedianHelp(shortArr, 0, kth-1, longArr, 0, kth-1);
	        }
	        else if(kth>lenL)
	        {
	                if(shortArr[kth-lenL-1]>=longArr[lenL-1])
	                    return shortArr[kth-lenL-1];
	            if(shortArr[lenS-1]<longArr[kth-lenS-1])
	                return longArr[kth-lenS-1];
	                return getUpMedianHelp(shortArr,kth-lenL,lenS-1,longArr,kth-lenS,lenL-1);
	        }
	        else
	        {
	                if(longArr[kth-lenS-1]>=shortArr[lenS-1])
	                {
	                    return longArr[kth-lenS-1];
	                }
	            return getUpMedianHelp(shortArr,0,lenS-1,longArr,kth-lenS,kth-1);
	        }
	}
    public static int findKthNum(int[] arr1, int[] arr2, int kth) {
    	int[] longArray = arr1.length > arr2.length ? arr1 : arr2;
    	int[] shortArray = arr1.length <= arr2.length ? arr1 : arr2;
    	if (kth == shortArray.length + longArray.length) {
    		return Math.max(arr1[arr1.length-1], arr2[arr2.length-1]);
    	}
    	if (kth <= shortArray.length)  return getUpMedian(Arrays.copyOf(arr1, kth), Arrays.copyOf(arr2, kth));
    	else if (kth > longArray.length) {
    		int newLen = shortArray.length + longArray.length - kth ;
    		int[] newArr1 = new int[newLen], newArr2 = new int[newLen];
    		System.arraycopy(longArray, longArray.length - newLen, newArr1, 0, newLen);
    		System.arraycopy(shortArray, shortArray.length - newLen, newArr2, 0, newLen);
    		return getUpMedian(newArr1, newArr2);
    	} else {
    		int pre = kth - 1, pos = longArray.length + shortArray.length - kth;
    		int startIndex = pre - shortArray.length, endIndex = kth - 1;
    		
    		if (longArray[startIndex] >= shortArray[shortArray.length - 1])
    			return longArray[startIndex];
    		int[] newArr1 = new int[shortArray.length], newArr2 = new int[shortArray.length];
    		if (pos == 0 || endIndex == 0 || newArr2.length == 3) {
    			// special use code
    		}
    		System.arraycopy(longArray, startIndex+1, newArr1, 0, shortArray.length);
    		return getUpMedian(newArr1, shortArray);
    	}
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
