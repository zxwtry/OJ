#coding=utf-8

'''
    url: leetcode.com/problems/regular-expression-matching/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年3月27日
    @details:    BackTraceSolution: TLE
    @details:    DPSolution: AC 99ms 67.25%
'''

class DPSolution(object):
    def isMatch(self, s, p):
        I = 0 if s == None else len(s)
        J = 0 if p == None else len(p)
        m = [[False for i in range(J + 1)] for j in range(I + 1)]
        m[0][0] = True
        for j in range(J):
            if '*' == p[j] and m[0][j - 1]:
                m[0][j + 1] = True
        for i in range(I):
            for j in range(J):
                if s[i] == p[j] or '.' == p[j]:
                    m[i + 1][j + 1] = m[i][j]
                elif p[j] == '*':
                    if s[i] != p[j - 1] and '.' != p[j - 1]:
                        m[i + 1][j + 1] = m[i + 1][j - 1]
                    else:
                        m[i + 1][j + 1] = \
                        m[i][j + 1] or\
                        m[i][j - 1] or\
                        m[i + 1][j - 1]
                        #[p[j - 1],p[j]]组成一组
                        #匹配多次，看s[0,i-1]和p[0,j]是否能够匹配
                        #匹配一次，看s[0,i-1]和p[0,j-2]是否能够匹配
                        #匹配0次，看s[0,i]和p[0,j-2]是否能够匹配
                else:
                    m[i + 1][j + 1] = False
        return m[I][J]
               
class BackTraceSolution(object):
    def a(self, s, i, l):
        return s[i] if  i < l else '\0'
    def m(self, s, i, I, p, j, J):
        sc, pc = self.a(s, i, I), self.a(p, j, J)
        if pc == '\0': return sc == '\0'
        if self.a(p, j + 1, J) == '*':
            while sc == pc or (pc == '.'and sc != '\0'):
                i += 1
                sc = self.a(s, i, I)
                if self.m(s, i - 1, I, p, j + 2, J):
                    return True
            return self.m(s, i, I, p, j + 2, J)
        else:
            if sc == pc or (pc == '.' and sc != '\0'):
                return self.m(s, i + 1, I, p, j + 1, J)
            return False
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        I = 0 if s == None else len(s)
        J = 0 if p == None else len(p)
        return self.m(s, 0, I, p, 0, J)

if __name__ == "__main__":
    s = "baccbbcbcacacbbc"
    p = "c*.*b*c*ba*b*b*.a*"
    print(DPSolution().isMatch(s, p))