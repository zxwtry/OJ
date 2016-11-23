package nowcoder.zuo;

import tools.TreeNode辅助.TreeNode;

/**
 * @auther      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book050_先序中序后序数组两两重构二叉树.java
 * @type        Book050_先序中序后序数组两两重构二叉树
 * @date        2016年11月23日 下午4:06:25
 * @details     保证节点的值互不相同
 */
public class Book050_先序中序后序数组两两重构二叉树 {
	public static void main(String[] args) {
		debugPreAndIn();
	}
	
	private static void debugPreAndIn() {
		int maxLevel = 3;
		int min = 0;
		int max = 100;
		double nullPercent = 0.4;
		TreeNode head = tools.TreeNode辅助.A_生成随机二叉树(maxLevel, min, max, nullPercent);
		head = tools.TreeNode辅助.A_生成随机满二叉树(maxLevel, min, max);
		int[] pre = tools.TreeNode辅助.C_前序数组(head);
		int[] in = tools.TreeNode辅助.C_中序数组(head);
		PreAndIn pi = new PreAndIn();
		TreeNode newHead = pi.construct(pre, in);
		System.out.println(tools.TreeNode辅助.D_head1和head2是不是值拓扑相同的树(head, newHead));
	}

	static class PreAndIn{
		public TreeNode construct(int[] pre, int[] in) {
			if (pre == null || in == null || pre.length < 1 || in.length < 1 || pre.length != in.length)	return null;
			return construct_internal(pre, 0, pre.length - 1, in, 0, in.length - 1);
		}
		private TreeNode construct_internal(int[] pre, int preSti, int preEni, int[] in, int inSti, int inEni) {
			if (preSti > preEni || inSti > inEni) {
				return null;
			}
			TreeNode head = new TreeNode(in[inSti]);
			int headIndex = preSti - 1;
			while (headIndex < preEni &&  pre[++ headIndex] != in[inSti]){}
			head.left = construct_internal(pre, preSti, headIndex - 1, in, inSti + 1, headIndex - preSti + inSti);
			System.out.println((headIndex + 1)+"..." + preEni + "###" + (headIndex + 1 - preEni + inEni) + "..." + inEni);
			head.right = construct_internal(pre, headIndex + 1, preEni, in, headIndex + 1 - preEni + inEni, inEni);
			return head;
		}
	}
	
}
