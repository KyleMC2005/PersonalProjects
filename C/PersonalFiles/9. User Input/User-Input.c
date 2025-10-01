#include <stdio.h>
#include <string.h>

int main(){

    char name[25]; //bytes
    int age;

    printf("What's your name?");
    //scanf("%s", &name);
    // name is variable, 25 is the byte size, and stdin is standard input
    fgets(name, 25, stdin);
    name[strlen(name)-1] = '\0';

    printf("\nHow old are you?");
    scanf("%d", &age);

    printf("Hello %s, How are you?", name);
    printf("\nYou are %d years old", age);

    // If I tried to put my forename *and* my surname into it, it would take my first name, and not give me the correct output for my age, as it does not let me input it
    // This is because scanf will take in input until a whitespace (space) in an output
    // If your input has whitespace, you must use the fgets function instead

    // The fgets function automatically introuduces a \n at the end of your input, to remove it, use the line of code on line 2 to introduce String Functions
    // And line 13 to eliminate the new line

    return 0;


}