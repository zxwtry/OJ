package nowcoder.zuo;

import java.util.Stack;

import tools.ListNode辅助.ListNode;

/*
 * 	给链表1->2->3->4->null, num=3，链表调整后为：1->2->4->null
 */

public class Book023_在单链表中删除指定值的节点 {
	public static void main(String[] args) {
//		testStackSolution();
//		testAdjustSolution();
		test();
	}
	static void test() {
		int n = 10;
		int min = 0;
		int max = 1000;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
		int num = arr[(int)(Math.random() * (n + 1))];
		ListNode head1 = tools.ListNode辅助.A_一维生成器(arr);
		ListNode head2 = tools.ListNode辅助.A_一维生成器(arr);
		StackSolution ss = new StackSolution();
		ss.removeVal(head1, num);
		AdjustSolution as = new AdjustSolution();
		as.removeVal(head2, num);
		tools.Utils.printArray(arr, n + 1, 5);
		System.out.println(num);
		tools.ListNode辅助.B_打印链表_一行(head1, n + 100);
		tools.ListNode辅助.B_打印链表_一行(head2, n + 100);
	}
	static void testAdjustSolution() {
		int n = 10;
		int min = 0;
		int max = 1000;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
		int num = arr[(int)(Math.random() * (n + 1))];
		ListNode head = tools.ListNode辅助.A_一维生成器(arr);
		AdjustSolution as = new AdjustSolution();
		tools.Utils.printArray(arr, n + 1, 5);
		System.out.println(num);
		as.removeVal(head, num);
		tools.Utils.printArray(arr, n + 1, 5);
	}
	static void testStackSolution() {
		int n = 10;
		int min = 0;
		int max = 1000;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
		int num = arr[(int)(Math.random() * (n + 1))];
		ListNode head = tools.ListNode辅助.A_一维生成器(arr);
		StackSolution ss = new StackSolution();
		tools.Utils.printArray(arr, n + 1, 5);
		System.out.println(num);
		ss.removeVal(head, num);
		tools.Utils.printArray(arr, n + 1, 5);
	}
	//时间复杂度O(N)，额外空间复杂度O(N)
	static class StackSolution {
		public ListNode removeVal(ListNode head, int num) {
			Stack<ListNode> stack = new Stack<>();
			while (head != null) {
				if (head.val != num) {
					stack.push(head);
				}
				head = head.next;
			}
			while (! stack.isEmpty()) {
				stack.peek().next = head;
				head = stack.pop();
			}
			return head;
		}
	}
	//时间复杂度O(N)，额外空间复杂度O(1)
	static class AdjustSolution {
		public ListNode removeVal(ListNode head, int num) {
			while (head != null) {
				if (head.val != num) {
					break;
				}
				head = head.next;
			}
			ListNode pre = head;
			ListNode cur = head;
			while (cur != null) {
				if (cur.val == num) {
					pre.next = cur.next;
				} else {
					pre = cur;
				}
				cur = cur.next;
			}
			return head;
		}
	}
}
