#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "history.h"

struct CommandHistory *getCommandHistory(void) {
    // DO CHECK FOR TASK 6 HERE & RETURN IT
    // if(history in file) { return CommandHistory{hist}; }
    // else { return ...; }

    struct CommandHistory *history = malloc(sizeof(struct CommandHistory));

    if(history == NULL) {
        fprintf(stderr, "Failed to allocate memory for history\n");
        exit(EXIT_FAILURE);
    }

    history -> count = 0;
    history -> index = 0;

    for(int i = 0; i < MAX_HISTORY; i++) {
        history -> commands[i] = NULL;
    }

    return history;
}

void addHistory(struct CommandHistory *hist, char *input) {
    // Free oldest command if its there
    if(hist -> commands[hist -> index] != NULL) {
        free(hist -> commands[hist -> index]);
    } 

    // Store new command
    hist -> commands[hist -> index] = strdup(input);

    // Move index forward
    hist -> index = (hist -> index + 1) % MAX_HISTORY;

    // If haven't filled up history, increment count
    if(hist -> count < MAX_HISTORY) {
        hist -> count++;
    }
}

void clearCommandHistory(struct CommandHistory *hist) {
    for(int i = 0; i < hist -> count; i++) {
        free(hist -> commands[i]);
    }

    hist -> count = 0;
    hist -> index = 0;
}

char *executeHistory(struct CommandHistory *hist, int index) {
    // Check if index is within the bounds of the array
    if(index < 1 || index > hist -> count) {
        printf("Error - Invalid history index\n");
        return NULL;
    }

    // Make memory for tokens
    char *input = hist -> commands[index - 1];

    if(input == NULL) {
        printf("Error - Couldn't find command in history\n");
        return NULL;
    }

    return input;
}

void printHistory(struct CommandHistory *hist) {
    for(int i = 0; i < hist -> count; i++) {
        printf("%d %s", i, hist -> commands[i]);
    }

    printf("Total Count: %d\n", hist -> count);
}