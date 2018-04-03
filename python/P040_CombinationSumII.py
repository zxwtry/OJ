#coding=utf-8

'''
    url: leetcode.com/problems/combination-sum-ii/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月6日
    @details:    Solution: 286ms 16.23%
'''

class Solution(object):
    def search(self, ans, s, si, c, ci, cn, t, sign):
        if t == 0 and ci == cn:
            ans_t=[0]*si
            for i in range(si):
                ans_t[i] = s[i]
            ans.append(ans_t)
            return
        if t < 0 or ci == cn:
            return
        if not(sign and c[ci - 1] == c[ci]):
            self.search(ans, s, si, c, ci+1, cn, t, False)
        s[si] = c[ci]
        self.search(ans, s, si+1, c, ci+1, cn, t-c[ci], True)
        
                
    def combinationSum2(self, c, t):
        """
        :type c: List[int]
        :type t: int
        :rtype: List[List[int]]
        """
        cn = 0 if c == None else len(c)
        if cn == 0: return []
        c.sort(key=None, reverse=False)
        s, si, ans=[0]*cn, 0, []
        self.search(ans, s, si, c, 0, cn, t, False)
        return ans

if __name__ == "__main__":
    print(Solution().combinationSum2([1,1], 1))