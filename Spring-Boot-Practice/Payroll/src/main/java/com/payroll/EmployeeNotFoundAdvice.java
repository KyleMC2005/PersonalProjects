package com.payroll;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeNotFoundAdvice {

    @ExceptionHandler(EmployeeNotFoundException.class) // We are handling this exception
    @ResponseStatus(HttpStatus.NOT_FOUND) // When the HttpStatus says it has not found something
    String employeeNotFoundHandler(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }
}
