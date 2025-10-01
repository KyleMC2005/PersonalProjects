#include <stdio.h>
#include <ctype.h> // We include this because of some String Functions

int main(){

    char unit;
    float temp;

    printf("\nIs the temperature in (F) or (C)?: ");            
    scanf("%c", &unit);                                         // User inputs whether the temperature is in Farenheit or Celsius

    unit = toupper(unit);                                       // This line makes the Unit in uppercase no matter what (lowercase would be tolower)

    if(unit == 'C'){
        printf("\nEnter the temperature in Celsius: ");
        scanf("%f", &temp);
        temp = (temp * 9 / 5) + 32;                             // The formula for converting from Celsius to Farenheit
        printf("The temperature in Farenheit is:  %.1f", temp);
    }
    else if(unit == 'F'){
        printf("\nEnter the temperature in Farenheit: ");
        scanf("%f", &temp);
        temp = ((temp - 32) *5) / 9;                             // The formula for converting from Celsius to Farenheit
        printf("The temperature in Celsius is:  %.1f", temp);
    }
    else{
        printf("\n %c is not a valid unit of measurement", unit);
    }


    return 0;
}