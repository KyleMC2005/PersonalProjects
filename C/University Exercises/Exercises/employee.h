#include <stdio.h>

typedef struct Employee {
int employee_number;
char first_name[50];
char last_name[50];
char job_title[50];
double salary;
}Employee; 

//functional prototyping 

Employee* new_employee_array(int n_employees);
Employee* get_highest_paid(Employee* employees,int n_employees);
void print_details(Employee* highest);
void enter_details(Employee* employees);
void delete_employee_array(Employee** employees);