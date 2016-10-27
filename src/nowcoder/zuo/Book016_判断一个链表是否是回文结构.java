package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

public class Book016_判断一个链表是否是回文结构 {
	public static void main(String[] args) {
		testSolution();
	}
	private static void testSolution() {
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
}
