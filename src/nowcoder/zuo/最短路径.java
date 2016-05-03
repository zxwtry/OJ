package nowcoder.zuo;

public class 最短路径 {
	public static void main(String[] args) {
//		int[] A = new int[]{1,5,3,4,2,6,7};
		int[] A = new int[]{1,2,3,4,5,6,7};
		System.out.println(findShortest(A, A.length));
	}
    public static int findShortest(int[] A, int n) {
    	int[] min = new int[n];
    	int[] max = new int[n];
    	for (int index = n-1; index >= 0; index --) {
    		int pre = index == n-1 ? Integer.MAX_VALUE : min[index + 1];
    		min[index] = pre > A[index] ? A[index] : pre;
    	}
    	int index1 = 0;
    	for (; index1 < n; index1 ++) {
    		if (A[index1] > min[index1]) {
    			break;
    		}
    	}
    	for (int index = 0; index < n; index ++) {
    		int pre = index == 0 ? Integer.MIN_VALUE : max[index - 1];
    		max[index] = pre > A[index] ? pre : A[index];
    	}
    	int index2 = n - 1;
    	for (; index2 >= 0; index2 --) {
    		if (A[index2] < max[index2]) {
    			break;
    		}
    	}
    	int re = index2 - index1 + 1;
    	return re < 0 ? 0 : re;
    }
}
