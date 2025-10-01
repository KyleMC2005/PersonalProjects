#include <stdio.h>

int str_len(char* str){                             
 
 int stringlength = 0;
 
 for (int i = 0; *(str+i) != '\0'; i++) {                                 
    stringlength++;
    }
    return stringlength;
}

char *str_rev_and_copy(char* src, char* dest){

    int i = str_len(src);
    int j = 0;   
   while (i > 0) {                                 
    *(dest+(j)) = *(src+(i-1));
    i--; j++;
    }   
    // At this point they're at the end
    *(dest+str_len(src)) = '\0';
    return dest;
}

char *str_rev(char *str) {
    char *str_ptr = str; // Store starting pointer
    char *str_end = (str+str_len(str)-1); // Store ending pointer
    char temp; // Store temp swap variable

    while(str_end > str_ptr) { // Only loop until the beginning of the string
        temp = *str_ptr; // Set temp to start char

        *str_ptr = *str_end; // Set start to end
        *str_end = temp; // Set end to start

        // Increment Forward/Backward
        str_ptr++;
        str_end--;
    }

    // Return unused pointer
    return str;
}

int str_cmp(char *str1, char *str2){

    // Compare the values of each value, if str1 is greater return 1 or if it's lower return -1
    // If they are the same then move on to the next one, if all of them are identical then return 0

    //Check the length of both strings:
    int i = 0;
    
    while (str1[i] != '\0' || str2[i] != '\0') {
        if (str1[i] > str2[i]) {
            return 1; // str1 is greater than str2
        } 
        else if (str1[i] < str2[i]) {
            return -1; // str1 is less than str2
        }
        i++; // Move to the next character
    }

    return 0; // Both strings are equal
}

char *str_trim(char *strr){

    // Remove any spaces, ie " ", "/n", & "/t" from before the beginning and ending of the string
    int j = str_len(strr)-1;
    int i = 0;

    // Check for any starting whitespace
    while (strr[i] == ' ' || strr[i] == '\n' || strr[i] == '\t'){                     // Skip the whitespace at the beginning
            i++; // If it's equal to any of those it moves to the next index, we just i as the starting point
        }

        while (j >= i && (strr[j] == ' ' || strr[j] == '\n' || strr[j] == '\t')){                     // Skip the whitespace at the end
            j--; // If it's equal to any of those it moves to the previous index, we just j as the ending point
        }

        for (int k = 0; k <= j - i; k++) {                                                          // Creating a trimmed string 
            strr[k] = strr[i+k];
        }

        strr[j-i+1] = '\0';                                                                         // Null terminate 

    return strr;
}

int main(){
		
	char str[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	//printf("%s is %d characters long!\n", str, str_len(str));
	
	// This part tests only str_cpy, uncomment it when str_len is working

	char str2[100] = "XXXXXXXXXXXXXXXXXXXXXXXXXX   If you're reading this, you probably forgot a '\\0'!";

    char stra[] = "abcd";
    char strb[] = "abcd";

    char strr[] = "     \t\t\nHello, World!\n\n\tGoodbye, World!   \t\n  ";
	
	//str_rev_and_copy(str, str2);
    printf("%s", str_rev(str));
    //str_cmp(stra, strb);
    //str_trim(strr);

}