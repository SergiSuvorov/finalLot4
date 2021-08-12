package ru.stm.lot4.device.exception;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@EnableWebMvc
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {
        List<String> message = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getObjectName() + "." + fieldError.getField() + ": "
                        + fieldError.getDefaultMessage()).collect(Collectors.toList());
        log.error("Validation exception!" + String.join(", ", message));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Validate request exception!\n" + String.join("\n", message));
    }
}