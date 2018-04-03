#coding=utf-8

'''
    url: leetcode.com/problems/merge-two-sorted-lists/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月29日
    @details:    Solution: 85ms 10.23% 
'''

from leetcode.Utils import *

class Solution(object):
    def mergeTwoLists(self, l1, l2):
        if l1 == None or l2 == None:
            return  l2 if l1 == None else l1
        t1, t2, a, t = l1, l2, None, l2 if l1.val > l2.val else l1
        while t1 != None and t2 != None:
            if t1.val > t2.val:
                if a == None:
                    a = t2
                else:
                    a.next = t2
                    a = a.next
                t2 = t2.next
            else:
                if a == None:
                    a = t1
                else:
                    a.next = t1
                    a = a.next
                t1 = t1.next
        while t2 != None:
            a.next = t2
            t2 = t2.next
            a = a.next
        while t1 != None:
            a.next = t1
            t1 = t1.next
            a = a.next
        return t

if __name__ == "__main__":
    l1 = convertArrayToListNode([9])
    l2 = convertArrayToListNode([2, 4 ,6 ,8])
    a = Solution().mergeTwoLists(l1, l2)
    printListNode(a)
        