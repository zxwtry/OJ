#coding=utf-8

'''
    url: leetcode.com/problems/reorder-list
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月31日
    @details:    Solution:  178ms 72.32%
'''

class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def reorderList(self, head):
        """
        :type head: ListNode
        :rtype: void Do not return anything, modify head in-place instead.
        """
        le, t, r = 0, head, head
        pr, n = None, None
        while t != None:
            le += 1
            t = t.next
            
        t = head
        for i in range((le - 1) // 2):
            t = t.next
        
#         print("t.val : " + str(t.val))
        
        r = t.next
        t.next = None
        while r != None:
            n = r.next
            
            r.next = pr
            pr = r
            
            r = n
        
        r = pr
        t = head
        while r != None:
            pr = r.next
            n = t.next
            
            r.next = t.next
            t.next = r
            
            r = pr
            t = n
        


if __name__ == "__main__":
    l0 = ListNode(0)
    l1 = ListNode(1)
    l2 = ListNode(2)
    l3 = ListNode(3)
    l0.next = l1
    l1.next = l2
    l2.next = l3
    
    Solution().reorderList(l0)
    
    l = l0
    
    while l != None:
        print(str(l.val) + " ")
        l = l.next