package nowcoder.zuo;

import tools.DoubleNode辅助.DoubleNode;
import tools.ListNode辅助.ListNode;

public class Book014_反转单向和双向链表 {
	public static void main(String[] args) {
//		testListNode();
//		testListNodeBook();
//		testDoubleNode();
		testDoubleNodeBook();
	}
	static void testDoubleNodeBook() {
		int n = 100;
		int min = 0;
		int max = 1000;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
		DoubleNode head = tools.DoubleNode辅助.A_双向链表生成器(arr);
		SolutionReverseDoubleNodeBook srlnb = new SolutionReverseDoubleNodeBook();
		head = srlnb.reverseList(head);
		boolean isTrue = true;
		int index = arr.length - 1;
		while (head != null) {
			isTrue &= head.val == arr[index];
			index --;
			head = head.next;
		}
		System.out.println(isTrue);
	}
	static void testDoubleNode() {
		int n = 100;
		int min = 0;
		int max = 1000;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
		DoubleNode head = tools.DoubleNode辅助.A_双向链表生成器(arr);
		SolutionReverseDoubleNode srlnb = new SolutionReverseDoubleNode();
		head = srlnb.reverseList(head);
		boolean isTrue = true;
		int index = arr.length - 1;
		while (head != null) {
			isTrue &= head.val == arr[index];
			index --;
			head = head.next;
		}
		System.out.println(isTrue);
	}
	static void testListNodeBook() {
		int n = 100;
		int min = 0;
		int max = 1000;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
		ListNode head = tools.ListNode辅助.A_一维生成器(arr);
		SolutionReverseListNodeBook srlnb = new SolutionReverseListNodeBook();
		head = srlnb.reverseList(head);
		boolean isTrue = true;
		int index = arr.length - 1;
		while (head != null) {
			isTrue &= head.val == arr[index];
			index --;
			head = head.next;
		}
		System.out.println(isTrue);
	}
	static void testListNode() {
		int n = 100;
		int min = 0;
		int max = 1000;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
		ListNode head = tools.ListNode辅助.A_一维生成器(arr);
		SolutionReverseListNode srln = new SolutionReverseListNode();
		head = srln.reverseList(head);
		boolean isTrue = true;
		int index = arr.length - 1;
		while (head != null) {
			isTrue &= head.val == arr[index];
			index --;
			head = head.next;
		}
		System.out.println(isTrue);
	}
	static class SolutionReverseListNode {
		public ListNode reverseList(ListNode head) {
			ListNode preNode = null, curNode = head;
			ListNode preNodeSave = null, curNodeSave = head;
			while (curNode != null) {
				preNodeSave = curNode;
				curNodeSave = curNode.next;
				curNode.next = preNode;
				preNode = preNodeSave;
				curNode = curNodeSave;
			}
			return preNode;
		}
	}
	static class SolutionReverseListNodeBook {
		public ListNode reverseList(ListNode head) {
			ListNode preNode = null, nextNode = null;
			while (head != null) {
				nextNode = head.next;
				head.next = preNode;
				preNode = head;
				head = nextNode;
			}
			return preNode;
		}
	}
	static class SolutionReverseDoubleNode {
		public DoubleNode reverseList(DoubleNode head) {
			DoubleNode preNode = null, curNode = head;
			DoubleNode preNodeSave = null, curNodeSave = null;
			while(curNode != null) {
				preNodeSave = curNode;
				curNodeSave = curNode.next;
				curNode.next = preNode;
				curNode.last = curNodeSave;
				preNode = preNodeSave;
				curNode = curNodeSave;
			}
			return preNode;
		}
	}
	static class SolutionReverseDoubleNodeBook {
		public DoubleNode reverseList(DoubleNode head) {
			DoubleNode preNode = null, nextNode = null;
			while(head != null) {
				nextNode = head.next;
				head.next = preNode;
				head.last = nextNode;
				preNode = head;
				head = nextNode;
			}
			return preNode;
		}
	}
}
