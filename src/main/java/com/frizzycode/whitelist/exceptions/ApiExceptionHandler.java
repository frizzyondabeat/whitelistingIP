package com.frizzycode.whitelist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(InvalidIpException.class)
    public ResponseEntity<Object> handleInvalidIpRequestException(InvalidIpException exception){
        return new ResponseEntity<>(
                ExceptionBlueprint.builder()
                        .exception(exception.getMessage())
                        .httpStatus(HttpStatus.FORBIDDEN)
                        .time(ZonedDateTime.now()),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(IpNotFoundException.class)
    public ResponseEntity<Object> handleRequestException(IpNotFoundException exception){
        return new ResponseEntity<>(
                ExceptionBlueprint.builder()
                        .exception(exception.getMessage())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .time(ZonedDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }
}
