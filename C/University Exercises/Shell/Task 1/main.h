/** @brief Take a string, tokenize it & return it
 * @param inp The string to tokenize
 * @param out The string to return through
*/
void tokenizeInput(char *inp, char **out);

/** @brief Remove all trailing illegal/whitespace characters from a string
 * @param string The string to remove the illegal/whitespace characters from
*/
void removeTrailingWhitespaces(char *string);

/** @brief Exit the shell */
void exitProgram();

/** @brief Display the shell prompt & process user input */
void prompt();