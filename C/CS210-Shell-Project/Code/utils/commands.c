#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include "commands.h"

extern char original_path[1024];

void getPath(void) {
    // Get the current path & print it, if there is an error also print that
    char *path = getenv("PATH");

    printf(path ? "Current PATH: %s\n" : "Error: Could not get PATH\n", path);
}

void setPath(char **tokens) {
    // If there is no path argument 
    if(tokens[1] == NULL) {
        printf("Error: setpath requires a path argument\n");
        return;
    }

    // If we fail to set the path
    if(setenv("PATH", tokens[1], 1) != 0) {
        printf("Error: Could not set PATH\n");
    } 
    
    // Otherwise, we assume it has worked
    else {
        printf("PATH set to: %s\n", tokens[1]);
    }
} 

void changeDir(char **tokens) {
    // If there is a path after the command
    if(tokens[1] != NULL) {    
        // If we change directories successfully
        if(chdir(tokens[1]) == 0) {
            printf("Changed to -> %s!\n", tokens[1]);
        } 
        
        // If something goes wrong when changing
        else {
            printf("Error: Cannot find directory: \"%s\"\n", tokens[1]);
        }
    } 
    
    // If there is no path after the command, just go back 1 directory
    else {
        chdir("../");
    }
}

void exitProgram(void) {
    printf("\nRestoring original PATH...\n");

    // Set the path to the original one before the program started

    setenv("PATH", original_path, 1);

    // if (setenv("PATH", original_path, 1) == 0) {
    //     printf("Original PATH restored: %s\n", original_path);
    // } 
    
    // If we can't, send an error msg
    // else {
    //     printf("Error: Could not restore original PATH\n");
    // }
    
    // Exit shell
    printf("Exiting Shell...\n");
    exit(0);
}
