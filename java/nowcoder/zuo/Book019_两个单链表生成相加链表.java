package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

public class Book019_两个单链表生成相加链表 {
	public static void main(String[] args) {
		testSolution();
	}
	static void testSolution() {
		for (int times = 0; times < 1000; times ++) {
			int val1 = (int)(Math.random() * Integer.MAX_VALUE);
			int val2 = (int)(Math.random() * Integer.MAX_VALUE);
			int[] arr1 = generateIntArray(val1);
			int[] arr2 = generateIntArray(val2);
			ListNode add1 = tools.ListNode辅助.A_一维生成器(arr1);
			ListNode add2 = tools.ListNode辅助.A_一维生成器(arr2);
			Solution s = new Solution();
			ListNode ans = s.addTwoListNode(add1, add2);
	//		tools.ListNode辅助.B_打印链表_一行(ans, 1000);
			long standardAnswer = (long)val1 + val2;
			long solutionAnswer = 0;
			while (ans != null) {
				solutionAnswer = solutionAnswer * 10 + ans.val;
				ans = ans.next;
			}
			
			if (standardAnswer != solutionAnswer) {
				System.out.println(val1);
				System.out.println(val2);
				System.out.println(standardAnswer +"..."+ solutionAnswer);
			}
		}
	}
	private static int[] generateIntArray(int val1) {
		int[] arr = new int[12];
		int index = 0;
		while (val1 != 0) {
			arr[index ++] = val1 % 10;
			val1 = val1 / 10;
		}
		int[] ans = new int[index];
		for (int i = 0; i < index; i ++) {
			ans[index - 1- i] = arr[i];
		}
		return ans;
	}
	/*
	 * 	9->3->7	代表整数937
	 * 	6->3	代表整数63
	 * 	相加得到1000
	 * 	返回1->0->0->0
	 */
	/*
	 * 	第一种方法，使用相加的方法
	 */
	static class Solution {
		public ListNode addTwoListNode(ListNode add1, ListNode add2) {
			int len1 = 0, len2 = 0;
			ListNode travel1 = add1;
			while (travel1 != null) {
				len1 ++;
				travel1 = travel1.next;
			}
			ListNode travel2 = add2;
			while (travel2 != null) {
				len2 ++;
				travel2 = travel2.next;
			}
			if (len1 > len2) {
				return addTwoListNode(add1, len1, add2, len2);
			} else {
				return addTwoListNode(add2, len2, add1, len1);
			}
		}
		private ListNode addTwoListNode(ListNode add1, int len1, ListNode add2, int len2) {
			ListNode travel1 = add1;
			int cut = len1 - len2;
			while (cut > 0) {
				travel1 = travel1.next;
				cut --;
			}
			ListNode addSeq1 = travel1;
			ListNode addSeq2 = add2;
			while (addSeq1 != null) {
				addSeq1.val += addSeq2.val;
				addSeq1 = addSeq1.next;
				addSeq2 = addSeq2.next;
			}
			//反转add1
			ListNode reverseFront = add1;
			ListNode reverseBehind = null;
			ListNode saveFront = null;
			ListNode saveBehind = null;
			while (reverseFront != null) {
				saveFront = reverseFront.next;
				saveBehind = reverseFront;
				
				reverseFront.next = reverseBehind;
				
				reverseFront = saveFront;
				reverseBehind = saveBehind;
			}
			ListNode reverseHead = reverseBehind;
			int carry = 0;
			while (true) {
				int sum = carry + reverseHead.val;
				reverseHead.val = sum % 10;
				carry = sum / 10;
				if (reverseHead.next == null) {
					break;
				} else {
					reverseHead = reverseHead.next;
				}
			}
			if (carry != 0) {
				ListNode newNode = new ListNode(carry);
				reverseHead.next = newNode;
			}
			ListNode newReverseFront = reverseBehind;
			ListNode newReverseBehind = null;
			while (newReverseFront != null) {
				saveFront = newReverseFront.next;
				saveBehind = newReverseFront;
				
				newReverseFront.next = newReverseBehind;
				
				newReverseFront = saveFront;
				newReverseBehind = saveBehind;
			}
			return newReverseBehind;
		}
	}
}
