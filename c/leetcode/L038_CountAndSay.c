/*
    url: leetcode.com/problems/count-and-say/
    3ms 15.29%
*/

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

//array list start

typedef char T;
typedef struct al sal;
typedef struct al * pal;

struct al {
    int capacity;
    int size;
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
    if (index < 0 || index > l->size) return;
    if (l->capacity == l->size) al_expand_capacity(l);
    for (i = l->size - 1; i >= index; i --) {
        l->arr[i+1] = l->arr[i];
    }
    l->arr[index] = v;
    l->size ++;
}

void al_set_index(pal l, T v, int index) {
    if (index < 0 || index > l->size) return;
    if (index == l->size) {
        al_add_last(l, v);
        return;
    }
    l->arr[index] = v;
    //if T by malloc, need to free
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

T al_val(pal l, int index) {
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

void itoa_my(int val, char* chs, int radix) {
    int ATOA_MY_LEN = 33;
    char s[33];
    int index = ATOA_MY_LEN;
    int negative = val < 0;
    //invalid radix
    if (radix < 2) return;
    s[-- index] = '\0';
    while (val != 0) {
        s[-- index] = (char)(abs(val % radix) + '0');
        val = val / radix;
    }
    if (negative) {
        s[-- index] = '-';
    }
    //reuse negative as chs index
    negative = 0;
    while (index < ATOA_MY_LEN) {
        *(chs + negative ++) = s[index ++];
    }
}

char* check_cap(char* to_check, int* to_check_cap, int to_check_index) {
    int new_cap = 0, index = 0;
    char* temp = NULL;
    if (to_check_index < *to_check_cap) return to_check;
    new_cap = to_check_index + 100 > 2 * *to_check_cap ? to_check_index + 100 : 2 * *to_check_cap;
    temp = (char*) malloc(sizeof(char) * (new_cap + 1));
    for (index = 0; index <= *to_check_cap; index ++) {
        *(temp + index) = *(to_check + index);
    }
    *to_check_cap = new_cap;
    free(to_check);
    return temp;
}

void swap_pchar(char** a, char** b) {
    char* t = * a;
    *a = *b;
    *b = t;
}

void swap_int(int* a, int *b) {
    int t = *a;
    *a = *b;
    *b = t;
}

void swap_al(pal* a, pal* b) {
    pal t = *a;
    *a = *b;
    *b = t;
}

char* countAndSay(int n) {
    int answer_cap = 1, save_cap = 1, ni = 1;
    int answer_size = 0, save_size = 0;
    int answer_index = 0, save_index = 0, char_count = 0;
    int int_char_index = 0;
    char int_char[14];
    char char_pre = '\0';
    pal answer = (pal) malloc(sizeof(sal));
    pal save = (pal) malloc(sizeof(sal));
    char* temp = NULL;
    answer->capacity = 16;
    answer->size = 0;
    answer->arr = (T*) malloc(sizeof(T) * answer->capacity);
    save->capacity = 16;
    save->size = 0;
    save->arr = (T*) malloc(sizeof(T) * save->capacity);
    al_add_last(answer, '1');
    
    while (n != ni) {
        //just count
        ni ++;
        //count answer to save
        char_pre = '\0';
        char_count = 0;
        save_index = 0;
        answer_index = 0;
        save->size = 0;
        while (answer_index <= answer->size) {
            //only char_count ++ and continue
            if (answer_index != answer->size && al_val(answer, answer_index) == char_pre) {
                char_count ++;
                answer_index ++;
                continue;
            }
            if (answer_index != 0) {
                //save to save
                itoa_my(char_count, int_char, 10);
                int_char_index = 0;
                while (int_char[int_char_index] != '\0') {
                    al_add_last(save, int_char[int_char_index ++]);
                }
                al_add_last(save, char_pre);
            }
            if (answer_index != answer->size) {
                char_count = 1;
                char_pre = al_val(answer, answer_index ++);
            } else break;
        }
        swap_al(&answer, &save);      
    }
    al_free_all(save);
    al_add_last(answer, '\0');
    return al_convert_to_array_free_l(answer);
}

int main() {
    int n = 15;
    char* ans = countAndSay(n);
    printf("answer is %s\r\n", ans);
    free(ans);
}