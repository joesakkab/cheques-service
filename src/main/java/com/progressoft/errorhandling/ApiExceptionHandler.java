package com.progressoft.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    private final Map<String, List<String>> errorMap = new HashMap<>();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map<String, List<String>> handleValidationError(MethodArgumentNotValidException ex) {
        errorMap.clear();
        ex.getBindingResult().getFieldErrors().forEach( error -> {
            String currentField = error.getField();
            if (errorMap.containsKey(currentField)) {
                errorMap.get(currentField).add(error.getDefaultMessage());
            } else {
                errorMap.put(currentField, new ArrayList<>());
                errorMap.get(currentField).add(error.getDefaultMessage());
            }
        });
        return errorMap;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Map<String, List<String>> handleEntityNotFoundError(EntityNotFoundException ex) {
        errorMap.clear();
        List<String> error = new ArrayList<>();
        error.add(ex.getMessage());
        errorMap.put("ENTITY NOT FOUND: ", error);

        return errorMap;
    }


}
