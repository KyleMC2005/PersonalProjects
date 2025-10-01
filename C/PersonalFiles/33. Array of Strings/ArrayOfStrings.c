# include <stdio.h>
# include <string.h>                                            // We include this for the String method copy

int main()
{
    char cars[][10] = {"Mustang", "Corvette", "Camaro"};

    // We cannot do this:
    //cars[0] = "Tesla";

    strcpy(cars[0], "Tesla");                                   // This is how you would edit an entry in a specific part of the Array

    // This will print each entry of the Array 
    for(int i = 0; i < sizeof(cars)/sizeof(cars[0]); i++)
    {
        printf("%s\n", cars[i]);
    }

    return 0;
}