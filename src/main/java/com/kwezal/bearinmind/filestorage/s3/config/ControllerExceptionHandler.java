package com.kwezal.bearinmind.filestorage.s3.config;

import com.kwezal.bearinmind.filestorage.s3.exceptions.ErrorResponse;
import com.kwezal.bearinmind.filestorage.s3.exceptions.InvalidRequestDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    // BAD REQUEST

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequestDataException.class)
    public ErrorResponse handleInvalidRequestDataException(InvalidRequestDataException e) {
        log.info("Invalid request data for properties : " + e.getProperties());

        return new ErrorResponse(e.getErrorCode(), e.getErrorArguments());
    }
}
