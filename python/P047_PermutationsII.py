#coding=utf-8

'''
    url: leetcode.com/problems/permutations-ii
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月8日
    @details:    Solution: 119ms 63.51%
'''

class Solution(object):
    def isDuplicate(self, n, i, j):
        for k in range(i, j):
            if (n[k] == n[j]):
                return True
        return False
    
    def swap(self, n, i, j):
        t = n[i]
        n[i] = n[j]
        n[j] = t
        
    def search(self, n, ans, i, nn):
        if i == nn:
            ans.append(list(n))
        for j in range(i, nn):
            if not self.isDuplicate(n, i, j):
                self.swap(n, i, j)
                self.search(n, ans, i+1, nn)
                self.swap(n, i, j)
            
    def permuteUnique(self, n):
        """
        :type n: List[int]
        :rtype: List[List[int]]
        """
        nn , ans = 0 if n == None else len(n), []
        if nn == 0: return ans
        self.search(n, ans, 0, nn);
        return ans

if __name__ == "__main__":
    n = [1,1,1]
    ans = Solution().permuteUnique(n)
    print(ans)