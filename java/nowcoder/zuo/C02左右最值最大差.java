package nowcoder.zuo;

public class C02左右最值最大差 {
	public static void main(String[] args) {
//		int[] A = new int[]{1,5,3,4,2,6,7};
//		int[] A = new int[]{1,2,3,4,5,6,7};
		int[] A = new int[]{28,75,38,44,66,1};
		System.out.println(findMaxGap(A, A.length));
	}
    public static int findMaxGap(int[] A, int n) {
        int[] maxForward = new int[n];
        int[] maxBackward = new int[n];
        for (int index = 0; index < n; index ++) {
        	int pre = index == 0 ? Integer.MIN_VALUE : maxForward[index-1];
        	maxForward[index] = pre > A[index] ? pre : A[index];
        }
        for (int index = n-1; index >= 0; index --) {
        	int pre = index == n-1 ? Integer.MIN_VALUE : maxBackward[index+1];
        	maxBackward[index] = pre > A[index] ? pre : A[index];
        }
        int max = Integer.MIN_VALUE;
        for (int index = 0; index <= n-2; index ++ ) {
        	int newMax = Math.abs(maxForward[index] - maxBackward[index+1]);
        	max = max > newMax ? max : newMax;
        }
        return max;
    }
}
