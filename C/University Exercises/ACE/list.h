#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    char *value;
    struct Node *next;
} Node;

typedef Node** List;

List new_list();
Node* new_node(char *string);               // This is going to be to create new nodes
void delete_node(Node* root);               // This is going to be to delete the nodes
void push(Node **List, char *string);
char* pop(Node **List);
void print_list(List list);
char* peek(Node **List);
void add(List list, char* string);
char* rem(List list);
int is_empty(List list);
int size(List list);
void clear(List list);
int index_of(List list, char *string);
int contains(List list, char *string);
char* get_at(List list, int index);
int replace_at(List list, int index, char *string);
int insert_at(List list, int index, char *string);
char* remove_at(List list, int index);
List copy_list(List list);
List sublist(List list, int start, int end);
int save_list(List list, char *string);
List load_list(char *filename);