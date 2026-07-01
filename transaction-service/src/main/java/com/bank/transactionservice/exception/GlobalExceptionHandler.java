package com.bank.transactionservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(feign.FeignException.BadRequest.class)
    public ResponseEntity<String> handleFeignBadRequest(feign.FeignException.BadRequest ex) {

        if (ex.contentUTF8().contains("Insufficient Balance")) {
            return ResponseEntity.badRequest().body("Insufficient Balance");
        }

        return ResponseEntity.badRequest().body(ex.contentUTF8());
    }
}
