package com.luv2code.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {
    //Add Exception Handling code
    //Add an exception handler using @ExceptionHandler

    //This Exception handler will catch StudentNotFoundException, and the following code response will be returned

    //Earlier we had put the @ExceptionalHandler and the corresponding code in the specific rest controller, but with the help of @ControllerAdvice we can declare them globally
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> amiHandleException(StudentNotFoundException exc){
        //create a StudentErrorResponse
        StudentErrorResponse error=new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        //return ResponseEntity
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
        //First one is the body of the response and second one is the status code of the response
    }

    //Add another exception handler ... to catch any exception (catch all)

    //Generic exception to catch any exception type
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> amiHandleException(Exception exc){
        StudentErrorResponse error=new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        //return ResponseEntity
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
