#coding=utf-8

'''
    url: leetcode.com/problems/add-binary/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月15日
    @details:    Solution: 68ms 40.59%
'''

from jinja2._compat import unichr

class Solution(object):
    def addBinary(self, a, b):
        """
        :type a: str
        :type b: str
        :rtype: str
        """
        an = 0 if a == None else len(a)
        bn = 0 if b == None else len(b)
        if an == 0 or bn == 0:
            return a if bn == 0 else b
        c, ai , bi, ans = 0, an-1, bn - 1, []
        while ai > -1 and bi > -1:
            c += (int(a[ai]) + int(b[bi]))
            ans.append(unichr(ord('0')+(c%2)))
            c = c // 2
            ai, bi = ai-1, bi-1
        while ai > -1:
            c += (int(a[ai]))
            ans.append(unichr(ord('0')+(c%2)))
            c = c // 2
            ai -= 1
        while bi > -1:
            c += (int(b[bi]))
            ans.append(unichr(ord('0')+(c%2)))
            c = c // 2
            bi -= 1
        if c != 0:
            ans.append(unichr(ord('0')+(c%2)))
        while len(ans) != 1 and ans[len(ans)-1] == '0': ans.pop()
        ans.reverse()
        return "".join(ans)

if __name__ == "__main__":
    print(Solution().addBinary("0", "0"))
        