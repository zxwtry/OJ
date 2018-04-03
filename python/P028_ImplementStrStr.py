#coding=utf-8

'''
    url: leetcode.com/problems/implement-strstr/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月1日
    @details:    Solution: 62ms 32.59%
'''

class Solution(object):
    def getNext(self, p, pn):
        #return short p
        if pn < 2: return [-1]
        bi, fi, ne = 0, 2, [0] * pn
        ne[0], ne[1] = -1, 0
        while fi < pn:
            if p[fi - 1] == p[bi]:
                bi += 1
                ne[fi] = bi
                fi += 1
            elif bi <= 0:
                ne[fi] = 0
                fi += 1
            else:
                bi = ne[bi]
        return ne
        
    def strStr(self, s, p):
        """
        :type s: str origin string
        :type p: str pattern string
        :rtype: int
        """
        sn = 0 if s == None else len(s)
        pn = 0 if p == None else len(p)
        #pn == 0 match s at index 0
        if pn == 0: return 0
        #sn == 0 and pn > 0 can not match
        if sn == 0: return -1
        si, pi, ne = 0, 0, self.getNext(p, pn)
        while si < sn:
            if s[si] == p[pi]:
                si += 1
                pi += 1
                if pi == pn: break
            elif ne[pi] == -1:
                si += 1
            else:
                pi = ne[pi]
        return si - pn if pi == pn else -1
    
if __name__ == "__main__":
    s, p ="mississippi", "issi"
    print(Solution().strStr(s, p))
    
    