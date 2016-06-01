/*
题目URL:http://www.nowcoder.com/courses/6/6/2
题目大意：
    １，给定一个数组，每一个数定义一个单调和
    ２，单调和：(左边<=原值)总和
    ３，求数组所有元素单调和的总和
    ４，要求算法效率高：时间O(N)，空间O(N)
*/

import java.util.*;
public class NowCoderZuo62 {
    public static void main(String[] args) {
        int[] A = {1, 3, 5, 2, 4, 6};
        calcMonoSum(A, A.length);
    }
    public static int calcMonoSum(int[] A, int n) {
        int[] B = new int[n];
        int[] C = new int[n];
        Arrays.fill(C, 0);
        split(A, B, C, n, 0, n-1);
        return 0;
    }
    public static void split(int[] A, int[] B, int[] C, int n, int sta, int end) {
        if (end - sta == 1) {
            if (A[sta] <= A[end]) {
                C[end]++;
            } else {
                swap(A, sta, end);
                swap(B, sta, end);
                swap(C, sta, end);
            }
            return;
        }
        if (sta == end)
            return;
        int mid = (sta + end) >> 1;
        split(A, B, C, n, sta, mid);
        split(A, B, C, n, mid+1, end);
        int in1 = sta, in2 = mid+1, in = sta, cou = 0;
        while (in1 <= mid && in2 <= end) {
            if (A[in1] < A[in2]) {
                B[in++] = A[in1++];
                cou ++;
            } else {
                C[]
                B[in++] = A[in2++];
                
            }
        }
        if (in1 == mid+1)
            System.arraycopy(A, in2, B, in, end-in2+1);
        else
            System.arraycopy(A, in1, B, in, end-in1+1);
        System.arraycopy(B, sta, A, sta, end-sta+1);
    }
    static void swap(int[] arr, int in1, int in2) {
        arr[in1] = arr[in1] ^ arr[in2];
        arr[in2] = arr[in1] ^ arr[in2];
        arr[in1] = arr[in1] ^ arr[in2];
    }
}
