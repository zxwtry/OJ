#coding=utf-8

'''
    url: leetcode.com/problems/reverse-nodes-in-k-group/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月31日
    @details:    Solution: 78ms 60.31%
'''

from leetcode.Utils import *

class Solution(object):
    def reverseKGroup(self, head, k):
        """
        :type head: ListNode
        :type k: int
        :rtype: ListNode
        """
        if k < 2: return head
        ans, t0, t3 = head, None, head
        while True:
            t1, t2 = t3, t3
            for i in range(k - 1):
                t2 = None if t2 == None else t2.next
            if t2 == None:
                if t0 != None: t0.next = t1
                break
            if t0 == None: ans = t2
            else: t0.next = t2
            r0, r1 = t0, t1
            t3, t0 = t2.next, t1
            while r1 != t3:
                r2 = r1.next
                r1.next = r0
                r0, r1 = r1, r2
        return ans
            
            
if __name__ == "__main__":
    head = convertArrayToListNode([1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
    k = 4
    sol = Solution()
    printListNode(sol.reverseKGroup(head, k))