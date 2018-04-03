#coding=utf-8

'''
    url: leetcode.com/problems/longest-valid-parentheses
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月3日
    @details:    Solution: 145ms 13.54%
'''

class Solution(object):
    def longestValidParentheses(self, s):
        """
        :type s: str
        :rtype: int
        """
        sn = 0 if s == None else len(s)
        if sn == 0: return 0
        stk, ans, cnt = [], 0, 0
        m = [False] * sn
        for i in range(sn):
            if s[i] == '(':
                stk.append(i)
            elif len(stk) != 0:
                m[i] = True
                m[stk.pop()] = True
        for i in range(sn):
            cnt = cnt + 1 if m[i] else 0
            ans = max(ans, cnt)
        return ans

if __name__ == "__main__":
    sol = Solution()
    print(sol.longestValidParentheses(")()())"))