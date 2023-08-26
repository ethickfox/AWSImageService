package com.epam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException() {
        super("No such Image exists");
    }
}
