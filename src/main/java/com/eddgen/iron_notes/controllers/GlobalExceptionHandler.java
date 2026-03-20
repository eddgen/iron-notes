package com.eddgen.iron_notes.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eddgen.iron_notes.exceptions.ResourceNotFoundException;

public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
        "status", 404,
        "message", ex.getMessage()
    ));
}
}
