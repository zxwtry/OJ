#include <iostream>
#include <stdio.h>

const int COSTMAX = 13880;
const int COSTNUM = 3502;
int cost[COSTNUM] = {0};
int value[COSTNUM] = {0};
int maxValue[COSTNUM][COSTMAX] = {0};

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
    for (int costIndex = 1; costIndex <= costMax; costIndex++) {
        for (int numIndex = 0; numIndex < costNum; numIndex++) {
            if (costIndex < cost[numIndex]) {

            } else {
            }
        }
    }
    printf("%d\n", maxValue[costMax]);
    return 0;
}