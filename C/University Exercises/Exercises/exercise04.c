#include <stdio.h>
#include <stdlib.h>

int main(void){

    // Declare a variable to store the double digit value
    int doubledigit;
    int tens;
    int units;


    printf("Please enter an integer with double digits\n");
    scanf("%d", &doubledigit);

    // This checks to make sure that it is a double digit number
    
    if(doubledigit <9 || doubledigit > 99){                     // If the number is not greater than 9 and less than 100, it can't be a double digit number
        printf("Invalid input!\n");
        return 1;                                               // Exit code to say that it failed                    
    }

    tens = doubledigit/10;                                      // Dividing by ten gives the tens, ie 45 /10 is 4.5 so gives 4
    units = doubledigit%10;                                     // Modulus 10 gives .5 therefore 5

    printf("%d%d\n", units, tens);





    return 0;
}