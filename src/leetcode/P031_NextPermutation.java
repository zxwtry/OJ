package leetcode;

import java.util.Arrays;

/*
 * 	Implement next permutation, which rearranges numbers 
 * 	into the lexicographically next greater permutation of numbers.

	If such arrangement is not possible, it must rearrange it 
	as the lowest possible order (ie, sorted in ascending order).

	The replacement must be in-place, do not allocate extra memory.

	Here are some examples. Inputs are in the left-hand column and 
	its corresponding outputs are in the right-hand column.
	1,2,3 → 1,3,2
	3,2,1 → 1,2,3
	1,1,5 → 1,5,1
	全排列中的下一个
 */

public class P031_NextPermutation {
    
    static class Solution {
        public void nextPermutation(int[] a) {
            int an = a == null ? 0 : a.length;
            if (an < 2) {
                return;
            }
            int i = an - 2;
            while (i > -1 && a[i] >= a[i+1]) {
                i --;
            }
            if (i == -1) {
                Arrays.sort(a);
                return;
            }
            int j = binaryFindFirstLarger(a, i+1, an-1, a[i]);
            swap(a, i, j);
            reverse(a, i + 1, an - 1);
        }
        
        int binaryFindFirstLarger(int[] a, int i, int j, int v) {
            int m = 0;
            while (i < j) {
                m = (i + j + 1) / 2;
                if (a[m] > v) {
                    i = m;
                } else if (a[m] == v) {
                    j = m - 1;
                } else {
                    //a[m] < v
                    j = m - 1;
                }
            }
            return i;
        }

        void swap(int[] a, int i, int j) {
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        
        void reverse(int[] a, int i, int j) {
            while (i < j) {
                swap(a, i ++, j --);
            }
        }
    }
    
}