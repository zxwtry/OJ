package leetcode;

import java.util.HashSet;
/*
 * 	There are a total of n courses you have to take, labeled from 0 to n - 1.
	
	
	Some courses may have prerequisites, for example to take course 0 you have to
	 first take course 1, which is expressed as a pair: [0,1]
	  课程有依赖关系，比如[0, 1]表示：假如需要上0号课程，先必须上完1号课程
	
	Given the total number of courses and a list of prerequisite pairs, is it 
	possible for you to finish all courses?
	
	For example:
	
	2, [[1,0]]
	There are a total of 2 courses to take. To take course 1 you should have 
	finished course 0. So it is possible.
	
	2, [[1,0],[0,1]]
	There are a total of 2 courses to take. To take course 1 you should have 
	finished course 0, and to take course 0 you should also have finished course 1.
	 So it is impossible.
	
	Note:
	The input prerequisites is a graph represented by a list of edges, 
	not adjacency matrices. Read more about how a graph is represented.
 */

public class P207_CourseSchedule {
	public static void main(String[] args) {
		int n = 4;
		int[][] p = new int[][] {
			{0,1},
			{3,1},
			{1,3},
			{3,2},
		};
		Solution2 s = new Solution2();
		System.out.println(s.canFinish(n, p));
	}
	/*
	 * 	一种非常非常差的方法
	 * 	48 ms
	 * 	27.43%
	 */
	static class Solution {
	    public boolean canFinish(int n, int[][] p) {
	    	Node[] ns = new Node[n];
	    	for (int i = 0; i < n; i ++) {
	    		ns[i] = new Node(i);
	    	}
	    	for (int[] pArr : p) {
	    		int wantToLearn = pArr[0];
	    		int needToLearn = pArr[1];
	    		ns[wantToLearn].addPre(needToLearn);
	    		ns[needToLearn].addPos(wantToLearn);
	    	}
	    	for (int i = 0; i < n; i ++) {
	    		if (ns[i].allPres.size() == 0) {
	    			ns[i].release(ns);
	    			ns[i].isFinished = true;
	    		}
	    	}
	    	while (true) {
	    		boolean isFind = false;
	    		boolean isAllFind = true;
	    		for (int i= 0; i < n; i ++) {
	    			if (! ns[i].isFinished && ns[i].allPres.size() == 0 ) {
	    				isFind = true;
	    				ns[i].release(ns);
	    				ns[i].isFinished = true;
	    			} else if (! ns[i].isFinished) {
	    				isAllFind = false;
	    			}
	    		}
	    		if (isAllFind) {
	    			return true;
	    		}
	    		if (! isFind) {
	    			return false;
	    		}
	    	}
	    }
	    static class Node {
	    	int val;
	    	HashSet<Integer> allPres = null;
	    	HashSet<Integer> allPoss = null;
	    	boolean isFinished = false;
	    	public Node(int val) {
	    		this.val = val;
	    		allPres = new HashSet<Integer>();
	    		allPoss = new HashSet<Integer>();
	    	}
	    	public void addPre(int pre) {
	    		allPres.add(pre);
	    	}
	    	public void addPos(int pos) {
	    		allPoss.add(pos);
	    	}
	    	public void release(Node[] ns) {
	    		for (int pos : allPoss) {
	    			HashSet<Integer> set = ns[pos].allPres;
	    			if (set.contains(val)) {
	    				set.remove(val);
	    			}
	    		}
	    	}
	    }
	}
	
	/*
	 * 	先写效率相对高，但是代码非常烦的方法
	 * 	63 ms
	 * 	17.18%
	 * 	又翻车了
	 */
	static class Solution2 {
	    @SuppressWarnings("unchecked")
		public boolean canFinish(int n, int[][] p) {
			HashSet<Integer>[] allPres = new HashSet[n];
			HashSet<Integer>[] allPoss = new HashSet[n];
	    	for (int[] pArr : p) {
	    		int wantToLearn = pArr[0];
	    		int needToLearn = pArr[1];
	    		if (allPres[wantToLearn] == null) {
	    			allPres[wantToLearn] = new HashSet<>();
	    		}
	    		if (allPoss[needToLearn] == null) {
	    			allPoss[needToLearn] = new HashSet<>();
	    		}
	    		allPres[wantToLearn].add(needToLearn);
	    		allPoss[needToLearn].add(wantToLearn);
	    	}
	    	while (true) {
	    		boolean isOneFind = false;
	    		boolean isAllFind = true;
	    		for (int i = 0; i < n; i ++) {
	    			boolean isPreNull = allPres[i] == null;
	    			boolean isPosNull = allPoss[i] == null;
	    			if (isPreNull && isPosNull) {
	    				continue;
	    			}
	    			if (! isPosNull && isPreNull) {
	    				clearAllPosIndex(allPoss, i, allPres);
	    				allPoss[i] = null;
	    				isOneFind = true;
	    				continue;
	    			}
	    			if (! isPreNull && isPosNull) {
	    				isAllFind = false;
	    				continue;
	    			}
	    			if (allPres[i].isEmpty()) {
	    				clearAllPosIndex(allPoss, i, allPres);
	    				allPres[i] = null;
	    				allPoss[i] = null;
 	    				isOneFind = true;
	    			} else {
	    				isAllFind = false;
	    			}
	    		}
	    		if (isAllFind) {
	    			return true;
	    		}
	    		if (! isOneFind) {
	    			return false;
	    		}
			}
	    }
	    void clearAllPosIndex(HashSet<Integer>[] allPoss, int index, HashSet<Integer>[] allPres) {
	    	if (allPoss[index] == null) {
	    		return;
	    	}
	    	for (int pos : allPoss[index]) {
	    		if (allPres[pos] != null && allPres[pos].contains(index) ) {
	    			allPres[pos].remove(index);
	    		}
	    		if (allPres[pos].isEmpty()) {
	    			allPres[pos] = null;
	    		}
	    	}
	    }
	}
}