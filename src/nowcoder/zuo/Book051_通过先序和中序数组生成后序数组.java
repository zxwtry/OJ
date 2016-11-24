package nowcoder.zuo;

import java.util.HashMap;

import tools.TreeNode辅助.TreeNode;

public class Book051_通过先序和中序数组生成后序数组 {
	public static void main(String[] args) {
		debugMySolution();
		debugBookSolution();
	}
	
	static void debugBookSolution() {
			boolean isAllTrue = true;
		for (int times = 0; times < 10000; times ++) {
			int maxLevel = 20, min = 0, max = (16 << maxLevel);
			double nullPercent =0.1;
			TreeNode head = tools.TreeNode辅助.A_生成随机二叉树_不包含值相同的节点(maxLevel, min, max, nullPercent);
			int[] pre = tools.TreeNode辅助.C_前序数组(head);
			int[] in = tools.TreeNode辅助.C_中序数组(head);
			int[] pos = tools.TreeNode辅助.C_后序数组(head);
			
			BookSolution s = new BookSolution();
			int[] pos_s = s.getPos(pre, in);
			
			for (int index = 0; index < pos.length; index ++) {
				isAllTrue &= pos[index] == pos_s[index];
			}
		}
		
		System.out.println(isAllTrue);
			
	}

	static void debugMySolution() {
		
	}

	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book051_通过先序和中序数组生成后序数组.java
	 * @type        MySolution
	 * @date        2016年11月24日 下午7:32:56
	 * @details     未完成
	 */
	static class MySolution {
		public int[] getPos(int[] pre, int[] in) {
			if (pre == null || in == null || pre.length != in.length || pre.length < 1)		return null;
			int[] pos = new int[pre.length];
			getPos_internal(pos, pre, 0, pre.length - 1, in, 0, in.length - 1, new int[] {pre.length - 1});
			return pos;
		}
		private void getPos_internal(int[] pos, int[] pre, int preSti, int preEni, int[] in, int inSti, int inEni, int[] posIndex) {
			if (preSti > preEni || inSti > inEni) 	return;
			if (preSti == preEni || inSti == inEni)	{
				pos[posIndex[0] --] = pre[preSti];
				return;
			}
			int inIndex = inSti - 1;
			while (inIndex < inEni && in[++ inIndex] != pre[preSti]) {};
			getPos_internal(pos, pre, preSti + 1, preEni, in, inSti, inEni, posIndex);
			
		}
	}
	
	static class BookSolution 
	{
		public int[] getPos(int[] pre, int[] in) {
			if (pre == null || in == null)	return null;
			int[] pos = new int[pre.length];
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; i < in.length; i ++)	map.put(in[i], i);
			getPos_internal(pre, 0, pre.length - 1, in, 0, in.length - 1, pos, 0, pos.length - 1, map);
			return pos;
		}

		private int getPos_internal(int[] pre, int preSti, int preEni, int[] in, int inSti, int inEni, int[] pos, int posSti, int posEni,
				HashMap<Integer, Integer> map) {
			if (preSti > preEni)	return posEni;
			pos[posEni --] = pre[preSti];
			int i = map.get(pre[preSti]);
			posEni = getPos_internal(pre, preEni - inEni + i + 1, preEni, in, i + 1, inEni, pos, posSti, posEni, map);
			return getPos_internal(pre, preSti + 1,  preEni - inEni + i, in, inSti, i - 1, pos, posSti, posEni, map);
		}
	}
	
}
