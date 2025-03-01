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

        String[] priorityKeywords = {
                "Contents",
                "size",
                "correction",
                "types"
        };

        for (String keyword : priorityKeywords) {
            Optional<String> error = ex.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .filter(msg -> msg.contains(keyword))
                    .findFirst();

            if (error.isPresent()) {
                errors.put("error", error.get());
                break;
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
