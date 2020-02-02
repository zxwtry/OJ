#include <iostream>
#include <stdio.h>

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
    for (int numIndex = 0; numIndex < costNum; numIndex++) {
        scanf("%d %d\n", &cost[numIndex], &value[numIndex]);
    }
    for (int numIndex = 0; numIndex < costNum; numIndex++) {
        for (int costIndex = costNum; costIndex >= cost[numIndex];
             costIndex--) {
            maxValue[costIndex] =
                std::max(maxValue[costIndex - cost[numIndex]] + value[numIndex],
                         maxValue[costIndex]);
        }
    }
    printf("%d\n", maxValue[costNum]);
    return 0;
}