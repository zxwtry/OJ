#coding=utf-8

'''
    url: leetcode.com/problems/merge-k-sorted-lists/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月29日
    @details:    Solution: 302ms 11.23%
'''

from leetcode.Utils import *

class Solution(object):
    def cmp(self, a, b):
        if a == None:
            return 1
        elif b == None:
            return -1
        return a.val - b.val
    
    def swap(self, a, i, j):
        t = a[i]
        a[i] = a[j]
        a[j] = t
    
    def mergeKLists(self, ls):
        """
        :type lists: List[ListNode]
        :rtype: ListNode
        """
        lsn = 0 if ls == None else len(ls)
        if (lsn == 0): return []
        heap, a, hi = [None] * lsn, None, 0
        for i in range(lsn):
            if ls[i] != None:
                heap[hi] = ls[i]
                hi += 1
        if hi == 0: return a
        for i in range((lsn - 1) // 2, -1, -1):
            p = i
            c = 2 * p + 1
            while c < hi:
                if c + 1 < hi and self.cmp(heap[c + 1], heap[c]) < 0:
                    c += 1
                if self.cmp(heap[p], heap[c]) > 0:
                    self.swap(heap, p, c)
                else: break
                p = c
                c = 2 * p + 1
        head = ListNode(0)
        travel = head
        while heap[0] != None:
            temp = ListNode(heap[0].val)
            travel.next = temp
            travel = temp
            if heap[0].next == None:
                heap[0] = heap[hi - 1]
                hi -= 1
                if hi == 0: break
            else:
                heap[0].val = heap[0].next.val
                heap[0].next = heap[0].next.next
            p = 0
            c = 2 * p + 1
            while c < hi:
                if c + 1 < hi and self.cmp(heap[c + 1], heap[c]) < 0:
                    c += 1
                if self.cmp(heap[p], heap[c]) > 0:
                    self.swap(heap, p, c)
                else: break
                p = c
                c = 2 * p + 1
        return head.next
    
if __name__ == "__main__":
    ls = [convertArrayToListNode([1, 4, 9, 13]), 
          convertArrayToListNode([2, 6, 10, 14]),
          convertArrayToListNode([3, 7, 11, 15]),
          convertArrayToListNode([4, 8, 12, 16])]
    a = Solution().mergeKLists(ls)
    printListNode(a)
        
        
        
        
        