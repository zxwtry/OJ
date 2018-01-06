/*
    url: leetcode.com/problems/anagrams/
    AC 835ms 0.00%
    can use hash instead of map[26]
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//array list start

typedef char* T;
typedef struct al sal;
typedef struct al * pal;

struct al {
    int capacity;
    int size;
    int map[26];
    T* arr;
};

void al_expand_capacity(pal l) {
    T* new_arr = (T*) malloc(sizeof(T) * (l->capacity * 2 + 1));
    int i = 0;
    for (i = 0; i < l->capacity; i ++)
        new_arr[i] = l->arr[i];
    free(l->arr);
    l->arr = new_arr;
    l->capacity = l->capacity * 2 + 1;
}

void al_add_last(pal l, T v) {
    if (l->capacity == l->size) al_expand_capacity(l);
    l->arr[l->size] = v;
    l->size ++;
}

void al_add_first(pal l, T v) {
    int i = 0;
    if (l->capacity == l->size) al_expand_capacity(l);
    for (i = l->size; i > 0; i --)
        l->arr[i] = l->arr[i - 1];
    l->arr[0] = v;
    l->size ++;
}

void al_add_to_index(pal l, T v, int index) {
    int i = 0;
    if (index > l->size) return;
    if (l->capacity == l->size) al_expand_capacity(l);
    for (i = l->size - 1; i >= index; i --) {
        l->arr[i+1] = l->arr[i];
    }
    l->arr[index] = v;
    l->size ++;
}

//if T is ptr, need to free l->size - 1
void al_remove_last(pal l) {
    if (l->size == 0) return;
    l->arr[l->size - 1] = 0; //or NULL and free
    l->size --;
}

//if T is ptr, need to free 0
void al_remove_first(pal l) {
    int i = 0;
    if (l->size == 0) return;
    l->arr[0] = 0; //or NULL and free
    for (i = 1; i < l->size; i ++) {
        l->arr[i - 1] = l->arr[i];
    }
    l->size --;
}

T* al_convert_to_array_free_l(pal l) {
    T* arr = l->arr;
    free(l);
    return arr;
}

T al_access_by_index(pal l, int index) {
    if (index >= l->size || index < 0) return 0;
    return l->arr[index];
}

void al_free_all(pal l) {
    free(l->arr);
    free(l);
}

void al_print(pal l) {
    int i = 0;
    if (l->size == 0) return;
    for (i = 0; i < l->size; i ++)
        printf("%d ", l->arr[i]);
    printf("\r\n");
}


//array list end

int same_map(int* m1, int* m2) {
    int i = 0;
    for (i = 0; i < 26; i ++) {
        if (m1[i] != m2[i])
            return 0;
    }
    return 1;
}

void al_add_answer(pal* l, int ln, char* s, int* m) {
    int i = 0, j = 0;
    for (i = 0; i < ln; i ++) {
        if (l[i]->size == 0) {
            for (j = 0; j < 26; j ++)
                l[i]->map[j] = m[j];
            al_add_last(l[i], s);
            return;
        }
        if (same_map(l[i]->map, m)) {
            al_add_last(l[i], s);
            return;
        }
    }
}

char*** group_al_list(pal* l, int ln, int** cn, int* rn) {
    int i = 0;
    char*** ans = NULL;
    for (i = 0; i < ln; i ++) {
        if (0 == (l[i]->size)) break;
    }
    *rn = i;
    *cn = (int*) malloc(sizeof(int) * (*rn));
    ans = (char***) malloc(sizeof(char**) * (*rn));
    for (i = 0; i < (*rn); i ++) {
        (*cn)[i] = l[i]->size;
        ans[i] = al_convert_to_array_free_l(l[i]);
    }
    return ans;
}

char*** groupAnagrams(char** s, int sn, int** cn, int* rn) {
    pal* l = (pal*) malloc(sizeof(pal) * sn);
    int i = 0, j = 0, map[26], len = 0;
    char*** ans = NULL;
    for (i = 0; i < sn; i ++) {
        l[i] = (pal) malloc(sizeof(sal));
        l[i]->size = 0;
        l[i]->capacity = 16;
        l[i]->arr = (T*) malloc(sizeof(T) * l[i]->capacity);
        for (j = 0; j < 26; j ++)
            l[i]->map[j] = 0;
    }
    for (i = 0; i < sn; i ++) {
        for (j = 0; j < 26; j ++)
            map[j] = 0;
        len = strlen(s[i]);
        for (j = 0; j < len; j ++)
            map[s[i][j] - 'a'] ++;
        al_add_answer(l, sn, s[i], map);
    }
    ans = group_al_list(l, sn, cn, rn);
    free(l);
    return ans;
}


int main() {
    char* s0 = "eat";
    char* s1 = "tea";
    char* s2 = "tan";
    char* s3 = "ate";
    char* s4 = "nat";
    char* s5 = "bat";
    char* s[6];
    int* cn = NULL, rn = 0, sn = 6;
    char*** answer = NULL;
    int i = 0, j = 0;
    s[0] = s0;
    s[1] = s1;
    s[2] = s2;
    s[3] = s3;
    s[4] = s4;
    s[5] = s5;
    answer = groupAnagrams(s, sn, &cn, &rn);
    for (i = 0; i < rn; i ++) {
        for (j = 0; j < cn[i]; j ++) {
            printf("%s   ", answer[i][j]);
        }
        printf("\r\n");
    }
    free(answer);
}