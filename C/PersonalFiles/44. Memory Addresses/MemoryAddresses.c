#include <stdio.h>

int main()
{
    // Memory = an array of bytes within RAM (street)
    // Memory Block = a single unit (Byte) within memory, used to hold some value (person)
    // Memory Address = the address of where a memory block is located (house address)

    char a = 'X';
    char b = 'Y';
    char c = 'Z';

    short d = 'X';
    short e = 'Y';
    short f = 'Z';

    int g = 'X';
    int h = 'Y';
    int i = 'Z';

    double j = 'X';
    double k = 'Y';
    double l = 'Z';

    // If I create an array, the bytes it uses depends on the type of array, and how many variables we limit to the Array, ie a char array will
    // Take up 1 byte per variable, whereas a double would be 8 per variable
    char m[2] = {'a'};
    short n[2] = {'a'};

    printf("Char only uses 1 byte of memory\n");
    printf("%d bytes\n", sizeof(a));
    printf("%d bytes\n", sizeof(b));
    printf("%d bytes\n", sizeof(c));

    printf("%p\n", &a);                       // This will display the address of variable a
    printf("%p\n", &b);                       // This will display the address of variable b
    printf("%p\n", &c);                       // This will display the address of variable c
    printf("\n");
    printf("**************************\n");

    printf("\nShort uses 2 bytes of memory, so you will notice that the hexadecimal addresses will have a gap of two for these:\n");
    printf("%d bytes\n", sizeof(d));
    printf("%d bytes\n", sizeof(e));
    printf("%d bytes\n", sizeof(f));
    printf("%p\n", &d);                       // This will display the address of variable d
    printf("%p\n", &e);                       // This will display the address of variable e
    printf("%p\n", &f);                       // This will display the address of variable f
    printf("\n");
    printf("**************************\n");

    printf("\nint uses 4 bytes of memory, so you will notice that the hexadecimal addresses will have a gap of four for these:\n");
    printf("%d bytes\n", sizeof(g));
    printf("%d bytes\n", sizeof(h));
    printf("%d bytes\n", sizeof(i));
    printf("%p\n", &g);                       // This will display the address of variable d
    printf("%p\n", &h);                       // This will display the address of variable e
    printf("%p\n", &i);                       // This will display the address of variable f
    printf("\n");
    printf("**************************\n");

    printf("\ndouble uses 8 bytes of memory, so you will notice that the hexadecimal addresses will have a gap of eight for these:\n");
    printf("%d bytes\n", sizeof(j));
    printf("%d bytes\n", sizeof(k));
    printf("%d bytes\n", sizeof(l));
    printf("%p\n", &j);                       // This will display the address of variable d
    printf("%p\n", &k);                       // This will display the address of variable e
    printf("%p\n", &l);                       // This will display the address of variable f
    printf("\n");
    printf("**************************\n");

    // The amount of bytes this takes up is dependent not just on the type, but how many variables it holds
    printf("This char uses %d bytes\n", sizeof(m));
    printf("%p\n", &m);
     printf("This short uses %d bytes\n", sizeof(n));
    printf("%p\n", &n);

    return 0;
}