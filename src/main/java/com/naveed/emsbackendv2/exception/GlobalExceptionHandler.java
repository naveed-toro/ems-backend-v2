package com.naveed.emsbackendv2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// یہ اینوٹیشن اس کلاس کو پورے پروجیکٹ کا "ایرر گارڈ" بنا دیتی ہے
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. اگر کوئی چیز ڈیٹا بیس سے نہ ملے (جو RuntimeException ہم نے سروس میں لگایا تھا)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        // 404 Not Found کا اسٹیٹس واپس بھیجیں
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // 2. اگر یوزر غلط ڈیٹا بھیجے (جیسے نام خالی ہو یا ای میل غلط ہو) - @Valid کے ایررز
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        // 400 Bad Request کا اسٹیٹس واپس بھیجیں
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // 3. باقی کوئی بھی عام ایرر آ جائے تو سسٹم کریش ہونے کے بجائے یہ میسج دے
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGlobalException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "An unexpected error occurred: " + ex.getMessage());
        // 500 Internal Server Error کا اسٹیٹس واپس بھیجیں
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}