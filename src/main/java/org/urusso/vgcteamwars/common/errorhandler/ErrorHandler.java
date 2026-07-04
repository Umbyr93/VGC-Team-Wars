package org.urusso.vgcteamwars.common.errorhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.urusso.vgcteamwars.common.dto.ErrorDto;
import org.urusso.vgcteamwars.common.exception.BaseException;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    private static final String GENERIC_ERROR = "GENERIC_ERROR";
    private static final String VALIDATION_ERROR = "VALIDATION_ERROR";
    private static final String EXCEPTION_OCCURRED = "Exception occured";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logError(ex);

        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("code", VALIDATION_ERROR);

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }

     @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> baseException(BaseException ex) {
         logError(ex);

        var errorDto = new ErrorDto(ex.getCode(), ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(errorDto);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> genericException(Throwable ex) {
        logError(ex);

        var errorDto = new ErrorDto(GENERIC_ERROR, ex.getMessage());
        return ResponseEntity.internalServerError().body(errorDto);
    }

    private static void logError(Throwable ex) {
        log.error(EXCEPTION_OCCURRED, ex);
    }
}
