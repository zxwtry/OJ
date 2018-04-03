#coding=utf-8

'''
    url: leetcode.com/problems/decode-ways
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月23日
    @details:    Solution: 52ms 60.05%
'''

class Solution(object):
    def numDecodings(self, s):
        """
        :type s: str
        :rtype: int
        """
        sn = 0 if s == None else len(s)
        if sn == 0 or s[0] == '0': return 0
        v1, a1, a2 = int(s[0]), 1, 1
        for i in range(1, sn):
            v0 = int(s[i])
            if v0 == 0:
                if v1 in {1, 2}:
                    a2, a1=a1, a2
                else: return 0
            elif v1 == 1 or (v1==2 and v0<7):
                a2, a1 = a1, a1+a2
            else: a2, a1 = a1, a1
            v1 = v0
        return a1
    

if __name__ == "__main__":
    m = [
            '0',
            '1',
            '9',
            '10',
            '11',
            '19',
            '20',
            '21',
            '29',
            '30',
            '88',
        ]
    for s in m:
        print("%s \t %d" % (s, Solution().numDecodings(s)))