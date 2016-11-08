package tools;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.sun.xml.internal.ws.handler.HandlerProcessor.RequestOrResponse;

public class TreeNode辅助 {
	/*
	 * 	maxLevel : 二叉树的最大层数
	 * 	1 : 一层
	 * 	2 : 二层
	 * 	levelIndex从0开始标号
	 * 	double nullPercent : 在生成一个节点的时候，出现null的概率
	 * 	二叉树所有节点的值：[min, max]
	 * 	和A_生成随机满二叉树的差别在于：就是一个最大层数是maxLevel的随机二叉树
	 */
	public static TreeNode A_生成随机二叉树(int maxLevel, int min, int max, double nullPercent) {
		if (maxLevel < 1) {
			return null;
		}
		/*
		 * 	level=1	: 只有1个
		 * 	level=2 : 只有2个
		 * 	level=3 : 只有4个
		 */
		TreeNode[] arr = new TreeNode[1 << (maxLevel - 1)];
		/*
		 * 	考虑到需要维持一个相对位置不变的顺序
		 * 	使用双亲靠左，左孩原位置，右孩右边位置的策略。
		 */
		/*
		 * 	先进行 0 level的初始化
		 */
		TreeNode head = A_生成一个随机节点_可以是NULL(min, maxLevel, nullPercent);
		arr[0] = head;
		for (int indexOfLevel = 1; indexOfLevel < maxLevel; indexOfLevel ++) {
			int indexRange = 1 << (maxLevel - indexOfLevel - 1);
			TreeNode parent = null;
			for (int index = 0; index < arr.length; index += indexRange) {
				if (((index / indexRange) & 0x1) == 0) {
					//需要更新parent
					parent = arr[index];
					//设置，如果双亲节点是null，那么就是这颗随机大树的，从这节点开始的子树，就没了。
					if (parent != null) {
						//生成arr[index]
						arr[index] = A_生成一个随机节点_可以是NULL(min, maxLevel, nullPercent);
						//设置parent.left
						parent.left = arr[index];
					}
				} else {
					//不需要更新parent
					//生成arr[index]
					arr[index] = A_生成一个随机节点_可以是NULL(min, maxLevel, nullPercent);
					if (parent != null) {
						//设置parent.right
						parent.right = arr[index];
					}
				}
			}
		}
		return head;
	}
	/*
	 * 	level : 二叉树的层数
	 * 	1 : 一层
	 * 	2 : 二层
	 * 	levelIndex从0开始标号
	 * 	二叉树所有节点的值：[min, max]
	 */
	public static TreeNode A_生成随机满二叉树(int level, int min, int max) {
		if (level < 1) {
			return null;
		}
		/*
		 * 	level=1	: 只有1个
		 * 	level=2 : 只有2个
		 * 	level=3 : 只有4个
		 */
		TreeNode[] arr = new TreeNode[1 << (level - 1)];
		/*
		 * 	考虑到需要维持一个相对位置不变的顺序
		 * 	使用双亲靠左，左孩原位置，右孩右边位置的策略。
		 */
		/*
		 * 	先进行 0 level的初始化
		 */
		TreeNode head = A_生成一个随机节点(min, max);
		arr[0] = head;
		for (int indexOfLevel = 1; indexOfLevel < level; indexOfLevel ++) {
			int indexRange = 1 << (level - indexOfLevel - 1);
			TreeNode parent = null;
			for (int index = 0; index < arr.length; index += indexRange) {
				if (((index / indexRange) & 0x1) == 0) {
					//需要更新parent
					parent = arr[index];
					//生成arr[index]
					arr[index] = A_生成一个随机节点(min, max);
					//设置parent.left
					parent.left = arr[index];
				} else {
					//不需要更新parent
					//生成arr[index]
					arr[index] = A_生成一个随机节点(min, max);
					//设置parent.right
					parent.right = arr[index];
				}
			}
		}
		return head;
	}
	/*
	 * 	生成一个随机TreeNode节点，节点的val在[min, max]之间随机
	 * 	如果min > max，那么返回一个null
	 */
	public static TreeNode A_生成一个随机节点(int min, int max) {
		TreeNode ans = null;
		if (min <= max) {
			ans = new TreeNode((int)(Math.random() * (max - min + 1)));
		}
		return ans;
	}
	/*
	 * 	生成一个随机TreeNode节点，节点的val在[min, max]之间随机
	 * 	如果min > max，那么返回一个null
	 * 	nullPercent：表示生成null的概率
	 */
	public static TreeNode A_生成一个随机节点_可以是NULL(int min, int max, double nullPercent) {
		TreeNode ans = null;
		if (min <= max) {
			if (Math.random() < nullPercent) {
				ans = null;
			} else {
				ans = new TreeNode((int)(Math.random() * (max - min + 1)));
			}
		}
		return ans;
	}
	/*
	 * 	使用Integer.MIN_VALUE作为null的标志
	 */
	public static TreeNode A_生成满二叉树(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		if (arr.length == 1) {
			return new TreeNode(arr[0]);
		}
		int judge = arr.length + 1, count_1 = 0;
		while (judge != 0) {
			count_1 += (judge & 1) == 1 ? 1 : 0;
			judge = judge >>> 1;
		}
		if (count_1 != 1) {
			System.out.println("输入不是一个满二叉树");
			return null;
		}
		TreeNode[] tree_arr = new TreeNode[arr.length];
		for (int i = 0; i < arr.length; i ++) {
			tree_arr[i] = arr[i] == Integer.MIN_VALUE ? null : new TreeNode(arr[i]);
		}
		for (int i = (arr.length - 2) / 2; i > -1; i --) {
			if (tree_arr[i] != null) {
				tree_arr[i].left = tree_arr[2 * i + 1];
				tree_arr[i].right = tree_arr[2 * i + 2];
			}
		}
		return tree_arr[0];
	}
	public static void B_按层打印(TreeNode root) {
		if (root == null) {
			System.out.println("输入的TreeNode为null");
			return;
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		Queue<Integer> q_int = new LinkedList<Integer>();
		q.add(root);
		q_int.add(1);
		int pre_int = -1;
		while (! q.isEmpty()) {
			TreeNode root_now = q.poll();
			int q_now = q_int.poll();
			if (q_now != pre_int) {
				if (pre_int == -1)
					System.out.printf("现在开始第%d层\t",q_now);
				else
					System.out.printf("\r\n现在开始第%d层\t",q_now);
				pre_int = q_now;
			}
			System.out.print(root_now.val+"\t");
			if (root_now.left != null) {
				q.add(root_now.left);
				q_int.add(q_now + 1);
			}
			if (root_now.right != null) {
				q.add(root_now.right);
				q_int.add(q_now + 1);
			}
		}
		System.out.println();
	}
	public static int[] C_前序数组(TreeNode head) {
		ArrayList<Integer> list = new ArrayList<>();
		C_前序数组_Internal(head, list);
		if (list == null || list.size() == 0) {
			return new int[]{};
		}
		int[] arr = new int[list.size()];
		for (int i = 0; i < arr.length; i ++) {
			arr[i] = list.get(i);
		}
		return arr;
	}
	private static void C_前序数组_Internal(TreeNode head, ArrayList<Integer> list) {
		if (head == null) {
			return;
		}
		list.add(head.val);
		C_前序数组_Internal(head.left, list);
		C_前序数组_Internal(head.right, list);
	}
	public static class TreeNode {
		public int val;
		public TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
		}
	}
}
