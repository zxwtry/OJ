#include <algorithm>
#include <iostream>
#include <map>

using namespace std;
typedef unsigned int uint32_t;
int rand()
{
    return 100;
}

const uint32_t MOD = 65536;
const int RANGE_MAX = 300100;

bool modMap[RANGE_MAX] = {false}; // 记录是否出现过
bool allowDup = true;             // 是否允许重复

uint32_t getRand(uint32_t times, uint32_t range)
{
    // 构造完全随机数 val
    uint32_t val = 0;
    map<uint32_t, uint32_t> mp;
    uint32_t m = 1;
    for (int i = 0; i <= times; i++)
    {
        mp[i] = m;
        m = m * MOD;
    }
    for (int i = 0; i <= times; i++)
    {
        val += rand() * mp[i];
    }
    // 溢出情况，未完全覆盖的 随机部分，直接丢弃
    if (val + MOD < val)
    {
        return getRand(times, range);
    }
    // 生成 [0, range - 1] 随机数
    return val % range;
}

uint32_t
completeRand(uint32_t start, uint32_t end)
{
    if (start >= end)
    {
        return start;
    }

    uint32_t range = end - start + 1;
    uint32_t times = 0;
    uint32_t r = range;
    while (r != 0)
    {
        r = r / MOD;
        times++;
    }

    uint32_t val = 0;
    if (allowDup)
    {
        val = getRand(times, range);
    }
    else
    {
        do
        {
            val = getRand(times, range);
        } while (modMap[val] == false);
        modMap[val] = true;
    }

    return val + start;
}

int main()
{
    uint32_t start = 1;
    uint32_t end = 300000;
    const uint32_t N = 100000;
    uint32_t arr[N];
    for (int i = 0; i < N; i++)
    {
        arr[i] = completeRand(start, end);
        cout << i << "\t" << arr[i] << endl;
    }
}
