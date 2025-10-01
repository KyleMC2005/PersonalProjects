#include <stdio.h>

int main()
{

    // When wanting to specify an address, use double backslashes before the name
    FILE *pF = fopen("C:\\Users\\kyle2\\OneDrive\\Desktop\\test.txt", "w");                   // Data type is File, it's a pointer for the File, and we use fopen to open a file called test.txt, with "w" to write
                                                                                             // "w" is write, "a" is append

    fprintf(pF, "\nPatrick Star");                                                           // Will print this text to the file


    fclose(pF);                                                                             // To close the file when we're done with it
    
    remove("test.txt");

   // This deletes the file, and then sends a message, if the file isn't found it sends an alternate message
   /*
   if(remove("test.txt") == 0)
   {
        printf("That file was deleted successfully!");
   }
   else 
   {
        printf("That file was not deleted!");
   }
   */
    return 0;
}