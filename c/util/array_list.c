/*
    array list
*/

#include <stdio.h>
#include <stdlib.h>

typedef int T;
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

T al_at(pal l, int index) {
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

int main() {
    pal l = al_init(2);
    T* arr = NULL;
    al_add_last(l, 4);
    al_print(l);
    al_add_last(l, 5);
    al_print(l);
    al_add_to_index(l, 102, 2);
    al_print(l);
    al_add_last(l, 6);
    al_print(l);
    al_remove_first(l);
    al_print(l);
    al_add_first(l, 11);
    al_print(l);
    al_add_last(l, 7);
    al_print(l);
    al_add_first(l, 12);
    al_print(l);
    al_remove_last(l);
    al_print(l);
    printf("%d %d\r\n", al_at(l, 0), al_at(l, 100));
    arr = al_convert_to_array_free_l(l);
}