#include <stdio.h>
#include <math.h>

int main(){

    const double PI = 3.14159;
    double radius;
    double circumference;
    double area;

    printf("\nEnter the radius of a circle: ");
    scanf("%lf", &radius);

    circumference = 2*PI*radius;
    area = PI*radius*radius;

    //other way to calculate the area, using the math function we just learned
    //area = PI * pow(radius, 2);

    printf("Circumference: %lf", circumference);
    printf("\nArea: %lf", area);
    return 0;
}