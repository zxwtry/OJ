#coding=utf-8

'''
    url: leetcode.com/problems/largest-rectangle-in-histogram
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月20日
    @details:    Solution: 102ms 31.41%
'''

class Solution(object):
    def largestRectangleArea(self, h):
        """
        :type h: List[int]
        :rtype: int
        """
        hn = 0 if h == None else len(h)
        if hn == 0: return 0
        s, sn, a, hi = [], 0, 0, 0
        while hi < hn+1:
            hv = 0 if hi == hn else h[hi]
            if sn == 0 or h[s[sn-1]] <= hv:
                s.append(hi)
                sn += 1
            else:
                i = s.pop()
                sn -= 1
                l = hi if sn == 0 else hi-1-s[sn-1]
                a = max(a, h[i]*l)
                hi -= 1
            hi += 1
        return a
                
        
if __name__ == "__main__":
    i = [
            [1,5,6,2,3],
            [9,6,3],
            [6,6,6],
            [8],
            [9,4,2,9,9],
            []
        ]
    for h in i:
        print(Solution().largestRectangleArea(h))