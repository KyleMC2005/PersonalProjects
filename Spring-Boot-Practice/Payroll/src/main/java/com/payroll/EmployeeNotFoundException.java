package com.payroll;

public class EmployeeNotFoundException extends RuntimeException{

    EmployeeNotFoundException(Long id) {
        super("Cound not find employee " + id);
    }
}
