package com.rezatron.mtgprice.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@Slf4j
@ControllerAdvice
public
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String TRACE = "trace";

    @Value( "${reflectoring.trace:false}" )
    private boolean printStackTrace;

    @ExceptionHandler( RuntimeException.class )
    @ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
    public
    ResponseEntity<ErrorResponse> handleAllUncaughtException(RuntimeException exception, WebRequest request) {
        log.error( "Unknown error occurred",
                   exception );
        return buildErrorResponse( exception,
                                   "Unknown error occurred",
                                   HttpStatus.INTERNAL_SERVER_ERROR,
                                   request );
    }

    @Override
    @ResponseStatus( HttpStatus.UNPROCESSABLE_ENTITY )
    protected
    ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request)
    {
        ErrorResponse errorResponse = new ErrorResponse( HttpStatus.UNPROCESSABLE_ENTITY.value(),
                                                         "Validation error. Check 'errors' field for details." );

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError( fieldError.getField(),
                                              fieldError.getDefaultMessage() );
        }
        return ResponseEntity.unprocessableEntity().body( errorResponse );
    }

    @ExceptionHandler( Exception.class )
    @ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
    public
    ResponseEntity<ErrorResponse> handleAllUncaughtException(Exception exception, WebRequest request) {
        log.error( "Unknown error occurred",
                   exception );
        return buildErrorResponse( exception,
                                   "Unknown error occurred",
                                   HttpStatus.INTERNAL_SERVER_ERROR,
                                   request );
    }

    private
    ResponseEntity<ErrorResponse> buildErrorResponse(Exception exception, HttpStatus httpStatus, WebRequest request) {
        return buildErrorResponse( exception,
                                   exception.getMessage(),
                                   httpStatus,
                                   request );
    }

    private
    ResponseEntity<ErrorResponse> buildErrorResponse(Exception exception, String message, HttpStatus httpStatus,
                                                     WebRequest request)
    {
        ErrorResponse errorResponse = new ErrorResponse( httpStatus.value(),
                                                         exception.getMessage() );

        if (printStackTrace && isTraceOn( request )) {
            errorResponse.setStackTrace( ExceptionUtils.getStackTrace( exception ) );
        }
        return ResponseEntity.status( httpStatus ).body( errorResponse );
    }

    private
    boolean isTraceOn(WebRequest request) {
        String[] value = request.getParameterValues( TRACE );
        return Objects.nonNull( value ) && value.length > 0 && value[0].contentEquals( "true" );
    }
}
