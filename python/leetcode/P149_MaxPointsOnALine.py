"""
User:  zxwtry
File:  P149_MaxPointsOnALine.py
Date:  2018/4/2
Time:  21:59
"""

from util.Point import Point

class Solution:
    def maxPoints(self, points):
        """
        :type points: List[Point]
        :rtype: int
        """
        a1, a2, a3 = list(), list(), list()
        # 查看重复的点
        p1, p2 = None, None
        ps = list()
        ps_cnt = list()
        for p in points:
            exists = False
            for ps_i in range(len(ps)):
                if self.judgeEqual(p, ps[ps_i]):
                    ps_cnt[ps_i] += 1
                    exists = True
            if not exists:
                ps.append(p)
                ps_cnt.append(1)
        if len(ps) < 2:
            return sum(cnt for cnt in ps_cnt)
        ans, ps_len = 2, len(ps)
        for i in range(ps_len):
            for j in range(i + 1, ps_len, 1):
                ans_one = ps_cnt[i] + ps_cnt[j]
                for k in range(ps_len):
                    if k == i or k == j:
                        continue
                    if self.judgeOnline(ps[i], ps[j], ps[k]):
                        ans_one += ps_cnt[k]
                ans = max(ans, ans_one)
        return ans

    def judgeOnline(self, p1, p2, p3):
        return (p2.y - p1.y) * (p3.x - p1.x) == (p2.x - p1.x) * (p3.y - p1.y)

    def judgeEqual(self, p1, p2):
        if p1 is None and p2 is None:
            return True
        if p1 is None or p2 is None:
            return False
        return p1.x == p2.x and p1.y == p2.y


class Solution2:
    def maxPoints(self, points):
        """
        :type points: List[Point]
        :rtype: int
        """
        a1, a2, a3 = list(), list(), list()
        # 查看重复的点
        p1, p2 = None, None
        ps = list()
        ps_cnt = list()
        for p in points:
            exists = False
            for ps_i in range(len(ps)):
                if self.judgeEqual(p, ps[ps_i]):
                    ps_cnt[ps_i] += 1
                    exists = True
            if not exists:
                ps.append(p)
                ps_cnt.append(1)
        if len(ps) < 2:
            return sum(cnt for cnt in ps_cnt)
        ans, ps_len = 2, len(ps)
        for i in range(ps_len):
            m = dict()
            for j in range(ps_len):
                if i == j:
                    continue
                xv = ps[i].x - ps[j].x
                yv = ps[i].y - ps[j].y
                gcdv = self.gcd(xv, yv)
                xv = xv // gcdv
                yv = yv // gcdv
                if xv < 0:
                    xv, yv = -xv, -yv
                key = str(xv) + "#" + str(yv)
                if key in m.keys():
                    m[key] += ps_cnt[j]
                else:
                    m[key] = ps_cnt[j]
            ans = max(ans, max(v for v in m.values()) + ps_cnt[i])
        return ans

    def judgeEqual(self, p1, p2):
        if p1 is None and p2 is None:
            return True
        if p1 is None or p2 is None:
            return False
        return p1.x == p2.x and p1.y == p2.y

    def gcd(self, a, b):
        a, b = abs(a), abs(b)
        a, b = max(a, b), min(a, b)
        while b != 0:
            a, b = b, a % b
        return a

if __name__ == "__main__":
    # [[0,0],[1,65536],[65536,0]]
    xs = [0, 1, 65536]
    ys = [0, 65536, 0]
    ps = Point().constructFromArray(xs, ys)
    print(Solution2().maxPoints(ps))
