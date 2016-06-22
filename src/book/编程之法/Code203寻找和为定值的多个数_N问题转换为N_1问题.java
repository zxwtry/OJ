package book.编程之法;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * 	
 */

public class Code203寻找和为定值的多个数_N问题转换为N_1问题 {
	static ArrayList<Integer> list = null;
	static LinkedList<Integer> ll = null;
	public static void main(String[] args) {
		int[] arr1 = null;
		arr1 = new int[]{1, 9, 2, 8, 3, 7, 4, 6, 5, 4, 3, 8};
		solve(arr1, 15);
	}
	static void solve(int[] arr, int n) {
		int mj = 0;
		if (arr == null || (mj = arr.length - 1) < 0)
			return;
		ll = new LinkedList<Integer>();
	}
	static void add(int j, int mj, int s, int n) {
		if (j > mj)	return;
		if (s == n) {
			Iterator<Integer> it = ll.iterator();
			while(it.hasNext())
				System.out.printf("%d ", it.next());
		}
		
	}
	static void sum(int s, int n) {
		if (n <= 0 || s <= 0)	return;
		int ml = 0;
		if (s == n && (ml = list.size()-1) >= 0) {
			for(int i = ml; i > 0; i --)
				System.out.printf("%d ", list.get(i));
			System.out.println(list.get(0));
		}
		list.add(n);
		sum(s-n, n-1);
		list.remove(list.size()-1);
		sum(s, n-1);
	}
}