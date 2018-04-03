#coding=utf-8

'''
    url: leetcode.com/problems/generate-parentheses/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月29日
    @details:    Solution: 56ms 52.31%
'''

class Solution(object):
    def search(self, l, r, n, t, ti, a):
        if ti == 2 * n:
            a.append("".join(t))
        if l < n:
            t[ti] = '('
            self.search(l + 1, r, n, t, ti + 1, a)
        if l > r and r < n:
            t[ti] = ')'
            self.search(l, r + 1, n, t, ti + 1, a)
            
    def generateParenthesis(self, n):
        t, a = [""] * (2 * n), []
        self.search(0, 0, n, t, 0, a)
        return a        
        
if __name__ == "__main__":
    a = Solution().generateParenthesis(2)
    print(a)