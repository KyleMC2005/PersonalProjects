#include <stdio.h>
#include <math.h>
#include <ctype.h>


int main(){

    char calcform;
    float x;
    float y;
    float z;

    printf("+ Addition\n- Subtraction\n* Multiplication\n/ Division");
    printf("\nWhat form of calculation would you like to do today? ");
    scanf("%s", &calcform);

    calcform = toupper(calcform);

    switch (calcform){
        // Addition
        case '+' :
        printf("\nPlease input the first variable to be added: ");
        scanf("%f", &x);

        printf("\nPlease input the second variable to be added: ");
        scanf("%f", &y);

        z = x+y;
        printf("\nThe answer is: %f", z);
        break;
        // Subtraction
        case '-' :
        printf("\nPlease input the first variable to be subtracted: ");
        scanf("%f", &x);

        printf("\nPlease input the first variable to be subtracted: ");
        scanf("%f", &y);

        z = x-y;
        printf("\nThe answer is: %f", z);
        break;
        // Multiplication
        case '*' :
        printf("\nPlease input the first variable to be multiplied: ");
        scanf("%f", &x);

        printf("\nPlease input the first variable to be multiplied: ");
        scanf("%f", &y);

        z = x*y;
        printf("\nThe answer is: %f", z);
        break;
        // Division
        case '/' :
        printf("\nPlease input the first variable to be divided: ");
        scanf("%f", &x);

        printf("\nPlease input the first variable to be divided: ");
        scanf("%f", &y);

        z = x/y;
        printf("\nThe answer is: %f", z);
        break;

        default :
        printf("\nYou have not entered a value that can be associated with a standard calculation for this program");
    }

    return 0;
}