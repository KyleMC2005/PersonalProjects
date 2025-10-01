#include <stdio.h>

/*void print_array(int* ptr, int len){
        for (int i = 0; i < len; i++){
            if (i == len-1) {
                printf("%d", *(ptr+i));
            }
            else {
            printf("%d, ", *(ptr+i));
        }
    }
}*/

void print_2d_array(char* ptr, int ylen, int xlen){

    // Okay so apparently I just print everything in a 2d array, by using commas for x, and newline for y
    
    int row = ylen;
    int column = xlen;
    
    for (int i = 0; i < row; i++){
        for (int j = 0; j < column; j++){
            if (j == xlen-1){
                printf("%c", *(ptr + i * column + j));
            }
            else {printf("%c, ", *(ptr + i * column + j));}
        }
        printf("\n");
    }
}

int main(){
	
	int arr[50] = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29};
	
	//print_array(arr, 15);
	
	//This part tests print_2d_array, uncomment it when print_array is working

	char arr2[4][5] = {
		{'A', 'B', 'C', 'D', 'E'},
		{'F', 'G', 'H', 'I', 'J'},
		{'K', 'L', 'M', 'N', 'O'},
		{'P', 'Q', 'R', 'S', 'T'},
	};
	
	print_2d_array(*arr2, 4, 5);
	
		

}