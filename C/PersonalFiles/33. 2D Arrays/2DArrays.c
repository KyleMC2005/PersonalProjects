# include <stdio.h>

int main()
{
    // 2D Array = An array, where each element is an entire array
    // Useful if you need a matrix, grid, or table of data

    // A 2D Array involves 2 [] of these, and we surround the entire thing with '{}' while also using internal ones for each grid
    // The first square is how many arrays we are allowed within the 2D array, and the second is how many are allowed in each one
    int numbers[3][3] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    // This is another way to declare it, although this is mostly for visual representation to understand how it is allocated easier
    numbers[0][0] = 1;
    numbers[0][1] = 2;
    numbers[0][2] = 3;
    numbers[1][0] = 4;
    numbers[1][1] = 5;
    numbers[1][2] = 6;

    
    // By using hard integer limits for this, it doesn't allow for me to change the size of the 2D Array without having to modify the for Loop
    /*for(int i = 0; i < 2; i++)
    {
        for(int j = 0; j < 3; j++)
        {
            printf("%d ", numbers[i][j]);
        }
        printf("\n");
    }
    */

   // Unless I do this:

   int rows = sizeof(numbers)/sizeof(numbers[0]);
   int columns = sizeof(numbers[0])/sizeof(numbers[0][0]);

   for(int i = 0; i < rows; i++)
    {
        for(int j = 0; j < columns; j++)
        {
            printf("%d ", numbers[i][j]);
        }
        printf("\n");
    }

    return 0;
}