#include <stdio.h>

int main(){

    // variable = Allocated space in memory to store a value.
    // We refer to a variable's name to access the stored value.
    // That variable now behaves as if it was the value it contains.
    // BUT we need to declare what type of data we are storing.

    int x; // declare an integer x
    x = 123; // initalisation
    int y = 321; //declaration and initialisation

    int age = 19; // integer
    float gpa = 2.05; // floating point number
    char grade = 'a'; // single character
                        
                      // Since C is not an object oriented language, this means that unlike Java, we do not have String :(

    char name[] = "Kyle"; // array of characters, this is how we emulate a String, basically by having a bunch of characters 

    // to insert the age variable into this message, I must place a % symbol into where I would like it to be placed

    // So I must place the percentage, a secret symbol (in this case 'd') and then a comma and the variable that I would like to tie that %d to:
    printf("You are %d years old\n", age); 

    printf("My name is %s\n", name);

    printf("My average grade is %c\n", grade);

    printf("Your gpa is %f\n", gpa);
    return 0;
}