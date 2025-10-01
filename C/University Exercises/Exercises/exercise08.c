#include <stdio.h>

int str_len(char* str){                             
 
 int stringlength = 0;
 
 for (int i = 0; *(str+i) != '\0'; i++) {                                 
    stringlength++;
    }
    return stringlength;
}
                                   
void str_cpy(char* src, char* dest){

    int i = 0;   
   while (*(src+i) != '\0') {                                 
    *(dest+i) = *(src+i);
    i++;
    }   
    // At this point they're at the end
    *(dest+i) = '\0';        
}

int main(){
		
	char* str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	//printf("%s is %d characters long!\n", str, str_len(str));
	
	// This part tests only str_cpy, uncomment it when str_len is working

	char str2[100] = "XXXXXXXXXXXXXXXXXXXXXXXXXX   If you're reading this, you probably forgot a '\\0'!";
	
	str_cpy(str, str2);
	
	printf("Copied from:\t%s\nCopied to:\t%s", str, str2);
	
    
}
