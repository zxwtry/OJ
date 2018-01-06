/*
    url: leetcode.com/problems/generate-parentheses/
    3ms 13.16%
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct list {
    char * c;
    struct list * next;
};

struct list * add_list(char * temp, int len, struct list * travel) {
    char * tt = (char *) malloc(sizeof(char) * len);
    struct list * t_list = (struct list *) malloc(sizeof(struct list ));
    int i = 0;
    for (i = 0; i < len; i ++) {
        *(tt + i) = *(temp + i);
    }
    t_list->c = NULL;
    t_list->next = NULL;
    if (travel != NULL) {
        travel->next = t_list;
    }
    return t_list;
}

void generate(struct list ** travel, char * temp, int left, int right, int index, int len, int * count) {
    struct list * temp_list = NULL;
    int i = 0;
    if (left + right == len - 1) {
        temp_list = (struct list *) malloc(sizeof(struct list));
        temp_list->c = NULL;
        temp_list->next = NULL;
        temp_list->c = (char *) malloc(sizeof(char) * len);
        for (i = 0; i < len; i ++)
            *(temp_list->c + i) = *(temp + i);
        (*travel)->next = temp_list;
        (*travel) = temp_list;
        (*count) ++;
    } else {
        if (left < (len - 1) / 2) {
            *(temp + index) = '(';
            generate(travel, temp, left + 1, right, index + 1, len, count);
        }
        if (left > right) {
            *(temp + index) = ')';
            generate(travel, temp, left, right + 1, index + 1, len, count);
        }
    }
};

void free_list(struct list * head) {
    struct list * temp = NULL;
    while (head != NULL) {
        temp = head->next;
        free(head);
        head = temp;
    }
}

char** generateParenthesis(int n, int* returnSize) {
    char * temp = (char *) malloc(sizeof(char) * (n * 2 + 1));
    //just a head
    struct list * head = (struct list *) malloc(sizeof(struct list));
    struct list * travel = head;
    int count = 0, i = 0;
    char ** answer = NULL;
    if (n < 0) return answer;
    *(temp + 2 * n) = '\0';
    generate(&travel, temp, 0, 0, 0, 2 * n + 1, &count);
    *returnSize = count;
    answer = (char **) malloc(sizeof(char *) * count);
    travel = head->next;
    for (i = 0; i < count;i ++) {
        *(answer + i) = travel->c;
        travel = travel->next;
    }
    free(head);
    return answer;
}

int main() {
    int n = 3;
    int returnSize[1] = {0};
    int i = 0;
    char ** answer = generateParenthesis(n, returnSize);
    for (i = 0; i < *returnSize; i ++) {
        printf("%s\r\n", *(answer + i));
    }
    for (i = 0; i < *returnSize; i ++) {
        free(*(answer + i));
    }
    free(answer);
}

