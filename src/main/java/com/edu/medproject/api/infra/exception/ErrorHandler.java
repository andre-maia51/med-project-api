package com.edu.medproject.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404Handler() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400Handler(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ErrorDataValidationDTO::new).toList());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorValidationHandler(ValidationException exception) {
       return ResponseEntity.badRequest().body(exception.getMessage());
    }

    public record ErrorDataValidationDTO(String field, String message) {
        public ErrorDataValidationDTO(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
