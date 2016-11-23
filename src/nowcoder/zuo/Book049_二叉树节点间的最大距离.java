package nowcoder.zuo;

import java.util.LinkedList;
import java.util.Queue;

import tools.TreeNode辅助.TreeNode;

/**
 * 	从二叉树的节点A出发，可以向上或者向下走，但沿途的节点只能经过一次。	
 * 	到达节点B时，路径上的节点数叫做A到B的距离
 * 			0
 * 		1		2
 * 	  3   4   5    6
 * 	比如，1和3之间的距离是2
 * 	比如，1和5之间的距离是4
 * 	现在，想要得到的是一棵二叉树的所有节点距离中，
 * 	最大的距离。
 */

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book049_二叉树节点间的最大距离.java
 * @type        Book049_二叉树节点间的最大距离
 * @date        2016年11月23日 下午2:28:46
 * @details     
 */
public class Book049_二叉树节点间的最大距离 {
	public static void main(String[] args) {
		debugMySolution();
	}
	
	private static void debugMySolution() {
		int maxLevel = 5;
		int min = 0;
		int max = Integer.MAX_VALUE;
		double nullPercent = 0.1;
		TreeNode head = tools.TreeNode辅助.A_生成随机二叉树(maxLevel, min, max, nullPercent);
		MySolution s = new MySolution();
		System.out.println(s.getMaxDist(head));
		System.out.println();
	}

	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book049_二叉树节点间的最大距离.java
	 * @type        MySolution
	 * @date        2016年11月23日 下午2:29:10
	 * @details     
	 */
	static class MySolution {
		public int getMaxDist(TreeNode head) {
			if (head == null)	return 0;
			if (head.left == null && head.right == null) return 1;
			int leftLayer = getLayer(head.left);
			int rightLayer = getLayer(head.right);
			int maxDist = leftLayer + rightLayer + 1;
			maxDist = Math.max(maxDist, getMaxDist(head.left));
			maxDist = Math.max(maxDist, getMaxDist(head.right));
			return maxDist;
		}
		private int getLayer(TreeNode head) {
			if (null == head)	return 0;
			Queue<TreeNode> q = new LinkedList<TreeNode>();
			Queue<Integer> qLayer = new LinkedList<Integer>();
			q.add(head);
			qLayer.add(1);
			TreeNode rootNow = null;
			int layNow = 0;
			while (! q.isEmpty()) {
				rootNow = q.poll();
				layNow = qLayer.poll();
				if (rootNow.left != null) {
					q.add(rootNow.left);
					qLayer.add(layNow + 1);
				}
				if (rootNow.right != null) {
					q.add(rootNow.right);
					qLayer.add(layNow + 1);
				}
			}
			return layNow;
		}
	}
	
}
