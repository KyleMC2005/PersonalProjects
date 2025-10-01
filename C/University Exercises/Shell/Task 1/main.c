#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "main.h"

void tokenizeInput(char *inp, char **out) {
    int i = 0; // Pointer counter

    // Can be 1 big string, but easier to see the delimiters this way
    char delims[] = {' ', '\t', '|', '<', '>', '&', ';'};

    // Get 1st token
    char *token = strtok(inp, delims);

    // Whilst there are tokens
    while(token != NULL) {
        out[i] = token; // Store token in string
        i++;

        token = strtok(NULL, delims); // Get next token
    }

    out[i] = NULL; // Null terminate
}

void removeTrailingWhitespaces(char *string) {
    int i = 0, j = 0;

    // Skip leading whitespaces
    while(string[i] == ' ' ||
    string[i] == '\t' || 
    string[i] == '|' || 
    string[i] == '<' || 
    string[i] == '>' || 
    string[i] == '&' || 
    string[i] == ';') i++;


    if(string[i+1] == '\0') {
        printf("Empty String!\n");
        return;
    }

    while((string[j++] = string[i++])); // Shift chars over

    // Remove trailing newline on the input string
    string[strlen(string)-1] = '\0';
}

void exitProgram() {
    printf("\nExiting Shell...\n");
    exit(0);
}

void prompt() {
    char input[1024]; // Capture user input
    char *tokens[512]; // Truncate string to right size in case of chars exceeding limit
    
    printf("> "); // Display prompt

    // Get user input & store it
    char *detected_input = fgets(input, sizeof input, stdin);

    // Check for EOF (CTRL + D on Linux, CTRL + C on Windows)
    if(detected_input == NULL) {
        exitProgram();
    }

    // If no EOF, check user input string
    else {
        // Check to see if we exceed the character limit, if we do print message & return
        if(strlen(input) > 512) {
            printf("Too many characters! (> 512)\n");
            return;
        }

        // Empty string? Return
        else if(input[0] == '\n') {
            return;
        }

        // No issues with the initial input, continue to processing
        else {
            removeTrailingWhitespaces(input);
            tokenizeInput(input, tokens);

            // Print out tokens to console
            for(int i = 0; tokens[i] != NULL; i++) {
                printf("%d => \"%s\"\n", i, tokens[i]);
            }

            // if the 1st token is the exit command
            if(strcmp(tokens[0], "exit") == 0) {
                exitProgram();
            }
        }
    }
}

// ? MAIN LOOP

int main(void) {
    // Infinite loop until exit
    while(1) {
        prompt();
    };
    
    return 0;
}