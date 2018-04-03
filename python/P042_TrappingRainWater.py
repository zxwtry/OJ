#coding=utf-8

'''
    url: leetcode.com/problems/trapping-rain-water
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月6日
    @details:    Solution: 65ms 42.44%
'''

class Solution(object):
    def trap(self, h):
        """
        :type h: List[int]
        :rtype: int
        """
        hn = 0 if h == None else len(h)
        if hn == 0: return 0
        mi, m1, m2, ans = 0, h[0], h[hn-1], 0
        for i in range(1, hn):
            if h[i] > h[mi]:
                mi = i
        for k in range(1, mi):
            ans += max(m1 - h[k], 0)
            m1 = max(h[k], m1)
        for k in range(hn-2, mi, -1):
            ans += max(m2 - h[k], 0)
            m2 = max(h[k], m2)
        return ans
    
if __name__ == "__main__":
    h=[0,1,0,2,1,0,1,3,2,1,2,1]
    print(Solution().trap(h))
            