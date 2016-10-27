package nowcoder.zuo;

import java.util.HashMap;

public class Book018_复制含有随机指针节点的链表 {
	public static void main(String[] args) {
		testSolution();
	}
	static void testSolution() {
		int n = (int) (Math.random() * 10000);
		int min = 0;
		int max = n * n + 1;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
		ComplexListNode[] nodeArr = new ComplexListNode[n];
		for (int i = 0; i < n; i ++) {
			nodeArr[i] = new ComplexListNode(arr[i]);
			if (i != 0) {
				nodeArr[i - 1].next = nodeArr[i];
			}
		}
		for (int i = 0; i < n; i ++) {
			int selectIndex = (int) (Math.random() * (n + 2));
			nodeArr[i].rand = selectIndex >= n ? null : nodeArr[selectIndex];
		}
		Solution s = new Solution();
		ComplexListNode head = nodeArr[0];
		ComplexListNode newHead = s.copyListWithRandom(head);
		boolean isAllTrue = true;
		while (head != null) {
			isAllTrue &= head.val == newHead.val;
			if (head.rand == null) {
				isAllTrue &= newHead.rand == null;
			} else {
				isAllTrue &= (newHead.rand == null ? false : (newHead.rand.val == head.rand.val));
			}
			head = head.next;
			newHead = newHead.next;
		}
		System.out.println(isAllTrue);
	}
	/*
	 * 	先复制链表，再指定random指针
	 */
	static class Solution {
		public ComplexListNode copyListWithRandom(ComplexListNode head) {
			if (head == null) {
				return null;
			}
			ComplexListNode copyHead = null;
			ComplexListNode copyTravel = null;
			HashMap<ComplexListNode, ComplexListNode> map = new HashMap<>();
			while(head != null) {
				ComplexListNode newNode = new ComplexListNode(head.val);
				if (null == copyHead) {
					copyHead = newNode;
					copyTravel = newNode;
				} else {
					copyTravel.next = newNode;
					copyTravel = newNode;
				}
				map.put(newNode, head.rand);
				head = head.next;
			}
			copyTravel = copyHead;
			while (copyTravel != null) {
				copyTravel.rand = map.get(copyTravel);
				copyTravel = copyTravel.next;
			}
			return copyHead;
		}
	}
	static class ComplexListNode {
		public int val;
		public ComplexListNode next;
		public ComplexListNode rand;
		public ComplexListNode(int value) {
			this.val = value;
		}
	}
}
