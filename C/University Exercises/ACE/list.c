#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

List new_list() {
    List list = malloc(sizeof(Node*));
    *list = NULL;
    return list;
}

int is_empty(List list) {
    return (*list == NULL);
}

int size(List list) {
    int count = 0;
    Node *current = *list;
    while (current != NULL) {
        count++;
        current = current->next;
    }
    return count;
}

void clear(List list) {
    while (!is_empty(list)) {
        rem(list);
    }
}

void add(List list, char* string) {
    Node* new_node = malloc(sizeof(Node));
    new_node->value = malloc(strlen(string) + 1);
    strcpy(new_node->value, string);
    new_node->next = *list;
    *list = new_node;
}

char* rem(List list) {
    if (*list == NULL) {
        return NULL;
    }
    
    Node* first = *list;
    char* value = malloc(strlen(first->value) + 1);
    strcpy(value, first->value);
    
    *list = first->next;
    free(first->value);
    free(first);
    
    return value;
}

void push(List list, char* string) {
    Node* new_node = malloc(sizeof(Node));
    new_node->value = malloc(strlen(string) + 1);
    strcpy(new_node->value, string);
    new_node->next = NULL;
    
    if (*list == NULL) {
        *list = new_node;
        return;
    }
    
    Node* current = *list;
    while (current->next != NULL) {
        current = current->next;
    }
    current->next = new_node;
}

char* peek(List list) {
    if (*list == NULL) {
        return NULL;
    }
    
    Node* current = *list;
    while (current->next != NULL) {
        current = current->next;
    }
    
    return current->value;
}

char* pop(List list) {
    if (*list == NULL) {
        return NULL;
    }
    
    Node* last = NULL;
    Node* current = *list;
    
    while (current->next != NULL) {
        last = current;
        current = current->next;
    }
    
    char* value = malloc(strlen(current->value) + 1);
    strcpy(value, current->value);
    
    if (last == NULL) {
        *list = NULL;
    } else {
        last->next = NULL;
    }
    
    free(current->value);
    free(current);
    return value;
}

int index_of(List list, char *value) {
    int index = 0;
    Node *current = *list;
    
    while (current != NULL) {
        if (strcmp(current->value, value) == 0) {
            return index;
        }
        current = current->next;
        index++;
    }
    
    return -1;
}

int contains(List list, char *value) {
    return index_of(list, value) != -1;
}

char *get_at(List list, int n) {
    if (n < 0 || *list == NULL) {
        return NULL;
    }
    
    Node *current = *list;
    int index = 0;
    
    while (current != NULL && index < n) {
        current = current->next;
        index++;
    }
    
    if (current == NULL) {
        return NULL;
    }
    
    return current->value;
}

int replace_at(List list, int n, char *value) {
    if (n < 0 || *list == NULL) {
        return 0;
    }
    
    Node *current = *list;
    int index = 0;
    
    while (current != NULL && index < n) {
        current = current->next;
        index++;
    }
    
    if (current == NULL) {
        return 0;
    }
    
    free(current->value);
    current->value = malloc(strlen(value) + 1);
    strcpy(current->value, value);
    return 1;
}

int insert_at(List list, int n, char *value) {
    if (n < 0) {
        return 0;
    }
    
    if (n == 0) {
        add(list, value);
        return 1;
    }
    
    Node *current = *list;
    int index = 0;
    
    while (current != NULL && index < n - 1) {
        current = current->next;
        index++;
    }
    
    if (current == NULL) {
        return 0;
    }
    
        Node *new_node = malloc(sizeof(Node));
    new_node->value = malloc(strlen(value) + 1);
    strcpy(new_node->value, value);
    
    new_node->next = current->next;
    current->next = new_node;
    
    return 1;
}

char *remove_at(List list, int n) {
    if (n < 0 || *list == NULL) {
        return NULL;
    }
    
    if (n == 0) {
        return rem(list);
    }
    
    Node *current = *list;
    int index = 0;
    
    while (current != NULL && index < n - 1) {
        current = current->next;
        index++;
    }
    
    if (current == NULL || current->next == NULL) {
        return NULL;
    }
    
    Node *to_remove = current->next;
    char *value = malloc(strlen(to_remove->value) + 1);
    strcpy(value, to_remove->value);
    
    current->next = to_remove->next;
    free(to_remove->value);
    free(to_remove);
    
    return value;
}

List copy_list(List list) {
    List new_list_ptr = new_list();
    Node *current = *list;
    
    while (current != NULL) {
        push(new_list_ptr, current->value);
        current = current->next;
    }
    
    return new_list_ptr;
}

List sublist(List list, int start, int end) {
    if (start < 0 || start >= end || end > size(list)) {
        return NULL;
    }
    
    List new_list_ptr = new_list();
    Node *current = *list;
    int index = 0;
    
    while (current != NULL && index < end) {
        if (index >= start) {
            push(new_list_ptr, current->value);
        }
        current = current->next;
        index++;
    }
    
    return new_list_ptr;
}

void print_list(List list) {
    printf("[");
    
    if (*list != NULL) {
        Node *current = *list;
        printf("\"%s\"", current->value);
        current = current->next;
        
        while (current != NULL) {
            printf(", \"%s\"", current->value);
            current = current->next;
        }
    }
    printf("]\n");
}

int save_list(List list, char *string){


}

List load_list(char *filename){


  
}