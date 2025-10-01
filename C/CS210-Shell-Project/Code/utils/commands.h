#ifndef COMMANDS_H
#define COMMANDS_H

extern char original_path[1024];

/** @brief Get the current working directory */
void getPath(void);

/** @brief Set the current working directory
 * @param tokens The tokenized command String
*/
void setPath(char **tokens);

/** @brief Change the working directory
 * @param tokens The tokenized command String
*/
void changeDir(char **tokens);

/** @brief Exit the shell */
void exitProgram(void);

#endif