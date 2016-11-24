package nowcoder.zuo;

import java.util.HashMap;
import java.util.LinkedList;

import tools.TreeNode辅助.TreeNode;

public class Book048_Tarjan算法与并查集解决二叉树节点间最近公共祖先的批量查询 {
	public static void main(String[] args) {
		Tarjan t = new Tarjan();
		int[] arr = new int[(1 << 2) - 1];
		for (int index = 0; index < arr.length; index ++)	arr[index] = index;
		TreeNode[] ns = new TreeNode[arr.length];
		for (int index = 0; index < arr.length; index ++)	ns[index] = new TreeNode(arr[index]);
		for (int index = 0; index < (arr.length+1) / 2 - 1; index ++) {
			ns[index].left = ns[index * 2 + 1];
			ns[index].right = ns[index * 2 + 2];
		}
		Query[] queries = new Query[(arr.length - 1) * (arr.length) / 2];
		int queryIndex = 0;
		for (int i = 0; i < arr.length; i ++) {
			for (int j = i + 1; j < arr.length; j ++) {
				queries[queryIndex ++] = new Query(ns[i], ns[j]);
			}
		}
		TreeNode[] ans = t.query(ns[0], queries);
		for (int index = 0; index < ans.length; index ++) {
			System.out.println(queries[index].t1.val + "..." + queries[index].t2.val + "###" + ans[index].val);
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book048_Tarjan算法与并查集解决二叉树节点间最近公共祖先的批量查询.java
	 * @type        Tarjan
	 * @date        2016年11月24日 下午3:37:14
	 * @details     Tarjan
	 */
	static class Tarjan {
		private HashMap<TreeNode, LinkedList<TreeNode>> queryMap = null;
		private HashMap<TreeNode, LinkedList<Integer>> indexMap = null;
		private HashMap<TreeNode, TreeNode> ancestorMap = null;
		private DistjointSets sets = null;
		public Tarjan() {
			this.queryMap = new HashMap<TreeNode, LinkedList<TreeNode>>();
			this.indexMap = new HashMap<TreeNode, LinkedList<Integer>>();
			this.ancestorMap = new HashMap<TreeNode, TreeNode>();
			sets = new DistjointSets();
		}
		public TreeNode[] query(TreeNode head, Query[] queries) {
			TreeNode[] ans = new TreeNode[queries.length];
			setQueries(queries, ans);
			sets.init(head);
			setAnswers(head, ans);
			return ans;
		}
		private void setAnswers(TreeNode head, TreeNode[] ans) {
			if (head == null)	return;
			setAnswers(head.left, ans);
			sets.union(head.left, head);
			ancestorMap.put(sets.getFather(head), head);
			setAnswers(head.right, ans);
			sets.union(head.right, head);
			ancestorMap.put(sets.getFather(head), head);
			LinkedList<TreeNode> nList = queryMap.get(head);
			LinkedList<Integer> iList = indexMap.get(head);
			TreeNode node = null, nodeFather = null;
			int index = 0;
			while (nList != null && !nList.isEmpty()) {
				node = nList.poll();
				index = iList.poll();
				nodeFather = sets.getFather(node);
				if (ancestorMap.containsKey(nodeFather))
					ans[index] = ancestorMap.get(nodeFather);
			}
		}
		private void setQueries(Query[] queries, TreeNode[] ans) {
			TreeNode t1 = null, t2 = null;
			for (int i = 0; i < ans.length; i ++) {
				t1 = queries[i].t1; t2 = queries[i].t2;
				if (t1 == t2 || t1 == null || t2 == null) {
					ans[i] = t1 == null ? t2 : t1;
				} else {
					if (! queryMap.containsKey(t1)) {
						queryMap.put(t1, new LinkedList<TreeNode>());
						indexMap.put(t1, new LinkedList<Integer>());
					}
					if (! queryMap.containsKey(t2)) {
						queryMap.put(t2, new LinkedList<TreeNode>());
						indexMap.put(t2, new LinkedList<Integer>());
					}
					queryMap.get(t1).add(t2);
					indexMap.get(t1).add(i);
					queryMap.get(t2).add(t1);
					indexMap.get(t2).add(i);
				}
			}
		}
	}
	
	public static class Query {
		public TreeNode t1, t2;
		public Query(TreeNode t1, TreeNode t2) {
			this.t1 = t1;
			this.t2 = t2;
		}
	}
	
	/**
	 * @auther      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     nowcoder.zuo
	 * @file        Book048_Tarjan算法与并查集解决二叉树节点间最近公共祖先的批量查询.java
	 * @type        DistjointSets
	 * @date        2016年11月24日 下午3:02:03
	 * @details     并查集
	 */
	static class DistjointSets {
		private HashMap<TreeNode, TreeNode> fatherMap = null;
		private HashMap<TreeNode, Integer> rankMap = null;
		public DistjointSets() {
			fatherMap = new HashMap<TreeNode, TreeNode>();
			rankMap = new HashMap<TreeNode, Integer>();
		}
		public void init(TreeNode head) {
			fatherMap.clear();
			rankMap.clear();
			init_internal(head);
		}
		private void init_internal(TreeNode head) {
			if (head == null)	return;
			fatherMap.put(head, head);
			rankMap.put(head, 0);
			init_internal(head.left);
			init_internal(head.right);
		}
		public TreeNode getFather(TreeNode node) {
			TreeNode father = fatherMap.get(node);
			if (father != node)
				father = getFather(father);
			fatherMap.put(node, father);
			return father;
		}
		public void union(TreeNode t1, TreeNode t2) {
			if (t1 == null || t2 == null)	return;
			TreeNode t1_f = getFather(t1);
			TreeNode t2_f = getFather(t2);
			if (t1_f != t2_f) {
				int t1_f_r = rankMap.get(t1_f);
				int t2_f_r = rankMap.get(t2_f);
				if (t1_f_r > t2_f_r) {
					fatherMap.put(t2_f, t1_f);
				} else if (t1_f_r < t2_f_r) {
					fatherMap.put(t1_f, t2_f);
				} else {
					fatherMap.put(t1_f, t2_f);
					rankMap.put(t2_f, t2_f_r + 1);
				}
			}
		}
	}
	
}
