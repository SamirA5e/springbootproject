package com.onlyjavatech.samir;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

//@RestController
//@Controller
@RestControllerAdvice
public class MyExceptionHandler{

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(value = {NullPointerException.class,NumberFormatException.class,ArrayIndexOutOfBoundsException.class})
    @ExceptionHandler(value = NullPointerException.class)
//    @ResponseBody
    public String exceptionHandlerNull(){
        System.out.println("---  null page  ---");
        return "nullPage";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandlerGeneric(){
        System.out.println("---  null exception page  ---");
        return "nullPageException";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = EntityNotFoundException.class)
    public String exceptionEntityNotFound(){
        System.out.println("---  null exception page  ---");
        return "Id is not present....";
    }
}
