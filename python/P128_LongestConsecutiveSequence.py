#coding=utf-8

'''
    url: leetcode.com/problems/longest-consecutive-sequence
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月12日
    @details:    Solution:  95ms 10.93%
'''

class Solution(object):
    def longestConsecutive(self, n):
        """
        :type n: List[int]
        :rtype: int
        """
        nn = 0 if n == None else len(n)
        if nn == 0: return 0
        m, ans = {}, 0
        for v in n:
            if not v in m:
                l = m[v-1] if v-1 in m else 0
                r = m[v+1] if v+1 in m else 0
                s = l + r + 1
                ans = max(ans, s)
                m[v] = s
                m[v-l] = s
                m[v+r] = s
        return ans
    
if __name__ == "__main__":
    n = [1, 2, 3, 4]
    print(Solution().longestConsecutive(n))