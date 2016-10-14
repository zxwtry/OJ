package leetcode;

import java.util.ArrayList;

public class P210_CourseScheduleII {
	public static void main(String[] args) {
		
	}
	/*
	 * 	原本以为会是一道非常难的题，没想到。。。
	 * 	24 ms
	 * 	33.23%
	 */
	static class Solution {
		int[] order = null;
		int orderIndex = 0;
	    public int[] findOrder(int n, int[][] p) {
	    	order =  new int[n];
	        if (canFinish(n, p)) {
	        	return order;
	        } else {
	        	return new int[] {};
	        }
	    }
		public boolean canFinish(int n, int[][] p) {
			@SuppressWarnings("unchecked")
			ArrayList<Integer>[] allPoss = new ArrayList[n];
			int[] allPres = new int[n];
			for (int[] pArr : p) {
				int target = pArr[0];
				int need = pArr[1];
				if (allPoss[need] == null) {
					allPoss[need] = new ArrayList<>();
				}
				allPoss[need].add(target);
				allPres[target] ++;
			}
			while (true) {
				boolean isAllFinished = true;
				boolean isOneFinished = false;
				for (int i = 0; i < n; i ++) {
					if (allPres[i] < 0) {
						continue;
					}
					if (allPres[i] == 0) {
						if (allPoss[i] != null) {
							for (int pos : allPoss[i]) {
								allPres[pos] --;
							}
						}
						order[orderIndex ++] = i;
						isOneFinished = true;
						allPres[i] = -1;
					} else {
						isAllFinished = false;
					}
				}
				if (isAllFinished) {
					return true;
				}
				if (! isOneFinished) {
					return false;
				}
			}
		}
	}
}
