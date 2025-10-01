#include <stdio.h>

int main()
{
    FILE *pF = fopen("amogus.txt", "r");                        // We use r since we're reading
    char buffer[255];

    // This will store the first line of text into buffer)
    //fgets(buffer, 255, pF)

    if(pF == NULL)
    {
        printf("Unable to open File\n");
    }
    else
    {
        // Using a while loop so that we continuously print until we reach the end of the poem, aka, buffer becomes null
    while(fgets(buffer, 255, pF) != NULL)                           
    {
        printf("%s", buffer);                                       // This will print the contents of buffer
    }
    }

    fclose(pF);

    return 9;
}
