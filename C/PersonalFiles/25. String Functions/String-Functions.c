#include <stdio.h>
#include <string.h>     // This is for String Functions

int main(){

    char string1[] = "Kyle";
    char string2[] = "McCann";

    strlwr(string1);                            // Converts a string to lowercase
    strupr(string1);                            // Converts a string to uppercase
    strcat(string1, string2);                   // Appends String2 to end of String1
    strncat(string1, string2, 1);               // Appends n characters from String2 to String1
    strcpy(string1, string2);                   // Copy String2 to String1
    strncpy(string1, string2, 1);               // copy n characters of string2 to string1

    strset(string1, '?');                       // Sets all characters of a tring to a given character
    strnset(string1, 'x', 1);                   // Sets first n characters of a string to a given character
    strrev(string1);                            // Reverses string

    int result = strlen(string1);               // Returns string length as int
    int result = strcmp(string1, string2);      // String compare all characters
    int result = strncmp(string1, string2, 1);  // String compare n characters
    int result = strcmpi(string1, string1);     // String compare all (ignore case)
    int result = strnicmp(string1, string1, 1); // String compare n characters (ignore case)


    printf("%s", string1);
    return 0;
}