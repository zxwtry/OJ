#include <bits/stdc++.h>
using namespace std;

char s[1000008];
char p[10008];
int nxt[100008];
void process();
void getNext(char *, int);
int kmp(char *, char *);

int main()
{
    if (getenv("ZXWPC"))
    {
        freopen("w003.in", "r", stdin);
        freopen("w003.out", "w", stdout);
    }
    int i;
    scanf("%d", &i);
    while (i--)
    {
        scanf("%s\n", p);
        scanf("%s\n", s);
        process();
    }
    return 0;
}

void process()
{
    int res = kmp(s, p);
    printf("%d\n", res);
}

int kmp(char *s, char *p)
{
    int sn = NULL == s ? 0 : strlen(s);
    int pn = NULL == p ? 0 : strlen(p);
    if (pn < 1)
        return 0;
    if (sn < pn)
    {
        return -1;
    }
    getNext(p, pn);
    int si = 0;
    int pi = 0;
    int count = 0;
    while (si != sn && pi != pn)
    {
        if (pi == -1 || s[si] == p[pi])
        {
            si++;
            pi++;
            if (pi == pn)
            {
                pi = nxt[pi];
                count++;
            }
        }
        else
        {
            pi = nxt[pi];
        }
    }
    return count;
}

void getNext(char *p, int pn)
{
    int fi = 0;
    int bi = -1;
    nxt[0] = -1;
    while (fi < pn)
    {
        if (-1 == bi || p[fi] == p[bi])
        {
            fi++;
            bi++;
            nxt[fi] = bi;
        }
        else
        {
            bi = nxt[bi];
        }
    }
}
