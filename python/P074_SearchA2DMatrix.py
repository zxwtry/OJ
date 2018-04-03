#coding=utf-8

'''
    url: leetcode.com/problems/search-a-2d-matrix/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月17日
    @details:    Solution: 55ms 36.01%
'''

class Solution(object):
    def search(self, m, ri, ci, rj, cj, rn, cn, t):
        rm, cm = (ri + rj) // 2, (ci + cj) // 2
        if rm < 0 or rm >= rn: return False
        if cm < 0 or cm >= cn: return False
        if m[rm][cm] == t: return True
        if ri > rj or ci > cj: return False
        if m[rm][cm] > t:
            return self.search(m, ri, ci, rm-1, cj, rn, cn, t) \
                or self.search(m, rm, ci, rj, cm-1, rn, cn, t)
        return self.search(m, ri, cm+1, rj, cj, rn, cn, t) \
            or self.search(m, rm+1, ci, rj, cm, rn, cn, t)
            
    def searchMatrix(self, m, t):
        """
        :type m: List[List[int]]
        :type t: int
        :rtype: bool
        """
        if m == None or len(m) == 0: return False
        if m[0] == None or len(m[0]) == 0: return False
        rn, cn = len(m), len(m[0])
        return self.search(m, 0, 0, rn-1, cn-1, rn, cn, t)
        
