#include <stdio.h>

enum Day{Sun = 1, Mon = 2, Tue = 3, Wed = 4, Thu = 5, Fri = 6, Sat = 7};

int main()
{
    // enum = a user definied type of named integer identifiers
    // helps to make a program more readable

    enum Day today = Tue;

    // enums are NOT STRINGS, but they can be treated as int

    //printf("%d", today);

    if(today == Sun || today == Sat)
    {
        printf("It's the weekend! Party Time!");
    }
    else 
    {
        printf("Have to work today >:c");
    }

    return 0;
}