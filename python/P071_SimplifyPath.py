#coding=utf-8

'''
    url: leetcode.com/problems/simplify-path/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月16日
    @details:    Solution: 65ms 36.88%
'''

class Solution(object):
    def simplifyPath(self, p):
        """
        :type p: str
        :rtype: str
        """
        ps = p.split("/")
        l = []
        for s in ps:
            if s == "": continue
            if s == ".": continue
            if s == "..": 
                if len(l) > 0: l.pop()
                continue
            l.append(s)
        if len(l) == 0:
            return "/"
        ans = ""
        for s in l:
            ans += "/"
            ans += s
        return ans

if __name__ == "__main__":
    p = "/"
    print(Solution().simplifyPath(p))