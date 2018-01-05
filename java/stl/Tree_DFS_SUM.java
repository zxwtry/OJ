package stl;
/*
 * 	给定一个数组和一个数字
 * 	返回　数组中的某些数字和是否为给定
 */
public class Tree_DFS_SUM {
	static int[] arr;
	static int num;
	public static void main(String[] args) {
		arr = new int[]{1,2,3,4};
		num = 11;
		System.out.printf("%b", dfs(0, 0));
	}
	static boolean dfs(int dep, int sum) {
		if (dep == arr.length)
			return sum == num;
		if (dfs(dep+1, sum))
			return true;
		if (dfs(dep+1, sum+arr[dep]))
			return true;
		return false;
	}
}
