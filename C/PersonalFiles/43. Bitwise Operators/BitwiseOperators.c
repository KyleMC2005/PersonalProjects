#include <stdio.h>

int main()
{
    // BITWISE OPERATORS = special operators used in bit level programming
    //                     (knowing binary is important for this topic)

    //  & = AND
    //  | = OR
    //  ^ = XOR
    //  << Left Shift
    //  >> Right Shift

    int x =6;       // 6  = 00000110
    int y = 12;     // 12 = 00001100
    int z = 0;      // 0  = 00000000

    /*z = x & y;
    printf("AND = %d\n", z);*/

    /*z = x | y;
    printf("OR = %d\n", z);*/

    
    /*z = x ^ y;
    printf("XOR = %d\n", z);*/

    /*z = x << 1;                                 // Essentially doubles
    printf("Shift Left = %d\n", z);*/

    z = x >> 1;                                 // Essentially Halves
    printf("Shift Right = %d\n", z);

    return 0;
}