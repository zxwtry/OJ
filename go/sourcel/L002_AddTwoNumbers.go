package sourcel

import (
	"fmt"
)

/**
 * User:  zxwtry
 * Date:  2018/4/2
 * Time:  11:48
 */

type ListNode struct {
	Val  int
	Next *ListNode
}

func l002A_addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	if l1 == nil {
		return l2
	}
	if l2 == nil {
		return l1
	}
	ans := l1
	for l2 != nil || l1 != nil {
		if l2 != nil {
			l1.Val += l2.Val
			l2 = l2.Next
		}
		addNode(l1)
		if l1.Next == nil && l2 != nil {
			l1.Next = &ListNode{0, nil}
		}
		l1 = l1.Next
	}
	return ans
}

func addNode(l *ListNode) {
	if l.Val < 10 {
		return
	}
	if l.Next == nil {
		l.Next = &ListNode{l.Val / 10, nil}
		l.Val %= 10
	} else {
		l.Next.Val += l.Val / 10
		l.Val %= 10
	}
}

func l002_Con(array []int) *ListNode {
	arrayLen := len(array)
	var now *ListNode
	var pre *ListNode
	pre = nil
	for i := arrayLen - 1; i > -1; i-- {
		now = &ListNode{array[i], pre}
		pre = now
	}
	return now
}

func l002_Print(l *ListNode) {
	arr := make([]int, 0, 10)
	for l != nil {
		arr = append(arr, l.Val)
		l = l.Next
	}
	fmt.Println(arr)
}

func L002_Main() {
	// (2 -> 4 -> 3) + (5 -> 6 -> 4)
	arr1 := []int{2}
	l1 := l002_Con(arr1)
	arr2 := []int{5, 6, 4}
	l2 := l002_Con(arr2)
	l3 := l002A_addTwoNumbers(l1, l2)
	l002_Print(l3)
}
