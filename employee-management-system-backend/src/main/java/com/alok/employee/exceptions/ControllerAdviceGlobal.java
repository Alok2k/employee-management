package com.alok.employee.exceptions;

import com.alok.employee.customexception.EmployeeNotFoundException;
import com.alok.employee.customexception.EmptyInputException;
import com.alok.employee.customexception.EmptyListException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice   //handles exception Globally
public class  ControllerAdviceGlobal extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = EmptyInputException.class)
    public ResponseEntity<List> handleEmptyInput(EmptyInputException eie){
        return new ResponseEntity<List>
                (Collections.singletonList("empty Inputs "), HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(value = EmptyListException.class)
    public ResponseEntity<String> handleEmptyInput(EmptyListException eie){
        return new ResponseEntity<String>
                ("empty List  ", HttpStatus.BAD_REQUEST);
    }




    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException nsee){
        return new ResponseEntity<String>
                ("No Values avilable in Database", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException employeeNotFoundException){
        return new ResponseEntity<String>("No Employee for this id", HttpStatus.NOT_FOUND);
    }




    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported
            (HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
             HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>("change Http method type",HttpStatus.METHOD_NOT_ALLOWED);
    }


}
