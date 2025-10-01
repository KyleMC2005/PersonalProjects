#include <stdio.h>

void hello(char[], int);    //Function Prototype

int main(){

    // function prototype

    // WHAT IS IT?
    // Function declaration, w/o a body, before main()
    // Ensures that calls to a function are made with the correct arguments

    // IMPORTANT NOTES
    // Many C compilers do not check for parameter matching
    // Missing arguments will result in unexpected behaviour 
    // A function protoype causes the compiler to flag an error if arguments are missing

    // ADVANTAGES
    // 1. Easier to navigate a program w/ main() at the top
    // 2. Helps with debugging
    // 3. Commonly used in header files

    char name[] = "Kyle";
    int age = 19;

    hello(name, age);                               // Because I included a Function Prototype, I have essentially said what arguments are required for the function to work, and if
    return 0;                                       // The incorrect arguments are used, the program should not run, thus eliminating a logic error, making something more fixable
}

void hello(char name[], int age){

    printf("\nHello %s", name);
    printf("\nYou are %d years old", age);
}