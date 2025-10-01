#include <stdio.h>

int main(void){

//FizzBuzz Game
    //So basically print the numbers 1-100 with any that are a multiple of 3, 5, or 7 generating a message
    // If a multiple of 3, print fizz
    // If a multiple of 5, print buzz
    // If a multiple of 5 and 3, print fizzbuzz
    // If a multiple of 7, print bazz
    // if a multiple of another other than 7, print fizz, or buzz, or both etc


    int number;

    number = 1;

    // I could use modulus to determine if it is a multiple, if it is, then the modulus will return 0
    //Set the temp to be the number first of all


    for (int i =0; i <100; i++){

        int temp = number;

        // Check if the number is a multiple of 3, 5, and 7
        if (temp%3 == 0){
            printf("Fizz");}
        if (temp%5 == 0){
            printf("Buzz");}
        if (temp%7 == 0){
            printf("Bazz");}
        if (temp%9 == 0){
            printf("Bop");}
            
            else if (temp%3 != 0 && temp%5 != 0 && temp%7 != 0 && temp%9 != 0){
                printf("%d", number);
            }

            printf("\n");
            number++;
        
    }
    return 0;
}