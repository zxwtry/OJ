#coding=utf-8

'''
    url: leetcode.com/problems/swap-nodes-in-pairs/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月31日
    @details:    Solution: 52ms 31.83%
'''

from leetcode.Utils import *

class Solution(object):
    def swapPairs(self, head):
        if head == None: return head
        ans = head.next if head.next != None else head
        t0, t1, t2, t3 = None, head, head.next, None if \
        head.next == None else head.next.next
        while t2 != None:
            t2.next = t1
            t1.next = t3
            if t0 != None:
                t0.next = t2
            t0, t1, t2 = t1, t3, None if t3 == None else t3.next
            t3 = None if t2 == None else t2.next
        return ans

if __name__ == "__main__":
    head = convertArrayToListNode([1, 2, 3, 4, 5, 6, 7, 8])
    sol = Solution()
    printListNode(sol.swapPairs(head))