#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <ctype.h>
#include <sys/wait.h>

#include "commands.h"
#include "userinput.h"
#include "history.h"

struct Command {
    const char *command;
    void (*function)(char **tokens);
};

struct Command commands[] = {
    {"exit", (void (*)(char **))exitProgram},
    {"getpath", (void (*)(char **))getPath},
    {"setpath", (void (*)(char **))setPath},
    {"cd", (void (*)(char **))changeDir},
    {NULL, NULL}  // Marks the end of the array
};

void executeSystemCommand(char **tokens) {
    // Make fork
    pid_t pid = fork();
    
    // Error when forking
    if (pid < 0) { 
        printf("Error: Forking failed.\n");
        exit(EXIT_FAILURE);
    }

    // Child process
    if(pid == 0) { 
        // Ignore the default "command not found" error message entirely
        if(execvp(tokens[0], tokens) == -1) {
            // Simply exit the child without printing anything
            exit(EXIT_FAILURE);
        }
    } 
    
    // Parent process
    else {
        wait(NULL);
    }
}

int executeCommand(char **tokens) {
    int i = 0;

    while(commands[i].command != NULL) {
        if(strcmp(tokens[0], commands[i].command) == 0) {
            commands[i].function(tokens);
            return 1;
        }

        i++;
    }

    return 0;
}

void tokenizeInput(char *inp, char **out) {
    // Counter
    int i = 0;

    // Get the first token in the String
    char *token = strtok(inp, " \t|<>&;\n");
    
    // While we have tokens
    while(token != NULL) {
        // Set the token to the array index
        out[i] = token;

        // Increment counter
        i++;

        // Get new token, if any, from String
        token = strtok(NULL, " \t|<>&;\n");
    }
    
    out[i] = NULL;
}

void checkForCommands(char **tokens, struct CommandHistory *hist, char *input) {
    // Do command at index x
    if(tokens[0][0] == '!' && isdigit(tokens[0][1])) {
        // Get user inputted index
        int history_num = atoi(&tokens[0][1]); // Get history index

        // Get tokens in history
        char *hist_input = executeHistory(hist, history_num);

        if(hist_input == NULL) {
            printf("Error when retrieving history command\n");
            return;
        }

        char *hist_tokens[512];
        
        // Tokenize history command String
        tokenizeInput(hist_input, hist_tokens);

        // Execute command with history tokens
        int executed = executeCommand(hist_tokens);

        if(!executed) {
            executeSystemCommand(hist_tokens);
        }

        free(hist_input);
        return;
    }

    // Do command at index x (with dash)
    else if(tokens[0][0] == '!' && tokens[0][1] == '-' && isdigit(tokens[0][2])) {
        int history_num = atoi(&tokens[0][2]); // Get history index

        char *hist_input = executeHistory(hist, history_num);

        if(hist_input == NULL) {
            printf("Error when retrieving history command\n");
            return;
        }

        char *hist_tokens[512];

        tokenizeInput(hist_input, hist_tokens);

        int executed = executeCommand(hist_tokens);

        if(!executed) {
            executeSystemCommand(hist_tokens);
        }

        free(hist_input);
        return;
    }

    // Do last command only
    else if(tokens[0][0] == '!' && tokens[0][1] == '!') {
        char *hist_input = executeHistory(hist, 0);

        if(hist_input == NULL) {
            printf("Error when retrieving history command\n");
            return;
        }

        char *hist_tokens[512];

        tokenizeInput(hist_input, hist_tokens);

        int executed = executeCommand(hist_tokens);

        if(!executed) {
            executeSystemCommand(hist_tokens);
        }

        free(hist_input);
        return;
    }

    else if(strcmp(tokens[0], "history") == 0) {
        printHistory(hist);
    }

    // Its not a history call, so execute normal command
    else {
        // Execute regular command
        int executed = executeCommand(tokens);

        addHistory(hist, input);

        // If there is no reference to the command in our struct, assume its a system command
        if(!executed) {
            executeSystemCommand(tokens);
        }
    }
}