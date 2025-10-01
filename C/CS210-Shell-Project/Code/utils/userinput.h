#ifndef USERINPUT_H
#define USERINPUT_H

#ifndef COMMANDS_H
#define COMMANDS_H

#include "history.h"

/** @brief Execute a system command
 * @param tokens The tokenized command String
*/
void executeSystemCommand(char **tokens);

/** @brief Execute a command
 * @param tokens The tokenzied command String
*/
int executeCommand(char **tokens);

/** @brief Take a string, tokenize it & return it
 * @param inp The string to tokenize
 * @param out The string to return through
*/
void tokenizeInput(char *inp, char **out);

/** @brief Take the tokens & check for specific commands
 * @param tokens The tokens to check
*/
void checkForCommands(char **tokens, struct CommandHistory *hist, char *input);

#endif
#endif