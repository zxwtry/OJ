#coding=utf-8

'''
    url: leetcode.com/problems/convert-sorted-list-to-binary-search-tree
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月29日
    @details:    Solution: 256ms 86.37%
'''

class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def search(self, n, ni, nj):
        if ni > nj: return None
        nm = (ni+nj) // 2
        root = TreeNode(n[nm])
        root.left = self.search(n, ni, nm-1)
        root.right = self.search(n, nm+1, nj)
        return root
    
    def sortedListToBST(self, h):
        """
        :type h: ListNode
        :rtype: TreeNode
        """
        n, l = h, []
        while n != None: 
            l.append(n.val)
            n = n.next
        return self.search(l, 0, len(l)-1)