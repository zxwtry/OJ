#coding=utf-8

'''
    url: leetcode.com/problems/subsets-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月22日
    @details:    Solution: 65ms 71.66%
'''

class Solution(object):
    def search(self, n, ni, nn, r, ri, rn, iu, a):
        if ni == nn and ri == rn:
            a.append(list(r))
        if ni >= nn or ri > rn: return
        if not (ni != 0 and iu and n[ni-1]==n[ni]):
            self.search(n, ni+1, nn, r, ri, rn, False, a)
        if ri < rn: r[ri] = n[ni]
        self.search(n, ni+1, nn, r, ri+1, rn, True, a)
        
    def subsetsWithDup(self, n):
        """
        :type n: List[int]
        :rtype: List[List[int]]
        """
        nn = 0 if n == None else len(n)
        if nn == 0: return []
        n.sort(key=None, reverse=False)
        a = []
        for rn in range(nn+1):
            r = [0]*rn
            self.search(n, 0, nn, r, 0, rn, False, a)
        return a

if __name__ == "__main__":
    a = Solution().subsetsWithDup([1,2,2,2])
    print(a)
        