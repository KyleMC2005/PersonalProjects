#include <stdio.h>

int main(){

    int age;

    printf("\nPlease enter your age: ");
    scanf("%d", &age);

    if(age >= 18){
        printf("You are now signed up!");
    }
    else if(age == 0){
        printf("You cannot sign up as you have just been born!");
    }

    else if(age < 0) {
        printf("That is not a valid age!");
    }
    else {
        printf("You are too young to sign up!");
    }

    return 0;
}