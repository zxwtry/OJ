#coding=utf-8

'''
    url: leetcode.com/problems/next-permutation/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月1日
    @details:    Solution: 95ms 11.05%
'''

class Solution(object):
    #[i, j)
    def decreaseFirstLarger(self, n, i, j, v):
        j -= 1
        while i < j:
            m = (i + j + 1) // 2
            if n[m] > v:
                i = m
            else:
                j = m - 1
        return j
    
    def swap(self, n, i, j):
        t = n[i]
        n[i] = n[j]
        n[j] = t
    
    #[i, j)
    def reverse(self, n, i, j):
        j -= 1
        while i < j:
            self.swap(n, i, j)
            i += 1
            j -= 1
    
    def nextPermutation(self, n):
        """
        :type n: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        nn = 0 if n == None else len(n)
        #return short n
        if nn < 2: return
        #find decrease sub list [i, nn - 1]
        i = nn - 1
        while i > 0 and n[i - 1] >= n[i]:
            i -= 1
        #[0, nn - 1] is decrease sub list
        if i == 0:
            n.reverse()
            return
        self.swap(n, i - 1, self.decreaseFirstLarger(n, i, nn, n[i - 1]))
        self.reverse(n, i, nn)

if __name__ == "__main__":
    n = [2, 5, 1, 1]
    Solution().nextPermutation(n)
    print(" %d" * len(n) % tuple(n))
        