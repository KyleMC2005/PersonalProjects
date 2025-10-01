#include <stdio.h>
#include "list.h"

int main( void ) {
  Node root = {"First!", NULL};         // Value, Pointer
  if (root.next == NULL) {              // If pointer == null
  	printf("%s", root.value);           // Print the root value
  }
  return 0;
}