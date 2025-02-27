package eha.martin.qrcodeservice.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(ConstraintViolationException ex) {
        Map<String, String> error = new HashMap<>();

        String errorMessage = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .filter(message -> message.contains("Image size"))
                .findFirst()
                .orElseGet(() ->
                        ex.getConstraintViolations().stream()
                                .map(ConstraintViolation::getMessage)
                                .findFirst()
                                .orElse("Unknown error")
                );

        error.put("error", errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
