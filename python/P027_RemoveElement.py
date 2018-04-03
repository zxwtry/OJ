#coding=utf-8

'''
    url: leetcode.com/problems/remove-element/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月31日
    @details:    Solution: 55ms 34.34%
'''

class Solution(object):
    def removeElement(self, nums, val):
        """
        :type nums: List[int]
        :type val: int
        :rtype: int
        """
        i = 0
        for j in range(len(nums)):
            if nums[j] != val:
                nums[i] = nums[j]
                i += 1
        return i

if __name__ == "__main__":
    nums = [3, 2, 2, 3]
    v = 3
    sol = Solution()
    n = sol.removeElement(nums, v)
    val = []
    for i in range(n):
        val.append(nums[i])
    print(" %d" * n % tuple(val))