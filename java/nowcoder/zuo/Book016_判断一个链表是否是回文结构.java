package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

public class Book016_判断一个链表是否是回文结构 {
	public static void main(String[] args) {
//		testSolution();
		testBookSolution();
	}
	static void testBookSolution() {
		int n = (int)(Math.random() * 1000);
		int[] arr = tools.Random随机生成器.A_生成一个不重复随机数据(n, 0, n * n * n + 1);
		int[] arrGhost = arr.clone();
		reverse(arrGhost);
		int[] arrReal = new int[arr.length * 2];
		System.arraycopy(arr, 0, arrReal, 0, arr.length);
		System.arraycopy(arrGhost, 0, arrReal, arr.length, arr.length);
//		arrReal[0] = 0;
		System.out.println(new StanrdSolution().judge(arrReal));
		ListNode head = tools.ListNode辅助.A_一维生成器(arrReal);
		BookSolution bookSolution = new BookSolution();
		System.out.println(bookSolution.isPalindrome(head));
	}
	static void testSolution() {
		int n = (int)(Math.random() * 1000);
		int[] arr = tools.Random随机生成器.A_生成一个不重复随机数据(n, 0, n * n * n + 1);
		int[] arrGhost = arr.clone();
		reverse(arrGhost);
		int[] arrReal = new int[arr.length * 2];
		System.arraycopy(arr, 0, arrReal, 0, arr.length);
		System.arraycopy(arrGhost, 0, arrReal, arr.length, arr.length);
		System.out.println(new StanrdSolution().judge(arrReal));
		ListNode head = tools.ListNode辅助.A_一维生成器(arrReal);
		MySolution ms = new MySolution();
		System.out.println(ms.judge(head));
	}
	private static void reverse(int[] arrGhost) {
		int sti = 0, eni = arrGhost.length - 1;
		while (sti < eni) {
			int temp = arrGhost[sti];
			arrGhost[sti] = arrGhost[eni];
			arrGhost[eni] = temp;
			sti ++;
			eni --;
		}
	}
	static class StanrdSolution {
		public boolean judge(int[] arr) {
			int sti = 0, eni = arr.length - 1;
			boolean isFalse = false;
			while (sti < eni && ! isFalse) {
				if (arr[sti] != arr[eni]) {
					isFalse = true;
				}
				sti ++;
				eni --;
			}
			return ! isFalse;
		}
	}
	static class MySolution {
		public boolean judge(ListNode head) {
			int length = 0;
			ListNode travel = head;
			while (travel != null) {
				length ++;
				travel = travel.next;
			}
			int[] val = new int[length / 2];
			travel = head;
			for (int i = 0; i < val.length; i ++) {
				val[i] = travel.val;
				travel = travel.next;
			}
			if ((length & 0x1) == 1) {
				travel = travel.next;
			}
//			travel = travel.next;
			boolean isFalse = false;
			for (int i = val.length - 1; ! isFalse && i > -1; i --) {
				if (val[i] != travel.val) {
					isFalse = true;
				}
				travel = travel.next;
			}
			return !isFalse;
		}
	}
	/*
	 * 	这里有两个优化：
	 * 		1,	查找中间节点，是两个指针的方法。
	 * 		2,	逆序后半段链表来进行优化
	 */
	static class BookSolution {
		public boolean isPalindrome(ListNode head) {
			if (head == null || head.next == null) {
				return true;
			}
			ListNode n1 = head;
			ListNode n2 = head;
			while (n2.next != null && n2.next.next != null) {
				n1 = n1.next;
				n2 = n2.next.next;
			}
			n2 = n1.next;
			n1.next = null;
			ListNode n3 = null;
			while (n2 != null) {
				n3 = n2.next;
				n2.next = n1;
				n1 = n2;
				n2 = n3;
			}
			n3 = n1;
			n2 = head;
			boolean res = true;
			while (n1 != null && n2 != null && res) {
				if (n1.val != n2.val) {
					res = false;
				} else {
					n1 = n1.next;
					n2 = n2.next;
				}
			}
			n1 = n3.next;
			n3.next = null;
			while (n1 != null) {
				n2 = n1.next;
				n1.next = n3;
				n3 = n1;
				n1 = n2;
			}
			return res;
		}
	}
}
