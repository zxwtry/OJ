#coding=utf-8

'''
    url: leetcode.com/problems/single-number
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年5月18日
    @details:    Solution:  89ms 68.38%
'''
from _operator import xor

class Solution(object):
    def singleNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        ans = 0
        for n in nums:
            ans = xor(ans, n)
        return ans;
    
if __name__ == "__main__":
    nums = [1, 2, 2, 1 ,4, 3, 3]
    print(Solution().singleNumber(nums))