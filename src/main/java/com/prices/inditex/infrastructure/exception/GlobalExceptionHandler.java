package com.prices.inditex.infrastructure.exception;


import com.prices.inditex.domain.model.ErrorResponseDTO;
import com.prices.inditex.domain.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Global exception handler for the application.
 * Provides centralized exception handling for controllers using @ControllerAdvice.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles NotFoundException and returns a NOT_FOUND (404) response.
     *
     * @param ex The NotFoundException instance.
     * @param request The WebRequest instance.
     * @return A ResponseEntity containing the error response.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(NotFoundException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /**
     * Handles IllegalArgumentException and returns a BAD_REQUEST (400) response.
     *
     * @param ex The IllegalArgumentException instance.
     * @param request The WebRequest instance.
     * @return A ResponseEntity containing the error response.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /**
     * Handles general exceptions and returns an INTERNAL_SERVER_ERROR (500) response.
     *
     * @param ex The Exception instance.
     * @param request The WebRequest instance.
     * @return A ResponseEntity containing the error response.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
    }

    /**
     * Handles MissingServletRequestParameterException and returns a BAD_REQUEST (400) response.
     *
     * @param ex The MissingServletRequestParameterException instance.
     * @return A ResponseEntity containing the error response.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingParams(MissingServletRequestParameterException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST,
                String.format("The required query parameter '%s' is missing.", ex.getParameterName()));
    }

    /**
     * Builds a standardized error response.
     *
     * @param status The HTTP status to be returned.
     * @param message The error message to be included in the response.
     * @return A ResponseEntity containing the error response.
     */
    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String message) {
        var body = new ErrorResponseDTO();
        body.setTimestamp(LocalDateTime.now());
        body.setStatus(status.value());
        body.setError(status.getReasonPhrase());
        body.setMessage(message);
        return new ResponseEntity<>(body, status);
    }
}