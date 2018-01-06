/*
    url: leetcode.com/problems/simplify-path
    AC 6ms 28.00%
*/

#include <stdio.h>
#include <stdlib.h>

//element type
typedef int T;
typedef struct dll sdll;
typedef struct dll * pdll;
typedef struct dln sdln;
typedef struct dln * pdln;


//doubly list node
struct dln {
    T val;
    pdln pre;
    pdln nxt;
};

//doubly linked list
struct dll {
    pdln first;
    pdln last;
    int size;
};

pdll dll_init() {
    pdll l = (pdll) malloc(sizeof(sdll));
    l->first = NULL;
    l->last = NULL;
    l->size = 0;
    return l;
}

void dll_add_first(pdll l, T v) {
    pdln t = (pdln) malloc(sizeof(sdln));
    t->val = v;
    t->pre = NULL;
    t->nxt = NULL;
    if (l->size == 0) {
        l->first = t;
        l->last = t;
        l->size = 1;
        return;
    }
    t->nxt = l->first;
    l->first->pre = t;
    l->first = t;
    l->size ++;
}

void dll_add_last(pdll l, T v) {
    pdln t = (pdln) malloc(sizeof(sdln));
    t->val = v;
    t->pre = NULL;
    t->nxt = NULL;
    if (l->size == 0) {
        l->first = t;
        l->last = t;
        l->size = 1;
        return;
    }
    t->pre = l->last;
    l->last->nxt = t;
    l->last = t;
    l->size ++;
}

void dll_remove_first(pdll l) {
    pdln t = NULL;
    if (l->first == NULL) return;
    if (l->first == l->last) {
        free(l->first);
        l->first = NULL;
        l->last = NULL;
        l->size = 0;
        return;
    }
    t = l->first->nxt;
    t->pre = NULL;
    free(l->first);
    l->first = t;
    l->size --;
}

void dll_remove_last(pdll l) {
    pdln t = NULL;
    if (l->last == NULL) return;
    if (l->first == l->last) {
        free(l->first);
        l->first = NULL;
        l->last = NULL;
        l->size = 0;
        return;
    }
    t = l->last->pre;
    t->nxt = NULL;
    free(l->last);
    l->last = t;
    l->size --;
}

T* dll_convert_to_array(pdll l) {
    T* arr = NULL;
    int arr_index = 0;
    pdln travel = NULL;
    pdln t1 = NULL, t2 = NULL;
    if (l == NULL || l->size == 0) return arr;
    arr = (T*) malloc(sizeof(T) * l->size);
    travel = l->first;
    while (travel != NULL) {
        arr[arr_index ++] = travel->val;
        travel = travel->nxt;
    }
    t1 = l->first;
    while (t1 != NULL) {
        t2 = t1->nxt;
        free(t1);
        t1 = t2;
    }
    free(l);
    return arr;
}

void dll_print(pdll l) {
    pdln t = l == NULL ? NULL : l->first;
    while (t != NULL) {
        printf("%d ", t->val);
        t = t->nxt;
    }
    printf("\r\n");
}

void dll_free_all(pdll l) {
    pdln t1 = l->first, t2 = NULL;
    while (t1 != NULL) {
        t2 = t1->nxt;
        free(t1);
        t1 = t2;
    }
    free(l);
}

char* simplifyPath(char* p) {
    pdll l = dll_init();
    int pn = 0, i = 0, sti = 0, len = 0, ai = 0;
    char c = '\0', *ans = NULL; 
    pdln n = NULL;
    while (1) {
        c = p[i ++];
        if (c == '\0') break;
        if (c == '/') {
            if (len == 2 && p[sti] == '.' && p[sti+1] == '.') {
                dll_remove_last(l);
            } else if (len != 0 && ! (len == 1 && p[sti] == '.')) {
                dll_add_last(l, sti);
            }
            sti = i;
            len = 0;
            continue;
        }
        len ++;
    }
    if (len == 2 && p[sti] == '.' && p[sti+1] == '.') {
        dll_remove_last(l);
    } else if (len != 0 && ! (len == 1 && p[sti] == '.')) {
        dll_add_last(l, sti);
    }
    pn = i;
    ans = (char*) malloc(sizeof(char) * pn);
    n = l->first;
    while (n != NULL) {
        i = n->val;
        ans[ai ++] = '/';
        while (1) {
            c = p[i++];
            if (c == '\0' || c == '/') break;
            ans[ai ++] = c;
        }
        n = n->nxt;
    }
    ans[ai ++] = '\0';
    if (l->first == NULL) {
        ans[0] = '/';
        ans[1] = '\0';
        return ans;
    }
    return ans;
}

int main() {
    char* p = "/home//foo/";
    char* a = simplifyPath(p);
    printf("answer is %s\r\n", a);
    free(a);
    return 0;
}