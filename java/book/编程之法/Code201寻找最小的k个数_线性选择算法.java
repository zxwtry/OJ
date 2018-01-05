package book.编程之法;

import java.util.PriorityQueue;

public class Code201寻找最小的k个数_线性选择算法 {
	static int[] find(int[] arr, int k) {
	    int arrLen = arr == null ? 0 : arr.length;
	    if (arrLen <= k) return arr;
	    PriorityQueue<T> pq = new PriorityQueue<>();
	    for (int v : arr) {
	        pq.add(new T(v));
	        if (pq.size() > k) pq.poll();
	    }
	    int[] ans = new int[k];
	    for (int i = k-1; i > -1; i --)
	        ans[i] = pq.poll().val;
	    return ans;
	}
	private static class T implements Comparable<T> {
		int val;
		T(int val) {
			this.val = val;
		}
		@Override
        public int compareTo(T t) {
	        return Integer.compare(t.val, this.val);
        }
	}
}
