package nowcoder.zuo;

public class C03局部最小值位置 {
	public static void main(String[] args) {
		int[] arr = new int[]{4,1,0,3};
		System.out.println(getLessIndex(arr));
	}
    public static int getLessIndex(int[] arr) {
    	if (arr == null || arr.length == 0) {
    		return -1;
    	}
    	if (arr.length == 1) {
    		return 0;
    	}
    	final int len = arr.length;
    	if (arr[len - 1] < arr[len - 2])
    		return len-1;
    	if (arr[0] < arr[1])
    		return 0;
    	int begin = 0, end = len - 1;
    	while (begin < end) {
    		int mid = (begin + end) >> 1;
    		if (arr[mid+1] < arr[mid]) {
    			begin = mid;
    		} else if (arr[mid-1] < arr[mid]) {
    			end = mid;
    		} else {
    			return mid;
    		}
    	}
    	return begin;
    }
}
