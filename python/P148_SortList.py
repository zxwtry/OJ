# coding = utf8

"""
Sort a linked list in O(n log n) time using constant space complexity.
"""

from util.ListNode import ListNode


#归并排序
class Solution(object):
    def sortList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        hn, t = 0, head
        while t is not None:
            hn += 1
            t = t.next
        return self.sort_split(head, hn)

    def sort_split(self, h, hn):
        if hn == 2:
            return self.swap_next(h)
        if hn == 1:
            return h
        if hn == 0:
            return None

        hi, m = 2, h

        while hi < hn:
            hi += 2
            m = m.next

        h2 = m.next
        m.next = None

        v1 = self.sort_split(h, (hn + 1) // 2)
        v2 = self.sort_split(h2, hn - ((hn + 1) // 2))

        return self.merge(v1, v2)

    def merge(self, h1, h2):
        if h1 is None:
            return h2
        if h2 is None:
            return h1
        if h1.val > h2.val:
            return self.merge(h2, h1)
        t1, t2, t = h1.next, h2, h1
        while not (t1 is None and t2 is None):
            if t1 is not None and t2 is not None:
                if t1.val < t2. val:
                    t.next = t1
                    t = t1
                    t1 = t1.next
                else:
                    t.next = t2
                    t = t2
                    t2 = t2.next
            elif t2 is not None:
                t.next = t2
                t = t2
                t2 = t2.next
            else:
                t.next = t1
                t = t1
                t1 = t1.next
        return h1

    def swap_next(self, h):
        nxt = h.next
        if h.val > nxt.val:
            nxt.next = h
            h.next = None
            return nxt
        else:
            return h


if __name__ == "__main__":
    arr1 = []
    node = ListNode().construct_array(arr1)
    ans = Solution().sort_list(node)
    ans.print_list(ans)
