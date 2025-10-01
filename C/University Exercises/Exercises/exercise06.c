#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>

// Function Prototyping
void print_digit(int digit) {

    static const int digits[10][7] = {{1, 1, 1, 0, 1, 1, 1}, {0, 0, 1, 0, 0, 1, 0}, {1, 0, 1, 1, 1, 0, 1}, {1, 0, 1, 1, 0, 1, 1},  {0, 1, 1, 1, 0, 1, 0}, {1, 1, 0, 1, 0, 1, 1}, {1, 1, 0, 1, 1, 1, 1},  {1, 0, 1, 0, 0, 1, 0}, {1, 1, 1, 1, 1, 1, 1},  {1, 1, 1, 1, 0, 1, 0}};
    printf("\n %c \n",  (digits[digit][0]) ? '-' : ' ');
    printf("%c %c\n", (digits[digit][1]) ? '|' : ' ', (digits[digit][2]) ? '|' : ' ');
    printf(" %c \n",  (digits[digit][3]) ? '-' : ' ');
    printf("%c %c\n", (digits[digit][4]) ? '|' : ' ', (digits[digit][5]) ? '|' : ' ');
    printf(" %c \n",  (digits[digit][6]) ? '-' : ' ');
}

int main(void){

    char numbercheck[10];

    do {
         // User Input & Input Validation

        // Simultaneously checking that it is a digit, and that the input is a single character (ie 0 to 9)
        printf("Please enter a single digit: ");
         scanf("%s", numbercheck);
         if (isdigit(numbercheck[0]) && numbercheck[1] == '\0'){
            int digit = numbercheck[0] - '0';
            print_digit(digit);
         }  
        else if (numbercheck[0] == 'q'){
         printf("You have chosen to quit");
         exit(1);}
        
        else 
            printf("Invalid input\n");
         }

         while (numbercheck[0] != 'q');

    return 0;
}