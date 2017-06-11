package stl;

import tools.ListNode辅助.ListNode;

public class Array_Bubble_Sort_希尔排序 {
    public static void main(String[] args) {
        int n = 10;
        int min = 1;
        int max = 3;
        ListNode h = tools.ListNode辅助.A_随机生成器_最大长度N_范围min_max(n, min, max);
        ListNode a = shellSort(h);
        tools.ListNode辅助.B_打印链表(a, n, 3);
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
    public static ListNode shellSort(ListNode h) {
        if (h == null || h.next == null) return h;
        ListNode f = h.next.next;
        ListNode s = h;
        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
        }
        f = shellSort(s.next);
        s.next = null;
        return merge(shellSort(h), f);
    }
    private static ListNode merge(ListNode a, ListNode b) {
        ListNode vh = new ListNode(0);
        ListNode p = vh;
        while (a != null && b != null) {
            if (a.val < b.val) {
                p.next = a;
                p = a;
                a = a.next;
            } else {
                p.next = b;
                p = b;
                b = b.next;
            }
        }
        if (a != null) {
            p.next = a;
        }
        if (b != null) {
            p.next = b;
        }
        return vh.next;
    }
}
