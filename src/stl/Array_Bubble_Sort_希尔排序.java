package stl;

import tools.ListNode辅助.ListNode;

public class Array_Bubble_Sort_希尔排序 {
    public static void main(String[] args) {
        int len = 50;
        int[] arr = tools.Random随机生成器.A_生成一个不重复随机数据(len, 0, len-1);
//        help = new int[arr.length];
        shellSort(arr, 0, arr.length - 1);
        tools.Utils.printArray(arr, 10, 3);
    }
    static int[] help = null;
    //[i, j]
    public static void shellSort(int[] arr, int i, int j) {
        if (help == null) help = new int[arr.length];
        if (i == j) return;
        if (i > j) return;
        if (i == j-1) {
            if (arr[i] > arr[j]) tools.Utils.swap(arr, i, j);
            return;
        }
        int m = (i + j) / 2;
        shellSort(arr,   i, m);
        shellSort(arr, m+1, j);
        System.arraycopy(arr, i, help, i, j-i+1);
        int p = i, q = m+1, t = i;
        while (p <= m && q <= j) {
            if (help[p] < help[q]) {
                arr[t ++] = help[p ++];
            } else {
                arr[t ++] = help[q ++];
            }
        }
        if (p <= m) System.arraycopy(help, p, arr, t, m-p+1);
        if (q <= j) System.arraycopy(help, q, arr, t, j-q+1);
    }
    public static void shellSort(ListNode head) {
        
    }
}
