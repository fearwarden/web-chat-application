package com.cybertaur.webchatapplication.users.exceptions;

import com.cybertaur.webchatapplication.users.exceptions.throwables.UserNotFoundException;
import com.cybertaur.webchatapplication.utils.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpResponse<Object> userNotFoundHandler(UserNotFoundException ex) {
        return new HttpResponse<>(false, ex.getMessage(), null);
    }
}
