#include <stdio.h>
#include <ctype.h>

int main()
{
    char questions[][100] = {"1. What year did the C language debut?: ", 
                             "2. Who is credited with creating C?: ",
                             "3. What is the predecessor of C?: "};

    char options[][100] = {"A. 1969", "B. 1972", "C. 1975", "D. 1999",
                           "A. Dennis Richie", "B. Nikola Tesla", "C. John Carmack", "D. Doc Brown",
                           "A. Objective C", "B. B", "C. C++", "D. C#"};

    char answers[3] = {'B', 'A', 'B'};
    int numberofQuestions = sizeof(questions)/sizeof(questions[0]);

    char guess;
    char score;

    printf("QUIZ GAME\n");

    for(int i = 0; i < numberofQuestions; i++)
    {
        printf("**************************\n");
        printf("%s\n", questions[i]);
        printf("**************************\n");

        for(int j = (i*4); j < (i * 4) + 4; j++)
        {
            printf("%s\n", options[j]);
        }

        printf("guess: ");
        scanf(" %c", &guess);

        guess = toupper(guess);         // making it uppercase

        if(guess == answers[i])
        {
            printf("Correct!\n");
            score++;
        }
        else 
        {
            printf("Wrong!\n");
        }
    }

    printf("**************************\n");
    printf("Final Score: %d/%d\n", score, numberofQuestions);
    printf("**************************\n");


    return 0;
}