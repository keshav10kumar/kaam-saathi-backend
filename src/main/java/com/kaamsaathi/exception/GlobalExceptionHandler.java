package com.kaamsaathi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ 1. Handle validation errors (@Valid + @NotBlank)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(
            MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        // ✅ LOG (validation issues → WARN)
        log.warn("Validation failed: {}", errorMessage);

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    // ✅ 2. Handle business exceptions (RuntimeException)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {

        // ✅ LOG (business logic issues)
        log.error("Business exception: {}", ex.getMessage());

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // ✅ 3. Handle any unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {

        // ✅ LOG FULL STACK TRACE (VERY IMPORTANT)
        log.error("Unexpected error occurred", ex);

        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}