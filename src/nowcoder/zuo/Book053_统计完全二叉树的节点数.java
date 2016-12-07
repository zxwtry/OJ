package nowcoder.zuo;

import java.util.LinkedList;
import java.util.Queue;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book053_统计完全二叉树的节点数.java
 * @type        Book053_统计完全二叉树的节点数
 * @date        2016年11月25日 下午8:01:13
 * @details     
 */
public class Book053_统计完全二叉树的节点数 {
	public static void main(String[] args) {
		debugAll();
	}
	
	static void debugAll() {
		for (int times = 0; times < 20; times ++) {
		int[] arr = new int[(1 << (int)(times)) - 1];
		TreeNode head = tools.TreeNode辅助.A_生成满二叉树(arr);
		Solution1 s1 = new Solution1();
		int ans1 = s1.nodeNum(head);
		Solution2 s2 = new Solution2();
		int ans2 = s2.nodeNum(head);
		System.out.println(ans1 + "..." + ans2);
		}
	}

	static class Solution1 {
		public int nodeNum(TreeNode head) {
			Queue<TreeNode> q = new LinkedList<TreeNode>();
			if (head != null) {
				q.add(head);
			}
			TreeNode rootNow = null;
			int ans = 0;
			while (! q.isEmpty()) {
				rootNow = q.poll();
				if (rootNow.left != null) {
					q.add(rootNow.left);
				}
				if (rootNow.right != null) {
					q.add(rootNow.right);
				}
				ans ++;
			}
			return ans;
		}
	}
	
	static class Solution2 {
		public int nodeNum(TreeNode head) {
			if (head == null) {
				return 0;
			}
			return nodeNum_internal(head, 1, mostLeftLevel(head, 1));
		}

		private int nodeNum_internal(TreeNode node, int i, int ll) {
			if (i == ll)	return 1;
			if (mostLeftLevel(node.right, i + 1) == ll) {
				return ( 1 << (ll- i)) + nodeNum_internal(node.right, i + 1, ll); 
			} else {
				return ( 1<< (ll - i - 1)) + nodeNum_internal(node.left, i + 1, ll);
			}
		}

		private int mostLeftLevel(TreeNode head, int l) {
			int level = l;
			while (head != null && head.left != null) {
				level ++;
				head = head.left;
			}
			return level;
		}
	}
	
}
