package com.lgrochal.socialapp.interfaces.exception;

import com.lgrochal.socialapp.service.exception.ResourceNotFoundException;
import com.lgrochal.socialapp.service.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class, ServiceException.class})
    @ResponseBody public ErrorDTO handleBadRequest(Exception exception) {
        return new ErrorDTO(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseBody public ErrorDTO handleNotFound(Exception exception) {
        return new ErrorDTO(exception.getMessage());
    }
}
