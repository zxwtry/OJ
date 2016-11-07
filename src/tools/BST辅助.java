package tools;

import java.util.Arrays;

import tools.TreeNode辅助.TreeNode;

public class BST辅助 {
	public static TreeNode A_从数组中生成满搜索二叉树(int[] arr) {
		return tools.TreeNode辅助.A_生成满二叉树(arr);
	}
	public static TreeNode A_随机生成一个满搜索二叉树(int level, int min, int max) {
		if (level < 1) {
			return null;
		}
		int len = (1 << level) - 1;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(len, min, max);
		Arrays.sort(arr);
		return halfConstruct(arr, 0, len - 1);
	}
	private static TreeNode halfConstruct(int[] arr, int sti, int eni) {
		if (sti == eni) {
			return new TreeNode(arr[sti]);
		} else {
			int mid = (sti + eni) / 2;
			TreeNode root = new TreeNode(arr[mid]);
			root.left = halfConstruct(arr, sti, mid - 1);
			root.right = halfConstruct(arr, mid + 1, eni);
			return root;
		}
	}
	public static TreeNode A_生成一个满搜索二叉树_0(int level) {
		if (level < 1) {
			return null;
		}
		int[] arr = new int[(1 << level) - 1];
		for (int i = 0; i != arr.length; i ++) {
			arr[i] = i;
		}
		return halfConstruct(arr, 0, arr.length - 1);
	}
	public static TreeNode A_随机生成一个搜索二叉树(int level, int min, int max, double nullPercent) {
		if (level < 1) {
			return null;
		}
		int len = (1 << level) - 1;
		int N = Integer.MIN_VALUE;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(len, min, max);
		Arrays.sort(arr);
		for (int i = 0; i != len; i ++) {
			if (Math.random() < nullPercent) {
				arr[i] = N;
			}
		}
		arr = new int[] {0, N, 2, 3, 4, N, 6};
		return halfConstructNotFull(arr, 0, len - 1, N);
	}
	private static TreeNode halfConstructNotFull(int[] arr, int sti, int eni, int N) {
		if (sti == eni) {
			if (arr[sti] == N) {
				return null;
			} else {
				return new TreeNode(arr[sti]);
			}
		} else {
			int mid = (sti + eni) / 2;
			if (arr[mid] == N) {
				return null;
			} else {
				TreeNode root = new TreeNode(arr[mid]);
				root.left = halfConstructNotFull(arr, sti, mid - 1, N);
				root.right = halfConstructNotFull(arr, mid + 1, eni, N);
				return root;
			}
		}
	}
}
