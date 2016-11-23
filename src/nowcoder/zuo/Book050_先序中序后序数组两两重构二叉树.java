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
//		debugPreAndIn();
		debugInAndPos();
	}
	
	static void debugInAndPos() {
		int maxLevel = 20, min = 0, max = (16 << maxLevel);
		double nullPercent = 0.4;
		TreeNode head = tools.TreeNode辅助.A_生成随机二叉树_不包含值相同的节点(maxLevel, min, max, nullPercent);
		int[] pos = tools.TreeNode辅助.C_后序数组(head);
		int[] in = tools.TreeNode辅助.C_中序数组(head);
		InAndPos ip = new InAndPos();
		TreeNode newHead = ip.construct(in, pos);
		System.out.println(tools.TreeNode辅助.D_head1和head2是不是值拓扑相同的树(head, newHead));
	}

	static void debugPreAndIn() {
		int maxLevel = 20, min = 0, max = (16 << maxLevel);
		double nullPercent = 0.4;
		TreeNode head = tools.TreeNode辅助.A_生成随机二叉树_不包含值相同的节点(maxLevel, min, max, nullPercent);
		int[] pre = tools.TreeNode辅助.C_前序数组(head);
		int[] in = tools.TreeNode辅助.C_中序数组(head);
		PreAndIn pi = new PreAndIn();
		TreeNode newHead = pi.construct(pre, in);
		System.out.println(tools.TreeNode辅助.D_head1和head2是不是值拓扑相同的树(head, newHead));
	}

	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book050_先序中序后序数组两两重构二叉树.java
	 * @type        PreAndIn
	 * @date        2016年11月23日 下午8:48:51
	 * @details     前序和中序，重构二叉树
	 */
	static class PreAndIn{
		public TreeNode construct(int[] pre, int[] in) {
			if (pre == null || in == null || pre.length < 1 || in.length < 1 || pre.length != in.length)	return null;
			return construct_internal(pre, 0, pre.length - 1, in, 0, in.length - 1);
		}
		private TreeNode construct_internal(int[] pre, int preSti, int preEni, int[] in, int inSti, int inEni) {
			if (inSti > inEni || preSti > preEni) {
				return null;
			}
			TreeNode head = new TreeNode(pre[preSti]);
			int headIndex = inSti - 1;
			while (headIndex < inEni &&  in[++ headIndex] != pre[preSti]){}
			head.left = construct_internal(in, inSti, headIndex - 1, pre, preSti + 1, headIndex - inSti + preSti);
			head.right = construct_internal(in, headIndex + 1, inEni, pre, headIndex + 1 - inEni + preEni, preEni);
			return head;
		}
	}
	
	static class InAndPos {
		public TreeNode construct(int[] in, int[] pos) {
			if (pos == null || in == null || pos.length < 1 || in.length < 1 || pos.length != in.length)	return null;
			return contruct_internal(in, 0, in.length - 1, pos, 0, pos.length - 1);
		}
		private TreeNode contruct_internal(int[] in, int inSti, int inEni, int[] pos, int posSti, int posEni) {
			if (inSti > inEni || posSti > posEni)	return null;
			TreeNode head = new TreeNode(pos[posEni]);
			int headIndex = inSti - 1;
			while (headIndex < inEni && in[++ headIndex] == pos[posEni]) {}
			head.left = contruct_internal(in, inSti, headIndex - 1, pos, posSti, posSti + headIndex - 1 - inSti);
			head.right = contruct_internal(in, headIndex + 1, inEni, pos, posEni + headIndex - inEni, posEni - 1);
			return head;
		}
	}
	
}
