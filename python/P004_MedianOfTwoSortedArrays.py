#coding=utf-8

'''
    url: leetcode.com/problems/median-of-two-sorted-arrays/haracters/
    sorted-array找两个情况太多，采用先找一个的方法
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月25日
    @details:    Solution: AC 105ms 73.21%
'''

class Solution(object):
    def findKth(self, nums1, l1, r1, nums2, l2, r2, k):
        if r1-l1 > r2-l2: 
            return self.findKth(nums2, l2, r2, nums1, l1, r1, k)
        if l1 >= r1: 
            return nums2[l2 + k]
        if k == 0: 
            return min(nums1[l1], nums2[l2])
        k_2 = min((k + 1) // 2, r1-l1)
        if nums1[l1 + k_2 - 1] < nums2[l2 + k_2 - 1]:
            return self.findKth(nums1, l1 + k_2, r1, nums2, l2, r2, k - k_2)
        else:
            return self.findKth(nums1, l1, r1, nums2, l2 + k_2, r2, k - k_2)
        
    def findMedianSortedArrays(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: float
        """
        l1, l2 = len(nums1), len(nums2)
        if (l1+l2) % 2 == 0: 
            return (self.findKth(nums1, 0, l1, nums2, 0, l2, (l1 + l2) // 2) + 
                    self.findKth(nums1, 0, l1, nums2, 0, l2, (l1 + l2) // 2 - 1)) / 2.0
        else: 
            return self.findKth(nums1, 0, l1, nums2, 0, l2, (l1 + l2) // 2)

if __name__ == "__main__":
    nums1 = [1, 2]
    nums2 = [3, 4]
    s = Solution()
    print(s.findMedianSortedArrays(nums1, nums2))
        