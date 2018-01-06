/*
    url: leetcode.com/problems/text-justification
    AC 3ms 0.00%
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//array_list start

typedef char* T;
typedef struct al sal;
typedef struct al * pal;

struct al {
    int capacity;
    int size;
    T* arr;
};

pal al_init(int capacity) {
    pal l = (pal) malloc(sizeof(sal));
    if (capacity < 1) return NULL;
    l->arr = (T*) malloc(sizeof(T) * capacity);
    l->capacity = capacity;
    l->size = 0;
    return l;
}

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

T* al_convert_to_array_free_l(pal l) {
    T* arr = l->arr;
    free(l);
    return arr;
}

void al_print(pal l) {
    int i = 0;
    if (l->size == 0) return;
    for (i = 0; i < l->size; i ++)
        printf("%s \r\n", l->arr[i]);
    printf("\r\n");
}

//array_list end


char** fullJustify(char** ws, int wn, int mw, int* rn) {
    int wi = 0, ci = 0, nw = 0, i = 0, bn = 0;
    int j = 0, ai = 0, jn = 0;
    char* a = NULL;
    pal l = al_init(16);
    int* wl = (int*) malloc(sizeof(int) * wn);
    for (i = 0; i < wn; i ++)
        wl[i] = strlen(ws[i]);
    while (wi < wn) {
        ci = 1;
        i = wi;
        nw = strlen(ws[wi]);
        a = (char*) malloc(sizeof(char) * (mw+1));
        ai = 0;
        a[mw] = '\0';
        while (wi+1 != wn && nw+wl[wi+1]+1 <= mw) {
            nw += wl[wi+1]+1;
            wi ++;
            ci ++;
        }
        wi ++;
        bn = mw-nw+ci-1;
        for (; i < wi; i ++) {
            for (j = 0; j < wl[i]; j ++)
                a[ai++] = ws[i][j];
            if (i != wi-1) {
                jn = bn / (ci-1) + (bn % (ci-1) == 0 ?  0 : 1);
                if (wi == wn) jn = 1;
                for (j = 0; j < jn; j ++)
                    a[ai++] = ' ';
                bn -= jn;
                ci --;
            } else {
                while (ai < mw)
                    a[ai++] = ' ';
            }
        }
        
        al_add_last(l, a);
    }
    *rn = l->size;
    return al_convert_to_array_free_l(l);
}

int main() {
    int wn = 7;
    char** ws = (char**) malloc(sizeof(char*) * wn);
    char** ans = NULL;
    int mw = 12;
    int rn = 0;
    int i = 0;

    ws[0] = "what";
    ws[1] = "must";
    ws[2] = "be";
    ws[3] = "shall";
    ws[4] = "be.";
    ws[5] = "text";
    ws[6] = "justification.";
    ans = fullJustify(ws, 5, mw, &rn);
    for (i = 0; i < rn; i ++) {
        printf("%s\tlen is %d\r\n", ans[i], strlen(ans[i]));
        free(ans[i]);
    }
    free(ans);
    free(ws);
    return 0;
    
}