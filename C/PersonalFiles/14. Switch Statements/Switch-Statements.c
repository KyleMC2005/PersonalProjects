#include <stdio.h>

int main(){

    // switch = A more efficient alternative to using many "else if" statements
    // allows a value to be tested for equality against many cases

    char grade;

    printf("Please enter your grade: ");
    scanf("%c", &grade);

    switch(grade){
        case 'A' :
        printf("Perfect!\n");
        break;                                              // The reason we have breaks is so that when the condition is met, we can *break* out of the switch after running the
        case 'B' :                                          // Line of code corresponding that result, (ie if there was no breaks and we got a C, it would print the C part
        printf("You did good!\n");                          // and everything else)
        break;
        case 'C' :
        printf("You did okay!\n");
        break;
        case 'D' :
        printf("At least it's not an F!\n");
        break;
        case 'F' :
        printf("You're cooked bro\n");
        break;
        default:                                            // default is basically if it met none of those conditions
            printf("Please only enter valid grades!\n");
    }

    return 0;
}