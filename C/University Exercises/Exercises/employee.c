#include <stdio.h>
#include "employee.h"
#include <stdlib.h>

//Creation of Functions
Employee* new_employee_array(int n_employees){
    Employee* employees = malloc(n_employees *  sizeof(Employee));
    return employees;
}

// This is where the fun begins 
void enter_details(Employee* employees){
    // User input for employee
    
    printf("Enter details of employee %d: ", employees->employee_number);
    scanf("%49s %49s %49s %f", employees->first_name, employees->last_name, employees->job_title, &employees->salary);
    }

Employee* get_highest_paid(Employee* employees,int n_employees){
    Employee* maximum = &employees[0];
    float max = 0;
    for (int i = 0; i < n_employees; i++){
        if (max < employees[i].salary){
        max = employees[i].salary;
        maximum = &employees[i];
        }
    }
    return maximum;
}
void print_details(Employee* highest){
printf("----------------------------------\n");
printf("Employee Record\n");
printf("----------------------------------\n");
printf("Employee number: %d\n", highest->employee_number);
printf("Name:            %s, %s\n", highest->last_name, highest->first_name);
printf("Job Title:       %s\n", highest->job_title);
printf("Salary:          %.2f\n", highest->salary);
printf("----------------------------------\n");
}

void delete_employee_array(Employee** employees){
free(*employees);
*employees = NULL;
}