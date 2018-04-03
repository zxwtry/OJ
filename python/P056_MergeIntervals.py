#coding=utf-8

'''
    url: leetcode.com/problems/merge-intervals
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月11日
    @details:    Solution: 142ms 12.46%
'''



class Interval(object):
    def __init__(self, s=0, e=0):
        self.start = s
        self.end = e
        
def print_Interval(i):
    for j in range(len(i)):
        print("%d: %d\t%d" % (j, i[j].start, i[j].end))

class Solution(object):
    def merge(self, i):
        """
        :type i: List[Interval]
        :rtype: List[Interval]
        """
        k, t = 0, -1
        i.sort(key=lambda x: (x.start, -x.end))
        while k < len(i):
            if t >= i[k].start:
                i[k-1].end = max(i[k].end,i[k-1].end)
                t = max(t, i[k].end)
                i.remove(i[k])
            else: 
                t = i[k].end
                k += 1
        return i
        
if __name__ == "__main__":
    i0 = Interval(1,4)
    i1 = Interval(0,2)
    i2 = Interval(3,5)
    i3 = Interval(2,3)
    i4 = Interval(15,18)
    i5 = Interval(1, 3)
    i = [i0, i1, i2, i3, i4, i5]
    i = [i0, i1, i2]
    print_Interval(Solution().merge(i))
