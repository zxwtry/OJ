#coding=utf-8

'''
    url: leetcode.com/problems/triangle
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月4日
    @details:    Solution:  68ms 26.43%
'''

class Solution(object):
    def minimumTotal(self, t):
        """
        :type t: List[List[int]]
        :rtype: int
        """
        tn = 0 if t == None else len(t)
        if tn == 0: return 0
        m = [0]*tn
        m[0] = t[0][0]
        for i in range(1, tn):
            for j in range(i, -1, -1):
                m[j] = t[i][j] + min(m[j-1] if j == i else m[j], m[j] if j==0 else m[j-1])
        return min(m)
    
if __name__ == "__main__":
    t = [[-1],[2,3],[1,-1,-3]]
    print(Solution().minimumTotal(t))