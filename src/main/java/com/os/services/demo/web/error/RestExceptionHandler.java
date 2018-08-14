
package com.os.services.demo.web.error;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.os.services.demo.web.error.ErrorDetail.Develop;

@ControllerAdvice
public class RestExceptionHandler
{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request)
    {
        return this.createResult(rnfe, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex, HttpServletRequest request)
    {
        return this.createResult(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<?> createResult(Exception exception, HttpStatus httpStatus)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(httpStatus.value());
        errorDetail.setTitle(httpStatus.getReasonPhrase());
        errorDetail.setDetail(exception.getMessage());

        Develop develop = new Develop();
        develop.setException(exception);

        errorDetail.setDevelop(develop);
        return new ResponseEntity<>(errorDetail, null, httpStatus);
    }
}