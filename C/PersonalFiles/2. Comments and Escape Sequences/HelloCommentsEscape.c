#include <stdio.h>

int main() {
    

    //These are comments obviously, we use these in c too

    /*
    This is how you do a multiple line comment 
    Again, like Java, how crazy 🤯
    */

   /* Escape Sequence = character combination consisting of a backslash /
      followed by a letter or combination of digits.
      They specify actions within a line or a string of text.
      \n = newline
      \t = tab
      */

    printf("I like pizza\n");
    printf("I\nlike\n pizza!\n");
    printf("1\t2\t3\n4\t5\t6");

    // Now let's say I have a scenario where I want to use double quotes, like a quote in a quote. I would do it like this:

    printf("\"I like pizza\" - Something I've definitely said");
    return 0;
}