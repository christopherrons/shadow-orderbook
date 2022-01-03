package com.christopherrons.shadoworderbook.rest.subscription.errorhandling;

import com.christopherrons.shadoworderbook.rest.subscription.errorhandling.model.ApiException;
import com.christopherrons.shadoworderbook.rest.subscription.errorhandling.exceptions.ApiRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(final ApiRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiException, badRequest);
    }

}
