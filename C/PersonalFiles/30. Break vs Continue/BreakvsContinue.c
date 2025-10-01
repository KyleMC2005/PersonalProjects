# include <stdio.h>

int main()
{
    // continue = skips rest of code & forces the next iteration of the loop
    // break = exits a loop/switch

    // In this case I want to create a program to print the numbers 1 to 20, but not print the number 13, in this case I can use a continue if i is 13, meaning it will
    // Automatically move to the next iteration without applying any of the code for the current iteration

    for(int i = 1; i <= 20; i++)
    {
        if(i == 13)
        {
            continue;
        }
        printf("%d\n", i);
    }


    // In this version, I will use a break, so when we get to 13, instead of skipping the iteration, we will stop at that point

    for(int i = 1; i <= 20; i++)
    {
        if(i == 13)
        {
            break;
        }
        printf("%d\n", i);
    }

    return 0;
}