package nowcoder.zuo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

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
//		debugInAndPos();
//		debugPreAndPos();
		testAll();
	}
	
	static void testAll() {
		for (int times = 0; times < 10000; times ++) {
			int maxLevel = 20, min = 0, max = (16 << maxLevel);
			double nullPercent = 0.1;
			TreeNode head = tools.TreeNode辅助.A_生成随机二叉树_不包含值相同的节点(maxLevel, min, max, nullPercent);
			Queue<TreeNode> q = new LinkedList<TreeNode>();
			if (head != null)	q.add(head);
			TreeNode rootNow = null;
			while (! q.isEmpty()) {
				rootNow = q.poll();
				if (rootNow.left == null || rootNow.right == null) {
					rootNow.left = null;
					rootNow.right = null;
				} else {
					q.add(rootNow.left);
					q.add(rootNow.right);
				}
			}
			int[] pre = tools.TreeNode辅助.C_前序数组(head);
			int[] in = tools.TreeNode辅助.C_中序数组(head);
			int[] pos = tools.TreeNode辅助.C_后序数组(head);
			try {
				TreeNode piT = new PreAndIn().construct(pre, in);
				TreeNode ipT = new InAndPos().construct(in, pos);
				TreeNode ppT = new PreAndPos().construct(in, pos);
				
				boolean isAllSame = true;
				isAllSame &= tools.TreeNode辅助.D_head1和head2是不是值拓扑相同的树(head, piT);
				isAllSame &= tools.TreeNode辅助.D_head1和head2是不是值拓扑相同的树(head, ipT);
				isAllSame &= tools.TreeNode辅助.D_head1和head2是不是值拓扑相同的树(head, ppT);
				if (! isAllSame) {
					tools.FileUtils.B_纪录String_append("D:/file/temp/pre.txt", "又翻车了" + times);
					StringBuilder st = new StringBuilder();
					for (int val : pre) 	st.append(val + " ");
					tools.FileUtils.B_纪录String_append("D:/file/temp/pre.txt", st.toString());
					
					st.delete(0, st.length());
					tools.FileUtils.B_纪录String_append("D:/file/temp/in.txt", "又翻车了" + times);
					for (int val : in) 	st.append(val + " ");
					tools.FileUtils.B_纪录String_append("D:/file/temp/in.txt", st.toString());
					
					st.delete(0, st.length());
					tools.FileUtils.B_纪录String_append("D:/file/temp/pos.txt", "又翻车了" + times);
					for (int val : pos) 	st.append(val + " ");
					tools.FileUtils.B_纪录String_append("D:/file/temp/pos.txt", st.toString());
				}
			} catch (Exception e) {
				tools.FileUtils.B_纪录String_append("D:/file/temp/pre.txt", "又翻车了" + times);
				StringBuilder st = new StringBuilder();
				for (int val : pre) 	st.append(val + " ");
				tools.FileUtils.B_纪录String_append("D:/file/temp/pre.txt", st.toString());
				
				st.delete(0, st.length());
				tools.FileUtils.B_纪录String_append("D:/file/temp/in.txt", "又翻车了" + times);
				for (int val : in) 	st.append(val + " ");
				tools.FileUtils.B_纪录String_append("D:/file/temp/in.txt", st.toString());
				
				st.delete(0, st.length());
				tools.FileUtils.B_纪录String_append("D:/file/temp/pos.txt", "又翻车了" + times);
				for (int val : pos) 	st.append(val + " ");
				tools.FileUtils.B_纪录String_append("D:/file/temp/pos.txt", st.toString());
			}
		}
	}

	static void debugPreAndPos() {
		int maxLevel = 20, min = 0, max = (16 << maxLevel);
		double nullPercent = 0.1;
		TreeNode head = tools.TreeNode辅助.A_生成随机二叉树_不包含值相同的节点(maxLevel, min, max, nullPercent);
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		if (head != null)	q.add(head);
		TreeNode rootNow = null;
		while (! q.isEmpty()) {
			rootNow = q.poll();
			if (rootNow.left == null || rootNow.right == null) {
				rootNow.left = null;
				rootNow.right = null;
			} else {
				q.add(rootNow.left);
				q.add(rootNow.right);
			}
		}
//		int[] arr = new int[(1 << 16) - 1];
//		for (int index = 0; index < arr.length; index ++)	arr[index] = index;
//		TreeNode head = tools.TreeNode辅助.A_生成满二叉树(arr);
		int[] pos = tools.TreeNode辅助.C_后序数组(head);
		int[] pre = tools.TreeNode辅助.C_前序数组(head);
		int[] in = tools.TreeNode辅助.C_中序数组(head);
		StringBuilder st = new StringBuilder();
		for (int val : pre) 	st.append(val + " ");
		tools.FileUtils.B_纪录String_newFile("D:/file/temp/pre.txt", st.toString());
		st.delete(0, st.length());
		for (int val : in) 	st.append(val + " ");
		tools.FileUtils.B_纪录String_newFile("D:/file/temp/in.txt", st.toString());
		st.delete(0, st.length());
		for (int val : pos) 	st.append(val + " ");
		tools.FileUtils.B_纪录String_newFile("D:/file/temp/pos.txt", st.toString());
		st.delete(0, st.length());
		PreAndPos pp = new PreAndPos();
		TreeNode newHead = pp.construct(pre, pos);
		System.out.println(tools.TreeNode辅助.D_head1和head2是不是值拓扑相同的树(head, newHead));
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
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book050_先序中序后序数组两两重构二叉树.java
	 * @type        InAndPos
	 * @date        2016年11月23日 下午9:06:24
	 * @details     中序和后序，重构二叉树
	 */
	static class InAndPos {
		public TreeNode construct(int[] in, int[] pos) {
			if (pos == null || in == null || pos.length < 1 || in.length < 1 || pos.length != in.length)	return null;
			return contruct_internal(in, 0, in.length - 1, pos, 0, pos.length - 1);
		}
		private TreeNode contruct_internal(int[] in, int inSti, int inEni, int[] pos, int posSti, int posEni) {
			if (inSti > inEni || posSti > posEni)	return null;
			TreeNode head = new TreeNode(pos[posEni]);
			if (inSti == inEni || posSti == posEni)	return head;
			int headIndex = inSti - 1;
			while (headIndex < inEni && in[++ headIndex] == pos[posEni]) {}
			head.left = contruct_internal(in, inSti, headIndex - 1, pos, posSti, posSti + headIndex - 1 - inSti);
			head.right = contruct_internal(in, headIndex + 1, inEni, pos, posEni + headIndex - inEni, posEni - 1);
			return head;
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book050_先序中序后序数组两两重构二叉树.java
	 * @type        PreAndPos
	 * @date        2016年11月23日 下午9:08:07
	 * @details     通过前序和后序是不能唯一确定一棵二叉树
	 * @details     pre: [1, 2]		pos: [2, 1]
	 * @details     1 				1
	 * @details       2			  2
	 * @details     能够从前序后序中，构建出来的二叉树的特点是：
	 * @details     	除叶子节点外，所有的节点都有左孩子和右孩子
	 * @details     这里保证上面的条件
	 */
	static class PreAndPos {
		public TreeNode construct(int[] pre, int[] pos) {
			if (pos == null || pre == null || pos.length < 1 || pre.length < 1 || pos.length != pre.length)		return null;
			return contruct_internal(pre, 0, pre.length - 1, pos, 0, pos.length - 1);
		}
		private TreeNode contruct_internal(int[] pre, int preSti, int preEni, int[] pos, int posSti, int posEni) {
			if (preSti > preEni || posSti > posEni)		return null;
			TreeNode head = new TreeNode(pre[preSti]);
			if (preSti == preEni || posSti == posEni)		return head;
			int leftIndex = posSti - 1;
			while (leftIndex < posEni && pre[preSti + 1] != pos[++ leftIndex]) {}
			head.left = contruct_internal(pre, preSti + 1, preSti - posSti + leftIndex, pos, posSti, leftIndex);
			head.right = contruct_internal(pre, preSti - posSti + leftIndex + 1, preEni, pos, leftIndex + 1, posEni - 1);
			return head;
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book050_先序中序后序数组两两重构二叉树.java
	 * @type        PreAndInBook
	 * @date        2016年11月24日 下午7:15:25
	 * @details     书上的方法
	 */
	static class PreAndInBook {
		public TreeNode construct(int[] pre, int[] in) {
			if (pre == null || in == null)	return null;
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; i < pre.length; i ++)	map.put(in[i], i);
			return construct_internal(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
		}

		private TreeNode construct_internal(int[] pre, int pi, int pj, int[] in, int ini, int inj,
				HashMap<Integer, Integer> map) {
			if (pi > pj)	return null;
			TreeNode head = new TreeNode(pre[pi]);
			int i = map.get(pre[pi]);
			head.left = construct_internal(pre, pi + 1, pi + i - ini, in, ini, i - 1, map);
			head.right = construct_internal(pre, pi + i - ini + 1, pj, in, i + 1, inj, map);
			return head;
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book050_先序中序后序数组两两重构二叉树.java
	 * @type        InAndPosBook
	 * @date        2016年11月24日 下午7:16:31
	 * @details     书上的方法
	 */
	static class InAndPosBook {
		public TreeNode construct(int[] in, int[] pos) {
			if (in == null || pos == null) 	return null; 
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; i < in.length; i ++)	map.put(in[i], i);
			return construct_internal(in, 0, in.length - 1, pos, 0, pos.length - 1, map);
		}

		private TreeNode construct_internal(int[] in, int ii, int ij, int[] pos, int pi, int pj,
				HashMap<Integer, Integer> map) {
			if (ii > ij)	return null;
			TreeNode head = new TreeNode(pos[pj]);
			int i = map.get(pos[pj]);
			head.left = construct_internal(in, ii, i - 1, pos, pi, pj + i - ii - 1, map);
			head.right = construct_internal(in, i + 1, ij, pos, pj + i - ii, pj - 1, map);
			return head;
		}
	}
}
