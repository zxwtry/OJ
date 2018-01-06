/*
    refer to Java LinkedList<T>
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

int main() {
    pdll l = (pdll) malloc(sizeof(sdll));
    int* arr = NULL;
    l->first = NULL;
    l->last = NULL;
    l->size = 0;

    dll_add_last(l, 4);
    dll_add_first(l, 11);
    dll_add_last(l, 5);
    dll_add_first(l, 12);
    dll_add_last(l, 6);
    dll_add_first(l, 13);
    dll_add_last(l, 7);

    dll_remove_first(l);
    dll_remove_last(l);
    dll_remove_first(l);
    dll_remove_last(l);
    dll_remove_first(l);
    dll_remove_last(l);
    dll_remove_last(l);

    dll_add_last(l, 4);

    dll_add_first(l, 9);
    

    dll_print(l);

    arr = dll_convert_to_array(l);

    printf("%d %d\r\n", arr[0], arr[1]);

}

