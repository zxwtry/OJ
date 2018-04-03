#coding=utf-8

'''
    url: leetcode.com/problems/search-for-a-range/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月4日
    @details:    Solution: 46ms 74.11%
'''

class Solution(object):
    #[i, j)
    def binarySearchFirstEqualOrLarger(self, n, i, j, t):
        j -= 1
        if n[j] < t: return j + 1
        while i < j:
            m = (i + j) // 2
            if n[m] >= t:
                j = m
            else:
                i = m + 1
        return i
    
    def searchRange(self, n, t):
        """
        :type n: List[int]
        :type t: int
        :rtype: List[int]
        """
        nn = 0 if n == None else len(n)
        if nn == 0: return [-1, -1]
        v1 = self.binarySearchFirstEqualOrLarger(n, 0, nn, t)
        v2 = self.binarySearchFirstEqualOrLarger(n, 0, nn, t+1)
        v2 -= 1
        if v1 < 0 or v1 >= nn or n[v1] != t or n[v2] != t:
            return [-1, -1]
        return [v1, v2]
        
if __name__ == "__main__":
    n = [1, 1]
    t = 1
    print(Solution().searchRange(n, t))
