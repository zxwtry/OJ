package tools;

public class DoubleNode辅助 {
	public static class DoubleNode {
		public int val;
		public DoubleNode last;
		public DoubleNode next;
		public DoubleNode(int val) {
			this.val = val;
		}
	}
	public static DoubleNode A_双向链表生成器(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		DoubleNode head = new DoubleNode(arr[0]);
		DoubleNode preHead = head;
		for (int i = 1; i < arr.length; i ++) {
			DoubleNode nowNode = new DoubleNode(arr[i]);
			preHead.next = nowNode;
			nowNode.last = preHead;
			preHead = nowNode;
		}
		return head;
	}
	public static void B_打印双向链表(DoubleNode head) {
		if (head == null) {
			System.out.println();
		} else {
			while (head != null) {
				System.out.print(head.val + " ");
				head = head.next;
			}
			System.out.println();
		}
	}
}
