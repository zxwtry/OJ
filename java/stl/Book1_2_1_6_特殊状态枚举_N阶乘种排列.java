package stl;

import java.util.Arrays;

/*
 * 	C++的STL中提供next_permutation函数，可以获得n个元素的n!种排列
 * 	现在采用普通方法实现
 * 	3.2节将介绍位运算的实现方法
 */
public class Book1_2_1_6_特殊状态枚举_N阶乘种排列 {
	public static void main(String[] args) {
		generatePermutation();
	}
	static int N;
	static boolean[] used;
	static int[] perm;
	static void generatePermutation() {
		N = 5;
		used = new boolean[N];
		perm = new int[N];
		Arrays.fill(used, false);
		nextPermutation(0);
	}
	static void nextPermutation(int pi) {
		if (pi == N) {
			for (int p : perm)
				System.out.printf("%d ", p);
			System.out.println();
			return;
		}
		for (int ni = 0; ni < N; ni ++) {
			if (! used[ni]) {
				perm[pi] = ni;
				used[ni] = true;
				nextPermutation(pi+1);
				used[ni] = false;
			}
		}
	}
}
