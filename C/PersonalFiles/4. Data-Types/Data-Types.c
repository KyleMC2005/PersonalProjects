#include <stdio.h>
#include <stdbool.h>

int main(){

    // point of this program is to essentially show all of the data types we'll commonly see

    char a = 'C';                   // single character         %c
    char b[] = "Kyle";               // array of characters      %s (s is like for string)

    float c = 3.141592;             // 4 bytes (32 bits of precision) 6 - 7 digits %f
    double d = 3.131592653589793;   // 8 bytes (64 bits of precision) 15 - 16 digits %lf (lf is like long float kinda)

    bool e = true;                  // 1 byte (true or false, ie 1 or 0) %d

    char f =100;                    // 1 byte (-128 to 127) %d or %c
    unsigned char g = 255;          // 1 byte (0 to 255) %d or %c

    short int h = 32767;            // 2 bytes (-32768 to 32767) %d
    unsigned short int i = 65535;   // 2 bytes (0 to 65535) %d, since it is unsigned, we do not use negative numbers, 
                                    // meaning that the bits are used solely for 0 and positive numbers, resulting in being able to get double the range positively
    
    int j = 2147483647;             // 4 bytes (-2,147,483,648 to 2,147,483, 647) %d
    unsigned int k = 4294967295;    // 4 bytes (0 to 4,294,967,295) %u

    long long int l = 9223372036854775807; // 8 bytes (-9 quintillion to 9 quintillion) %ll
    unsigned long long int m =5;    // You get the point, it's fucking long, (0 to 18 quintillion) %ll

    // If we want to format a number, like a float number, we can show the first 15 digits for example, by doing this:

    printf("%0.15f\n", c);           // this will now print c with 15 digits as requested

    return 0;
}