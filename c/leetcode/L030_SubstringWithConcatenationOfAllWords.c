/*
    url: leetcode.com/problems/substring-with-concatenation-of-all-words/
    46ms 100.0%
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
    
int cmp(char * s1, int i1, char * s2, int i2, int len) {
    int i = 0;
    for (i = 0; i < len; i ++) {
        if (*(s1 + i1 + i) != *(s2 + i2 + i))
            return 0;
    }
    return 1;
}

int contains(char * s1, char ** record_char, int record_size, int len) {
    int i = 0;
    for (i = 0; i < record_size; i ++) {
        if (cmp(s1, 0, *(record_char + i), 0, len))
            return i;
    }
    return -1;
}

int* findSubstring(char* s, char** words, int len2, int* returnSize) {
    int len1 = s == NULL ? 0 : strlen(s);
    int wl = 0, left = 0, count = 0, record_size = 1, wi = 0, sign = 1;
    int * arr1 = NULL, * arr2 = NULL, i = 0, j = 0, k = 0, v = 0, vv = 0;
    char ** record_char = NULL;
    int * record_int = NULL, * target_int = NULL;
    int * answer = (int *) malloc(sizeof(int) * len1), answer_capacity = len1;
    int answer_index = 0, * answer_temp = NULL;

    if (len1 == 0 || len2 == 0) return NULL;
    wl = strlen(*(words + 0));
    for (i = 1; i < len2; i ++) {
        for (j = 0, sign = 0; ! sign & (j < i); j ++)
            sign |= cmp(*(words + j), 0, *(words + i), 0, wl);
        if (! sign) record_size ++;
    }
    record_char = (char **)(malloc(sizeof(char *) * record_size));
    record_int = (int *)(malloc(sizeof(int) * record_size));
    target_int = (int *)(malloc(sizeof(int) * record_size));
    for (i = 0; i < record_size; i ++) *(target_int + i) = 0;
    for (i = 0; i < record_size; i ++) *(record_int + i) = 0;
    *(record_char + 0) = *(words + 0);
    *(target_int + 0) += 1;
    for (i = 1, wi = 1; i < len2; i ++) {
        for (j = 0, sign = 0; ! sign & (j < i); j ++)
            sign |= cmp(*(words + j), 0, *(words + i), 0, wl);
        if (! sign) {
            *(record_char + (wi ++)) = *(words + i);
            *(target_int + j) += 1;
        } else {
            *(target_int + j - 1) += 1;
        }
    }
    for (i = 0; i < wl; i ++) {
        left = i;
        count = 0;
        for (k = 0; k < record_size; k ++)
            *(record_int + k) = 0;
        for (j = i; j <= len1 - wl; j += wl) {
            v = contains(s + j, record_char, record_size, wl);
            if (v != -1) {
                *(record_int + v) += 1;
                count ++;
                while (*(target_int + v) < *(record_int + v)) {
                    vv = contains(s + left, record_char, record_size, wl);
                    *(record_int + vv) -= 1;
                    count --;
                    left += wl;
                }
                if (count == len2) {
                    //ensure answer size
                    if (answer_index == answer_capacity) {
                        answer_temp = (int *)malloc(sizeof(int) * answer_capacity * 2);
                        for (vv = 0; vv < answer_capacity; vv ++)
                            answer_temp[vv] = answer[vv];
                        free(answer);
                        answer = answer_temp;
                        answer_capacity = answer_capacity * 2;
                    }
                    answer[answer_index ++] = left;
                    vv = contains(s + left, record_char, record_size, wl);
                    *(record_int + vv) -= 1;
                    count --;
                    left += wl;
                }
            } else {
                left = j + wl;
                count = 0;
                for (k = 0; k < record_size; k ++)
                    *(record_int + k) = 0;
            }
        }
    }
    * (returnSize + 0) = answer_index;
    free(record_char);
    free(record_int);
    return answer;
}

int main() {
    char * s = "bbbbbb";
    char ** words = NULL;
    char * w0 = "b";
    char * w1 = "b";
    char * w2 = "b";
    char * w3 = "b";
    int len2 = 4;
    int returnSize = 0;
    int * answer = NULL;
    int i = 0;
    words = (char **)(malloc(sizeof(char *) * len2));
    *(words + 0) = w0;
    *(words + 1) = w1;
    *(words + 2) = w2;
    *(words + 3) = w3;
    answer = findSubstring(s, words, len2, &returnSize);
    for (i = 0; i < returnSize; i ++)
        printf("%d ", *(answer + i));
    printf("\r\n");
    free(words);
    free(answer);
    return 0;
}

