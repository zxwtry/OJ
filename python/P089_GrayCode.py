#coding=utf-8

'''
    url: leetcode.com/problems/gray-code
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月22日
    @details:    Solution: 62ms 26.96%
'''

class Solution(object):
    def grayCode(self, n):
        """
        :type n: int
        :rtype: List[int]
        """
        s, r, v, a = [0]*n, [0]*n, 2, []
        for i in range(n-1, -1, -1):
            s[i] = v
            r[i] = -v / 2
            v *= 2
        for v in range(1 << n):
            val = 0
            for i in range(n):
                val = val * 2 + (0 if r[i]<0 else 1)
            a.append(val)
            for i in range(n):
                if r[i] > 0:
                    r[i] -= 1
                    if r[i] == 0: r[i] = -s[i]
                else:
                    r[i] += 1
                    if r[i] == 0: r[i] = s[i]
        return a

if __name__ == "__main__":
    print(Solution().grayCode(3))    
        
        