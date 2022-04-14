package com.onlyjavatech.samir.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class MyExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//  ----   You can use multiple exception like in {} -----
//  @ExceptionHandler(value = {NullPointerException.class,NumberFormatException.class,ArrayIndexOutOfBoundsException.class})
    @ExceptionHandler(value = NullPointerException.class)
//  Declare ResponseBody if we use @ControllerAdvice
//  @ResponseBody
    public String exceptionHandlerNull(NullPointerException exception) {
        LOGGER.error("{} - {}", HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        return "nullPage";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandlerGeneric(Exception exception) {
        LOGGER.error("{} - {}", HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        return "nullPageException";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = EntityNotFoundException.class)
    public String exceptionEntityNotFound(EntityNotFoundException exception) {
        LOGGER.error("{} - {}", HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        return "Id is not present....";
    }

    @ExceptionHandler(value = ObjectNotFoundException.class)
    public ErrorResponse objectNotFoundException(ObjectNotFoundException exception) {
        LOGGER.error("{} - {}", exception.getStatus(), exception.getMessage(), exception);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(exception.getStatus());
        errorResponse.setMessage(exception.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        LOGGER.error("{} - {}", HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage(exception.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(value = ValidationHandler.class)
    public ErrorResponse validationHandler(ValidationHandler exception) {
        LOGGER.error("{} - {}", exception.getStatus(), exception.getMessage(), exception);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage(exception.getMessage());
        return errorResponse;
    }

}
