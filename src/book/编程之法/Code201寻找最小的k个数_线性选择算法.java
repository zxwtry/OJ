package book.编程之法;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code201寻找最小的k个数_线性选择算法 {
	static int[] find(int[] arr, int k) {
	    int arrLen = arr == null ? 0 : arr.length;
	    if (arrLen <= k) return arr;
	    PriorityQueue<Integer> pq = new PriorityQueue<>(
            new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }
            }
	    );
	    for (int v : arr) {
	        pq.add(v);
	        if (pq.size() > k) pq.poll();
	    }
	    int[] ans = new int[k];
	    for (int i = k-1; i > -1; i --)
	        ans[i] = pq.poll();
	    return ans;
	}
}
