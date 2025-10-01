#include <stdio.h>
#include "list.h"

int main( void ) {
  List list = new_list();
  printf("Empty: %s\n", peek(list));
  printf("Empty: %s\n", peek(list));

  push(list, "One");
  printf("Single: %s\n", peek(list));
  printf("Single: %s\n", peek(list));

  push(list, "Two");
  push(list, "Three");
  printf("Multiple: %s\n", peek(list));
  printf("Multiple: %s\n", peek(list));

  print_list(list);
  
  return 0;
}