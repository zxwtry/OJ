#encoding=utf-8

'''
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
Example:
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
'''

'''
Solution1: AC 426 ms 55.36%    --- 使用HashMap完成
'''

class Solution1(object):
    def twoSum(self, nums, target):
        if len(nums) < 2:
            return False
        numsDict = {}
        for index in range(len(nums)):
            if nums[index] in numsDict:
                return [numsDict[nums[index]], index]
            else:
                numsDict[target - nums[index]] = index
