#coding=utf-8

'''
    url: leetcode.com/problems/valid-parentheses/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月29日
    @details:    Solution: 55ms 38.66%
'''

class Solution(object):
    def isValid(self, s):
        sn, v = 0 if s == None else len(s), []
        if sn == 0: return True
        for i in range(sn):
            if s[i] in {'(', '[', '{'}:
                v.append(s[i])
            else:
                if len(v) == 0:
                    return False
                c = v.pop()
                if not c+s[i] in {"()", "[]", "{}"}:
                    return False
        return True

if __name__ == "__main__":
    s = "(]"
    sol = Solution()
    print(sol.isValid(s))    