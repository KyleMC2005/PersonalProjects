#include <stdio.h>

void printAge(int *pAge)
{
    printf("You are %d years old", *pAge);    // Dereference
}



int main()
{
    // Pointer = a "variable-like" reference that holds a memory address to another variable, array, etc
    // Some tasks are performed more easily with pointers
    // *= indirection operator (value at address)
    
    int age = 19;
    int *pAge = NULL;                                   // Set to Null first 
    *pAge = &age;                                       // Ensure that the pointer type correlates to the data type of the value you wish to store the address of
    /*
    printf("address of age: %p\n", &age);
    printf("value of pAge: %p\n", pAge);

    printf("size of age: %d bytes\n", sizeof(age));
    printf("size of pAge: %d bytes\n", sizeof(pAge));

    printf("value of age: %d\n", age);
    printf("value at stored address: %d\n", *pAge);         // derefencing
    */
    printAge(pAge);

    return 0;
}