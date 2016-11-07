package nowcoder.zuo;

import javax.print.attribute.standard.RequestingUserName;

public class Book043_根据后序数组重建搜索二叉树 {
	public static void main(String[] args) {
		
	}
	/*
	 * 	给定一个整型数组arr，已知其中没有重复值，
	 * 	判断arr是否可能是搜索二叉树后序遍历的结果
	 */
	static class BookSolution {
		public boolean isBSTPos(int[] arr) {
			if (arr == null || arr.length == 0) {
				return false;
			}
			return isBSTPos(arr, 0, arr.length - 1);
		}
		private boolean isBSTPos(int[] arr, int sti, int eni) {
			if (sti == eni) {
				return true;
			}
			int less = -1;
			int more = eni;
			for (int i = sti; i < eni; i ++) {
				if (arr[eni] > arr[i]) {
					less = i;
				} else {
					more = more == eni ? i : more;
				}
			}
			if (less == -1 || more == eni) {
				return isBSTPos(arr, sti, eni - 1);
			}
			if (less != more - 1) {
				return false;
			}
			return isBSTPos(arr, sti, less) && isBSTPos(arr, more, eni - 1);
		}
	}
}
