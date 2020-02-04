#include <iostream>
#include <stdio.h>

using namespace std;

const int COSTMAX = 13880;
const int COSTNUM = 3502;
int cost[COSTNUM] = {0};
int value[COSTNUM] = {0};
int maxValue[COSTMAX] = {0};

int main() {
    if (getenv("ZXWPC")) {
        freopen("p3624.in", "r", stdin);
        freopen("p3624.out", "w", stdout);
    }
    int costNum = 0;
    int costMax = 0;
    scanf("%d %d\n", &costNum, &costMax);
    for (int numIndex = 1; numIndex <= costNum; numIndex++) {
        scanf("%d %d\n", &cost[numIndex], &value[numIndex]);
    }
    // for (int costIndex = 1; costIndex <= costMax; costIndex++) {
    //     for (int numIndex = 1; numIndex <= costNum; numIndex++) {
    //         if (costIndex >= cost[numIndex]) {
    //             maxValue[numIndex][costIndex] = std::max(
    //                 maxValue[numIndex - 1][costIndex - cost[numIndex]] +
    //                     value[numIndex],
    //                 maxValue[numIndex - 1][costIndex]);
    //         } else {
    //             maxValue[numIndex][costIndex] =
    //                 maxValue[numIndex - 1][costIndex];
    //         }
    //     }
    // }

    for (int numIndex = 1; numIndex <= costNum; numIndex++) {
        for (int costIndex = costMax; costIndex >= cost[numIndex];
             costIndex--) {
            maxValue[costIndex] =
                std::max(maxValue[costIndex - cost[numIndex]] + value[numIndex],
                         maxValue[costIndex]);
        }
    }
    printf("%d\n", maxValue[costMax]);
    return 0;
}