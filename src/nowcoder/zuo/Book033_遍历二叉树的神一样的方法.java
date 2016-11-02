package nowcoder.zuo;

import java.util.LinkedList;
import java.util.List;

import tools.TreeNode辅助.TreeNode;

/*
 * 就是要实现二叉树的前序，中序，后序遍历
 */

public class Book033_遍历二叉树的神一样的方法 {
	public static void main(String[] args) {
		testPre();
	}
	private static void testPre() {
		int N = Integer.MIN_VALUE;
		int[] arr = new int[] {
			1,
			2, 3,
			1, 2, 3, 4,
			N, N, N, N, N, N, N, N
		};
		TreeNode head = tools.TreeNode辅助.A_生成满二叉树(arr);
		MySolution ms = new MySolution();
		List<Integer> msList = ms.preOrder(head);
		CaesarSolution cs = new CaesarSolution();
		List<Integer> csList = cs.preOrder(head);
		boolean isAllTrue = msList.size() == csList.size();
		for (int i = 0; isAllTrue && i < msList.size(); i ++) {
			isAllTrue &= msList.get(i) == csList.get(i);
		}
		System.out.println(isAllTrue);
	}
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
		private void preOrderInternal(TreeNode head) {
			if (head != null) {
				list.add(head.val);
				preOrderInternal(head.left);
				preOrderInternal(head.right);
			}
		}
		public List<Integer> inOrder(TreeNode head) {
			list = new LinkedList<>();
			inOrderInternal(head);
			return list;
		}
		private void inOrderInternal(TreeNode head) {
			if (head != null) {
				inOrderInternal(head.left);
				list.add(head.val);
				inOrderInternal(head.right);
			}
		}
		public List<Integer> posOrder(TreeNode head) {
			list = new LinkedList<>();
			posOrderInternal(head);
			return list;
		}
		private void posOrderInternal(TreeNode head) {
			if (head != null) {
				posOrderInternal(head.left);
				posOrderInternal(head.right);
				list.add(head.val);
			}
		}
	}
	/*
	 * 	时间：O(N)
	 * 	空间：O(1)
	　*/
	static class CaesarSolution {
		public List<Integer> preOrder(TreeNode head) {
			List<Integer> list = new LinkedList<>();
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
		public List<Integer> inOrder(TreeNode head) {
			List<Integer> list = new LinkedList<>();
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
						cur1 = cur1.left;
						continue;
					} else {
						cur2.right = null;
					}
				}
				list.add(cur1.val);
				cur1 = cur1.right;
			}
			return list;
		}
		public List<Integer> posOrder(TreeNode head) {
			List<Integer> list = new LinkedList<>();
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
						cur1 = cur1.left;
						continue;
					} else {
						cur2.right = null;
						echoList(cur1.left, list);
					}
				}
				cur1 = cur1.right;
			}
			echoList(head, list);
			return list;
		}
		private void echoList(TreeNode head, List<Integer> list) {
			TreeNode tail = reverse(head);
			TreeNode cur = tail;
			while (cur != null) {
				list.add(cur.val);
				cur = cur.right;
			}
			reverse(tail);
		}
		private TreeNode reverse(TreeNode from) {
			TreeNode pre = null;
			TreeNode next = null;
			while (from != null) {
				next = from.right;
				from.right = pre;
				pre = from;
				from = next;
			}
			return pre;
		}
	}
}
