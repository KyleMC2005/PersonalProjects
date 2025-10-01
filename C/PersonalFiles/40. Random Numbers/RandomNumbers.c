#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
    // pseudo random numbers = A set of values or elements that are statistically random
    // (Don't use these for any sort of cryptographic security)

    // We use this seed so that the random generator is actually random, otherwise if we run it again, we will get the same results over and over again
    srand(time(0));

    int number1 = rand() % 20 + 1;                  // This will give a random number from 0 to 5,
    // int number1 = (rand() % 6) + 1;              // This would give a random number from 1 to 6

    int number2 = rand() % 20 + 1;
    int number3 = rand() % 20 + 1;

    printf("%d", number1);
    printf("\n%d", number2);
    printf("\n%d", number3);

                                                    // Now that we know how to get random numbers, let's make a guessing game for the next program
    return 0;
}