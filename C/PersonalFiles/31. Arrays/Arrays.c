# include <stdio.h>

int main()
{
    // Array = A data structure that can store many values of the same data type.

    double prices[] = {5.0, 10.0, 15.0, 20.0, 25.0};

    printf("$%.2lf", prices[0]);

    // As shown, there is more than one way to declare the contents of an Array

    prices[0] = 5.0;
    prices[1] = 10.0;
    prices[2] = 15.0;
    prices[3] = 20.0;
    prices[4] = 25.0;


    char name[] = "Kyle";

    return 0;
}