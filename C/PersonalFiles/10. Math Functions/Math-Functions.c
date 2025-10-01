#include <stdio.h>
#include <math.h>               // include this line to include math functions

int main(){

    double A = sqrt(9);         // square root of a number
    double B = pow(2, 5);       // powers, in this case 2^5
    double C = round(3.14);     // rounds numbers to the nearest integer
    double D = ceil(3.14);      // always rounds up
    double E = floor(3.99);     // always rounds down
    double F = fabs(-100);      // absolute value, the distance from 0
    double G = log(3);          // logarithms
    double H = sin(45);         // trig, sin
    double I = cos(45);         // trig, cosine
    double J = tan(45);         // trig, tangent

    printf("\n%lf", J);

    return 0;
}