package eha.martin.qrcodeservice.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        Optional<String> contentError = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .filter(msg -> msg.contains("Contents cannot be null or blank"))
                .findFirst();

        Optional<String> sizeError = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .filter(msg -> msg.contains("Image size"))
                .findFirst();

        if (contentError.isPresent()) {
            errors.put("error", contentError.get());
        } else if (sizeError.isPresent()) {
            errors.put("error", sizeError.get());
        } else {
            ex.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .findFirst()
                    .ifPresent(msg -> errors.put("error", msg));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
