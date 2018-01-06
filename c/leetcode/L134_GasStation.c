/*
    url: leetcode.com/problems/gas-station
    AC 3ms 25.00%
*/

#include <stdio.h>
#include <stdlib.h>


int real(int i, int n) {
    while (i < 0) i += n;
    while (i >= n) i -= n;
    return i;
}
int travel(int* gas, int* cost, int i, int n) {
    int buff = 0, index = 0, now_index = 0;
    for (index = 0; index <= n; index ++) {
        now_index = real(index + i, n);
        buff += gas[now_index] - cost[now_index];
        if (buff < 0) return now_index;
    }
    return -1;
}
int canCompleteCircuit(int* gas, int gasSize, int* cost, int costSize) {
    int n = gasSize, f = -1, i = 0;
    for (i = 0; i < n; i ++) {
        f = travel(gas, cost, i, n);
        if (f == -1) return i;
        if (f < i) return -1;
        i = f;
    }
    return -1;
}

int main() {
    int gas [] = {4, 6};
    int gasSize = 2;
    int cost [] = {5, 5};
    int costSize = 2;
    printf("answer is %d\n", canCompleteCircuit(gas, gasSize, cost, costSize));
}

