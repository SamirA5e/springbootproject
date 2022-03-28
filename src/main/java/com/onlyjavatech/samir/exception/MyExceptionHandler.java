package com.onlyjavatech.samir.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class MyExceptionHandler {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//  ----   You can use multiple exception like in {} -----
//  @ExceptionHandler(value = {NullPointerException.class,NumberFormatException.class,ArrayIndexOutOfBoundsException.class})
    @ExceptionHandler(value = NullPointerException.class)
//  Declare ResponseBody if we use @ControllerAdvice
//  @ResponseBody
    public String exceptionHandlerNull() {
        System.out.println("---  null page  ---");
        return "nullPage";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandlerGeneric() {
        return "nullPageException";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = EntityNotFoundException.class)
    public String exceptionEntityNotFound() {
        return "Id is not present....";
    }
}
