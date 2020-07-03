package com.groupepsih.psihback.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AppUserNotFoundExceprion extends RuntimeException {
    private String resourceName;
    private String userfield;
    private Object getUserfieldValue;

    public AppUserNotFoundExceprion(String resourceName, String userfield, Object getUserfieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, userfield, getUserfieldValue));
        this.resourceName = resourceName;
        this.userfield = userfield;
        this.getUserfieldValue = getUserfieldValue;
    }
}
