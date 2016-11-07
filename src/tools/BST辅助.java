package tools;

import java.util.Arrays;

import tools.TreeNode辅助.TreeNode;

public class BST辅助 {
	public static TreeNode A_从数组中生成满搜索二叉树(int[] arr) {
		return tools.TreeNode辅助.A_生成满二叉树(arr);
	}
	public static TreeNode A_随机生成一个满搜索二叉树(int level, int min, int max) {
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
}
