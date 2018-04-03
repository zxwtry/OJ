#coding=utf-8

'''
    url: leetcode.com/problems/remove-duplicates-from-sorted-array/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月31日
    @details:    Solution: 115ms 34.01%
'''

class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        i = 0
        for j in range(len(nums)):
            if j == 0 or nums[j - 1] != nums[j]:
                nums[i] = nums[j]
                i += 1
        return i

if __name__ == "__main__":
    nums = [1, 1, 1, 1]       
    sol = Solution()
    n = sol.removeDuplicates(nums)
    val = []
    for i in range(n):
        val.append(nums[i])
    print(" %d" * n % tuple(val))