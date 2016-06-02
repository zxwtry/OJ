package nowcoder.zuo;

/*
题目URL:http://www.nowcoder.com/courses/6/6/2
题目大意：
    １，给定一个数组，每一个数定义一个单调和
    ２，单调和：(左边<=原值)总和
    ３，求数组所有元素单调和的总和
    ４，要求算法效率高：时间O(N*log(N))，空间O(N)
    ５，暴力解法的时间复杂度是O(N*N)
*/

public class C06数组小和2 {
    static int COU = 0;
	public static void main(String[] args) {
        int[] A = {483,331,69,270,195,474,582,245,535};
        System.out.println(calcMonoSum(A, A.length));
    }
    public static int calcMonoSum(int[] A, int n) {
        COU = 0;
    	int[] B = new int[n];
        split(A, B, n, 0, n-1);
        return COU;
    }
    public static void split(int[] A, int[] B, int n, int sta, int end) {
        if (end - sta == 1) {
            if (A[sta] <= A[end])	COU += A[sta];
            else	swap(A, sta, end);
            return;
        }
        if (sta == end)
            return;
        int mid = (sta + end) >> 1;
        split(A, B, n, sta, mid);
        split(A, B, n, mid+1, end);
        int in1 = sta, in2 = mid+1, in = sta;
        while (in1 <= mid && in2 <= end)
        	if (A[in1] > A[in2])	B[in++] = A[in2++];
        	else {
            	COU += (end - in2 + 1) * A[in1];
                B[in++] = A[in1++];
            }
        if (in1 == mid+1)
            System.arraycopy(A, in2, B, in, end-in2+1);
        else
            System.arraycopy(A, in1, B, in, mid-in1+1);
        System.arraycopy(B, sta, A, sta, end-sta+1);
    }
    static void swap(int[] arr, int in1, int in2) {
        arr[in1] = arr[in1] ^ arr[in2];
        arr[in2] = arr[in1] ^ arr[in2];
        arr[in1] = arr[in1] ^ arr[in2];
    }
}
