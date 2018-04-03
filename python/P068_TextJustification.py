#coding=utf-8

'''
    url: leetcode.com/problems/text-justification/
    @author:     zxwtry
    @email:      zxwtry@qq.com
    @date:       2017年4月15日
    @details:    Solution: 55ms 25.07%
'''

class Solution(object):
    def fullJustify(self, ws, mw):
        """
        :type ws: List[str]
        :type mw: int
        :rtype: List[str]
        """
        wn = 0 if ws == None else len(ws)
        if wn == 0: return [""]
        ans, s, pi  = [], 0, 0
        for i in range(wn):
            if s+len(ws[i])+1 > mw and i != pi:
                b_num = mw - s + i-1 -pi
                if pi == i-1:
                    ans.append(ws[pi]+(" "*b_num))
                else:
                    ans_t = ""
                    for j in range(pi, i):
                        ans_t += ws[j]
                        if b_num > 0 and i-1-j != 0:
                            b_this = 0 if b_num % (i-1-j) == 0 else 1
                            b_this += b_num // (i-1-j)
                            ans_t += (" " * b_this)
                            b_num -= b_this
                    ans.append(ans_t)
                s = len(ws[i])
                pi = i
            else:
                if s == 0:
                    s = len(ws[i])
                else:
                    s += (1 + len(ws[i]))
        b_num, ans_t = mw - s + wn-1 -pi, ""
        for i in range(pi, wn):
            if i != pi: ans_t += " "
            ans_t += ws[i]
        ans_t += " " * (mw - len(ans_t))
        ans.append(ans_t)
        return ans

if __name__ == "__main__":
    ws = ["This", "is", "an", "example", "of", "text", "justification."]
    ws = ["This", "is"]
    ws = ["b", "c", "e", "f"]
    mw = 1
    for s in Solution().fullJustify(ws, mw):
        print(s)
        print(len(s))