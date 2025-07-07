package com.prices.inditex.domain.exception;

/**
 * Custom exception to indicate that a requested resource was not found.
 * Extends the RuntimeException class to provide unchecked exception behavior.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Constructs a new NotFoundException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public NotFoundException(String message) {
        super(message);
    }
}