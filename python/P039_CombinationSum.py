#coding=utf-8

'''
    url: leetcode.com/problems/combination-sum/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月5日
    @details:    Solution: 245ms 14.70%
'''

class Solution(object):
    def search(self, c, ci, t, ans, rec, ri):
        if t == 0:
            ans_t = []
            for i in range(ri):
                ans_t.append(rec[i])
            ans.append(ans_t)
            return
        elif t < 0:return
        if ci == len(c):
            return
        for j in range((t // c[ci]) + 1):
            for i in range(j):
                rec[ri+i]=c[ci]
            self.search(c, ci+1, t-j*c[ci], ans, rec, ri+j)
    
    def combinationSum(self, c, t):
        """
        :type c: List[int]
        :type t: int
        :rtype: List[List[int]]
        """
        ans,rec=[],[0]*(t//min(c)+1)
        self.search(c, 0, t, ans, rec, 0)
        return ans
    
if __name__ == "__main__":
    c, t = [2, 3, 6, 7], 7
    ans = Solution().combinationSum(c, t)
    print(ans)
    