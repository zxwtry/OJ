package nowcoder.zuo;

import java.util.LinkedList;
import java.util.List;

import tools.TreeNode辅助.TreeNode;

/*
 * 就是要实现二叉树的前序，中序，后序遍历
 */

public class Book033_遍历二叉树的神一样的方法 {
	/*
	 * 	时间：O(N)
	 * 	空间：O(h)
	 */
	static class MySolution {
		List<Integer> list = null;
		public List<Integer> preOrder(TreeNode head) {
			list = new LinkedList<>();
			preOrderInternal(head);
			return list;
		}
		private List<Integer> preOrderInternal(TreeNode head) {
			list.add(head.val);
			preOrderInternal(head.left);
			preOrderInternal(head.right);
			return null;
		}
	}
	/*
	 * 	时间：O(N)
	 * 	空间：O(1)
	　*/
	static class CaesarSolution {
		List<Integer> list = null;
		public List<Integer> preOrder(TreeNode head) {
			list = new LinkedList<>();
			if (head == null) {
				return list;
			}
			TreeNode cur1 = head;
			TreeNode cur2 = null;
			while (cur1 != null) {
				cur2 = cur1.left;
				if (cur2 != null) {
					while (cur2.right != null && cur2.right != cur1) {
						cur2 = cur2.right;
					}
					if (cur2.right == null) {
						cur2.right = cur1;
						list.add(cur1.val);
						cur1 = cur1.left;
						continue;
					} else {
						cur2.right = null;
					}
				} else {
					list.add(cur1.val);
				}
				cur1 = cur1.right;
			}
			return list;
		}
	}
}
