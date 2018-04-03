#coding=utf-8
'''
    url: leetcode.com/problems/remove-nth-node-from-end-of-list/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月29日
    @details:    Solution: 75ms 10.07%
'''

from leetcode.Utils import *

class Solution(object):
    def removeNthFromEnd(self, head, n):
        """
        :type head: ListNode
        :type n: int
        :rtype: ListNode
        """
        f, t = head, head
        for i in range(n):
            if f == None:
                return head
            else:
                f = f.next
        if f == None:
            return head.next
        while f.next != None:
            t = t.next
            f = f.next
        t.next = None if t.next == None else t.next.next
        return head 

if __name__ == "__main__":
    head = convertArrayToListNode([1, 2, 3, 4, 5, 6])
    ans = Solution().removeNthFromEnd(head, 3)
    printListNode(ans)
    