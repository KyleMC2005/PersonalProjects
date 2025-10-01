#include <stdio.h>

int main(){

    // constant = fixed value that cannot be altered by the program during its execution

    float random = 58.723;

    // Because I have not made this a constant, or in java terms "final", this value can be changed by the program whenever
    // This results in the variable pi being changed when I do this line of code
    random = 420.69;

    printf("%f\n", random);

    // Now, if I was to do the same thing but make the variable a constant, it cannot be changed by the program and will remain the same value
    // A common naming convention for constants in c is to make the name fully capitalised, it isn't necessary, but a common practice
    const float PI = 3.14159;

    // Now if I try to change this variable, I will run into an error like so:

    // PI = 3.48;



    printf("%f", PI);

    return 0;
}