#include <stdio.h>
#include <string.h>

typedef struct Student
{
    char name[12];
    float gpa;
}Student;


int main()
{
    Student student1 = {"Kyle", 3.6};
    Student student2 = {"Will", 4.0};
    Student student3 = {"Moadd", 4.0};
    Student student4 = {"Rosie", 3.6};
    Student student5 = {"Cara", 3.8};

    Student students[] ={student1, student2, student3, student4, student5};

    for(int i = 0; i < sizeof(students)/sizeof(students[0]); i++)
    {
        printf("%12s\t", students[i].name);
        printf("%.2f\n", students[i].gpa);

    }

    return 0;
}