#coding=utf-8

'''
    url: leetcode.com/problems/clone-graph
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月15日
    @details:    Solution:  99ms 59.52%
'''

class UndirectedGraphNode:
    def __init__(self, x):
        self.label = x
        self.neighbors = []

class Solution:
    # @param node, a undirected graph node
    # @return a undirected graph node
    def search(self, n, m):
        if n == None: return n
        if n in m: return m[n]
        ng = UndirectedGraphNode(n.label)
        m[n] = ng
        for nn in n.neighbors:
            ng.neighbors.append(self.search(nn, m))
        return ng
        
    def cloneGraph(self, n):
        m = {}
        return self.search(n, m)