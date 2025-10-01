#include <stdio.h>

void birthday(char name[],int age){                 // I am calling those variables from main and using them in my birthday function
    printf("\nHappy Birthday dear %s!", name);
    printf("\nYou are %d years old!", age);
}

int main(){
    char name[] = "Kyle";
    int age = 19;

    birthday(name, age);            // I am taking variables from main, and calling them to the birthday function

    return 0;
}