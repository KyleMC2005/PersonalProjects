#ifndef HISTORY_H
#define HISTORY_H

#define MAX_HISTORY 20

struct CommandHistory {
    char *commands[MAX_HISTORY]; // List of commands
    int index; // Insertion point
    int count; // Total commands in history
};

/** @brief Checks for prior command history, if none return a new struct
 * @return A CommandHistory struct
*/
struct CommandHistory *getCommandHistory(void);

/** @brief Add to the saved history */
void addHistory(struct CommandHistory *hist, char *input);

/** @brief Clear out the saved history & free all memory */
void clearCommandHistory(struct CommandHistory *hist);

/** @brief Get & return a previous command string */
char *executeHistory(struct CommandHistory *hist, int index);

void printHistory(struct CommandHistory *hist);

#endif