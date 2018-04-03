#coding=utf-8

'''
    url: leetcode.com/problems/maximal-rectangle
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月21日
    @details:    Solution: 155ms 64.34%
'''

class Solution(object):
    def count(self, t, tn):
        s, sn, i, a = [], 0, 0, 0
        while i <= tn:
            tv = 0 if i == tn else t[i]
            if sn == 0 or t[s[sn-1]] <= tv:
                s.append(i)
                sn, i = sn+1, i+1
            else:
                j, sn = s.pop(), sn-1
                l = i if sn == 0 else i-1-s[sn-1]
                a = max(a, t[j] * l)
        return a
                
    def maximalRectangle(self, m):
        """
        :type m: List[List[str]]
        :rtype: int
        """
        rn = 0 if m == None else len(m)
        if rn == 0: return 0
        cn = 0 if m[0] == None else len(m[0])
        if cn == 0: return 0
        t, a = [0] * cn, 0
        for i in range(rn):
            for j in range(cn):
                if m[i][j] == '1': t[j] += 1
                else: t[j] = 0
            a = max(a, self.count(t, cn))
        return a

if __name__ == "__main__":
    m = [
            ['1','0','1','0','0'],
            ['1','0','1','1','1'],
            ['1','0','1','1','1'],
            ['1','1','1','1','1'],
            ['1','1','1','1','1'],
            ['1','0','0','1','0'],
        ]
    print(Solution().maximalRectangle(m))
