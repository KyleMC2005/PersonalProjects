#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <time.h>

void resetBoard();
void printBoard();
int checkFreeSpaces();
void playerMove();                                      // Declaring the functions before main, aka function prototyping
void computerMove();
char checkWinner();
void printWinner(char);

// Declare Global Variables
char board[3][3];                                       // 2D Array of [3][3] since it is a 3x3 board
const char PLAYER = 'X';
const char COMPUTER = 'O';

int main()
{
    char Winner = ' ';                                  // Blank as the winner has not been decided yet
    char response;                                      // Decides whether to play again later

    do
    {
        Winner = ' ';
        response = ' ';
        resetBoard();                                    // This is to refresh the board after each game

    while(Winner = ' ' && checkFreeSpaces() != 0)       // Will loop while there isn't a winner and there is still empty spaces
    {
        printBoard();                                   // This is to print the freshly made board to be used

        playerMove();
        Winner = checkWinner();                         // Checking for a Winner
        if(Winner != ' ' || checkFreeSpaces() == 0)
        {
            break;
        }

        computerMove();
        Winner = checkWinner();                         // Checking for a Winner
        if(Winner != ' ' || checkFreeSpaces() == 0)
        {
            break;
        }
    }    

    printBoard();
    printWinner(Winner);

    printf("\nWould you like to play again? (Y/N): ");   // The program will repeat until a character other than 'Y' is entered   
    scanf(" %c", &response);
    response = toupper(response);                        // Ensures that lowercase letters are accepted, to avoid 'y' being counted as 'N'
    } while (response == 'Y');
    
    printf("\nThanks for playing!");

    return 0;
}

void resetBoard()                                       // Resets the board after every game
{
    for(int i = 0; i <3; i++)
    {
        for(int j = 0; j <3; j++)
        {   
            board[i][j] = ' ';                          // Nested Loop so that every entry in the 2D array (Board) is changed back to empty space for new games
        }
    }
}
void printBoard()
{
    printf(" %c | %c | %c ", board[0][0], board[0][1], board[0][2]);
    printf("\n---|---|---\n");
    printf(" %c | %c | %c ", board[1][0], board[1][1], board[1][2]);        // Board Design 
    printf("\n---|---|---\n");
    printf(" %c | %c | %c ", board[2][0], board[2][1], board[2][2]);
    printf("\n");
}
int checkFreeSpaces()                                                       // Tracks how many free spaces are left in the board, once this decrements to 0, game ends (in main)
{
    int freeSpaces = 9;

    for(int i = 0; i <3; i++)
    {
        for(int j = 0; j <3; j++)
        {
            if(board[i][j] != ' ')
            {
                freeSpaces--;
            }
        }
    }
    return freeSpaces;
}
void playerMove()                                                           // Allows the player to select a move, and checks that it's valid, otherwise go again
{
    int x;
    int y;

    do
    {
        printf("Enter row #(1-3): ");
    scanf("%d", &x);
    x--;

    printf("Enter column #(1-3): ");
    scanf("%d", &y);
    y--;

    if(board[x][y] != ' ')
    {
        printf("Invalid Move!\n");
    }
    else 
    {
        board[x][y] = PLAYER;
        break;                                                              // This is to break out of the while loop
    }
    } while (board[x][y] != ' ');
    

}
void computerMove()
{
    // Creates a seed based on current time

    srand(time(0));                                                         // This seeds the random generator
    int x;
    int y;

    if(checkFreeSpaces() > 0)                                               // As long as free spaces is above zero, the below code will run
    {
        do 
        {
            x = rand() % 3;
            y = rand() % 3;
        } while (board[x][y] != ' ');

        board[x][y] = COMPUTER;                                             // Randomly generates a move for the computer, ensuring that it's valid
    }
    else 
    {
        printWinner(' ');
    }

}
char checkWinner()                                                          // Checks to see if there's a winner 🤯
{
    // Check Rows
    for(int i = 0; i < 3; i++)
    {
        if(board[i][0] == board[i][1] && board[i][0] == board[i][2])        // This part checks to see if the contents of the rows all match, if so we have a winner
        {
            return board[i][0];
        }
    }
    // Check Columns
    for(int i = 0; i < 3; i++)
    {
        if(board[0][i] == board[1][i] && board[0][i] == board[2][i])        // This part does the same but with columns
        {
            return board[0][i];
        }
    }

    // Check Diagonals
        if(board[0][0] == board[1][1] && board[0][0] == board[2][2])        // This does one diagonal
        {
            return board[0][0];
        }
        if(board[0][2] == board[1][1] && board[0][2] == board[2][0])        // This does the other diagonal
        {
            return board[0][2];
        }
    
        return ' ';
}
void printWinner(char Winner)                                               // Takes the Winner, uses an if to see who it is, and prints the appropriate message
{
    if(Winner == PLAYER)
    {
        printf("YOU WIN!");
    }
    else if(Winner == COMPUTER)
    {
        printf("YOU LOSE!");
    }
    else 
    {
        printf("IT'S A TIE!");
    }
}