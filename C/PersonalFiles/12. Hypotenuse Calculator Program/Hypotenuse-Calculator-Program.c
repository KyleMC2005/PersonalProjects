#include <stdio.h>
#include <math.h>

int main(){

    double A;
    double B;
    double C;

    printf("Please enter Side A: ");
    scanf("%lf", &A);

    printf("Please enter Side B: ");
    scanf("%lf", &B);

    C = sqrt(A*A +B*B);

    printf("Hypotenuse Length: %lf", C);

    return 0;
}