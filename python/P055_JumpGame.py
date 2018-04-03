#coding=utf-8

'''
    url: leetcode.com/problems/jump-game
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月11日
    @details:    Solution: 45ms 46.70%
'''

class Solution(object):
    def canJump(self, n):
        """
        :type n: List[int]
        :rtype: bool
        """
        nn = 0 if n == None else len(n)
        mti = 0
        for i in range(nn):
            if i > mti: return False
            mti = max(mti, i+n[i])
        return True
    
if __name__ == "__main__":
    n = [2,3,1,1,4]
    n = [3,2,1,0,4]
    
    print(Solution().canJump(n))