package com.yael.penguin.store.penguin_store.domain.customErrors;

import org.springframework.http.HttpStatus;




public class CustomError extends RuntimeException {

    private String error;
    private HttpStatus status;


    private CustomError(String error, HttpStatus status){
        super(error);
        this.error = error;
        this.status = status;
    }


    public static CustomError internalServerError(String error){
        System.out.println("Internal server error: " + error);
        return new CustomError("Internal server error, try again later", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static CustomError badRequest(String error){
        return new CustomError(error, HttpStatus.BAD_REQUEST);
    }

    public static CustomError unauthorized(String error){
        return new CustomError(error, HttpStatus.UNAUTHORIZED);
    }

    public static CustomError notFound(String error){
        return new CustomError(error, HttpStatus.NOT_FOUND);
    }


    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public HttpStatus getStatus() {
        return status;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
