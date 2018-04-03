#coding=utf-8

'''
    url: leetcode.com/problems/insert-interval
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月11日
    @details:    Solution: 102ms 26.38%
'''

class Interval(object):
    def __init__(self, s=0, e=0):
        self.start = s
        self.end = e

def print_Interval(i):
    for j in range(len(i)):
        print("%d: %d\t%d" % (j, i[j].start, i[j].end))

class Solution(object):
    def cmp(self, n, ni, nn, val):
        if ni % 2 == 0:
            n1 = None if ni//2 == 0 else n[ni//2-1]
            n2 = None if ni//2 == nn else n[ni//2]
            if n1 != None and n1.end >= val: return -1;
            if n2 != None and n2.start <= val: return 1;
            return 0
        else:
            n1 = n[ni//2]
            if val < n1.start: return -1
            if val > n1.end: return 1
            return 0
    def state(self, n, nn, val):
        i, j = 0, 2*nn
        while i < j:
            m = i + (j - i) // 2
            cmp_val = self.cmp(n, m, nn, val)
            if cmp_val == 0: return m
            if cmp_val < 0: j=m-1
            else: i = m+1
        return i
    
    def insert(self, n, new_n):
        """
        :type n: List[Interval]
        :type new_n: Interval
        :rtype: List[Interval]
        """
        nn = 0 if n == None else len(n)
        i = self.state(n, nn, new_n.start)
        j = self.state(n, nn, new_n.end)
        if i == j:
            if i%2 == 0:
                n.insert(i//2, new_n)
            return n
        r1, r2 = i//2-1, (j+1)//2
        if i%2==1: new_n.start = n[i//2].start
        if j%2==1: new_n.end = n[j//2].end
        for k in range(r1+1, r2):
            n.remove(n[r1+1])
        n.insert(r1+1, new_n)
        return n

if __name__ == "__main__":
    i0 = Interval(1,2)
    i1 = Interval(3,5)
    i2 = Interval(6,7)
    i3 = Interval(8,10)
    i4 = Interval(12,16)
#     i5 = Interval(1, 3)
    i = [i0, i1, i2, i3, i4]
#     i = [i0, i1]
    new_n = Interval(4,9)
    print_Interval(Solution().insert(i, new_n))