#coding=utf-8

'''
    url: leetcode.com/problems/remove-duplicates-from-sorted-list-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月20日
    @details:    Solution: 58ms 81.52%
'''

class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None
    def __repr__(self, *args, **kwargs):
        return str(self.val)

def construct(l):
    ln = len(l)
    ls = [None] * ln
    for i in range(ln-1, -1, -1):
        ls[i] = ListNode(l[i])
        if i != ln-1:
            ls[i].next = ls[i+1]
    return ls[0]
    
def print_list_node(l):
    print("==================")
    while l != None:
        print(l.val)
        l = l.next
    print("==================")
    
class Solution(object):
    def deleteDuplicates(self, h):
        """
        :type h: ListNode
        :rtype: ListNode
        """
        if h == None: return None
        t, p1, a, p2 = h, None, h, None
        while t != None:
            if p1 == None:
                p1 = t
            elif p1.val != t.val:
                if p1.next != t:
                    if p2 == None: a = t
                    else: p2.next = t
                else: p2 = p1
                p1 = t
            t = t.next
        if p1 != None and p1.next != None:
            if p2 != None: p2.next = None
            else: a = None
        return a

if __name__ == "__main__":
    #test case:
    #[1, 2, 2, 3]
    #[1, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 6]
    #[2, 2, 3, 3, 4, 5, 5, 6, 6, 6, 7]
    #[1, 2, 2, 2, 2]
    #[2, 2, 3, 3, 4, 5, 5]
    #[2, 2, 3, 3, 4, 4, 5, 5]
    h = construct([2, 2, 3, 3, 4, 4, 5, 5])
    print_list_node(Solution().deleteDuplicates(h))