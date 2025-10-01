#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include "utils/userinput.h"
#include "utils/history.h"

char original_path[1024];

/** @brief Setup the initial values for the shell */
void setupShell(void) {
    char *path = getenv("PATH");

    if(path != NULL) {
        strcpy(original_path, path);
    }

    char *home = getenv("HOME");

    if(home != NULL) {
        if(chdir(home) != 0) {
            printf("Error: Could not change to HOME directory\n");
        }
    }

    char cwd[1024];

    if(getcwd(cwd, sizeof(cwd)) != NULL) {
        printf("Current working directory: %s\n", cwd);
    }

    // TASK 6 GOES HERE
    // Read saved history from file
}

// MAIN LOOP

/** @brief Display the shell prompt & process user input */
int main(void) {
    // Set PATH initially
    setupShell();

    // Get command history
    struct CommandHistory *history = getCommandHistory();
    
    char input[1024];
    char *tokens[512];

    while(1) {
        // Print prompt
        printf("> ");
        
        // Get & check user input for NULL
        if(fgets(input, sizeof(input), stdin) == NULL) {
            break; // EOF (Ctrl + D OR Ctrl + D)
        }

        // Input validation
        if(strlen(input) > 512 || input[0] == '\n') {
            printf("Error occurred with the input\n");
            break;
        }

        // Duplicate input as tokenizeInput irreversibly changes input when used
        char *dupe_input = malloc(strlen(input) + 1);
        strcpy(dupe_input, input); 

        // Tokenize input String into tokens
        tokenizeInput(input, tokens);

        // Check for NULL command name
        if(tokens[0] == NULL) {
            printf("Command is NULL");
            break;
        }

        // Check the tokens against known commands
        checkForCommands(tokens, history, dupe_input);
        free(dupe_input); // Free duplicated input upon completion
    }

    return 0;
}