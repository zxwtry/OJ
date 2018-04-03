#coding=utf-8

'''
    url: leetcode.com/problems/letter-combinations-of-a-phone-number/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月28日
    @details:    Solution: AC 45ms 60.78%
'''

class Solution(object):
    def search(self, a, s, t, d, di, dn, p):
        if di == dn:
            a.append("".join(p))
        else:
            v = int(d[di]) - 2
            for i in range(t[v]):
                p[di] = s[v][i]
                self.search(a, s, t, d, di + 1, dn, p)
    def letterCombinations(self, d):
        """
        :type d: str
        :rtype: List[str]
        """
        a = []
        dn = 0 if d == None else len(d)
        s = ['abc', 'def', 'ghi', 'jkl', 'mno', 'pqrs', 'tuv', 'wxyz']
        t = [3, 3, 3, 3, 3, 4, 3, 4]
        p = ['a'] * dn
        self.search(a, s, t, d, 0, dn, p)
        return a

if __name__ == "__main__":
    d = '89'
    print(len(Solution().letterCombinations(d)))