package com.progressoft.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
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
            updateMap(currentField, error.getDefaultMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map<String, List<String>> handleConstraintError(ConstraintViolationException ex) {
        errorMap.clear();
        ex.getConstraintViolations().forEach( error -> {
            String path = error.getPropertyPath().toString();
            String currentField = path.substring(path.lastIndexOf(".") + 1);
            updateMap(currentField, error.getMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map<String, List<String>> handleConstraintError(Exception ex) {
        errorMap.clear();
        System.out.println(ex.getMessage());
        updateMap("ERROR", ex.getMessage().substring(ex.getMessage().lastIndexOf(":") + 1));
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

    private void updateMap(String field, String message) {
        if (errorMap.containsKey(field)) {
            errorMap.get(field).add(message);
        } else {
            errorMap.put(field, new ArrayList<>());
            errorMap.get(field).add(message);
        }
    }


}
