#include <stdio.h>

#include "helper.h"

// ? IMPORTANT INFORMATION - TASK 2
// ? STEP 1 - Modify existing code to sort tokens into an array of Strings (max array size of 50)
// ? STEP 2 - fork(), exec() & wait() functions
// ? STEP 2.1 - Figure out how to execute fork() & make the parent process wait()
// ? STEP 2.2 - Figure out how to call exec() on external programs through C
// ? STEP 2.3 - Make sure exec() is only searching through PATH variables(?)

// MAIN LOOP

int main(void) {
    // Infinite loop until exit
    while(1) {
        prompt();
    };
    
    return 0;
}