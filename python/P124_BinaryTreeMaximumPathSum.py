#coding=utf-8

'''
    url: leetcode.com/problems/binary-tree-maximum-path-sum
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月10日
    @details:    Solution:  222ms 16.58%
'''

class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def search(self, n, ans):
        if n == None: return 0
        lv = max(self.search(n.left, ans), 0)
        rv = max(self.search(n.right, ans), 0)
        ans[0] = max(ans[0], lv+rv+n.val)
        return max(lv, rv) + n.val
        
    def maxPathSum(self, n):
        """
        :type n: TreeNode
        :rtype: int
        """
        ans = [-(1 << 31)]
        self.search(n, ans)
        return 0 if n == None else ans[0]
    
if __name__ == "__main__":
    t0 = TreeNode(-1)
    print(Solution().maxPathSum(t0))
    print(Solution().maxPathSum(None))