package com.yael.penguin.store.penguin_store.client.aop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yael.penguin.store.penguin_store.domain.customErrors.CustomError;





@RestControllerAdvice
public class ExceptionHandlerAop {


    @ExceptionHandler(CustomError.class)
    public ResponseEntity<?> handleCustomError(CustomError ex){
        return getResponse(ex.getError(), ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleInternalServerError(Exception ex){
        // TODO: manejar errores internos / no esperados
        System.out.println("Internal server error = " + ex.getMessage());
        return getResponse("Internal server error, try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodNotValid(MethodArgumentNotValidException ex){
        List<String> errors = ex.getAllErrors()
            .stream()
            .map(e -> new StringBuilder(e.getCode())
                    .append(" ")
                    .append(e.getDefaultMessage())
                    .toString()
                )
            .toList();

        return getResponse(errors, HttpStatus.BAD_REQUEST);
    }



    private ResponseEntity<?> getResponse(Object err, HttpStatus status){
        Map<String, Object> res = new HashMap<>();
        res.put("error", err);
        res.put("status", status.value());

        return ResponseEntity.status(status).body(res);
    }

}
