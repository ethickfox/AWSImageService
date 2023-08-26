package com.epam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

@ControllerAdvice
public class ImageControllerExceptionHandler {
    @ExceptionHandler(value = {NoSuchKeyException.class})
    public ResponseEntity<String> handleConflict(Exception ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
