#coding=utf-8

'''
    url: leetcode.com/problems/jump-game-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月7日
    @details:    Solution: 65ms 68.96%
'''

class Solution(object):
    def jump(self, n):
        """
        :type n: List[int]
        :rtype: int
        """
        nn = 0 if n == None else len(n)
        ans, mti, cur = 0, 0, 0
        for i in range(nn):
            if cur < i:
                ans += 1
                cur = mti
                if cur>=nn-1:break
            mti=max(mti,i+n[i])
        return ans

if __name__ == "__main__":
    n=[2,3,1,1,4]
    print(Solution().jump(n))