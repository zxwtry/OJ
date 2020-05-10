package leetcode

import "fmt"

func L002_01_addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	sum := l1
	for l1 != nil || l2 != nil {
		val := 0
		if l1 != nil {
			val += l1.Val
			l1 = l1.Next
		}
		if l2 != nil {
			val += l2.Val
			l2 = l2.Next
		}

		if l1 != nil {
			l1.Val = val
			l1 = l1.Next
		} else {
			sum = l2
		}
		if l2 != nil {
			l2.Val = val
			l2 = l2.Next
		}
	}
	carry := 0
	fmt.Println(carry)
	fmt.Println(sum)
	return l1
}
