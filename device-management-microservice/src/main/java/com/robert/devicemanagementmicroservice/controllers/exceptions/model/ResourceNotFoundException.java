package com.robert.devicemanagementmicroservice.controllers.exceptions.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ResourceNotFoundException extends CustomException{
    private static final String MESSAGE = "Rsource not found";
    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    public ResourceNotFoundException(String resource) {
        super(MESSAGE, httpStatus, resource, new ArrayList<>());
    }
}
